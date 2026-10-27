package gal.dam.biblioteca.dto;

/**
 * Disponibilidad de un libro: ejemplares totales, prestados ahora y disponibles.
 * <p>
 * 
 */
public record LibroDisponible(int idLibro, String titulo, int ejemplares, int prestados, int disponibles) {
}
