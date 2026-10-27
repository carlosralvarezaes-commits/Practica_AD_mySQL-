package gal.dam.biblioteca.dto;

import java.time.LocalDate;

/**
 * Una línea del historial de un socio: el préstamo con el título y los autores del libro.
 * fechaDevolucion es null si no se ha devuelto.
 * <p>
 * 
 */
public record PrestamoDetalle(int idPrestamo, String titulo, String autores, LocalDate fechaPrestamo,
        LocalDate fechaLimite, LocalDate fechaDevolucion) {
}
