package Datenbank;

public class Tabakwaren {
	private int tabakwarenID;
	private double ja;
	private double nein;

	public Tabakwaren(int tabakwarenID, double ja, double nein) {
		super();
		this.tabakwarenID = tabakwarenID;
		this.ja = ja;
		this.nein = nein;
	}
	
	public int getTabakwarenID() {
		return tabakwarenID;
	}
	
	public double getJa() {
		return ja;
	}
	
	public double getNein() {
		return nein;
	}
}
