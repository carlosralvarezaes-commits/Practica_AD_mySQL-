package gal.dam.biblioteca.dao;

import gal.dam.biblioteca.modelo.Libro;

/**
 * DAO de la tabla libro.
 * <p>
 * Tiene que heredar de DAO&lt;Libro, Integer&gt; buscarPorId y listarTodos.
 * <p>
 * TODO: declara aquí las consultas específicas del enunciado que correspondan a
 * este DAO. Todas reciben la Connection como primer parámetro y lanzan SQLException.
 */
public interface LibroDAO extends DAO<Libro,Integer>{

}
