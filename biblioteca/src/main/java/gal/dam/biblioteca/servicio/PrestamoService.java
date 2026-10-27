package gal.dam.biblioteca.servicio;

import gal.dam.biblioteca.dao.LibroDAO;
import gal.dam.biblioteca.dao.PrestamoDAO;
import gal.dam.biblioteca.dao.SocioDAO;
import gal.dam.biblioteca.dto.PrestamoDetalle;
import gal.dam.biblioteca.dto.PrestamoVencido;
import gal.dam.biblioteca.dto.ResumenGenero;
import gal.dam.biblioteca.modelo.Prestamo;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Operaciones sobre los préstamos.
 */
public class PrestamoService {

    private final PrestamoDAO prestamoDAO;
    private final LibroDAO libroDAO;
    private final SocioDAO socioDAO;

    public PrestamoService(PrestamoDAO prestamoDAO, LibroDAO libroDAO, SocioDAO socioDAO) {
        this.prestamoDAO = prestamoDAO;
        this.libroDAO = libroDAO;
        this.socioDAO = socioDAO;
    }

    // ---------- TODO ----------

    public List<Prestamo> listarPrestamos() throws SQLException {
        throw new UnsupportedOperationException("PrestamoService.listarPrestamos");
    }

    public Optional<Prestamo> buscarPrestamo(int id) throws SQLException {
        throw new UnsupportedOperationException("PrestamoService.buscarPrestamo");
    }

    public List<Prestamo> activosDeSocio(int idSocio) throws SQLException {
        throw new UnsupportedOperationException("PrestamoService.activosDeSocio");
    }

    public List<PrestamoDetalle> historial(int idSocio) throws SQLException {
        throw new UnsupportedOperationException("PrestamoService.historial");
    }

    public List<PrestamoVencido> vencidos(LocalDate fechaRef) throws SQLException {
        throw new UnsupportedOperationException("PrestamoService.vencidos");
    }

    public List<ResumenGenero> resumenPorGenero() throws SQLException {
        throw new UnsupportedOperationException("PrestamoService.resumenPorGenero");
    }
}
