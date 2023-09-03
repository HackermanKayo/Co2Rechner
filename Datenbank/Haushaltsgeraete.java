package Datenbank;
import java.sql.Connection;

/**
 * Diese Klasse erhaelt die Daten aus der Tabelle Haushaltsgeraete
 * @author R. Gizem Yaylar
 */

public class Haushaltsgeraete {
	
	Connection con; //Ruft die bereit Datenverbindung auf
	
	//Erstellt Variablen mit demselben Namen wie die Daten in der Tabelle
	private int haushaltsgeraeteID;
	private int sparsamerEHerd;
	private int alterEHerd;
	private int sparsamerKuehlschrank;
	private int alterKuehlschrank;
	private int sparsameWaschmaschine;
	private int alteWaschmaschine;
	private int sparsamerFernseher;
	private int alterFenseher;
	private int computer;
	private int sparsamerGeschirrspueler;
	private int alterGeschirrspueler;
	private int sparsameBeleuchtung;
	private int alteBeleuchtung;
	private int sparsamerGefrierschrank;
	private int alterGefrierschrank;
	private int waeschtrockner;
	private int dslRouter;
	private int mobiltelefon;
	
	public Haushaltsgeraete(int haushaltsgeraeteID, int sparsamerEHerd, int alterEHerd, int sparsamerKuehlschrank, int alterKuehlschrank, 
			                int sparsameWaschmaschine, int alteWaschmaschine, int sparsamerFernseher, int alterFenseher, int computer, 
			                int sparsamerGeschirrspueler, int alterGeschirrspueler, int sparsameBeleuchtung, int alteBeleuchtung,
			                int sparsamerGefrierschrank, int alterGefrierschrank, int waeschtrockner, int dslRouter, int mobiltelefon) {
		super();
		this.haushaltsgeraeteID = haushaltsgeraeteID;
		this.sparsamerEHerd = sparsamerEHerd;
		this.alterEHerd = alterEHerd;
		this.sparsamerKuehlschrank = sparsamerKuehlschrank;
		this.alterKuehlschrank = alterKuehlschrank;
		this.sparsameWaschmaschine = sparsameWaschmaschine;
		this.alteWaschmaschine = alteWaschmaschine;
		this.sparsamerFernseher = sparsamerFernseher;
		this.alterFenseher = alterFenseher;
		this.computer = computer;
		this.sparsamerGeschirrspueler = sparsamerGeschirrspueler;
		this.alterGeschirrspueler = alterGeschirrspueler;
		this.sparsameBeleuchtung = sparsameBeleuchtung;
		this.alteBeleuchtung = alteBeleuchtung;
		this.sparsamerGefrierschrank = sparsamerGefrierschrank;
		this.alterGefrierschrank = alterGefrierschrank;
		this.waeschtrockner = waeschtrockner;
		this.dslRouter = dslRouter;
		this.mobiltelefon = mobiltelefon;
	}
	
	//Die get-Methode gibt den Variablenwert zur Verwendung zurueck
	public int getHaushaltsgeraeteID() {
		return haushaltsgeraeteID;
	}
	
	public int getSparsamerEHerd() {
		return sparsamerEHerd;
	}
	
	public int getAlterEHerd() {
		return alterEHerd;
	}
	
	public int getSparsamerKuehlschrank() {
		return sparsamerKuehlschrank;
	}
	
	public int getAlterKuehlschrank() {
		return alterKuehlschrank;
	}
	
	public int getSparsameWaschmaschine() {
		return sparsameWaschmaschine;
	}
	
	public int getAlteWaschmaschine() {
		return alteWaschmaschine;
	}
	
	public int getSparsamerFernseher() {
		return sparsamerFernseher;
	}
	
	public int getAlterFenseher() {
		return alterFenseher;
	}
	
	public int getComputer() {
		return computer;
	}
	
	public int getSparsamerGeschirrspueler() {
		return sparsamerGeschirrspueler;
	}
	
	public int getAlterGeschirrspueler() {
		return alterGeschirrspueler;
	}
	
	public int getSparsameBeleuchtung() {
		return sparsameBeleuchtung;
	}
	
	public int getAlteBeleuchtung() {
		return alteBeleuchtung;
	}
	
	public int getSparsamerGefrierschrank() {
		return sparsamerGefrierschrank;
	}
	
	public int getAlterGefrierschrank() {
		return alterGefrierschrank;
	}
	
	public int getWaeschtrockner() {
		return waeschtrockner;
	}
	
	public int getDslRouter() {
		return dslRouter;
	}
	
	public int getMobiltelefon() {
		return mobiltelefon;
	}
 }

