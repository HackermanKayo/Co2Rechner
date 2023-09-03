package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Haustiere
 * @author R. Gizem Yaylar
 */

public class Haustiere {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int haustiereID;
	private double hund;
	private double katze;
	private double pferd;
	private double ziervoegel;
	
	public Haustiere(int haustiereID, double hund, double katze, double pferd, double ziervoegel) {
		super();
		this.haustiereID = haustiereID;
		this.hund = hund;
		this.katze = katze;
		this.pferd = pferd;
		this.ziervoegel = ziervoegel;
	}
	
	//Die get-Methode gibt den Variablenwert  zur Verwendung zurueck
	public int getHaustiereID() {
		return haustiereID;
	}
	
	public double getHund() {
		return hund;
	}
	
	public double getKatze() {
		return katze;
	}
	
	public double getPferd() {
		return pferd;
	}
	
	public double getZiervoegel() {
		return ziervoegel;
	}
	
}
