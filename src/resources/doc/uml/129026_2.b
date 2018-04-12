class PDOSqlite
!!!138498.java!!!	connect() : Connection
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

!!!138626.java!!!	prepare() : Statement
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

!!!138754.java!!!	execute(in query : String) : ResultSet

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
!!!138882.java!!!	executeSQL(in query : String) : ResultSet
		connect();
		prepare();
		return execute(query);

!!!139010.java!!!	disconnect() : void
		if (stmt != null) {
			try {
				stmt.close(); // Le stmt.close ferme automatiquement le
								// rset.
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		}
