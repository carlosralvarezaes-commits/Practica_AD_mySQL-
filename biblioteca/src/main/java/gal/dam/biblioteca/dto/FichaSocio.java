package gal.dam.biblioteca.dto;

import gal.dam.biblioteca.modelo.Prestamo;
import gal.dam.biblioteca.modelo.Socio;
import java.util.List;

/**
 * Un socio con sus préstamos activos. La construye un servicio a partir de dos DAO.
 * <p>
 * 
 */
public record FichaSocio(Socio socio, List<Prestamo> activos) {
}
