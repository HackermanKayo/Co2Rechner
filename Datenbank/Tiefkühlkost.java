package Datenbank;

public class Tiefk�hlkost {
	private int tiefk�hlkostID;
	private double gelegentlich;
	private double dreiMalProWoche;
	private double taeglich;
	
	public Tiefk�hlkost(int tiefk�hlkostID, double gelegentlich, double dreiMalProWoche, double taeglich) {
		super();
		this.tiefk�hlkostID = tiefk�hlkostID;
		this.gelegentlich = gelegentlich;
		this.dreiMalProWoche = dreiMalProWoche;
		this.taeglich = taeglich;
	}
	
	public int getTiefk�hlkostID() {
		return tiefk�hlkostID;
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
