package Datenbank;

public class Kaufverhalten {
	private int kaufverhaltenID;
	private double sparsam;
	private double durchschnittlich;
	private double groszügig;
	
	public Kaufverhalten(int kaufverhaltenID, double sparsam, double durchschnittlich, double groszügig) {
		super();
		this.kaufverhaltenID = kaufverhaltenID;
		this.sparsam = sparsam;
		this.durchschnittlich = durchschnittlich;
		this.groszügig = groszügig;
	}
	
	public int getKaufverhaltenID() {
		return kaufverhaltenID;
	}
	
	public double getSparsam() {
		return sparsam;
	}
	
	public double getDurchschnittlich() {
		return durchschnittlich;
	}
	
	public double getGroszügig() {
		return groszügig;
	}
}
