package Datenbank;
import java.sql.*;

/**
 * Diese Klasse speichert die vom Benutzer empfangenen Daten in der Tabelle Gewicht
 * @author R. Gizem Yaylar
 */

public class Gewicht {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	public void Einfuegen(int gewichtID, int gewicht) {
		try {
			//Alle Daten werden in die gleichnamige Spalte der Tabelle geschrieben
			String einfuegen = "INSERT INTO alter(alterID, alter) VALUES (" + gewichtID + ",'" + gewicht + ");";
			Statement stm = con.createStatement();
			stm.execute(einfuegen);
			System.out.println("Gewicht erfolgreich gespeichert.");
			} catch (SQLException e) {
				//Fehlermeldung bei der Speicherung
				e.printStackTrace();
				System.out.println("Beim Speichern ist ein Fehler aufgetreten.");
			}
	}
}
