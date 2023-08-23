package Datenbank;

public class Haustiere {
	private int haustiereID;
	private double hund;
	private double katze;
	private double pferd;
	private double zierv�gel;
	
	public Haustiere(int haustiereID, double hund, double katze, double pferd, double zierv�gel) {
		super();
		this.haustiereID = haustiereID;
		this.hund = hund;
		this.katze = katze;
		this.pferd = pferd;
		this.zierv�gel = zierv�gel;
	}
	
	public int getHaustiereID() {
		return haustiereID;
	}
	
	public double getHund() {
		return hund;
	}
	
	public double getKatze() {
		return katze;
	}
	
	public double getPferd() {
		return pferd;
	}
	
	public double getZierv�gel() {
		return zierv�gel;
	}
	
}
