package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Einkaufsverhalten
 * @author R. Gizem Yaylar
 */

public class Einkaufsverhalten {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int einkaufsverhaltenID;
	private double supermarktsortiement;
	private double regionaleProdukte;
	
	public Einkaufsverhalten(int einkaufsverhaltenID,double supermarktsortiement, double regionaleProdukte) {
		super();
		this.einkaufsverhaltenID = einkaufsverhaltenID;
		this.supermarktsortiement = supermarktsortiement;
		this.regionaleProdukte = regionaleProdukte;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurueck
	public int getEinkaufsverhaltenID() {
		return einkaufsverhaltenID;
	}
	
	public double getSupermarktsortiement() {
		return supermarktsortiement;
	}
	
	public double getRegionaleProdukte() {
		return regionaleProdukte;
	}
	
}
