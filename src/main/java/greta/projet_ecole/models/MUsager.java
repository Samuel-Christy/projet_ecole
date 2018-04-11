package greta.projet_ecole.models;

import java.util.ArrayList;

public class MUsager {

	private int id;
	private String nom;
	private String prenom;
	private ArrayList<MLivre> livres = new ArrayList<MLivre>();

	public MUsager(int id, String nom, String prenom, ArrayList<MLivre> livres) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.livres = livres;
	}

	public MUsager() {
		super();
	}

	public void load() {

	}

	public void save() {

	}

	public void assoc(MLivre livre) {

	}

	public void dissoc(MLivre livre) {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public ArrayList<MLivre> getLivres() {
		return livres;
	}

	public void setLivres(ArrayList<MLivre> livres) {
		this.livres = livres;
	}

}
