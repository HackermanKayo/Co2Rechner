package Datenbank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatenbankVerbindung {
	
	public static void main(String[] args) {
	String url = "http://localhost/phpmyadmin/index.php?route=/database/structure&db=co2emissionen";
    String user = "root@localhost";
    String pass = "";

    	try {
    		Connection con = DriverManager.getConnection(url, user, pass);
    		System.out.println("Verbindung erfolgreich hergestellt");

    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
	}
}
