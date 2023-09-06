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
        // Rufe die Methode fetchDataFromTable in Datenverwaltung auf, um Daten aus der Tabelle "housing" abzurufen
        List<Object[]> housingDataList = datenverwaltung.fetchDataFromTable(connection, "housing",
                new String[]{"personen"}, "id", 1);

        for (Object[] data : housingDataList) {
            int personen = (int) data[0];
            
            // Rufe die Methode fetchDataFromTable in Datenverwaltung auf, um den Faktor für die entsprechende Anzahl von Personen aus der Tabelle "housingvalues" abzurufen
            List<Object[]> housingValuesDataList = datenverwaltung.fetchDataFromTable(connection, "housingvalues",
                    new String[]{"wohnenpersonenfaktor"}, "personen", personen);

            // Nehmen Sie an, dass es nur einen Eintrag pro Personenanzahl gibt
            if(!housingValuesDataList.isEmpty()) {
                BigDecimal wohnenpersonenfaktor = (BigDecimal) housingValuesDataList.get(0)[0];
                BigDecimal co2Value = wohnenpersonenfaktor.multiply(BigDecimal.valueOf(personen));
                System.out.println("Calculated CO2 value for " + personen + " persons: " + co2Value);
            }
        }
    }

}
