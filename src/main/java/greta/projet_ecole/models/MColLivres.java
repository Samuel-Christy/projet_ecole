package greta.projet_ecole.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import greta.projet_ecole.pdo.PDOSqlite;

public class MColLivres {

	ArrayList<MLivre> livres = new ArrayList<MLivre>();

	public void list() {

		String query = "SELECT id FROM livres";
		ResultSet r = PDOSqlite.executeSQL(query);

		try {
			while (r.next()) {
				livres.add(new MLivre());
				livres.get(livres.size() - 1).load(r.getInt("id"));
				// r.getString("prenom")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void refreshList() {
		livres = new ArrayList<MLivre>();
		list();
	}

	public ArrayList<MLivre> getLivres() {
		return livres;
	}

	public void setLivres(ArrayList<MLivre> livres) {
		this.livres = livres;
	}

}
