package Datenbank;

import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Kaufverhalten
 * @author R. Gizem Yaylar
 */

public class Kaufverhalten {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int kaufverhaltenID;
	private double sparsam;
	private double durchschnittlich;
	private double groszügig;
	
	public Kaufverhalten(int kaufverhaltenID, double sparsam, double durchschnittlich, double groszügig) {
		super();
		this.kaufverhaltenID = kaufverhaltenID;
		this.sparsam = sparsam;
		this.durchschnittlich = durchschnittlich;
		this.groszügig = groszügig;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurueck
	public int getKaufverhaltenID() {
		return kaufverhaltenID;
	}
	
	public double getSparsam() {
		return sparsam;
	}
	
	public double getDurchschnittlich() {
		return durchschnittlich;
	}
	
	public double getGroszügig() {
		return groszügig;
	}
}
