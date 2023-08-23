package Datenbank;

public class Kraftstoff {
	private int kraftstoffID;
	private double benzin;
	private double diesel;
	private double gas;
	private double strom;
	
	public Kraftstoff(int kraftstoffID, double benzin, double diesel, double gas, double strom) {
		super();
		this.kraftstoffID = kraftstoffID;
		this.benzin = benzin;
		this.diesel = diesel;
		this.gas = gas;
		this.strom = strom;
	}
	
	public int getKraftstoffID() {
		return kraftstoffID;
	}
	
	public double getBenzin() {
		return benzin;
	}
	
	public double getDiesel() {
		return diesel;
	}
	
	public double getGas() {
		return gas;
	}
	
	public double getStrom() {
		return strom;
	}
}
