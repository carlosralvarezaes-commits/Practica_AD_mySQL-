package gal.dam.biblioteca.dao.mysql;

import gal.dam.biblioteca.dao.DAO;
import gal.dam.biblioteca.modelo.Genero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación con JDBC (MySQL) de DAO&lt;Genero, Integer&gt;.
 * <p>
 * CLASE ENTREGADA COMO EJEMPLO para que veais cómo tienen que quedar el resto de DAOs.
 * <ul>
 * <li>Recibe la Connection y NO la cierra (no va en el try-with-resources).</li>
 * <li>PreparedStatement y ResultSet sí van en try-with-resources.</li>
 * <li>Cada fila se convierte en objeto con mapear().</li>
 * </ul>
 * Género no tiene interfaz propia porque no tiene consultas específicas:
 * implementa directamente la interfaz genérica.
 */
public class GeneroDAOMySQL implements DAO<Genero, Integer> {

    @Override
    public Optional<Genero> buscarPorId(Connection con, Integer id) throws SQLException {
        String sql = "SELECT id, nombre FROM genero WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
            }
        }
    }

    @Override
    public List<Genero> listarTodos(Connection con) throws SQLException {
        String sql = "SELECT id, nombre FROM genero ORDER BY nombre";
        List<Genero> generos = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                generos.add(mapear(rs));
            }
        }
        return generos;
    }

    /** Convierte la fila actual del ResultSet en un Genero. */
    private Genero mapear(ResultSet rs) throws SQLException {
        return new Genero(rs.getInt("id"), rs.getString("nombre"));
    }
}
