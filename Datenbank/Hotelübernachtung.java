package Datenbank;

public class Hotelübernachtung {
	private int hotelübernachtungID;
	private double zweiWochenJahr;
	private double vierWochenJahr;
	private double mehrWochenJahr;
	
	public Hotelübernachtung(int hotelübernachtungID, double zweiWochenJahr, double vierWochenJahr, double mehrWochenJahr) {
		super();
		this.hotelübernachtungID = hotelübernachtungID;
		this.zweiWochenJahr = zweiWochenJahr;
		this.vierWochenJahr = vierWochenJahr;
		this.mehrWochenJahr = mehrWochenJahr;
	}
	
	public int getHotelübernachtungID() {
		return hotelübernachtungID;
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
