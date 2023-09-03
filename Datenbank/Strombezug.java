package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Strombezug
 * @author R. Gizem Yaylar
 */

public class Strombezug {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int stromID;
	private double strommix;
	private double oekostrom;
	
	public Strombezug(int stromID, double strommix, double oekostrom) {
		super();
		this.stromID = stromID;
		this.strommix = strommix;
		this.oekostrom = oekostrom;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurück
	public int getStromID() {
		return stromID;
	}
	
	public double getStrommix() {
		return strommix;
	}
	
	public double getOekostrom() {
		return oekostrom;
	}
}
