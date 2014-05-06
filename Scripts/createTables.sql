create database if not EXISTS AMSS;
use AMSS;

CREATE TABLE IF NOT EXISTS usuarios (
			IdUsuario INT NOT NULL AUTO_INCREMENT,
			Tipo VARCHAR(255),
			Nombre VARCHAR(255) NOT NULL,
			FechaDeNacimiento DATETIME,
			Email VARCHAR(255),
			Password VARCHAR(255) NOT NULL,
			PRIMARY KEY (IdUsuario)
			);

CREATE TABLE IF NOT EXISTS votos (
			IdVoto INT AUTO_INCREMENT,
			IdUsuario INT,
			IdArticulo INT,
			Favor BOOLEAN,
			PRIMARY KEY (IdVoto)
			);

CREATE TABLE IF NOT EXISTS suscripciones (
			IdSuscripcion INT NOT NULL AUTO_INCREMENT,
			IdSuscriptor INT NOT NULL,
			FechaDeInicio DATETIME,
			FechaDeFin DATETIME,
			PRIMARY KEY (IdSuscripcion),
			FOREIGN KEY (IdSuscriptor) REFERENCES usuarios (IdUsuario)
			);

CREATE TABLE IF NOT EXISTS articulos (
			IdArticulo INT NOT NULL AUTO_INCREMENT,
			Titulo VARCHAR (255),
			IdAutor INT NOT NULL,
			FechaPublicacion DATETIME,
			Publicado BOOLEAN,
			Informacion MEDIUMTEXT,
			PRIMARY KEY (IdArticulo),
			FOREIGN KEY (IdAutor) REFERENCES usuarios (IdUsuario)
			);


CREATE TABLE IF NOT EXISTS publicidad (
			IdPublicidad INT NOT NULL AUTO_INCREMENT,
			IdUsuario INT,
			IdArticulo INT,
			Informacion TEXT,
			FechaPublicacion DATETIME,
			Publicado BOOLEAN,
			PRIMARY KEY (IdPublicidad),
			FOREIGN KEY (IdUsuario) REFERENCES usuarios (IdUsuario),
			FOREIGN KEY (IdArticulo) REFERENCES articulos (IdArticulo)
			);
