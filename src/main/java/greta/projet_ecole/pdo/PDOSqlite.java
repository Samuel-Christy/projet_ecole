package greta.projet_ecole.pdo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PDOSqlite {

	private static String URL = "jdbc:sqlite:database.sqlite";

	private static Connection con;
	private static Statement stmt;
	private static ResultSet r;

	private static Connection connect() {
		if (con == null) {
			try {

				con = DriverManager.getConnection(URL);
				// System.out.println(con);
				return con;
			} catch (Exception e) {
				// NB : comment this when in production, else it'll throw
				// exceptions on request not returning results (insert, etc...)
				e.printStackTrace();
			}
		}
		return con;

	}

	private static Statement prepare() {
		// System.out.println(stmt + " " + con);
		if (stmt == null && con != null) {
			try {
				stmt = con.createStatement();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return stmt;

	}

	private static ResultSet execute(String query) {

		if (stmt != null) {
			try {
				// System.out.println(query);
				if (query.startsWith("SELECT") || query.startsWith("select")) {
					// System.out.println("PDOSqlite.execute()");

					r = stmt.executeQuery(query);
				} else {
					stmt.executeUpdate(query);
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return r;
	}

	public static ResultSet executeSQL(String query) {
		connect();
		prepare();
		return execute(query);

	}

	@SuppressWarnings("unused")
	private static void disconnect() {
		if (stmt != null) {
			try {
				stmt.close(); // Le stmt.close ferme automatiquement le
								// rset.
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
