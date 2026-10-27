package gal.dam.biblioteca.modelo;

/**
 * Un libro del catálogo (tabla libro). Guarda el id del género, no un objeto Genero.
 * 
 *
 */
public class Libro {
    private int id;
    private String isbn;
    private String titulo;
    private int anio;
    private int ejemplares;
    private int idGenero;

    public Libro(int id, String isbn, String titulo, int anio, int ejemplares, int idGenero) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.idGenero = idGenero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    @Override
    public String toString() {
        return id + ": " + titulo + " (" + anio + ", " + ejemplares + " ej.)";
    }
}
