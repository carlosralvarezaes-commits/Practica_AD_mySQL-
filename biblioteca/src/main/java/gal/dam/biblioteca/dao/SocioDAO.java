package gal.dam.biblioteca.dao;

import gal.dam.biblioteca.modelo.Socio;

/**
 * DAO de la tabla socio.
 * <p>
 * Tiene que heredar de DAO&lt;Socio, Integer&gt; buscarPorId y listarTodos.
 * <p>
 * TODO: declara aquí las consultas específicas del enunciado que correspondan a
 * este DAO. Todas reciben la Connection como primer parámetro y lanzan SQLException.
 */
public interface SocioDAO extends DAO<Socio, Integer> {

}
