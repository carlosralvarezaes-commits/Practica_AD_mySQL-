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
    public List<Libro> deAutor(String nombreAutor) throws SQLException {





        return null;
    }

    @Override
    public List<String> autoresDe(int idLibro) throws SQLException {
        return List.of();
    }


    // TODO: Mapear

    private Libro mapear(ResultSet rs) throws SQLException {
        return new Libro(rs.getInt("id"), rs.getString("isbn"),rs.getString("titulo"),rs.getInt("anio"),rs.getInt("ejemplares"),rs.getInt("id_genero"));
    }

}
