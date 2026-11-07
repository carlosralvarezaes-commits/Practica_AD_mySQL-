package gal.dam.biblioteca.dao;

import gal.dam.biblioteca.dto.PrestamoDetalle;
import gal.dam.biblioteca.modelo.Prestamo;
import gal.dam.biblioteca.dto.PrestamoDetalle;
import gal.dam.biblioteca.dto.PrestamoVencido;
import java.time.LocalDate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO de la tabla prestamo.
 * <p>
 * Tiene que heredar de DAO&lt;Prestamo, Integer&gt; buscarPorId y listarTodos.
 * <p>
 * TODO: declara aquí las consultas específicas del enunciado que correspondan a
 * este DAO. Todas reciben la Connection como primer parámetro y lanzan SQLException.
 */
public interface PrestamoDAO extends DAO<Prestamo,Integer>{
    List<Prestamo> activosDeSocio(Connection con,int idSocio) throws SQLException;
    List<PrestamoDetalle> historial(Connection con, int idSocio) throws SQLException;
    List<PrestamoVencido> vencidos(Connection con, LocalDate fechaRef) throws SQLException;
}
