package gal.dam.biblioteca.servicio;

import gal.dam.biblioteca.config.ConexionBD;
import gal.dam.biblioteca.dao.DAO;
import gal.dam.biblioteca.dao.LibroDAO;
import gal.dam.biblioteca.dto.LibroDisponible;
import gal.dam.biblioteca.dto.LibroPrestamos;
import gal.dam.biblioteca.modelo.Genero;
import gal.dam.biblioteca.modelo.Libro;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Operaciones sobre el catálogo: géneros, libros y autores.
 * <p>
 * Patrón de TODOS los métodos de servicio: abrir la conexión en un
 * try-with-resources, llamar a uno o varios DAO pasándoles esa conexión
 * y devolver el resultado. El servicio no escribe SQL.
 * <p>
 * listarGeneros() y buscarGenero() están hechos como ejemplo.
 */
public class CatalogoService {

    private final LibroDAO libroDAO;
    private final DAO<Genero, Integer> generoDAO;

    public CatalogoService(LibroDAO libroDAO, DAO<Genero, Integer> generoDAO) {
        this.libroDAO = libroDAO;
        this.generoDAO = generoDAO;
    }

    // ---------- Ejemplos dados para ver cómo funcionan ----------

    public List<Genero> listarGeneros() throws SQLException {
        try (Connection con = ConexionBD.getConexion()) {
            return generoDAO.listarTodos(con);
        }
    }

    public Optional<Genero> buscarGenero(int id) throws SQLException {
        try (Connection con = ConexionBD.getConexion()) {
            return generoDAO.buscarPorId(con, id);
        }
    }

    // ---------- TODO ----------

    public List<Libro> listarLibros() throws SQLException {
        throw new UnsupportedOperationException("CatalogoService.listarLibros");
    }

    public Optional<Libro> buscarLibro(int id) throws SQLException {
        throw new UnsupportedOperationException("CatalogoService.buscarLibro");
    }

    public List<Libro> buscarPorTitulo(String texto) throws SQLException {
        throw new UnsupportedOperationException("CatalogoService.buscarPorTitulo");
    }

    public List<Libro> librosDeAutor(String nombreAutor) throws SQLException {
        throw new UnsupportedOperationException("CatalogoService.librosDeAutor");
    }

    public List<String> autoresDe(int idLibro) throws SQLException {
        throw new UnsupportedOperationException("CatalogoService.autoresDe");
    }

    public List<Libro> nuncaPrestados() throws SQLException {
        throw new UnsupportedOperationException("CatalogoService.nuncaPrestados");
    }

    public List<LibroDisponible> disponibilidad() throws SQLException {
        throw new UnsupportedOperationException("CatalogoService.disponibilidad");
    }

    public List<LibroPrestamos> masPrestadoPorGenero() throws SQLException {
        throw new UnsupportedOperationException("CatalogoService.masPrestadoPorGenero");
    }
}
