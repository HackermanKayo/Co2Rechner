package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Heizung
 * @author R. Gizem Yaylar
 */

public class Heizung {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int heizungID;
	private double proPerson;

	public Heizung(int heizungID, double proPerson) {
		super();
		this.heizungID = heizungID;
		this.proPerson = proPerson;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurueck
	public int getHeizungID() {
		return heizungID;
	}
	
	public double getProPerson() {
		return proPerson;
	}
}
