-- Eliminar tablas si existen para permitir reimportar sin errores
DROP TABLE IF EXISTS detalledelibro;
DROP TABLE IF EXISTS itemprestamo;
DROP TABLE IF EXISTS prestamo;
DROP TABLE IF EXISTS itemlibro;
DROP TABLE IF EXISTS libro_has_autor;
DROP TABLE IF EXISTS libro;
DROP TABLE IF EXISTS acceso;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS autor;
DROP TABLE IF EXISTS editorial;
DROP TABLE IF EXISTS genero;
DROP TABLE IF EXISTS tipo_usuario;
DROP TABLE IF EXISTS tipo_documento;
DROP TABLE IF EXISTS tipousuario;
DROP TABLE IF EXISTS tipodocumento;

-- Conéctate como el usuario de la app (biblioteca) o con root
--   mysql -u biblioteca -p dbbiblioteca  < crea_biblioteca.sql

/* === TABLAS BÁSICAS DE CATÁLOGO ============================== */

CREATE TABLE tipo_documento (
  iddocumento VARCHAR(2) PRIMARY KEY,
  descr VARCHAR(50)
);

CREATE TABLE tipo_usuario (
  idtipousuario VARCHAR(2) PRIMARY KEY,
  descr VARCHAR(50)
);

CREATE TABLE genero (
  idgenero VARCHAR(4) PRIMARY KEY,
  descr VARCHAR(100)
);

CREATE TABLE editorial (
  ideditorial VARCHAR(4) PRIMARY KEY,
  descr VARCHAR(100)
);

CREATE TABLE autor (
  idautor VARCHAR(4) PRIMARY KEY,
  nombre VARCHAR(100)
);

-- Tabla de mapeo libro-autor
CREATE TABLE libro_has_autor (
  libro_idlibro VARCHAR(6),
  autor_idautor VARCHAR(4),
  PRIMARY KEY (libro_idlibro, autor_idautor)
);

-- Tabla de detalle de libro (vista para lectura de mapeo libro-autor)
CREATE TABLE detalledelibro (
  idlibro VARCHAR(6),
  idautor VARCHAR(4)
);

/* === TABLA DE USUARIOS ======================================= */

CREATE TABLE usuario (
  idcarnet      VARCHAR(10) PRIMARY KEY,
  apepat        VARCHAR(50),
  apemat        VARCHAR(50),
  nombres       VARCHAR(80),
  iddocumento   VARCHAR(2),
  nrodocumento  VARCHAR(20),
  fechanaci     DATE,
  direccion     VARCHAR(120),
  telefono      VARCHAR(20),
  celular       VARCHAR(20),
  email         VARCHAR(80),
  idtipousuario VARCHAR(2),
  CONSTRAINT fk_usuario_tipodoc   FOREIGN KEY (iddocumento) REFERENCES tipo_documento(iddocumento),
  CONSTRAINT fk_usuario_tipousu   FOREIGN KEY (idtipousuario)      REFERENCES tipo_usuario(idtipousuario)
);

/* === TABLA DE ACCESO (LOGIN) ================================= */

CREATE TABLE acceso (
  idacceso        VARCHAR(10) PRIMARY KEY,
  nomusuario      VARCHAR(50) UNIQUE,
  password        VARCHAR(50),
  usuario_idcarnet VARCHAR(10),
  CONSTRAINT fk_acceso_usuario FOREIGN KEY (usuario_idcarnet) REFERENCES usuario(idcarnet)
);

/* === TABLAS DE LIBROS Y PRÉSTAMOS (resumen) ================= */

CREATE TABLE libro (
  idlibro                    VARCHAR(6) PRIMARY KEY,
  titulo                     VARCHAR(150),
  isbn                       VARCHAR(20),
  nroejemplar                INT,
  nropagina                  INT,
  nroedicion                 INT,
  yearpublica                VARCHAR(4),
  genero_idgenero            VARCHAR(4),
  editorial_ideditorial      VARCHAR(4),
  CONSTRAINT fk_libro_genero    FOREIGN KEY (genero_idgenero) REFERENCES genero(idgenero),
  CONSTRAINT fk_libro_editorial FOREIGN KEY (editorial_ideditorial) REFERENCES editorial(ideditorial)
);

-- Tablas de préstamos

CREATE TABLE prestamo (
  idprestamo   VARCHAR(10) PRIMARY KEY,
  idcarnet     VARCHAR(10),
  fechamaxima  VARCHAR(20),
  CONSTRAINT fk_pres_usuario FOREIGN KEY (idcarnet) REFERENCES usuario(idcarnet)
);

CREATE TABLE prestamo_has_libro (
  prestamo_idprestamo  VARCHAR(10),
  libro_idlibro        VARCHAR(6),
  fechadevolucion      VARCHAR(20),
  PRIMARY KEY (prestamo_idprestamo, libro_idlibro),
  CONSTRAINT fk_phl_prestamo FOREIGN KEY (prestamo_idprestamo) REFERENCES prestamo(idprestamo),
  CONSTRAINT fk_phl_libro     FOREIGN KEY (libro_idlibro)     REFERENCES libro(idlibro)
);

/* === DATA MÍNIMA ============================================= */

-- Catálogos
INSERT INTO tipo_documento (iddocumento,descr) VALUES ('1', 'DNI');
INSERT INTO tipo_usuario   (idtipousuario,descr) VALUES ('1', 'Administrador');
INSERT INTO genero        (idgenero,descr) VALUES ('G001','General');
INSERT INTO editorial     (ideditorial,descr) VALUES ('E001','Editorial Demo');
INSERT INTO autor         (idautor,nombre) VALUES ('A001','Anonimo');

-- Usuario que usaremos para login (coincide con frmLogin)
INSERT INTO usuario (idcarnet, apepat, apemat, nombres, iddocumento,
                     nrodocumento, fechanaci, direccion, telefono, celular, email, idtipousuario)
VALUES ('C0001','PRUEBA','PRUEBA','Usuario Demo','1',
        '12345678','1990-01-01','Dirección','000000','000000','demo@mail.com','1');

INSERT INTO acceso (idacceso, nomusuario, password, usuario_idcarnet)
VALUES ('U001','EINCIOCH','40904759','C0001');