package gal.dam.biblioteca.dao.mysql;

import gal.dam.biblioteca.dao.PrestamoDAO;
import gal.dam.biblioteca.modelo.Prestamo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementación con JDBC (MySQL) de PrestamoDAO.
 * Para que desaparezcan los errores: El DAO tiene que hacer el extends
 * <p>
 * Normas: PreparedStatement para cualquier valor que venga de fuera,
 * PreparedStatement y ResultSet en try-with-resources, la Connection NUNCA
 * (la cierra quien la abrió: el servicio). Cada fila se convierte en objeto con mapear().
 */
public class PrestamoDAOMySQL implements PrestamoDAO {

    @Override
    public Optional<Prestamo> buscarPorId(Connection con, Integer id) throws SQLException {
        // TODO
        throw new UnsupportedOperationException("PrestamoDAOMySQL.buscarPorId");
    }

    @Override
    public List<Prestamo> listarTodos(Connection con) throws SQLException {
        // TODO
        throw new UnsupportedOperationException("PrestamoDAOMySQL.listarTodos");
    }

    // TODO: consultas específicas

    /** Convierte la fila actual del ResultSet en un Prestamo. */
    private Prestamo mapear(ResultSet rs) throws SQLException {
        // TODO
        throw new UnsupportedOperationException("PrestamoDAOMySQL.mapear");
    }
}
