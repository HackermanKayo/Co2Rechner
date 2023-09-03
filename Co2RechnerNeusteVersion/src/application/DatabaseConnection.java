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

    public Connection getDatabaseConnection() {
    	try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Verbindung zur Datenbank hergestellt.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return connection;
    }


}


