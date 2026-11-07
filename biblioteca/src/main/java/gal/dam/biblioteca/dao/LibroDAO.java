package gal.dam.biblioteca.dao;

import gal.dam.biblioteca.modelo.Libro;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO de la tabla libro.
 * <p>
 * Tiene que heredar de DAO&lt;Libro, Integer&gt; buscarPorId y listarTodos.
 * <p>
 * TODO: declara aquí las consultas específicas del enunciado que correspondan a
 * este DAO. Todas reciben la Connection como primer parámetro y lanzan SQLException.
 */
public interface LibroDAO extends DAO<Libro, Integer> {
    List<Libro> buscarPorTitulo(Connection con, String texto) throws SQLException;
    List<Libro> deAutor(String nombreAutor) throws SQLException;
    List<String> autoresDe(int idLibro) throws SQLException;



}
