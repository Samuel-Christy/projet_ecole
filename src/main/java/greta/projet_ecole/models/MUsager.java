package greta.projet_ecole.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import greta.projet_ecole.pdo.PDOSqlite;

public class MUsager {

	private int id = -1;
	private String nom;
	private String prenom;
	private ArrayList<MLivre> livres = new ArrayList<MLivre>();

	public MUsager(int id, String nom, String prenom, ArrayList<MLivre> livres) {
		this(id, nom, prenom);
		this.livres = livres;
	}

	public MUsager(int id, String nom, String prenom) {
		this(nom, prenom);
		this.id = id;
	}

	public MUsager(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	public MUsager() {
		super();
	}

	public void load(int id) {

		String query = "SELECT * FROM usagers WHERE id=" + id;
		ResultSet r = PDOSqlite.executeSQL(query);

		try {
			while (r.next()) {
				if (nom == null) {
					this.id = r.getInt("id");

					nom = r.getString("nom");
					prenom = r.getString("prenom");
				}
				// TODO : lire dates et Usager

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void save() {
		if (nom != null && prenom != null) {
			String query = "INSERT INTO usagers (nom,prenom) VALUES (";
			query += "\"" + nom + "\",";
			query += "\"" + prenom + "\"";
			query += ")";
			// System.out.println(query);
			PDOSqlite.executeSQL(query);
		} else {
			System.err.println(this + " : valeurs incorrectes");
		}
	}

	public void assoc(MLivre livre) {
		if (!livres.contains(livre)) {
			livres.add(livre);
		}

	}

	public void dissoc(MLivre livre) {
		if (livres.contains(livre)) {
			livres.remove(livre);
		}
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
