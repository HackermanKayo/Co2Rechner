package Datenbank;

public class Verkehrsmittel {
	private int verkehrsmittelID;
	private double bahn;
	private double bus;
	private double tram;
	private double flugzeugLangstrecke;
	private double flugzeugKurzstrecke;
	
	public Verkehrsmittel(int verkehrsmittelID, double bahn, double bus, double tram, double flugzeugLangstrecke, double flugzeugKurzstrecke) {
		super();
		this.verkehrsmittelID = verkehrsmittelID;
		this.bahn = bahn;
		this.bus = bus;
		this.tram = tram;
		this.flugzeugLangstrecke = flugzeugLangstrecke;
		this.flugzeugKurzstrecke = flugzeugKurzstrecke; 
	}
	
		public int getVerkehrsmittelID() {
			return verkehrsmittelID;
		}
		
		public double getBahn() {
			return bahn;
		}
		
		public double getBus() {
			return  bus;
		}
		
		public double getTram() {
			return tram;
		}
		
		public double getFlugzeugLangstrecke() {
			return flugzeugLangstrecke;
		}
		
		public double getFlugzeugKurzstrecke() {
			return flugzeugKurzstrecke;
		}
}
