package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Tiefkuehlkost
 * @author R. Gizem Yaylar
 */

public class Tiefkuehlkost {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int tiefkuehlkostID;
	private double gelegentlich;
	private double dreiMalProWoche;
	private double taeglich;
	
	public Tiefkuehlkost(int tiefkuehlkostID, double gelegentlich, double dreiMalProWoche, double taeglich) {
		super();
		this.tiefkuehlkostID = tiefkuehlkostID;
		this.gelegentlich = gelegentlich;
		this.dreiMalProWoche = dreiMalProWoche;
		this.taeglich = taeglich;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurück
	public int getTiefkuehlkostID() {
		return tiefkuehlkostID;
	}
	
	public double getGelegentlich() {
		return gelegentlich;
	}
	
	public double getdreiMalProWoche() {
		return dreiMalProWoche;
	}
	
	public double getTaeglich() {
		return taeglich;
	}
}
