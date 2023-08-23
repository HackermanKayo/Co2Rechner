package Datenbank;

public class MotorradTreibstoffverbrauch {
	private int motorradTreibstoffverbrauchID;
	private double dreiLiter;
	private double f�nfLiter;
	private double siebenLiter;
	private double mehrLiter;
	
	public MotorradTreibstoffverbrauch(int motorradTreibstoffverbrauchID, double dreiLiter, double f�nfLiter, double siebenLiter, double mehrLiter) {
		super();
		this.motorradTreibstoffverbrauchID = motorradTreibstoffverbrauchID;
		this.dreiLiter = dreiLiter;
		this.f�nfLiter = f�nfLiter;
		this.siebenLiter = siebenLiter;
		this.mehrLiter = mehrLiter;
	}
	
	public int getMotorradTreibstoffverbrauchID() {
		return motorradTreibstoffverbrauchID;
	}
	
	public double getDreiLiter() {
		return dreiLiter;
	}
	
	public double getF�nfLiter() {
		return f�nfLiter;
	}
	
	public double getSiebenLiter() {
		return siebenLiter;
	}
	
	public double getMehrLiter() {
		return mehrLiter;
	}
}
