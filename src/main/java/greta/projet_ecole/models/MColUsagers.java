package greta.projet_ecole.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import greta.projet_ecole.pdo.PDOSqlite;

public class MColUsagers {

	ArrayList<MUsager> usagers = new ArrayList<MUsager>();

	public void list() {

		String query = "SELECT * FROM usagers";
		ResultSet r = PDOSqlite.executeSQL(query);

		try {
			while (r.next()) {
				usagers.add(new MUsager(r.getString("nom"), r.getString("prenom")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void refreshList() {
		usagers = new ArrayList<MUsager>();
		list();
	}

	public ArrayList<MUsager> getUsagers() {
		return usagers;
	}

	public void setUsagers(ArrayList<MUsager> usagers) {
		this.usagers = usagers;
	}

}
