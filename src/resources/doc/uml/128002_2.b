class App
!!!128002.java!!!	main(inout args : String [[]]) : void

		createTables();
		// It works better with datas, you should uncoment this one the first time you
		// run me (and comment it back after)
		// seedDatabase();

		// create the Collection
		MColBibliotheque bibli = new MColBibliotheque();

		////////////////////////////////////////////////
		// code below is for testing
		///////////////////////////////////////////////

		// try an assoc :
		bibli.assoc(bibli.getLivres().get(1), bibli.getUsagers().get(1), new Date(), new Date());
		System.out.println(bibli.getLivres().get(1).getEmprunteur().getNom());

		// append a new book
		MLivre l = new MLivre("moi", "encore moi", "le moi et le sur-moi", 2018, "Ego :)");
		bibli.getLivres().add(l);

		// save it
		bibli.saveLivres();

		// dissoc
		// bibli.dissoc(bibli.getLivres().get(1));
		// bibli.saveLivres();

		///////////////////////////////////////////////
		// end of testing code
		//////////////////////////////////////////////

		// launch the app's main form, we'll run until closed
		VLivres.main(args);
!!!128130.java!!!	seedDatabase() : void
		for (int i = 0; i < 100; i++) {
			MLivre l = new MLivre("NomAuteur" + i, "PrenomAuteur" + i, "TitreLivre" + i, 1900 + i,
					i % 3 == 0 ? "editeur1" : "editeur2");
			l.save();
		}

		for (int i = 0; i < 10; i++) {
			MUsager u = new MUsager("Nom" + i, "Prenom" + i);
			u.save();
		}
!!!128258.java!!!	createTables() : void
		PDOSqlite.executeSQL(
				"CREATE TABLE IF NOT EXISTS usagers(	id      INTEGER PRIMARY KEY autoincrement NOT NULL ,	nom     TEXT NOT NULL,	prenom  TEXT NOT NULL);");
		PDOSqlite.executeSQL(
				"CREATE TABLE IF NOT EXISTS livres(	id             INTEGER PRIMARY KEY autoincrement NOT NULL ,	annee          INTEGER NOT NULL ,	prenom_auteur  TEXT NOT NULL ,	nom_auteur     TEXT NOT NULL ,	titre          TEXT NOT NULL ,	editeur        TEXT NOT NULL ,	date_sortie    NUMERIC  ,	date_retour    NUMERIC  ,	id_usagers     INTEGER) ;");
