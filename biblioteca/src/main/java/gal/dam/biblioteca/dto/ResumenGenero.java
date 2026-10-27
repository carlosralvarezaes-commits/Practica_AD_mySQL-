
package gal.dam.biblioteca.dto;

/**
 * Préstamos de un género: total, devueltos con retraso y porcentaje de retraso.
 * <p>
 * 
 */
public record ResumenGenero(String genero, int prestamos, int conRetraso, double porcentajeRetraso) {
}
