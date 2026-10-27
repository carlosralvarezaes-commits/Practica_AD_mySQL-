package gal.dam.biblioteca.dto;

/**
 * Un libro con su número de préstamos y el nombre de su género.
 * <p>
 * 
 */
public record LibroPrestamos(String genero, String titulo, int prestamos) {
}
