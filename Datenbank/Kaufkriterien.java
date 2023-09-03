package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Kaufkriterien
 * @author R. Gizem Yaylar
 */

public class Kaufkriterien {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int kaufkriterienID;
	private double langlebig;
	private double funtionalitaet;
	private double guenstig;
	
	public Kaufkriterien(int kaufkriterienID, double langlebig, double funtionalitaet, double guenstig) {
		super();
		this.kaufkriterienID = kaufkriterienID;
		this.langlebig = langlebig;
		this.funtionalitaet = funtionalitaet;
		this.guenstig = guenstig;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurueck
	public int getKaufkriterienID() {
		return kaufkriterienID;
	}
	
	public double getLanglebig() {
		return langlebig;
	}
	
	public double getFuntionalitaet() {
		return funtionalitaet;
	}
	
	public double getGuenstig() {
		return guenstig;
	}
}
