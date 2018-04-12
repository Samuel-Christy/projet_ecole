package greta.projet_ecole.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import greta.projet_ecole.models.MLivre;
import greta.projet_ecole.models.MUsager;
import greta.projet_ecole.pdo.PDOSqlite;

/**
 * @author Sam
 * @description Handles users and books, books and users... Quite ugly for now,
 *              would be much better with an ORM ! TODO : Handle the dates
 *              better!
 *
 */
public class CBibliotheque {

	private ArrayList<MLivre> livres = new ArrayList<MLivre>();
	private ArrayList<MUsager> usagers = new ArrayList<MUsager>();
	private ArrayList<MLivre> livres_en_cours = new ArrayList<MLivre>();
	private ArrayList<MLivre> livres_dispos = new ArrayList<MLivre>();

	/**
	 * constructor, builds the lists
	 */
	public CBibliotheque() {
		listLivres();
		listUsagers();
		assocPrets();
	}

	/**
	 * builds User's list
	 */
	private void listUsagers() {
		String query = "SELECT * FROM usagers";
		ResultSet r = PDOSqlite.executeSQL(query);

		try {
			while (r.next()) {
				MUsager u = new MUsager(r.getInt("id"), r.getString("nom"), r.getString("prenom"));
				usagers.add(u);
				// TODO : lire dates et Usager

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Builds books list, chronologically ordered
	 */
	private void listLivres() {
		String query = "SELECT * FROM livres ORDER BY date_sortie DESC";
		ResultSet r = PDOSqlite.executeSQL(query);

		try {
			while (r.next()) {
				MLivre l = new MLivre(r.getInt("id"), r.getString("nom_auteur"), r.getString("prenom_auteur"),
						r.getString("titre"), r.getInt("annee"), r.getString("editeur"));

				livres.add(l);
				// TODO : lire dates et Usager

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Assoc books to users after a fresh loading
	 */
	private void assocPrets() {

		for (MLivre l : livres) {
			String query = "SELECT id_usagers FROM livres where id=" + l.getId();
			ResultSet r = PDOSqlite.executeSQL(query);

			try {
				while (r.next()) {
					MUsager u = findUsager(r.getInt("id_usagers"));
					l.setEmprunteur(u);
					if (u != null) {
						u.assoc(l);
						livres_en_cours.add(l);
					}
				} // while
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // try

		} // for

	}

	/**
	 * @param livre
	 * @param usager
	 * @param date_sortie
	 * @param date_retour
	 *
	 *            associates a user to a book, beware, this is not saved until
	 *            the lists are
	 */
	public void assoc(MLivre livre, MUsager usager, Date date_sortie, Date date_retour) {
		livre.setEmprunteur(usager);
		livre.setDate_sortie(date_sortie);
		livre.setDate_retour(date_retour);
		if (!livres_en_cours.contains(livre)) {
			livres_en_cours.add(livre);
		}
	}

	/**
	 * @param livre
	 *            the exact opposite of assoc() :) [still does not save]
	 */
	public void dissoc(MLivre livre) {
		if (livre.getEmprunteur() != null) {
			livre.getEmprunteur().dissoc(livre);
		}
		livre.setEmprunteur(null);
		livre.setDate_retour(null);
		livre.setDate_sortie(null);
		if (livres_en_cours.contains(livre)) {
			livres_en_cours.remove(livre);
		}
	}

	/**
	 * Saves the list to DB, refreshes the local lists after doing so,
	 * definitively the one to be called sometimes
	 */
	public void saveLivres() {
		for (MLivre l : livres) {
			String query = "";

			// create or update ?
			try {
				if (l.getId() == -1) {
					query = "INSERT INTO livres (annee,editeur,nom_auteur,prenom_auteur,titre,id_usagers,date_sortie,date_retour) VALUES (";
					query += l.getAnnee() + ",";
					query += "\"" + l.getEditeur() + "\",";
					query += "\"" + l.getNom_auteur() + "\",";
					query += "\"" + l.getPrenom_auteur() + "\",";
					query += "\"" + l.getTitre() + "\",";
					// beware of the big bad NullPointer Exception !
					query += l.getEmprunteur() != null ? l.getEmprunteur().getId() : -1 + ",";
					query += l.getDate_sortie() != null ? "\"" + formatDateToStr(l.getDate_sortie()) + "\""
							: "NULL" + ",";
					query += l.getDate_retour() != null ? "\"" + formatDateToStr(l.getDate_retour()) + "\"" : "NULL";
					query += ")";
					System.out.println(query);
				} else {
					query = "UPDATE livres SET ";
					query += "annee = " + l.getAnnee() + ", ";
					query += "editeur = " + quoteString(l.getEditeur()) + ", ";
					query += "nom_auteur = " + quoteString(l.getNom_auteur()) + ", ";
					query += "prenom_auteur = " + quoteString(l.getPrenom_auteur()) + ", ";
					query += "titre = " + quoteString(l.getTitre()) + ", ";
					// beware of the big bad NullPointer Exception !
					query += "id_usagers = "
							+ (l.getEmprunteur() != null ? l.getEmprunteur().getId() + ", " : -1 + ", ");

					if (l.getDate_sortie() != null && l.getDate_retour() != null) {
						query += "date_sortie = " + quoteString(formatDateToStr(l.getDate_sortie())) + ", ";
						query += "date_retour = " + quoteString(formatDateToStr(l.getDate_retour())) + "";
					} else {
						query += "date_sortie = NULL, ";
						query += "date_retour = NULL";
					}
					query += " WHERE id=" + l.getId();
					// System.out.println(query);
				}
				PDOSqlite.executeSQL(query);
			} catch (Exception e) {
				// Something, somewhere, went wrong...
				System.err.println(query);
			}

		}
		// refresh the list

		reinitLists();
	}

	/**
	 * @param livre
	 * @param date_retour
	 *            sets a book's return date
	 */
	public void reportLivre(MLivre livre, Date date_retour) {
		if (livre.getEmprunteur() != null && livre.getDate_sortie() != null && livre.getDate_retour() != null
				&& livre.getDate_retour().before(date_retour)) {
			livre.setDate_retour(date_retour);
		}
	}

	/**
	 * clears the lists and reloads them from database, beware, any unsaved
	 * change will be lost.
	 */
	public void reinitLists() {
		livres.clear();
		usagers.clear();

		initLists();
	}

	/**
	 * loads from DB
	 */
	private void initLists() {
		listLivres();
		listUsagers();
		assocPrets();
	}

	/**
	 * @param livre
	 * @return it does what it says... except if a user is bound to the book
	 */
	public boolean delLivre(MLivre livre) {
		if (livre.getEmprunteur() == null) {
			String query = "DELETE FROM livres where id=" + livre.getId();
			try {
				PDOSqlite.executeSQL(query);
				livres.remove(livre);
				reinitLists();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}
		return false;
	}

	/**
	 * @param livre
	 *            this one is not kidding
	 */
	public void forceDelLivre(MLivre livre) {
		dissoc(livre);
		delLivre(livre);
	}

	/**
	 * @param id
	 * @return returns an user by id... or null if none found.
	 */
	public MUsager findUsager(int id) {
		for (MUsager u : usagers) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	public MLivre findLivre(int id) {
		for (MLivre u : livres) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	/**
	 * @param s
	 * @return DRY : just bored with these double quotes...
	 */
	private String quoteString(String s) {
		return "\"" + s + "\"";
	}

	private String formatDateToStr(Date date) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(date);
		String result = s;
		try {
			date = df.parse(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;

	}

	//////////////////////////////////////////////////////////////////////////
	// accessors, what else ?
	//////////////////////////////////////////////////////////////////////////

	public ArrayList<MLivre> getLivres() {
		return livres;
	}

	public void setLivres(ArrayList<MLivre> livres) {
		this.livres = livres;
	}

	public ArrayList<MUsager> getUsagers() {
		return usagers;
	}

	public void setUsagers(ArrayList<MUsager> usagers) {
		this.usagers = usagers;
	}

}
