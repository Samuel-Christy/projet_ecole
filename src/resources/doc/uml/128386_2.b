class MLivre
!!!132866.java!!!	MLivre(in id : int, in nom_auteur : String, in prenom_auteur : String, in titre : String, in annee : int, in editeur : String, inout emprunteur : MUsager, inout date_sortie : Date, inout date_retour : Date)
		this(nom_auteur, prenom_auteur, titre, annee, editeur);
		this.id = id;
		this.emprunteur = emprunteur;
		this.date_sortie = date_sortie;
		this.date_retour = date_retour;

!!!132994.java!!!	MLivre(in nom_auteur : String, in prenom_auteur : String, in titre : String, in annee : int, in editeur : String)

		this.nom_auteur = nom_auteur;
		this.prenom_auteur = prenom_auteur;
		this.titre = titre;
		this.editeur = editeur;

!!!133122.java!!!	MLivre(in id : int, in nom_auteur : String, in prenom_auteur : String, in titre : String, in annee : int, in editeur : String)
		this.id = id;
		this.nom_auteur = nom_auteur;
		this.prenom_auteur = prenom_auteur;
		this.titre = titre;
		this.editeur = editeur;

!!!133250.java!!!	MLivre()
		super();
!!!133378.java!!!	load(in id : int) : void

		String query = "SELECT * FROM livres WHERE id=" + id;
		ResultSet r = PDOSqlite.executeSQL(query);

		try {
			while (r.next()) {

				if (r.getString("titre") != null) {

					this.id = r.getInt("id");
					annee = r.getInt("annee");
					editeur = r.getString("editeur");
					nom_auteur = r.getString("nom_auteur");
					prenom_auteur = r.getString("prenom_auteur");
					titre = r.getString("titre");

					// TODO : lire dates et Usager
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

!!!133506.java!!!	save() : void
		String query = "INSERT INTO livres (annee,editeur,nom_auteur,prenom_auteur,titre) VALUES (";
		query += annee + ",";
		query += "\"" + editeur + "\",";
		query += "\"" + nom_auteur + "\",";
		query += "\"" + prenom_auteur + "\",";
		query += "\"" + titre + "\"";
		query += ")";
		System.out.println(query);
		PDOSqlite.executeSQL(query);

!!!133634.java!!!	delect() : void

!!!133762.java!!!	toString() : String
		return "nom_auteur=" + nom_auteur + ", prenom_auteur=" + prenom_auteur + ", titre=" + titre + ", annee=" + annee
				+ ", editeur=" + editeur + ", emprunteur=" + emprunteur + ", date_sortie=" + date_sortie
				+ ", date_retour=" + date_retour + "]";
!!!133890.java!!!	assoc(inout usager : MUsager) : void

!!!134018.java!!!	dissoc(inout usager : MUsager) : void

!!!134146.java!!!	getId() : int
		return id;
!!!134274.java!!!	setId(in id : int) : void
		this.id = id;
!!!134402.java!!!	getNom_auteur() : String
		return nom_auteur;
!!!134530.java!!!	setNom_auteur(in nom_auteur : String) : void
		this.nom_auteur = nom_auteur;
!!!134658.java!!!	getPrenom_auteur() : String
		return prenom_auteur;
!!!134786.java!!!	setPrenom_auteur(in prenom_auteur : String) : void
		this.prenom_auteur = prenom_auteur;
!!!134914.java!!!	getTitre() : String
		return titre;
!!!135042.java!!!	setTitre(in titre : String) : void
		this.titre = titre;
!!!135170.java!!!	getAnnee() : int
		return annee;
!!!135298.java!!!	setAnnee(in annee : int) : void
		this.annee = annee;
!!!135426.java!!!	getEditeur() : String
		return editeur;
!!!135554.java!!!	setEditeur(in editeur : String) : void
		this.editeur = editeur;
!!!135682.java!!!	getEmprunteur() : MUsager
		return emprunteur;
!!!135810.java!!!	setEmprunteur(inout emprunteur : MUsager) : void
		this.emprunteur = emprunteur;
!!!135938.java!!!	getDate_sortie() : Date
		return date_sortie;
!!!136066.java!!!	setDate_sortie(inout date_sortie : Date) : void
		this.date_sortie = date_sortie;
!!!136194.java!!!	getDate_retour() : Date
		return date_retour;
!!!136322.java!!!	setDate_retour(inout date_retour : Date) : void
		this.date_retour = date_retour;
