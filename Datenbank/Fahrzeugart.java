package Datenbank;

public class Fahrzeugart {
	private int fahrzeugartID;
	private double kleinwagen;
	private double mittelklasse;
	private double oberklasse;
	
	public Fahrzeugart(int fahrzeugartID, double kleinwagen, double mittelklasse, double oberklasse) {
		super();
		this.fahrzeugartID = fahrzeugartID;
		this.kleinwagen = kleinwagen;
		this.mittelklasse = mittelklasse;
		this.oberklasse = oberklasse;
	}
	
	public int getFahrzeugartID() {
		return fahrzeugartID;
	}
	
	public double getKleinwagen() {
		return kleinwagen;
	}
	
	public double getMittelklasse() {
		return mittelklasse;
	}
	
	public double getOberklasse() {
		return oberklasse;
	}
}
