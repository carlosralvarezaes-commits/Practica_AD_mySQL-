package gal.dam.biblioteca.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public final class ConexionBD {

    private static final String FICHERO = "db.properties";
    private static final Properties config = new Properties();

    static {
        try (InputStream in = new FileInputStream(FICHERO)) {
            config.load(in);
        } catch (IOException ex) {
            throw new ExceptionInInitializerError(
                    "No se pudo leer " + FICHERO + " (¿está en la raíz del proyecto?): " + ex.getMessage());
        }
    }

    private ConexionBD() {
    }

    /**
     * Abre una conexión nueva. Quien la pide es responsable de cerrarla
     * (siempre con try-with-resources).
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(
                config.getProperty("db.url"),
                config.getProperty("db.user"),
                config.getProperty("db.password"));
    }
}
