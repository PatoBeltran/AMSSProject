CREATE TABLE suscripciones (
			IdSuscripcion INT NOT NULL AUTO_INCREMENT,
			IdSuscriptor INT NOT NULL,
			FechaDeInicio DATETIME,
			FechaDeFin DATETIME,
			PRIMARY KEY (IdSuscripcion),
			FOREIGN KEY (IdSuscriptor) REFERENCES usuarios (IdUsuario)
			);

CREATE TABLE usuarios (
			IdUsuario NOT NULL AUTO_INCREMENT,
			Tipo VARCHAR(255),
			Nombre VARCHAR(255),
			FechaDeNacimiento DATETIME,
			Email VARCHAR(255),
			Password VARCHAR(255)
			);

CREATE TABLE articulos (
			IdArticulo INT NOT NULL AUTO_INCREMENT,
			Titulo VARCHAR (255),
			IdAutor INT NOT NULL,
			FechaPublicacion DATETIME,
			Publicado BOOLEAN,
			Informacion MEDIUMTEXT
			PRIMARY KEY (IdArticulo),
			FOREIGN KEY (IdAutor) REFERENCES usuarios (IdUsuario)
			);

CREATE TABLE votos (
			IdVoto INT AUTO_INCREMENT,
			IdUsuario INT,
			IdArticulo INT,
			Favor BOOLEAN,
			PRIMARY KEY (IdVoto)
			);

CREATE TABLE publicidad (
			IdPublicidad INT NOT NULL AUTO_INCREMENT,
			IdUsuario INT,
			IdArticulo INT,
			Informacion TEXT,
			FechaPublicacion DATETIME,
			Publicado BOOLEAN
			PRIMARY KEY (IdPublicidad),
			FOREIGN KEY (IdUsuario) REFERENCES usuarios (IdUsuario),
			FOREIGN KEY (IdArticulo) REFERENCES usuarios (articulos)
			);
