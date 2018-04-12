package greta.projet_ecole.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import greta.projet_ecole.pdo.PDOSqlite;

public class MLivre {
	private int id = -1;
	private String nom_auteur;
	private String prenom_auteur;
	private String titre;
	private int annee;
	private String editeur;
	private MUsager emprunteur;
	private Date date_sortie;
	private Date date_retour;

	// public MLivre(int id, String nom_auteur, String prenom_auteur, String
	// titre,
	// int annee, String editeur,
	// MUsager emprunteur, Date date_sortie, Date date_retour) {
	// this(nom_auteur, prenom_auteur, titre, annee, editeur);
	// this.id = id;
	// this.emprunteur = emprunteur;
	// this.date_sortie = date_sortie;
	// this.date_retour = date_retour;
	//
	// }

	public MLivre(String nom_auteur, String prenom_auteur, String titre, int annee, String editeur) {

		this.nom_auteur = nom_auteur;
		this.prenom_auteur = prenom_auteur;
		this.titre = titre;
		this.editeur = editeur;
		this.annee = annee;

	}

	public MLivre(int id, String nom_auteur, String prenom_auteur, String titre, int annee, String editeur) {
		this.id = id;
		this.nom_auteur = nom_auteur;
		this.prenom_auteur = prenom_auteur;
		this.titre = titre;
		this.editeur = editeur;
		this.annee = annee;

	}

	public MLivre() {
		super();
	}

	public void load(int id) {

		String query = "SELECT * FROM livres WHERE id=" + id;
		ResultSet r = PDOSqlite.executeSQL(query);

		try {
			while (r.next()) {

				if (r.getString("titre") != null) {

					this.id = r.getInt("id");
					annee = r.getInt("annee");
					editeur = r.getString("editeur");
					nom_auteur = r.getString("nom_auteur");
					prenom_auteur = r.getString("prenom_auteur");
					titre = r.getString("titre");

					// TODO : lire dates et Usager
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void save() {
		String query = "INSERT INTO livres	 (annee,editeur,nom_auteur,prenom_auteur,titre) VALUES (";
		query += annee + ",";
		query += "\"" + editeur + "\",";
		query += "\"" + nom_auteur + "\",";
		query += "\"" + prenom_auteur + "\",";
		query += "\"" + titre + "\"";
		query += ")";
		System.out.println(query);
		PDOSqlite.executeSQL(query);

	}

	public void delect() {

	}

	@Override
	public String toString() {
		return "nom_auteur=" + nom_auteur + ", prenom_auteur=" + prenom_auteur + ", titre=" + titre + ", annee=" + annee
				+ ", editeur=" + editeur + ", emprunteur=" + emprunteur + ", date_sortie=" + date_sortie
				+ ", date_retour=" + date_retour + "]";
	}

	public void assoc(MUsager usager) {

	}

	public void dissoc(MUsager usager) {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_auteur() {
		return nom_auteur;
	}

	public void setNom_auteur(String nom_auteur) {
		this.nom_auteur = nom_auteur;
	}

	public String getPrenom_auteur() {
		return prenom_auteur;
	}

	public void setPrenom_auteur(String prenom_auteur) {
		this.prenom_auteur = prenom_auteur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getAnnee() {
		System.out.println("MLivre.getAnnee()" + annee);
		return annee;
	}

	public void setAnnee(int annee) {
		System.out.println("MLivre.setAnnee()" + annee);
		this.annee = annee;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public MUsager getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(MUsager emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Date getDate_sortie() {
		return date_sortie;
	}

	public void setDate_sortie(Date date_sortie) {
		this.date_sortie = date_sortie;
	}

	public Date getDate_retour() {
		return date_retour;
	}

	public void setDate_retour(Date date_retour) {
		this.date_retour = date_retour;
	}

}
