package gal.dam.biblioteca.modelo;

import java.time.LocalDate;

/**
 * Un préstamo (tabla prestamo). fechaDevolucion es null mientras el libro no se ha devuelto.
 * 
 */
public class Prestamo {
    private int id;
    private int idSocio;
    private int idLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;
    private LocalDate fechaDevolucion;

    public Prestamo(int id, int idSocio, int idLibro, LocalDate fechaPrestamo, LocalDate fechaLimite, LocalDate fechaDevolucion) {
        this.id = id;
        this.idSocio = idSocio;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Préstamo " + id + ": socio " + idSocio + ", libro " + idLibro
                + ", del " + fechaPrestamo + " al " + fechaLimite
                + (fechaDevolucion == null ? " (sin devolver)" : ", devuelto el " + fechaDevolucion);
    }
}
