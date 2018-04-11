package greta.projet_ecole.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import greta.projet_ecole.pdo.PDOSqlite;

public class MColBibliotheque {

	ArrayList<MLivre> livres = new ArrayList<MLivre>();
	ArrayList<MUsager> usagers = new ArrayList<MUsager>();

	public MColBibliotheque() {
		listLivres();
		listUsagers();
		assocPrets();
	}

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

	private void listLivres() {
		String query = "SELECT * FROM livres";
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
					}
				} // while
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // try

		} // for

	}

	public void assoc(MLivre livre, MUsager usager, Date date_sortie, Date date_retour) {
		livre.setEmprunteur(usager);
		livre.setDate_sortie(date_sortie);
		livre.setDate_retour(date_retour);
	}

	public void dissoc(MLivre livre) {
		if (livre.getEmprunteur() != null) {
			livre.getEmprunteur().dissoc(livre);
		}
		livre.setEmprunteur(null);
		livre.setDate_retour(null);
		livre.setDate_sortie(null);
	}

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
					query += l.getEmprunteur() != null ? l.getEmprunteur().getId() : -1 + ",";
					query += l.getDate_sortie() != null ? "\"" + l.getDate_sortie() + "\"" : "NULL" + ",";
					query += l.getDate_retour() != null ? "\"" + l.getDate_retour() + "\"" : "NULL";
					query += ")";
					System.out.println(query);
				} else {
					query = "UPDATE livres SET ";
					query += "annee = " + l.getAnnee() + ", ";
					query += "editeur = " + quoteString(l.getEditeur()) + ", ";
					query += "nom_auteur = " + quoteString(l.getNom_auteur()) + ", ";
					query += "prenom_auteur = " + quoteString(l.getPrenom_auteur()) + ", ";
					query += "titre = " + quoteString(l.getTitre()) + ", ";
					query += "id_usagers = "
							+ (l.getEmprunteur() != null ? l.getEmprunteur().getId() + ", " : -1 + ", ");

					if (l.getDate_sortie() != null && l.getDate_retour() != null) {
						query += "date_sortie = " + quoteString(l.getDate_sortie().toString()) + ", ";
						query += "date_retour = " + quoteString(l.getDate_retour().toString()) + "";
					} else {
						query += "date_sortie = NULL, ";
						query += "date_retour = NULL";
					}
					query += " WHERE id=" + l.getId();
					// System.out.println(query);
				}
				PDOSqlite.executeSQL(query);
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(query);
			}

		}
		// refresh the list

		reinitLists();
	}

	private void reinitLists() {
		livres.clear();
		usagers.clear();

		initLists();
	}

	private void initLists() {
		listLivres();
		listUsagers();
		assocPrets();
	}

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

	private MUsager findUsager(int id) {
		for (MUsager u : usagers) {
			if (u.getId() == id)
				return u;
		}
		return null;
	}

	private String quoteString(String s) {
		return "\"" + s + "\"";
	}

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
