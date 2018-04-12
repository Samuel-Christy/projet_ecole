class MColUsagers
!!!132226.java!!!	list() : void

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

!!!132354.java!!!	getUsager(inout usager : MUsager) : int
		return usagers.lastIndexOf(usager);
!!!132482.java!!!	refreshList() : void
		usagers = new ArrayList<MUsager>();
		list();
!!!132610.java!!!	getUsagers() : MUsager
		return usagers;
!!!132738.java!!!	setUsagers(inout usagers : ArrayList<MUsager>) : void
		this.usagers = usagers;
