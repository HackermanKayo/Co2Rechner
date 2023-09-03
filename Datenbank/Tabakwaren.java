package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Tabakwaren
 * @author R. Gizem Yaylar
 */

public class Tabakwaren {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int tabakwarenID;
	private double ja;
	private double nein;

	public Tabakwaren(int tabakwarenID, double ja, double nein) {
		super();
		this.tabakwarenID = tabakwarenID;
		this.ja = ja;
		this.nein = nein;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurück
	public int getTabakwarenID() {
		return tabakwarenID;
	}
	
	public double getJa() {
		return ja;
	}
	
	public double getNein() {
		return nein;
	}
}
