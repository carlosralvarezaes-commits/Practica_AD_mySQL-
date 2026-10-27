package gal.dam.biblioteca.dto;

import java.time.LocalDate;

/**
 * Un préstamo sin devolver cuya fecha límite ya ha pasado, con los días de retraso.
 * <p>
 * 
 */
public record PrestamoVencido(int idPrestamo, String socio, String titulo, LocalDate fechaLimite, int diasRetraso) {
}
