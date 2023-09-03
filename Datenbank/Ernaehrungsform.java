package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Ernaehrungsform
 * @author R. Gizem Yaylar
 */

public class Ernaehrungsform {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int ernaehrungsID;
	private double fleischbetont;
	private double mischkost;
	private double fleischreduziert;
	private double vegetarisch;
	private double vegan;
		
	public Ernaehrungsform(int ernaehrungsID, double fleischbetont, double mischkost, double fleischreduziert, double vegetarisch, double vegan) {
		super();
		this.ernaehrungsID = ernaehrungsID;
		this.fleischbetont = fleischbetont;
		this.mischkost = mischkost;
		this.fleischreduziert = fleischreduziert;
		this.vegetarisch = vegetarisch;
		this.vegan = vegan;
	}
		
	//Die get-Methode gibt den Variablenwert  zur Verwendung zurueck
	public int getErnaehrungsID() {
		return ernaehrungsID;
	}
		
	public double getFleischbetont() {
		return fleischbetont;
	}
		
	public double getMischkost() {
		return mischkost;
	}
		
	public double getFlesichreduziert() {
		return fleischreduziert;
	}
		
	public double getVegetarisch() {
		return vegetarisch;
	}
		
	public double getVegan() {
		return vegan;
	}
}

