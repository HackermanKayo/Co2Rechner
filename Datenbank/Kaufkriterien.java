package Datenbank;

public class Kaufkriterien {
	private int kaufkriterienID;
	private double langlebig;
	private double funtionalitaet;
	private double günstig;
	
	public Kaufkriterien(int kaufkriterienID, double langlebig, double funtionalitaet, double günstig) {
		super();
		this.kaufkriterienID = kaufkriterienID;
		this.langlebig = langlebig;
		this.funtionalitaet = funtionalitaet;
		this.günstig = günstig;
	}
	
	public int getKaufkriterienID() {
		return kaufkriterienID;
	}
	
	public double getLanglebig() {
		return langlebig;
	}
	
	public double getFuntionalitaet() {
		return funtionalitaet;
	}
	
	public double getGünstig() {
		return günstig;
	}
}
