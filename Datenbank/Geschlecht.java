package Datenbank;
import java.sql.*;

/**
 * Diese Klasse speichert die vom Benutzer empfangenen Daten in der Tabelle Geschlecht
 * @author R. Gizem Yaylar
 */

enum Geschlecht {
	 Weiblich,
	 Maennlich,
	 Divers;
}

public class Geschlecht {
	
	Connection con;	 //Ruft die bereit Datenverbindung auf.
			 
	public void Einfuegen(enum geschlecht) {
		try {
			
			//Alle Daten werden in die gleichnamige Spalte der Tabelle geschrieben....
		// !!!! hier muss noch die insert kod geschrieben werden. jetzt funktioniert es mit enum nicht !!!!
			
			
			Statement stm = connection.createStatement();
			stm.execute(einfuegen);
			System.out.println("Alter erfolgreich gespeichert.");
			} catch (SQLException e) {
				//Fehlermeldung bei der Speicherung
				e.printStackTrace();
				System.out.println("Beim Speichern ist ein Fehler aufgetreten.");
			}
	}
}
	
