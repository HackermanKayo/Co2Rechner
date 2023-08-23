package Datenbank;

public class MotorradTreibstoffverbrauch {
	private int motorradTreibstoffverbrauchID;
	private double dreiLiter;
	private double fünfLiter;
	private double siebenLiter;
	private double mehrLiter;
	
	public MotorradTreibstoffverbrauch(int motorradTreibstoffverbrauchID, double dreiLiter, double fünfLiter, double siebenLiter, double mehrLiter) {
		super();
		this.motorradTreibstoffverbrauchID = motorradTreibstoffverbrauchID;
		this.dreiLiter = dreiLiter;
		this.fünfLiter = fünfLiter;
		this.siebenLiter = siebenLiter;
		this.mehrLiter = mehrLiter;
	}
	
	public int getMotorradTreibstoffverbrauchID() {
		return motorradTreibstoffverbrauchID;
	}
	
	public double getDreiLiter() {
		return dreiLiter;
	}
	
	public double getFünfLiter() {
		return fünfLiter;
	}
	
	public double getSiebenLiter() {
		return siebenLiter;
	}
	
	public double getMehrLiter() {
		return mehrLiter;
	}
}
