package gal.dam.biblioteca.modelo;

import java.time.LocalDate;

/**
 * Un socio de la biblioteca (tabla socio). tipo vale "ESTUDIANTE" o "DOCENTE".
 * 
 */
public class Socio {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String tipo;
    private LocalDate fechaAlta;

    public Socio(int id, String nombre, String apellidos, String email, String tipo, LocalDate fechaAlta) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.tipo = tipo;
        this.fechaAlta = fechaAlta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return id + ": " + nombre + " " + apellidos + " <" + email + "> " + tipo;
    }
}
