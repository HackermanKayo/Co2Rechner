package Datenbank;

public class Hotel�bernachtung {
	private int hotel�bernachtungID;
	private double zweiWochenJahr;
	private double vierWochenJahr;
	private double mehrWochenJahr;
	
	public Hotel�bernachtung(int hotel�bernachtungID, double zweiWochenJahr, double vierWochenJahr, double mehrWochenJahr) {
		super();
		this.hotel�bernachtungID = hotel�bernachtungID;
		this.zweiWochenJahr = zweiWochenJahr;
		this.vierWochenJahr = vierWochenJahr;
		this.mehrWochenJahr = mehrWochenJahr;
	}
	
	public int getHotel�bernachtungID() {
		return hotel�bernachtungID;
	}
	
	public double getZweiWochenJahr() {
		return zweiWochenJahr;
	}
	
	public double getVierWochenJahr() {
		return vierWochenJahr;
	}
	
	public double getMehrWochenJahr() {
		return mehrWochenJahr;
	}
	
}
