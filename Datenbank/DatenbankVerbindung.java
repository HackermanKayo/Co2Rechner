package Datenbank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Diese Klasse sorgt fuer die Verbindung mit der Datenbank.
 * @author R. Gizem Yaylar
 */
public class DatenbankVerbindung {
	
	private boolean update;
    private String query;
    private int tabelleId;

    private static Connection connection; 
 
    public static Connection connect() {

        //Datenbank Logindaten
        String url="jdbc:mysql://localhost:3306/co2emmissionen";
        String username="root@localhost";
        String password="";
        try {

            //Installiert den Datenbanktreiber
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();
            System.out.println("Verbindung Erfolgreich.");

        }
        //Fehlermeldung beir der Verbindung
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Verbindung fehlgeschlagen."); 
        }
      return connect;
    }
}
