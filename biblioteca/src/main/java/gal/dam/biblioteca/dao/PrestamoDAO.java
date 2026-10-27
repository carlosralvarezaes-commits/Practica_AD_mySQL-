package gal.dam.biblioteca.dao;

import gal.dam.biblioteca.modelo.Prestamo;

/**
 * DAO de la tabla prestamo.
 * <p>
 * Tiene que heredar de DAO&lt;Prestamo, Integer&gt; buscarPorId y listarTodos.
 * <p>
 * TODO: declara aquí las consultas específicas del enunciado que correspondan a
 * este DAO. Todas reciben la Connection como primer parámetro y lanzan SQLException.
 */
public interface PrestamoDAO extends DAO<Prestamo,Integer>{

}
