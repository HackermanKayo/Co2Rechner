package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Sport
 * @author R. Gizem Yaylar
 */

public class Sport {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int sportID;
	private double kein;
	private double wenig;
	private double viel;
	
	public Sport(int sportID, double kein, double wenig, double viel) {
		super();
		this.sportID = sportID;
		this.kein = kein;
		this.wenig = wenig;
		this.viel = viel;
	}
	
	//Die get-Methode gibt den Variablenwert  zur Verwendung zurück
	public int getSportID() {
		return sportID;
	}
	
	public double getKein() {
		return kein;
	}
	
	public double getWenig() {
		return wenig;
	}
	
	public double getViel() {
		return viel;
	}
}
