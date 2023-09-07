package application;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
import java.util.function.BiConsumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * 
 * @author Jannik 
 * Die Klasse PlaneAndShipController steuert die funktionalität
 * der GUI-Seite PlaneAndShip(Flug&Schifssreisen) Nutzereingaben werden
 * erkannt,verarbeitet und in der Datenbank gespeichert.
 */
public class PlaneAndShipController {

	private DatabaseConnection databaseConnection;
	private Connection connection;
	private Datenverwaltung datenverwaltung;

	/**
	 * Konstruktor für den Controller, der die Datenbankverbindung initialisiert.
	 */
	public PlaneAndShipController() {
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getDatabaseConnection();
		datenverwaltung = new Datenverwaltung(connection);
	}

	@FXML
	RadioButton economyEuropa,businessEuropa,economyTrans,businessTrans;
	@FXML
	TextField flugstundenEuropaEconomy,flugstundenEuropaBusiness,flugstundenTransEconomy,flugstundenTransBusiness,seefahrtDauer,flussfahrtDauer;

	/**
	 * Wechselt zur Seite "Mobilität".
	 *
	 * @param event Das auslösende Ereignis.
	 * @throws IOException Wenn ein Fehler beim Laden der Seite auftritt.
	 */
	@FXML
	public void switchToRides(ActionEvent event) throws IOException {
		Datenverwaltung.switchToPage("/FXML/Vehicles.fxml", event);
	}

	/**
	 * Wechselt zur Seite "Ernährung" und speichert die eingegebenen Daten.
	 *
	 * @param event Das auslösende Ereignis.
	 * @throws IOException Wenn ein Fehler beim Laden der Seite auftritt.
	 */
	@FXML
	public void switchToFood(ActionEvent event) throws IOException {
		eingabenSpeichern();
		Datenverwaltung.switchToPage("/FXML/Food.fxml", event);
	}

	
	public double estimateYearlyCO2Emission() {
	    double totalEmission = 0;

	    // Kombiniere RadioButtons, TextFields und spezifische Emissionsraten in einem Map
	    Map<RadioButton, Pair<TextField, Double>> emissionMapping = new HashMap<>();
	    emissionMapping.put(economyEuropa, new Pair<>(flugstundenEuropaEconomy, 400.0)); // 0.8 ist ein Beispielwert pro Stunde
	    emissionMapping.put(businessEuropa, new Pair<>(flugstundenEuropaBusiness, 500.0));
	    emissionMapping.put(economyTrans, new Pair<>(flugstundenTransEconomy, 600.0));
	    emissionMapping.put(businessTrans, new Pair<>(flugstundenTransBusiness, 700.0));

	    // Verarbeitung der Flugdaten
	    for (Map.Entry<RadioButton, Pair<TextField, Double>> entry : emissionMapping.entrySet()) {
	        if (entry.getKey().isSelected()) {
	            try {
	                double hours = Double.parseDouble(entry.getValue().getKey().getText());
	                totalEmission += hours * entry.getValue().getValue();
	            } catch (NumberFormatException e) {
	                System.out.println("Fehler bei der Umwandlung der Flugstunden.");
	            }
	        }
	    }

	    // Verarbeitung der Seefahrt- und Flussfahrt-Daten
	    Pair<TextField, Double> seefahrtData = new Pair<>(seefahrtDauer, 150.0); // 0.5 ist ein Beispielwert pro Tag
	    Pair<TextField, Double> flussfahrtData = new Pair<>(flussfahrtDauer, 50.0);

	    for (Pair<TextField, Double> data : Arrays.asList(seefahrtData, flussfahrtData)) {
	        try {
	            if (!data.getKey().getText().trim().isEmpty()) {
	                double duration = Double.parseDouble(data.getKey().getText());
	                totalEmission += duration * data.getValue();
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Fehler bei der Umwandlung der Seefahrt/Flussfahrt-Daten.");
	        }
	    }

	    System.out.println("Reise CO2-Ausstoß: " + totalEmission);
	    return totalEmission;
	}


	
	
	
	
	
	
	/**
	 * Speichert die eingegebenen Daten in der Datenbank.
	 */
	/**
	 * Speichert die eingegebenen Daten in der Datenbank.
	 */
	private void eingabenSpeichern() {
	    List<String> columns = new ArrayList<>();
	    List<String> placeholders = new ArrayList<>();
	    List<Object> values = new ArrayList<>();

	    // Funktion zum Hinzufügen von Werten zu den Eingaben
	    BiConsumer<String, TextField> addValue = (columnName, textField) -> {
	        String value = textField.getText();
	        try {
	            Double.parseDouble(value); // Versucht, den Wert in eine Zahl umzuwandeln
	            columns.add(columnName);
	            placeholders.add("?");
	            values.add(value);
	        } catch (NumberFormatException e) {
	            // Zeichenfolge ist keine gültige Zahl
	        }
	    };

	    if (economyEuropa.isSelected()) {
	        addValue.accept("flugstundenEuropaEconomy", flugstundenEuropaEconomy);
	    }

	    if (businessEuropa.isSelected()) {
	        addValue.accept("flugstundenEuropaBusiness", flugstundenEuropaBusiness);
	    }

	    if (economyTrans.isSelected()) {
	        addValue.accept("flugstundenTransEconomy", flugstundenTransEconomy);
	    }

	    if (businessTrans.isSelected()) {
	        addValue.accept("flugstundenTransBusiness", flugstundenTransBusiness);
	    }

	    addValue.accept("seefahrtDauer", seefahrtDauer);
	    addValue.accept("flussfahrtDauer", flussfahrtDauer);

	    try {
	        if (!columns.isEmpty()) {
	            String insertSql = "INSERT INTO planeandship(" + String.join(", ", columns) + ") VALUES ("
	                    + String.join(", ", placeholders) + ")";
	            datenverwaltung.saveEntries(connection,"planeandship", insertSql, values.toArray());
	        }

	        // Speichern der berechneten CO2-Emissionen in der Tabelle 'totalemission' 
	        double calculatedEmission = estimateYearlyCO2Emission();
	        String insertSql1 = "UPDATE totalemission SET planeandship =? WHERE ID=1";
	     	datenverwaltung.updateEntry(connection, "totalemission", insertSql1, calculatedEmission);

	    } catch (Exception e) {
	        datenverwaltung.showAlert("Fehler",
	                "Beim Speichern der Reisedaten ist ein Fehler aufgetreten: " + e.getMessage());
	    }
	}

}
