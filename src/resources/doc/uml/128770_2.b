class MColLivres
!!!131714.java!!!	list() : void

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

!!!131842.java!!!	refreshList() : void
		livres = new ArrayList<MLivre>();
		list();
!!!131970.java!!!	getLivres() : MLivre
		return livres;
!!!132098.java!!!	setLivres(inout livres : ArrayList<MLivre>) : void
		this.livres = livres;
