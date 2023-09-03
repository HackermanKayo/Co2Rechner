package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Hoteluebernachtung
 * @author R. Gizem Yaylar
 */

public class Hoteluebernachtung {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int hoteluebernachtungID;
	private double zweiWochenImJahr;
	private double vierWochenImJahr;
	private double mehrWochenImJahr;
	
	public Hoteluebernachtung(int hoteluebernachtungID, double zweiWochenImJahr, double vierWochenImJahr, double mehrWochenImJahr) {
		super();
		this.hoteluebernachtungID = hoteluebernachtungID;
		this.zweiWochenImJahr = zweiWochenImJahr;
		this.vierWochenImJahr = vierWochenImJahr;
		this.mehrWochenImJahr = mehrWochenImJahr;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurueck
	public int getHoteluebernachtungID() {
		return hoteluebernachtungID;
	}
	
	public double getZweiWochenImJahr() {
		return zweiWochenImJahr;
	}
	
	public double getVierWochenJahr() {
		return vierWochenImJahr;
	}
	
	public double getMehrWochenImJahr() {
		return mehrWochenImJahr;
	}
}
