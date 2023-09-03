package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
	
    private Connection connection;
    String jdbcUrl = "jdbc:mysql://localhost:3306/co2rechner";
    String user = "host";
    String password = "1234";

    public static Connection getDatabaseConnection() {
        try {
            // Verbindung zur Datenbank herstellen und zurückgeben
            String dbUrl = "jdbc:mysql://localhost:3306/co2rechner";
            String dbUser = "host";
            String dbPassword = "1234";
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Behandlung von Verbindungsfehlern
        }
    }


}


