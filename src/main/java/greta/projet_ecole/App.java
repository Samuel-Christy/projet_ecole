package greta.projet_ecole;

import java.util.Date;

import greta.projet_ecole.controllers.CBibliotheque;
import greta.projet_ecole.models.MLivre;
import greta.projet_ecole.models.MUsager;
import greta.projet_ecole.pdo.PDOSqlite;
import greta.projet_ecole.views.Vemprunts2;

/**
 * @author Sam
 * @description runs the app and runs some tests on the models...
 */
public class App {
	public static void main(String[] args) {

		createTables();
		// It works better with datas, you should uncoment this one the first
		// time you
		// run me (and comment it back after)
		// seedDatabase();

		// create the Collection
		CBibliotheque bibli = new CBibliotheque();

		////////////////////////////////////////////////
		// code below is for testing
		///////////////////////////////////////////////

		// try an assoc :
		// bibli.assoc(bibli.getLivres().get(1), bibli.getUsagers().get(1), new
		// Date(), new Date());
		// System.out.println(bibli.getLivres().get(1).getDate_retour());
		//
		// // append a new book
		// MLivre l = new MLivre("moi", "encore moi", "le moi et le sur-moi",
		// 2018, "Ego :)");
		// bibli.getLivres().add(l);
		//
		// // save it
		// bibli.saveLivres();
		// System.out.println(bibli.getLivres().get(1).getDate_retour());
		//
		// // dissoc
		// l = bibli.findLivre(2);
		// System.out.println(l.getId());
		// System.out.println(l.getEmprunteur());
		// // bibli.dissoc(l);
		// System.out.println(l.getEmprunteur());
		// bibli.saveLivres();

		bibli.assoc(bibli.getLivres().get(1), bibli.getUsagers().get(1), new Date(), new Date());
		bibli.saveLivres();
		///////////////////////////////////////////////
		// end of testing code
		//////////////////////////////////////////////

		// launch the app's main form, we'll run until closed

		// VLivres2.main(args, bibli);
		Vemprunts2.main(args, bibli);
	}

	/**
	 * adds some dummy data to the DB, may not be used on every run, so it's
	 * annotated.
	 */
	@SuppressWarnings("unused")
	private static void seedDatabase() {
		System.out.println("------------------------------------------------------");
		System.out.println("Drop and recreate tables");
		System.out.println("------------------------------------------------------");

		PDOSqlite.executeSQL("DROP TABLE IF EXISTS livres");
		PDOSqlite.executeSQL("DROP TABLE IF EXISTS usagers");

		createTables();

		System.out.println("------------------------------------------------------");
		System.out.println("Seeding tables");
		System.out.println("------------------------------------------------------");

		for (int i = 0; i < 10; i++) {
			MLivre l = new MLivre("NomAuteur" + i, "PrenomAuteur" + i, "TitreLivre" + i, 1990 + i,
					i % 3 == 0 ? "editeur1" : "editeur2");
			l.save();
		}

		for (int i = 0; i < 3; i++) {
			MUsager u = new MUsager("Nom" + i, "Prenom" + i);
			u.save();
		}

		System.out.println("------------------------------------------------------");
		System.out.println("Done");
		System.out.println("------------------------------------------------------");
	}

	/**
	 * Creates the DB file and the schema (only if the tables do not exist)
	 */
	private static void createTables() {

		System.out.println("------------------------------------------------------");
		System.out.println("Create brand new tables (if not present)");
		System.out.println("------------------------------------------------------");

		PDOSqlite.executeSQL(
				"CREATE TABLE IF NOT EXISTS usagers(	id      INTEGER PRIMARY KEY autoincrement NOT NULL ,	nom     TEXT NOT NULL,	prenom  TEXT NOT NULL);");
		PDOSqlite.executeSQL(
				"CREATE TABLE IF NOT EXISTS livres(	id             INTEGER PRIMARY KEY autoincrement NOT NULL ,	annee          INTEGER NOT NULL ,	prenom_auteur  TEXT NOT NULL ,	nom_auteur     TEXT NOT NULL ,	titre          TEXT NOT NULL ,	editeur        TEXT NOT NULL ,	date_sortie    NUMERIC  ,	date_retour    NUMERIC  ,	id_usagers     INTEGER) ;");
	}

}
