package Datenbank;

public class Haustiere {
	private int haustiereID;
	private double hund;
	private double katze;
	private double pferd;
	private double ziervögel;
	
	public Haustiere(int haustiereID, double hund, double katze, double pferd, double ziervögel) {
		super();
		this.haustiereID = haustiereID;
		this.hund = hund;
		this.katze = katze;
		this.pferd = pferd;
		this.ziervögel = ziervögel;
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
	
	public double getZiervögel() {
		return ziervögel;
	}
	
}
