-- =====================================================================
-- Práctica DAO · Biblioteca · 01_esquema.sql
-- Crea (o vuelve a crear) la base de datos biblioteca con sus 6 tablas.
-- Ejecútalo entero en MySQL Workbench y después 02_datos.sql.
-- ¡Cuidado! Empieza borrando la base de datos: se pierden los cambios.
-- =====================================================================

DROP DATABASE IF EXISTS biblioteca;
CREATE DATABASE biblioteca CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE biblioteca;

CREATE TABLE genero (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    nombre  VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE autor (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    nombre  VARCHAR(100) NOT NULL,
    pais    VARCHAR(50)  NOT NULL
);

CREATE TABLE libro (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    isbn        VARCHAR(17)  NOT NULL UNIQUE,
    titulo      VARCHAR(150) NOT NULL,
    anio        INT          NOT NULL,
    ejemplares  INT          NOT NULL CHECK (ejemplares >= 0),
    id_genero   INT          NOT NULL,
    CONSTRAINT fk_libro_genero FOREIGN KEY (id_genero)
        REFERENCES genero (id) ON DELETE RESTRICT
);

-- N:M pura: clave primaria compuesta por las dos claves ajenas
CREATE TABLE libro_autor (
    id_libro  INT NOT NULL,
    id_autor  INT NOT NULL,
    PRIMARY KEY (id_libro, id_autor),
    CONSTRAINT fk_la_libro FOREIGN KEY (id_libro)
        REFERENCES libro (id) ON DELETE CASCADE,
    CONSTRAINT fk_la_autor FOREIGN KEY (id_autor)
        REFERENCES autor (id) ON DELETE RESTRICT
);

CREATE TABLE socio (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(50)  NOT NULL,
    apellidos   VARCHAR(80)  NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    tipo        ENUM('ESTUDIANTE','DOCENTE') NOT NULL,
    fecha_alta  DATE NOT NULL
);

-- N:M con atributos y clave propia: un socio puede tomar prestado
-- el mismo libro varias veces. fecha_devolucion NULL = no devuelto.
CREATE TABLE prestamo (
    id                INT AUTO_INCREMENT PRIMARY KEY,
    id_socio          INT  NOT NULL,
    id_libro          INT  NOT NULL,
    fecha_prestamo    DATE NOT NULL,
    fecha_limite      DATE NOT NULL,
    fecha_devolucion  DATE NULL,
    CONSTRAINT fk_prestamo_socio FOREIGN KEY (id_socio)
        REFERENCES socio (id) ON DELETE RESTRICT,
    CONSTRAINT fk_prestamo_libro FOREIGN KEY (id_libro)
        REFERENCES libro (id) ON DELETE RESTRICT,
    CONSTRAINT ck_prestamo_fechas CHECK (fecha_limite >= fecha_prestamo)
);
