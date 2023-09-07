package Co2RechnerFertig.src.application;
enum FahrzeugArt {Kleinwage,MittelKlasse,OberKlasse,KfZ,Elektrofahrzeug,Motorrad,Roller,Elektroroller};
enum Kraftstoff{Benzin,Diesel,Erdgas,Fluessiggas};
enum verkehrsmittel {Eigene,Carsharing,Fernverkehr,SonstigerBahnfernverkehr,ZugNahverkehr,OPNV,Reisebus,Fahrrad,};
public class Mobilitaet {

private String FahrzeugBezeichnung;
private double Durchschnittsverbrauch,fahrleistung;
private int fahrzeugeAlter,fahrtenAnzahl;
private long distanz;
public static void main(String[] args) {
	Fahrzeugs Fahrzeug = Fahrzeugs.Kleinwage;
	
	switch(Fahrzeug){
	case Kleinwage:
		System.out.println("Kleinwage");
        break;
    case MittelKlasse:
    	System.out.println("Mittelklasse");
        break;
    case OberKlasse:	
    	System.out.println("Oberklasse");
        break;
    case KfZ:
    	System.out.println("KfZ");
        break;
    case Elektrofahrzeug:
    	System.out.println("Elektrofahrzeug");
        break;
    case Motorrad:
    	System.out.println("Motorrad");
        break;
    case Roller:
    	System.out.println("Roller");
        break;
    case Elektroroller:
    	System.out.println("Elektroroller");
        break;
        
	
	
	}
	
}

}
