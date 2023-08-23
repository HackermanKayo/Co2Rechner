package Datenbank;

public class Sport {
	private int sportID;
	private double kein;
	private double wenig;
	private double viel;
	
	public Sport(int sportID, double kein, double wenig, double viel) {
		super();
		this.sportID = sportID;
		this.kein = kein;
		this.wenig = wenig;
		this.viel = viel;
	}
	
	public int getSportID() {
		return sportID;
	}
	
	public double getKein() {
		return kein;
	}
	
	public double getWenig() {
		return wenig;
	}
	
	public double getViel() {
		return viel;
	}
}
