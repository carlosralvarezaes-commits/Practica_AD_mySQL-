package gal.dam.biblioteca;

import gal.dam.biblioteca.dao.DAO;
import gal.dam.biblioteca.dao.LibroDAO;
import gal.dam.biblioteca.dao.PrestamoDAO;
import gal.dam.biblioteca.dao.SocioDAO;
import gal.dam.biblioteca.dao.mysql.GeneroDAOMySQL;
import gal.dam.biblioteca.dao.mysql.LibroDAOMySQL;
import gal.dam.biblioteca.dao.mysql.PrestamoDAOMySQL;
import gal.dam.biblioteca.dao.mysql.SocioDAOMySQL;
import gal.dam.biblioteca.modelo.Genero;
import gal.dam.biblioteca.servicio.CatalogoService;
import gal.dam.biblioteca.servicio.PrestamoService;
import gal.dam.biblioteca.servicio.SocioService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;


public class App {

    /** Fecha de referencia del enunciado para las consultas de vencimientos. */
    private static final LocalDate FECHA_REF = LocalDate.of(2026, 10, 15);

    public static void main(String[] args) {
        // 1. DAO: se crea una sola instancia de cada uno (no guardan estado)
        LibroDAO libroDAO = new LibroDAOMySQL();
        SocioDAO socioDAO = new SocioDAOMySQL();
        PrestamoDAO prestamoDAO = new PrestamoDAOMySQL();
        DAO<Genero, Integer> generoDAO = new GeneroDAOMySQL();

        // 2. Servicios: reciben los DAO por el constructor (como interfaces)
        CatalogoService catalogo = new CatalogoService(libroDAO, generoDAO);
        SocioService socios = new SocioService(socioDAO, libroDAO, prestamoDAO);
        PrestamoService prestamos = new PrestamoService(prestamoDAO, libroDAO, socioDAO);

        probarEjemplo(catalogo);
        probarBloqueA(catalogo, socios, prestamos);
        probarBloqueB(catalogo, socios, prestamos);
        probarBloqueC(socios);
    }

    private static void probarEjemplo(CatalogoService catalogo) {
        prueba("Ejemplo · Géneros por nombre", catalogo::listarGeneros);
        prueba("Ejemplo · Género 5", () -> catalogo.buscarGenero(5));
    }

    private static void probarBloqueA(CatalogoService catalogo, SocioService socios, PrestamoService prestamos) {
        prueba("A.1 · Libros por título", catalogo::listarLibros);
        prueba("A.1 · Libro 4", () -> catalogo.buscarLibro(4));
        prueba("A.1 · Libro 99", () -> catalogo.buscarLibro(99));
        prueba("A.2 · Socios por apellidos", socios::listarSocios);
        prueba("A.2 · Socio 10", () -> socios.buscarSocio(10));
        prueba("A.3 · Préstamos por fecha", prestamos::listarPrestamos);
        prueba("A.3 · Préstamo 34", () -> prestamos.buscarPrestamo(34));
    }

    private static void probarBloqueB(CatalogoService catalogo, SocioService socios, PrestamoService prestamos) {
        prueba("B.1 · Título contiene \"fundacion\"", () -> catalogo.buscarPorTitulo("fundacion"));
        prueba("B.1 · Título contiene \"de\"", () -> catalogo.buscarPorTitulo("de"));
        prueba("B.2 · Libros de \"gaiman\"", () -> catalogo.librosDeAutor("gaiman"));
        prueba("B.3 · Autores del libro 8", () -> catalogo.autoresDe(8));
        prueba("B.3 · Autores del libro 13", () -> catalogo.autoresDe(13));
        prueba("B.4 · Socio marta.rey@biblio.gal", () -> socios.buscarPorEmail("marta.rey@biblio.gal"));
        prueba("B.4 · Socio nadie@biblio.gal", () -> socios.buscarPorEmail("nadie@biblio.gal"));
        prueba("B.5 · Préstamos activos del socio 1", () -> prestamos.activosDeSocio(1));
        prueba("B.5 · Préstamos activos del socio 10", () -> prestamos.activosDeSocio(10));
        prueba("B.6 · Libros de ciencia ficción (2) leídos por el socio 1",
                () -> socios.librosLeidosDeGenero(1, 2));
        prueba("B.7 · Libros nunca prestados", catalogo::nuncaPrestados);
        prueba("B.8 · Socios con préstamos vencidos a " + FECHA_REF, () -> socios.conPrestamosVencidos(FECHA_REF));
        prueba("B.9 · Historial del socio 3", () -> prestamos.historial(3));
        prueba("B.10 · Préstamos vencidos a " + FECHA_REF, () -> prestamos.vencidos(FECHA_REF));
        prueba("B.11 · Disponibilidad", catalogo::disponibilidad);
        prueba("B.12 · Más prestado de cada género", catalogo::masPrestadoPorGenero);
        prueba("B.13 · Lectores de todo el autor 3", () -> socios.lectoresDeTodoElAutor(3));
        prueba("B.13 · Lectores de todo el autor 9", () -> socios.lectoresDeTodoElAutor(9));
        prueba("B.14 · Lectores por encima de la media", socios::lectoresSobreLaMedia);
        prueba("B.15 · Resumen por género", prestamos::resumenPorGenero);
    }

    private static void probarBloqueC(SocioService socios) {
        prueba("C.2 · Ficha del socio 1", () -> socios.fichaSocio(1));
        prueba("C.2 · Ficha del socio 99", () -> socios.fichaSocio(99));
    }

    // ------------------------------------------------------------------
    // Utilidades para mostrar resultados (no hace falta tocarlas)
    // ------------------------------------------------------------------

    @FunctionalInterface
    private interface Prueba {
        Object ejecutar() throws SQLException;
    }

    private static void prueba(String titulo, Prueba prueba) {
        System.out.println("\n=== " + titulo + " ===");
        try {
            mostrar(prueba.ejecutar());
        } catch (UnsupportedOperationException ex) {
            System.out.println("  (pendiente: " + ex.getMessage() + ")");
        } catch (SQLException ex) {
            System.out.println("  ERROR de BD [SQLState " + ex.getSQLState() + " / código "
                    + ex.getErrorCode() + "]: " + ex.getMessage());
        } catch (RuntimeException ex) {
            System.out.println("  " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
    }

    private static void mostrar(Object resultado) {
        if (resultado instanceof Collection<?> lista) {
            if (lista.isEmpty()) {
                System.out.println("  (lista vacía)");
            }
            lista.forEach(e -> System.out.println("  " + e));
            System.out.println("  -> " + lista.size() + " resultado(s)");
        } else if (resultado instanceof Optional<?> opt) {
            System.out.println(opt.map(o -> "  " + o).orElse("  (Optional vacío)"));
        } else {
            System.out.println("  " + resultado);
        }
    }
}
