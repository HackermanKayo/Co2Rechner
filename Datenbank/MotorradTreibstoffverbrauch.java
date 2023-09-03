package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle MotorradTreibstoffverbrauch
 * @author R. Gizem Yaylar
 */

public class MotorradTreibstoffverbrauch {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int motorradTreibstoffverbrauchID;
	private double dreiLiter;
	private double fuenfLiter;
	private double siebenLiter;
	private double mehrLiter;
	
	public MotorradTreibstoffverbrauch(int motorradTreibstoffverbrauchID, double dreiLiter, double fuenfLiter, double siebenLiter, double mehrLiter) {
		super();
		this.motorradTreibstoffverbrauchID = motorradTreibstoffverbrauchID;
		this.dreiLiter = dreiLiter;
		this.fuenfLiter = fuenfLiter;
		this.siebenLiter = siebenLiter;
		this.mehrLiter = mehrLiter;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurueck
	public int getMotorradTreibstoffverbrauchID() {
		return motorradTreibstoffverbrauchID;
	}
	
	public double getDreiLiter() {
		return dreiLiter;
	}
	
	public double getFuenfLiter() {
		return fuenfLiter;
	}
	
	public double getSiebenLiter() {
		return siebenLiter;
	}
	
	public double getMehrLiter() {
		return mehrLiter;
	}
}
