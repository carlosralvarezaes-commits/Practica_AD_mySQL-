package gal.dam.biblioteca.dao.mysql;

import gal.dam.biblioteca.dao.PrestamoDAO;
import gal.dam.biblioteca.dto.PrestamoDetalle;
import gal.dam.biblioteca.dto.PrestamoVencido;
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


    @Override
    public List<PrestamoDetalle> historial(Connection con, int idSocio) throws SQLException {
        String consultaSql = "SELECT p.id, l.titulo, " +
                "GROUP_CONCAT(a.nombre ORDER BY a.nombre SEPARATOR ', ') AS autores, " +
                "p.fecha_prestamo, p.fecha_limite, p.fecha_devolucion " +
                "FROM prestamo p " +
                "JOIN libro l ON l.id = p.id_libro " +
                "JOIN libro_autor la ON la.id_libro = l.id " +
                "JOIN autor a ON a.id = la.id_autor " +
                "WHERE p.id_socio = ? " +
                "GROUP BY p.id, l.titulo " +
                "ORDER BY p.fecha_prestamo";
        List<PrestamoDetalle> listaHistorial = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(consultaSql)) {
            ps.setInt(1, idSocio);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaHistorial.add(mapearDetalle(rs));
                }
            }
        }
        return listaHistorial;
    }

    @Override
    public List<PrestamoVencido> vencidos(Connection con, LocalDate fechaRef) throws SQLException {
        String consultaSql = "SELECT p.id, CONCAT(s.nombre, ' ', s.apellidos) AS socio, l.titulo, " +
                "p.fecha_limite, DATEDIFF(?, p.fecha_limite) AS dias_retraso " +
                "FROM prestamo p " +
                "JOIN socio s ON s.id = p.id_socio " +
                "JOIN libro l ON l.id = p.id_libro " +
                "WHERE p.fecha_devolucion IS NULL AND p.fecha_limite < ? " +
                "ORDER BY dias_retraso DESC";
        List<PrestamoVencido> listaVencidos = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(consultaSql)) {
            ps.setObject(1, fechaRef);   // ? del DATEDIFF
            ps.setObject(2, fechaRef);   // ? del WHERE
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaVencidos.add(mapearVencido(rs));
                }
            }
        }
        return listaVencidos;
    }

    private PrestamoDetalle mapearDetalle(ResultSet rs) throws SQLException {
        return new PrestamoDetalle(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("autores"),
                rs.getObject("fecha_prestamo", LocalDate.class),
                rs.getObject("fecha_limite", LocalDate.class),
                rs.getObject("fecha_devolucion", LocalDate.class));
    }

    private PrestamoVencido mapearVencido(ResultSet rs) throws SQLException {
        return new PrestamoVencido(
                rs.getInt("id"),
                rs.getString("socio"),
                rs.getString("titulo"),
                rs.getObject("fecha_limite", LocalDate.class),
                rs.getInt("dias_retraso"));
    }




}
