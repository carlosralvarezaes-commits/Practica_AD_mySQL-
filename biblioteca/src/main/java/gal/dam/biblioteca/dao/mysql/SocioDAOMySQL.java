package gal.dam.biblioteca.dao.mysql;

import gal.dam.biblioteca.dao.SocioDAO;
import gal.dam.biblioteca.modelo.Libro;
import gal.dam.biblioteca.modelo.Prestamo;
import gal.dam.biblioteca.modelo.Socio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        String consultaSql = "SELECT id,nombre, apellidos, email , tipo , fecha_alta FROM socio WHERE id = ? ";
        try(PreparedStatement ps = con.prepareStatement(consultaSql)){
            ps.setInt(1,id );
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return Optional.of(mapear(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Socio> listarTodos(Connection con) throws SQLException {
        String consultaSql = "SELECT id,nombre, apellidos, email, tipo, fecha_alta FROM socio ORDER BY apellidos";
        List<Socio> listaSocios = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(consultaSql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                listaSocios.add(mapear(rs));
            }
        }
        return listaSocios;



    }

    // TODO: consultas específicas






    // TODO: Mapear
    /** Convierte la fila actual del ResultSet en un Socio. */
    private Socio mapear(ResultSet rs) throws SQLException {
        return new Socio(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellidos"),
                rs.getString("email"),
                rs.getString("tipo"),
                rs.getObject("fecha_alta",LocalDate.class)
        );

    }

    @Override
    public Optional<Socio> buscarPorEmail(Connection con, String email) throws SQLException {
        String consultaSql = "SELECT id, nombre, apellidos, email, tipo, fecha_alta FROM socio WHERE email = ? ORDER BY apellidos";
        try(PreparedStatement ps = con.prepareStatement(consultaSql)){
            ps.setString(1,email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        }
        return Optional.empty();

    }


    @Override
    public List<Libro> prestadosASocioDeGenero(int idSocio, int idGenero) throws SQLException {
        return List.of();
    }
}
