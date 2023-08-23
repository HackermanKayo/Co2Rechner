package Datenbank;

public class Tiefkühlkost {
	private int tiefkühlkostID;
	private double gelegentlich;
	private double dreiMalProWoche;
	private double taeglich;
	
	public Tiefkühlkost(int tiefkühlkostID, double gelegentlich, double dreiMalProWoche, double taeglich) {
		super();
		this.tiefkühlkostID = tiefkühlkostID;
		this.gelegentlich = gelegentlich;
		this.dreiMalProWoche = dreiMalProWoche;
		this.taeglich = taeglich;
	}
	
	public int getTiefkühlkostID() {
		return tiefkühlkostID;
	}
	
	public double getGelegentlich() {
		return gelegentlich;
	}
	
	public double getdreiMalProWoche() {
		return dreiMalProWoche;
	}
	
	public double getTaeglich() {
		return taeglich;
	}
}
