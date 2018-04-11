package greta.projet_ecole.models;

import java.util.Date;

public class MLivre {
	private int id = -1;
	private String nom_auteur;
	private String prenom_auteur;
	private String titre;
	private int année;
	private String editeur;
	private MUsager emprunteur;
	private Date date_sortie;
	private Date date_retour;

	public MLivre(int id, String nom_auteur, String prenom_auteur, String titre, int année, String editeur,
			MUsager emprunteur, Date date_sortie, Date date_retour) {
		super();
		this.id = id;
		this.nom_auteur = nom_auteur;
		this.prenom_auteur = prenom_auteur;
		this.titre = titre;
		this.année = année;
		this.editeur = editeur;
		this.emprunteur = emprunteur;
		this.date_sortie = date_sortie;
		this.date_retour = date_retour;

	}

	public MLivre() {
		super();
	}

	public void load() {

	}

	public void save() {

	}

	public void delect() {

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

	public int getAnnée() {
		return année;
	}

	public void setAnnée(int année) {
		this.année = année;
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
