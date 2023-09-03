package Datenbank;
import java.sql.*;

/**
 * Diese Klasse speichert die vom Benutzer empfangenen Daten in der Tabelle Alter
 * @author R. Gizem Yaylar
 */

public class Alter {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	public void Einfuegen(int alterID, int alter) {
		try {
			//Alle Daten werden in die gleichnamige Spalte der Tabelle geschrieben
			String einfuegen = "INSERT INTO alter(alterID, alter) VALUES (" + alterID + ",'" + alter + ");"; 
			Statement stm = con.createStatement();
			stm.execute(einfuegen);
			System.out.println("Alter erfolgreich gespeichert.");
			} catch (SQLException e) {
				//Fehlermeldung bei der Speicherung
				e.printStackTrace();
				System.out.println("Beim Speichern ist ein Fehler aufgetreten.");
			}
	}
	
}