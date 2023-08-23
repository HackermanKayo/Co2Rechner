package Datenbank;

public class Einkaufsverhalten {
	private int einkaufsverhaltenID;
	private double supermarktsortiement;
	private double regionaleProdukte;
	
	public Einkaufsverhalten(int einkaufsverhaltenID,double supermarktsortiement, double regionaleProdukte) {
		super();
		this.einkaufsverhaltenID = einkaufsverhaltenID;
		this.supermarktsortiement = supermarktsortiement;
		this.regionaleProdukte = regionaleProdukte;
	}
	
	public int getEinkaufsverhaltenID() {
		return einkaufsverhaltenID;
	}
	
	public double getSupermarktsortiement() {
		return supermarktsortiement;
	}
	
	public double getRegionaleProdukte() {
		return regionaleProdukte;
	}
	
}
