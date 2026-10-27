package gal.dam.biblioteca.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Operaciones comunes a todos los DAO.
 *
 * @param <T> tipo de la entidad (Libro, Socio...)
 * @param <K> tipo de su clave primaria (Integer en esta práctica)
 *
 * Todos los métodos reciben la conexión como primer parámetro: el DAO la usa,
 * pero no la abre ni la cierra. Así varias operaciones de varios DAO pueden
 * compartir la misma conexión (y, en la parte 2, la misma transacción).
 *
 * 
 */
public interface DAO<T, K> {

    /** Devuelve la entidad con esa clave, u Optional.empty() si no existe. */
    Optional<T> buscarPorId(Connection con, K id) throws SQLException;

    /** Devuelve todas las entidades, en el orden que indica el enunciado. */
    List<T> listarTodos(Connection con) throws SQLException;

    // ---- Parte 2 Próximamente ----
    // void insertar(Connection con, T t) throws SQLException;
    // boolean actualizar(Connection con, T t) throws SQLException;
    // boolean eliminar(Connection con, K id) throws SQLException;
}
