package greta.projet_ecole;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import greta.projet_ecole.models.MColBibliotheque;
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

		createTables();
		// seedDatabase();

		// create the Collection
		MColBibliotheque bibli = new MColBibliotheque();

		// try an assoc :
		bibli.assoc(bibli.getLivres().get(1), bibli.getUsagers().get(1), new Date(), new Date());
		System.out.println(bibli.getLivres().get(1).getEmprunteur().getNom());

		// append a new book
		MLivre l = new MLivre("moi", "encore moi", "le moi et le sur-moi", 2018, "Moi :)");
		bibli.getLivres().add(l);

		// save it
		bibli.saveLivres();

		// dissoc
		bibli.dissoc(bibli.getLivres().get(1));
		bibli.saveLivres();
		VLivres.main(args);
	}

	@SuppressWarnings("unused")
	@Deprecated
	private static void seedDatabase() {
		for (int i = 0; i < 100; i++) {
			MLivre l = new MLivre("NomAuteur" + i, "PrenomAuteur" + i, "TitreLivre" + i, 1900 + i,
					i % 3 == 0 ? "editeur1" : "editeur2");
			l.save();
		}

		for (int i = 0; i < 10; i++) {
			MUsager u = new MUsager("Nom" + i, "Prenom" + i);
			u.save();
		}
	}

	private static void createTables() {
		PDOSqlite.executeSQL(
				"CREATE TABLE IF NOT EXISTS usagers(	id      INTEGER PRIMARY KEY autoincrement NOT NULL ,	nom     TEXT NOT NULL,	prenom  TEXT NOT NULL);");
		PDOSqlite.executeSQL(
				"CREATE TABLE IF NOT EXISTS livres(	id             INTEGER PRIMARY KEY autoincrement NOT NULL ,	annee          INTEGER NOT NULL ,	prenom_auteur  TEXT NOT NULL ,	nom_auteur     TEXT NOT NULL ,	titre          TEXT NOT NULL ,	editeur        TEXT NOT NULL ,	date_sortie    NUMERIC  ,	date_retour    NUMERIC  ,	id_usagers     INTEGER) ;");
	}

	@SuppressWarnings("unused")
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
