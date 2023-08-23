package Datenbank;

public class Strombezug {
	private int stromID;
	private double strommix;
	private double oekostrom;
	
	public Strombezug(int stromID, double strommix, double oekostrom) {
		super();
		this.stromID = stromID;
		this.strommix = strommix;
		this.oekostrom = oekostrom;
	}
	
	public int getStromID() {
		return stromID;
	}
	
	public double getStrommix() {
		return strommix;
	}
	
	public double getOekostrom() {
		return oekostrom;
	}
}
