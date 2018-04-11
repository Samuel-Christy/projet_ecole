#------------------------------------------------------------
#        Script SQLite  
#------------------------------------------------------------


#------------------------------------------------------------
# Table: usagers
#------------------------------------------------------------
CREATE TABLE usagers(
	id      INTEGER autoincrement NOT NULL ,
	nom     TEXT ,
	prenom  TEXT ,
	PRIMARY KEY (id)
);


#------------------------------------------------------------
# Table: livres
#------------------------------------------------------------
CREATE TABLE livres(
	id             INTEGER autoincrement NOT NULL ,
	annee          INTEGER NOT NULL ,
	prenom_auteur  TEXT NOT NULL ,
	nom_auteur     TEXT NOT NULL ,
	titre          TEXT NOT NULL ,
	editeur        TEXT NOT NULL ,
	date_sortie    NUMERIC NOT NULL ,
	date_retour    NUMERIC NOT NULL ,
	id_usagers     INTEGER ,
	PRIMARY KEY (id) ,
	
	FOREIGN KEY (id_usagers) REFERENCES usagers(id)
);


