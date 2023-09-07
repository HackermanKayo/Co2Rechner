package application;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class Calculator {

    private Datenverwaltung datenverwaltung;
    private Connection connection;

    public Calculator(Connection connection, Datenverwaltung datenverwaltung) {
        this.connection = connection;
        this.datenverwaltung = datenverwaltung;
    }

// Methode zur Berechnung der CO2 Werte anhand der Nutzereingaben und der jeweiligen Faktoren aus der Datenbank
    public void calculateCO2() {
    	
    	
    }

}
