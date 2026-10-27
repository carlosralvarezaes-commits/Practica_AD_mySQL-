-- =====================================================================
-- Práctica DAO · Biblioteca · 02_datos.sql
-- Datos de prueba. Ejecútalo después de 01_esquema.sql.
-- Para volver a los datos iniciales: ejecuta otra vez 01 y 02.
--
-- Casos especiales (son los que ponen a prueba las consultas):
--   · Poesía tiene un libro (Follas novas) que nunca se ha prestado;
--     Freakonomics (Ensayo) tampoco.
--   · Fundación (2 ejemplares), Los desposeídos, American Gods y
--     Que me queres, amor? (1 ejemplar) tienen ahora mismo todos sus
--     ejemplares prestados.
--   · Buenos presagios y Freakonomics tienen dos autores.
--   · Nuno no tiene ningún préstamo.
--   · Alba ha tomado prestado Fundación dos veces.
--   · En Fantasía hay empate en el libro más prestado.
--   · Plazo: 15 días para ESTUDIANTE y 30 para DOCENTE.
--   · Fecha de referencia del enunciado: 2026-10-15. El préstamo de
--     Marta vence justo ese día (no cuenta como vencido).
-- Los ISBN son ficticios.
-- =====================================================================

USE biblioteca;

INSERT INTO genero (nombre) VALUES
('Novela'),
('Ciencia ficción'),
('Fantasía'),
('Ensayo'),
('Poesía');

INSERT INTO autor (nombre, pais) VALUES
('Gabriel García Márquez', 'Colombia'),
('Manuel Rivas', 'España'),
('Isaac Asimov', 'Estados Unidos'),
('Ursula K. Le Guin', 'Estados Unidos'),
('Terry Pratchett', 'Reino Unido'),
('Neil Gaiman', 'Reino Unido'),
('Yuval Noah Harari', 'Israel'),
('Irene Vallejo', 'España'),
('Rosalía de Castro', 'España'),
('Steven D. Levitt', 'Estados Unidos'),
('Stephen J. Dubner', 'Estados Unidos');

INSERT INTO libro (isbn, titulo, anio, ejemplares, id_genero) VALUES
('978-84-0001-001-1', 'Cien años de soledad', 1967, 3, 1),
('978-84-0001-002-8', 'O lapis do carpinteiro', 1998, 2, 1),
('978-84-0001-003-5', 'Que me queres, amor?', 1995, 1, 1),
('978-84-0001-004-2', 'Fundación', 1951, 2, 2),
('978-84-0001-005-9', 'Yo, robot', 1950, 2, 2),
('978-84-0001-006-6', 'Los desposeídos', 1974, 1, 2),
('978-84-0001-007-3', 'Un mago de Terramar', 1968, 2, 3),
('978-84-0001-008-0', 'Buenos presagios', 1990, 2, 3),
('978-84-0001-009-7', 'American Gods', 2001, 1, 3),
('978-84-0001-010-3', 'Sapiens', 2011, 3, 4),
('978-84-0001-011-0', 'El infinito en un junco', 2019, 2, 4),
('978-84-0001-012-7', 'Follas novas', 1880, 1, 5),
('978-84-0001-013-4', 'Freakonomics', 2005, 1, 4);

INSERT INTO libro_autor (id_libro, id_autor) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 3),
(5, 3),
(6, 4),
(7, 4),
(8, 5),
(8, 6),
(9, 6),
(10, 7),
(11, 8),
(12, 9),
(13, 10),
(13, 11);

INSERT INTO socio (nombre, apellidos, email, tipo, fecha_alta) VALUES
('Alba', 'Seoane Rial', 'alba.seoane@biblio.gal', 'ESTUDIANTE', '2025-09-15'),
('Breixo', 'Lamas Novo', 'breixo.lamas@biblio.gal', 'ESTUDIANTE', '2025-09-15'),
('Carme', 'Novoa Pardo', 'carme.novoa@biblio.gal', 'DOCENTE', '2025-09-01'),
('Diego', 'Ferreiro Gil', 'diego.ferreiro@biblio.gal', 'ESTUDIANTE', '2025-10-02'),
('Eva', 'Barreiro Mato', 'eva.barreiro@biblio.gal', 'ESTUDIANTE', '2025-11-20'),
('Fiz', 'Couto Sanmartín', 'fiz.couto@biblio.gal', 'DOCENTE', '2025-09-01'),
('Iria', 'Mosquera Lago', 'iria.mosquera@biblio.gal', 'ESTUDIANTE', '2026-01-12'),
('Lois', 'Pazos Vidal', 'lois.pazos@biblio.gal', 'ESTUDIANTE', '2026-02-03'),
('Marta', 'Rey Outeiral', 'marta.rey@biblio.gal', 'DOCENTE', '2025-09-01'),
('Nuno', 'Varela Freire', 'nuno.varela@biblio.gal', 'ESTUDIANTE', '2026-09-28');

INSERT INTO prestamo (id_socio, id_libro, fecha_prestamo, fecha_limite, fecha_devolucion) VALUES
(1, 4, '2026-01-10', '2026-01-25', '2026-01-20'),
(6, 4, '2026-01-08', '2026-02-07', '2026-02-01'),
(3, 8, '2026-01-15', '2026-02-14', '2026-02-10'),
(4, 1, '2026-01-20', '2026-02-04', '2026-02-01'),
(1, 5, '2026-02-02', '2026-02-17', '2026-02-25'),
(5, 7, '2026-02-05', '2026-02-20', '2026-02-18'),
(2, 4, '2026-02-11', '2026-02-26', '2026-02-20'),
(6, 5, '2026-02-15', '2026-03-17', '2026-03-10'),
(9, 1, '2026-02-20', '2026-03-22', '2026-03-15'),
(3, 9, '2026-03-01', '2026-03-31', '2026-03-25'),
(1, 1, '2026-03-05', '2026-03-20', '2026-03-18'),
(4, 4, '2026-03-10', '2026-03-25', '2026-03-30'),
(7, 1, '2026-03-12', '2026-03-27', '2026-03-25'),
(2, 7, '2026-04-01', '2026-04-16', '2026-04-30'),
(6, 6, '2026-04-02', '2026-05-02', '2026-05-15'),
(9, 11, '2026-04-05', '2026-05-05', '2026-04-28'),
(4, 10, '2026-04-15', '2026-04-30', '2026-04-29'),
(7, 2, '2026-04-20', '2026-05-05', '2026-05-02'),
(3, 10, '2026-05-02', '2026-06-01', '2026-06-20'),
(8, 10, '2026-05-05', '2026-05-20', '2026-05-18'),
(5, 3, '2026-05-10', '2026-05-25', '2026-05-22'),
(6, 1, '2026-06-01', '2026-07-01', '2026-06-20'),
(8, 8, '2026-06-02', '2026-06-17', '2026-06-15'),
(3, 2, '2026-06-10', '2026-07-10', '2026-07-01'),
(9, 7, '2026-06-15', '2026-07-15', '2026-08-01'),
(7, 11, '2026-07-01', '2026-07-16', '2026-07-20'),
(5, 8, '2026-07-05', '2026-07-20', '2026-07-18'),
(1, 10, '2026-07-10', '2026-07-25', '2026-07-24'),
(5, 1, '2026-08-03', '2026-08-18', '2026-08-14'),
(6, 10, '2026-08-25', '2026-09-24', NULL),
(3, 11, '2026-09-01', '2026-10-01', NULL),
(8, 7, '2026-09-10', '2026-09-25', '2026-09-22'),
(9, 3, '2026-09-15', '2026-10-15', NULL),
(1, 4, '2026-09-20', '2026-10-05', NULL),
(7, 9, '2026-09-28', '2026-10-13', NULL),
(2, 8, '2026-10-01', '2026-10-16', NULL),
(4, 4, '2026-10-05', '2026-10-20', NULL),
(5, 6, '2026-10-10', '2026-10-25', NULL);

-- Comprobación: 5 géneros, 11 autores, 13 libros, 15 filas en libro_autor,
-- 10 socios y 38 préstamos (8 sin devolver).
