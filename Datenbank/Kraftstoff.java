package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Kraftstoff
 * @author R. Gizem Yaylar
 */

public class Kraftstoff {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int kraftstoffID;
	private double benzin;
	private double diesel;
	private double gas;
	private double strom;
	
	public Kraftstoff(int kraftstoffID, double benzin, double diesel, double gas, double strom) {
		super();
		this.kraftstoffID = kraftstoffID;
		this.benzin = benzin;
		this.diesel = diesel;
		this.gas = gas;
		this.strom = strom;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurueck
	public int getKraftstoffID() {
		return kraftstoffID;
	}
	
	public double getBenzin() {
		return benzin;
	}
	
	public double getDiesel() {
		return diesel;
	}
	
	public double getGas() {
		return gas;
	}
	
	public double getStrom() {
		return strom;
	}
}
