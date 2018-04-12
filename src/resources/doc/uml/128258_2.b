class MUsager
!!!136450.java!!!	MUsager(in id : int, in nom : String, in prenom : String, inout livres : ArrayList<MLivre>)
		this(id, nom, prenom);
		this.livres = livres;
!!!136578.java!!!	MUsager(in id : int, in nom : String, in prenom : String)
		this(nom, prenom);
		this.id = id;
!!!136706.java!!!	MUsager(in nom : String, in prenom : String)
		this.nom = nom;
		this.prenom = prenom;
!!!136834.java!!!	MUsager()
		super();
!!!136962.java!!!	load(in id : int) : void

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

!!!137090.java!!!	save() : void
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
!!!137218.java!!!	assoc(inout livre : MLivre) : void
		if (!livres.contains(livre)) {
			livres.add(livre);
		}

!!!137346.java!!!	dissoc(inout livre : MLivre) : void
		if (livres.contains(livre)) {
			livres.remove(livre);
		}
!!!137474.java!!!	getId() : int
		return id;
!!!137602.java!!!	setId(in id : int) : void
		this.id = id;
!!!137730.java!!!	getNom() : String
		return nom;
!!!137858.java!!!	setNom(in nom : String) : void
		this.nom = nom;
!!!137986.java!!!	getPrenom() : String
		return prenom;
!!!138114.java!!!	setPrenom(in prenom : String) : void
		this.prenom = prenom;
!!!138242.java!!!	getLivres() : MLivre
		return livres;
!!!138370.java!!!	setLivres(inout livres : ArrayList<MLivre>) : void
		this.livres = livres;
