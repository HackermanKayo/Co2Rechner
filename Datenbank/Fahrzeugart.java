package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Fahrzeugart
 * @author R. Gizem Yaylar
 */

public class Fahrzeugart {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int fahrzeugartID;
	private double kleinwagen;
	private double mittelklasse;
	private double oberklasse;
	
	public Fahrzeugart(int fahrzeugartID, double kleinwagen, double mittelklasse, double oberklasse) {
		super();
		this.fahrzeugartID = fahrzeugartID;
		this.kleinwagen = kleinwagen;
		this.mittelklasse = mittelklasse;
		this.oberklasse = oberklasse;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurueck
	public int getFahrzeugartID() {
		return fahrzeugartID;
	}
	
	public double getKleinwagen() {
		return kleinwagen;
	}
	
	public double getMittelklasse() {
		return mittelklasse;
	}
	
	public double getOberklasse() {
		return oberklasse;
	}
}
