class MColBibliotheque
!!!129538.java!!!	MColBibliotheque()
		listLivres();
		listUsagers();
		assocPrets();
!!!129666.java!!!	listUsagers() : void
		String query = "SELECT * FROM usagers";
		ResultSet r = PDOSqlite.executeSQL(query);

		try {
			while (r.next()) {
				MUsager u = new MUsager(r.getInt("id"), r.getString("nom"), r.getString("prenom"));
				usagers.add(u);
				// TODO : lire dates et Usager

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
!!!129794.java!!!	listLivres() : void
		String query = "SELECT * FROM livres ORDER BY date_sortie DESC";
		ResultSet r = PDOSqlite.executeSQL(query);

		try {
			while (r.next()) {
				MLivre l = new MLivre(r.getInt("id"), r.getString("nom_auteur"), r.getString("prenom_auteur"),
						r.getString("titre"), r.getInt("annee"), r.getString("editeur"));
				livres.add(l);
				// TODO : lire dates et Usager

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
!!!129922.java!!!	assocPrets() : void

		for (MLivre l : livres) {
			String query = "SELECT id_usagers FROM livres where id=" + l.getId();
			ResultSet r = PDOSqlite.executeSQL(query);

			try {
				while (r.next()) {
					MUsager u = findUsager(r.getInt("id_usagers"));
					l.setEmprunteur(u);
					if (u != null) {
						u.assoc(l);
					}
				} // while
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // try

		} // for

!!!130050.java!!!	assoc(inout livre : MLivre, inout usager : MUsager, inout date_sortie : Date, inout date_retour : Date) : void
		livre.setEmprunteur(usager);
		livre.setDate_sortie(date_sortie);
		livre.setDate_retour(date_retour);
!!!130178.java!!!	dissoc(inout livre : MLivre) : void
		if (livre.getEmprunteur() != null) {
			livre.getEmprunteur().dissoc(livre);
		}
		livre.setEmprunteur(null);
		livre.setDate_retour(null);
		livre.setDate_sortie(null);
!!!130306.java!!!	saveLivres() : void
		for (MLivre l : livres) {
			String query = "";

			// create or update ?
			try {
				if (l.getId() == -1) {
					query = "INSERT INTO livres (annee,editeur,nom_auteur,prenom_auteur,titre,id_usagers,date_sortie,date_retour) VALUES (";
					query += l.getAnnee() + ",";
					query += "\"" + l.getEditeur() + "\",";
					query += "\"" + l.getNom_auteur() + "\",";
					query += "\"" + l.getPrenom_auteur() + "\",";
					query += "\"" + l.getTitre() + "\",";
					// beware of the big bad NullPointer Exception !
					query += l.getEmprunteur() != null ? l.getEmprunteur().getId() : -1 + ",";
					query += l.getDate_sortie() != null ? "\"" + l.getDate_sortie() + "\"" : "NULL" + ",";
					query += l.getDate_retour() != null ? "\"" + l.getDate_retour() + "\"" : "NULL";
					query += ")";
					System.out.println(query);
				} else {
					query = "UPDATE livres SET ";
					query += "annee = " + l.getAnnee() + ", ";
					query += "editeur = " + quoteString(l.getEditeur()) + ", ";
					query += "nom_auteur = " + quoteString(l.getNom_auteur()) + ", ";
					query += "prenom_auteur = " + quoteString(l.getPrenom_auteur()) + ", ";
					query += "titre = " + quoteString(l.getTitre()) + ", ";
					// beware of the big bad NullPointer Exception !
					query += "id_usagers = "
							+ (l.getEmprunteur() != null ? l.getEmprunteur().getId() + ", " : -1 + ", ");

					if (l.getDate_sortie() != null && l.getDate_retour() != null) {
						query += "date_sortie = " + quoteString(l.getDate_sortie().toString()) + ", ";
						query += "date_retour = " + quoteString(l.getDate_retour().toString()) + "";
					} else {
						query += "date_sortie = NULL, ";
						query += "date_retour = NULL";
					}
					query += " WHERE id=" + l.getId();
					// System.out.println(query);
				}
				PDOSqlite.executeSQL(query);
			} catch (Exception e) {
				// Something, somewhere, went wrong...
				System.err.println(query);
			}

		}
		// refresh the list

		reinitLists();
!!!130434.java!!!	reinitLists() : void
		livres.clear();
		usagers.clear();

		initLists();
!!!130562.java!!!	initLists() : void
		listLivres();
		listUsagers();
		assocPrets();
!!!130690.java!!!	delLivre(inout livre : MLivre) : boolean
		if (livre.getEmprunteur() == null) {
			String query = "DELETE FROM livres where id=" + livre.getId();
			try {
				PDOSqlite.executeSQL(query);
				livres.remove(livre);
				reinitLists();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}
		return false;
!!!130818.java!!!	forceDelLivre(inout livre : MLivre) : void
		dissoc(livre);
		delLivre(livre);
!!!130946.java!!!	findUsager(in id : int) : MUsager
		for (MUsager u : usagers) {
			if (u.getId() == id)
				return u;
		}
		return null;
!!!131074.java!!!	quoteString(in s : String) : String
		return "\"" + s + "\"";
!!!131202.java!!!	getLivres() : MLivre
		return livres;
!!!131330.java!!!	setLivres(inout livres : ArrayList<MLivre>) : void
		this.livres = livres;
!!!131458.java!!!	getUsagers() : MUsager
		return usagers;
!!!131586.java!!!	setUsagers(inout usagers : ArrayList<MUsager>) : void
		this.usagers = usagers;
