package Datenbank;

public class Ernaehrungsform {
		private int ernaehrungsID;
		private double fleischbetont;
		private double mischkost;
		private double fleischreduziert;
		private double vegetarisch;
		private double vegan;
		
		public Ernaehrungsform(int ernaehrungsID, double fleischbetont, double mischkost, double fleischreduziert, double vegetarisch, double vegan) {
			super();
			this.ernaehrungsID = ernaehrungsID;
			this.fleischbetont = fleischbetont;
			this.mischkost = mischkost;
			this.fleischreduziert = fleischreduziert;
			this.vegetarisch = vegetarisch;
			this.vegan = vegan;
		}
		
		public int getErnaehrungsID() {
			return ernaehrungsID;
		}
		
		public double getFleischbetont() {
			return fleischbetont;
		}
		
		public double getMischkost() {
			return mischkost;
		}
		
		public double getFlesichreduziert() {
			return fleischreduziert;
		}
		
		public double getVegetarisch() {
			return vegetarisch;
		}
		
		public double getVegan() {
			return vegan;
		}
	}

