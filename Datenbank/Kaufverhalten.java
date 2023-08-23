package Datenbank;

public class Kaufverhalten {
	private int kaufverhaltenID;
	private double sparsam;
	private double durchschnittlich;
	private double grosz�gig;
	
	public Kaufverhalten(int kaufverhaltenID, double sparsam, double durchschnittlich, double grosz�gig) {
		super();
		this.kaufverhaltenID = kaufverhaltenID;
		this.sparsam = sparsam;
		this.durchschnittlich = durchschnittlich;
		this.grosz�gig = grosz�gig;
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
	
	public double getGrosz�gig() {
		return grosz�gig;
	}
}
