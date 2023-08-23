package Datenbank;

public class Kaufkriterien {
	private int kaufkriterienID;
	private double langlebig;
	private double funtionalitaet;
	private double g�nstig;
	
	public Kaufkriterien(int kaufkriterienID, double langlebig, double funtionalitaet, double g�nstig) {
		super();
		this.kaufkriterienID = kaufkriterienID;
		this.langlebig = langlebig;
		this.funtionalitaet = funtionalitaet;
		this.g�nstig = g�nstig;
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
	
	public double getG�nstig() {
		return g�nstig;
	}
}
