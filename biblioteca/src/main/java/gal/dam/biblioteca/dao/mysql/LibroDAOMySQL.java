package gal.dam.biblioteca.dao.mysql;

import gal.dam.biblioteca.dao.LibroDAO;
import gal.dam.biblioteca.modelo.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String consultaSql = "SELECT id,isbn,titulo, anio, ejemplares, id_genero FROM libro WHERE id = ?";
        try(PreparedStatement ps = con.prepareStatement(consultaSql)){
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Libro> listarTodos(Connection con) throws SQLException {
        String consultaMySql = "SELECT id, isbn, titulo, anio, ejemplares, id_genero FROM libro ORDER BY titulo";
        List<Libro> listaLibros = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(consultaMySql);
                ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                listaLibros.add(mapear(rs));
            }
        }
        return listaLibros;
    }

    // TODO: consultas especificas

    @Override
    public List<Libro> buscarPorTitulo(Connection con, String texto) throws SQLException {
        String consultaSql = "SELECT id,isbn,titulo, anio, ejemplares, id_genero FROM libro WHERE titulo LIKE ? ORDER BY titulo";
        List<Libro > listaLibrosPorTitulo = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(consultaSql)){

            ps.setString(1,"%"+texto+"%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaLibrosPorTitulo.add(mapear(rs));
                }
            }
        }
        return listaLibrosPorTitulo;
    }

    @Override
    public List<Libro> deAutor(Connection con,String nombreAutor) throws SQLException {
        String consultaSql = "SELECT l.id, l.isbn, l.titulo , l.anio, l.ejemplares, l.id_genero" +
                " FROM libro AS l " +
                "JOIN libro_autor AS la ON l.id = la.id_libro " +
                "JOIN autor AS a ON la.id_autor = a.id " +
                "WHERE a.nombre LIKE ? " +
                "ORDER BY l.titulo";

        List<Libro> listaLibrosPorAutor = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(consultaSql)) {
            ps.setString(1, "%" + nombreAutor + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaLibrosPorAutor.add(mapear(rs));
                }
            }
        }
        return listaLibrosPorAutor;
    }


    @Override
    public List<String> autoresDe(Connection con ,int idLibro) throws SQLException {
        String consultaSql = "SELECT a.nombre " +
                "FROM autor AS a " +
                "JOIN libro_autor AS la ON la.id_autor = a.id " +
                "WHERE la.id_libro = ? " +
                "ORDER BY a.nombre";

        ArrayList<String> listaLibrosHechosPorAutor = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(consultaSql)){
            ps.setInt(1,idLibro);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    listaLibrosHechosPorAutor.add(rs.getString("nombre"));
                }
            }
        }
        return listaLibrosHechosPorAutor;
    }

    @Override
    public List<Libro> prestadosASocioDeGenero(Connection con, int idSocio, int idGenero) throws SQLException {
        String consultaSql = "SELECT DISTINCT l.id, l.isbn, l.titulo, l.anio, l.ejemplares, l.id_genero" +
                " FROM libro AS l " +
                "JOIN prestamo p ON p.id_libro = l.id";
        List<Libro> listaLibrosLeidos = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(consultaSql)) {
            ps.setInt(1, idSocio);     // 1.er ?  → p.id_socio
            ps.setInt(2, idGenero);    // 2.º ?   → l.id_genero
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaLibrosLeidos.add(mapear(rs));
                }
            }
        }
        return listaLibrosLeidos;

    }


    // TODO: Mapear

    private Libro mapear(ResultSet rs) throws SQLException {
        return new Libro(rs.getInt("id"), rs.getString("isbn"),rs.getString("titulo"),rs.getInt("anio"),rs.getInt("ejemplares"),rs.getInt("id_genero"));
    }

}
