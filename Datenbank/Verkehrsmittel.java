package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Verkehrsmittel
 * @author R. Gizem Yaylar
 */

public class Verkehrsmittel {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int verkehrsmittelID;
	private double bahn;
	private double bus;
	private double tram;
	private double flugzeugLangstrecke;
	private double flugzeugKurzstrecke;
	
	public Verkehrsmittel(int verkehrsmittelID, double bahn, double bus, double tram, double flugzeugLangstrecke, double flugzeugKurzstrecke) {
		super();
		this.verkehrsmittelID = verkehrsmittelID;
		this.bahn = bahn;
		this.bus = bus;
		this.tram = tram;
		this.flugzeugLangstrecke = flugzeugLangstrecke;
		this.flugzeugKurzstrecke = flugzeugKurzstrecke; 
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurück
	public int getVerkehrsmittelID() {
		return verkehrsmittelID;
	}
		
	public double getBahn() {
		return bahn;
	}
		
	public double getBus() {
		return  bus;
	}
		
	public double getTram() {
		return tram;
	}
	
	public double getFlugzeugLangstrecke() {
		return flugzeugLangstrecke;
	}
		
	public double getFlugzeugKurzstrecke() {
		return flugzeugKurzstrecke;
	}
}
