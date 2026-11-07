package gal.dam.biblioteca.dao.mysql;

import gal.dam.biblioteca.dao.PrestamoDAO;
import gal.dam.biblioteca.modelo.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        String consultaSql = "SELECT id, id_socio, id_libro, fecha_prestamo, fecha_limite, fecha_devolucion FROM prestamo WHERE id = ?";
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
    public List<Prestamo> listarTodos(Connection con) throws SQLException {
        String consultaSql = "SELECT id, id_socio, id_libro, fecha_prestamo, fecha_limite, fecha_devolucion FROM prestamo ORDER BY fecha_prestamo ,id";
        List<Prestamo> listaPrestamos = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(consultaSql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                listaPrestamos.add(mapear(rs));
            }
        }
        return listaPrestamos;
    }

    // TODO: consultas específicas









    // TODO: Mapear

    /** Convierte la fila actual del ResultSet en un Prestamo. */
    private Prestamo mapear(ResultSet rs) throws SQLException {
        return  new Prestamo(rs.getInt("id"), rs.getInt("id_socio"), rs.getInt("id_libro"),
         rs.getObject("fecha_prestamo", LocalDate.class), rs.getObject("fecha_limite", LocalDate.class),rs.getObject("fecha_devolucion", LocalDate.class));
    }

    @Override
    public List<Prestamo> activosDeSocio(Connection con , int idSocio) throws SQLException {
        String consultaSql = "SELECT id, id_socio, id_libro, fecha_prestamo, fecha_limite, fecha_devolucion "+
                "FROM prestamo "+
                "WHERE id_socio = ? AND fecha_devolucion IS NULL " +
                "ORDER BY fecha_prestamo";
        List<Prestamo> listaPrestamosActivos = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(consultaSql)) {
            ps.setInt(1, idSocio);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaPrestamosActivos.add(mapear(rs));
                }
            }
        }
        return listaPrestamosActivos;
    }

}
