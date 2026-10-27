package gal.dam.biblioteca.dao.mysql;

import gal.dam.biblioteca.dao.SocioDAO;
import gal.dam.biblioteca.modelo.Socio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementación con JDBC (MySQL) de SocioDAO.
 *  Para que desaparezcan los errores: El DAO tiene que hacer el extends
 * <p>
 * Normas: PreparedStatement para cualquier valor que venga de fuera,
 * PreparedStatement y ResultSet en try-with-resources, la Connection NUNCA
 * (la cierra quien la abrió: el servicio). Cada fila se convierte en objeto con mapear().
 */
public class SocioDAOMySQL implements SocioDAO {

    @Override
    public Optional<Socio> buscarPorId(Connection con, Integer id) throws SQLException {
        // TODO
        throw new UnsupportedOperationException("SocioDAOMySQL.buscarPorId");
    }

    @Override
    public List<Socio> listarTodos(Connection con) throws SQLException {
        // TODO
        throw new UnsupportedOperationException("SocioDAOMySQL.listarTodos");
    }

    // TODO: consultas específicas

    /** Convierte la fila actual del ResultSet en un Socio. */
    private Socio mapear(ResultSet rs) throws SQLException {
        // TODO
        throw new UnsupportedOperationException("SocioDAOMySQL.mapear");
    }
}
