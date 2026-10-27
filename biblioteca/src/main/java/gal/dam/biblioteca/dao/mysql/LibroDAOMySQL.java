package gal.dam.biblioteca.dao.mysql;

import gal.dam.biblioteca.dao.LibroDAO;
import gal.dam.biblioteca.modelo.Libro;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementación con JDBC (MySQL) de LibroDAO.
 *  Para que desaparezcan los errores: El DAO tiene que hacer el extends
 * <p>
 * Normas: PreparedStatement para cualquier valor que venga de fuera,
 * PreparedStatement y ResultSet en try-with-resources, la Connection NUNCA
 * (la cierra quien la abrió: el servicio). Cada fila se convierte en objeto con mapear().
 */
public class LibroDAOMySQL implements LibroDAO {

    @Override
    public Optional<Libro> buscarPorId(Connection con, Integer id) throws SQLException {
        // TODO
        throw new UnsupportedOperationException("LibroDAOMySQL.buscarPorId");
    }

    @Override
    public List<Libro> listarTodos(Connection con) throws SQLException {
        // TODO
        throw new UnsupportedOperationException("LibroDAOMySQL.listarTodos");
    }

    // TODO: consultas específicas

    /** Convierte la fila actual del ResultSet en un Libro. */
    private Libro mapear(ResultSet rs) throws SQLException {
        // TODO
        throw new UnsupportedOperationException("LibroDAOMySQL.mapear");
    }
}
