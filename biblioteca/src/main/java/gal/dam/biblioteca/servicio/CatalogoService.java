package gal.dam.biblioteca.servicio;

import gal.dam.biblioteca.config.ConexionBD;
import gal.dam.biblioteca.dao.DAO;
import gal.dam.biblioteca.dao.LibroDAO;
import gal.dam.biblioteca.dto.LibroDisponible;
import gal.dam.biblioteca.dto.LibroPrestamos;
import gal.dam.biblioteca.modelo.Genero;
import gal.dam.biblioteca.modelo.Libro;

import java.sql.*;
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
        try(Connection con = ConexionBD.getConexion()){
            return libroDAO.listarTodos(con);
        }
    }

    public Optional<Libro> buscarLibro(int id) throws SQLException {
        try(Connection con = ConexionBD.getConexion()){
            return libroDAO.buscarPorId(con, id);
        }
    }

    public List<Libro> buscarPorTitulo(String texto) throws SQLException {
        try(Connection con = ConexionBD.getConexion()){
            return libroDAO.buscarPorTitulo(con,texto);
        }
    }

    public List<Libro> librosDeAutor(String nombreAutor) throws SQLException {
        try(Connection con = ConexionBD.getConexion()){
            return libroDAO.deAutor(con , nombreAutor);
        }
    }

    public List<String> autoresDe(int idLibro) throws SQLException {
        try(Connection con = ConexionBD.getConexion()){
            return libroDAO.autoresDe(con, idLibro);
        }
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
