package gal.dam.biblioteca.dao;

import gal.dam.biblioteca.modelo.Libro;
import gal.dam.biblioteca.modelo.Prestamo;
import gal.dam.biblioteca.modelo.Socio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * DAO de la tabla socio.
 * <p>
 * Tiene que heredar de DAO&lt;Socio, Integer&gt; buscarPorId y listarTodos.
 * <p>
 * TODO: declara aquí las consultas específicas del enunciado que correspondan a
 * este DAO. Todas reciben la Connection como primer parámetro y lanzan SQLException.
 */
public interface SocioDAO extends DAO<Socio, Integer> {
    Optional<Socio> buscarPorEmail(Connection con,String email) throws SQLException;
    List<Socio> conPrestamosVencidos(Connection con, LocalDate fechaRef) throws SQLException;
    List<Socio> lectoresDeTodoElAutor(Connection con, int idAutor) throws SQLException;








}
