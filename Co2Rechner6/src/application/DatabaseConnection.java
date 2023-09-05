package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    String jdbcUrl = "jdbc:mysql://localhost:3306/co2rechner";
    String user = "host";
    String password = "1234";

    public Connection getDatabaseConnection() {
        Connection connection = null;
        try {
            // Verbindung zur Datenbank herstellen und zurückgeben
            connection = DriverManager.getConnection(jdbcUrl, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // Hier sollte eine Behandlung von Verbindungsfehlern erfolgen
        }
        return connection;
    }
}
