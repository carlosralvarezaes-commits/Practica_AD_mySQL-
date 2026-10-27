package gal.dam.biblioteca.servicio;

import gal.dam.biblioteca.dao.LibroDAO;
import gal.dam.biblioteca.dao.PrestamoDAO;
import gal.dam.biblioteca.dao.SocioDAO;
import gal.dam.biblioteca.dto.FichaSocio;
import gal.dam.biblioteca.modelo.Libro;
import gal.dam.biblioteca.modelo.Socio;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Operaciones sobre los socios.
 * <p>
 * Fíjate en que este servicio recibe tres DAO: algunas operaciones sobre un
 * socio necesitan datos de otras tablas (y por tanto de otros DAO).
 */
public class SocioService {

    private final SocioDAO socioDAO;
    private final LibroDAO libroDAO;
    private final PrestamoDAO prestamoDAO;

    public SocioService(SocioDAO socioDAO, LibroDAO libroDAO, PrestamoDAO prestamoDAO) {
        this.socioDAO = socioDAO;
        this.libroDAO = libroDAO;
        this.prestamoDAO = prestamoDAO;
    }

    // ---------- TODO ----------

    public List<Socio> listarSocios() throws SQLException {
        throw new UnsupportedOperationException("SocioService.listarSocios");
    }

    public Optional<Socio> buscarSocio(int id) throws SQLException {
        throw new UnsupportedOperationException("SocioService.buscarSocio");
    }

    public Optional<Socio> buscarPorEmail(String email) throws SQLException {
        throw new UnsupportedOperationException("SocioService.buscarPorEmail");
    }

    /**
     * El socio con sus préstamos activos. Usa DOS DAO con UNA sola conexión.
     * Si el socio no existe, lanza NoSuchElementException("No existe el socio " + idSocio).
     */
    public FichaSocio fichaSocio(int idSocio) throws SQLException {
        throw new UnsupportedOperationException("SocioService.fichaSocio");
    }

    public List<Libro> librosLeidosDeGenero(int idSocio, int idGenero) throws SQLException {
        throw new UnsupportedOperationException("SocioService.librosLeidosDeGenero");
    }

    public List<Socio> conPrestamosVencidos(LocalDate fechaRef) throws SQLException {
        throw new UnsupportedOperationException("SocioService.conPrestamosVencidos");
    }

    public List<Socio> lectoresDeTodoElAutor(int idAutor) throws SQLException {
        throw new UnsupportedOperationException("SocioService.lectoresDeTodoElAutor");
    }

    public List<Socio> lectoresSobreLaMedia() throws SQLException {
        throw new UnsupportedOperationException("SocioService.lectoresSobreLaMedia");
    }
}
