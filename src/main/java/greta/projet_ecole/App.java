package greta.projet_ecole;

import java.io.File;
import java.io.IOException;

import greta.projet_ecole.models.MColLivres;
import greta.projet_ecole.models.MColUsagers;
import greta.projet_ecole.models.MLivre;
import greta.projet_ecole.models.MUsager;
import greta.projet_ecole.pdo.PDOSqlite;
import greta.projet_ecole.views.VLivres;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		touchDatabase();
		createTables();

		MLivre l = new MLivre();
		l.load(1);

		System.out.println(l);

		MColUsagers cusagers = new MColUsagers();
		cusagers.list();
		for (MUsager u : cusagers.getUsagers()) {
			System.out.println(u.getNom());
		}

		MColLivres clivres = new MColLivres();
		clivres.list();

		for (MLivre u : clivres.getLivres()) {
			System.out.println(u.getTitre());
		}

		// launch the window :
		VLivres.main(args);
	}

	private static void createTables() {
		PDOSqlite.executeSQL(
				"CREATE TABLE IF NOT EXISTS usagers(	id      INTEGER PRIMARY KEY autoincrement NOT NULL ,	nom     TEXT NOT NULL,	prenom  TEXT NOT NULL);");
		PDOSqlite.executeSQL(
				"CREATE TABLE IF NOT EXISTS livres(	id             INTEGER PRIMARY KEY autoincrement NOT NULL ,	annee          INTEGER NOT NULL ,	prenom_auteur  TEXT NOT NULL ,	nom_auteur     TEXT NOT NULL ,	titre          TEXT NOT NULL ,	editeur        TEXT NOT NULL ,	date_sortie    NUMERIC  ,	date_retour    NUMERIC  ,	id_usagers     INTEGER) ;");
	}

	private static void touchDatabase() {
		// create the DB :
		File f = new File("database.sqlite");

		try {
			f.getParentFile().mkdirs();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
