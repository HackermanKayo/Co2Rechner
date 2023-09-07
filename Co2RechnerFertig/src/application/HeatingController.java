package application;

import java.io.IOException;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Der HeatingController verwaltet die Benutzeroberfläche und Interaktionen für
 * den Heizungsabschnitt des CO2-Rechners. Er ermöglicht es dem Benutzer,
 * Informationen zu seinem Heizungssystem, der durchschnittlichen Raumtemperatur
 * und seinem Warmwasserverbrauch einzugeben.
 */
public class HeatingController {

	// Verbindungen zu externen Klassen und Datenbanken
	private DatabaseConnection databaseConnection;
	private Connection connection;
	private Datenverwaltung datenverwaltung;

	/**
	 * Konstruktor: Initialisiert die Datenbankverbindung und andere zugehörige
	 * Klassen.
	 */
	public HeatingController() {
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getDatabaseConnection();
		datenverwaltung = new Datenverwaltung(connection);
	}

	// UI-Elemente
	@FXML
	ChoiceBox<String> heatingType, roomTemp;
	@FXML
	RadioButton waterHigh, waterMedium, waterLow;
	@FXML
	ToggleGroup tgWarmwasser;
	@FXML
	TextField heatingConsumption;
	@FXML
	Label estHeatingConsumption;

	/**
	 * Initialisiert die ChoiceBoxes und die RadioButton-Gruppe.
	 */
	@FXML
	private void initialize() {

		heatingType.setItems(FXCollections.observableArrayList("Fossil", "Erdgas", "Heizöl", "Fernwärme", "erneurbar"));
		roomTemp.setItems(FXCollections.observableArrayList("18°C-20°C", "20°C-22°C", "22°C-24°C"));

		tgWarmwasser = new ToggleGroup();
		waterHigh.setToggleGroup(tgWarmwasser);
		waterMedium.setToggleGroup(tgWarmwasser);
		waterLow.setToggleGroup(tgWarmwasser);

		tgWarmwasser.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			estimateHeatingConsumption();
		});
	}

	/**
	 * Wechselt zur "Housing"-Seite der Anwendung.
	 */
	@FXML
	public void switchToHousing(ActionEvent event) throws IOException {
		Datenverwaltung.switchToPage("/FXML/Housing.fxml", event);
	}

	/**
	 * Speichert die Benutzereingaben und wechselt zur "Electricity"-Seite der
	 * Anwendung.
	 */
	@FXML
	public void switchToElectricity(ActionEvent event) throws IOException {
		if (eingabenSpeichern()) {
			Datenverwaltung.switchToPage("/FXML/Electricity.fxml", event);
		}
	}

	public double estimateYearlyCO2Emission() {
	    double baseEmissionValue = 2000; // Ein Basiswert
	    double emissionFactor = 1.0;

	    // Extrahiere den Verbrauchswert aus dem Textfeld
	    double consumption = 0.0;
	    try {
	        consumption = Double.parseDouble(heatingConsumption.getText());
	    } catch (NumberFormatException e) {
	        System.out.println("Fehler bei der Umwandlung des Heizungsverbrauchs.");
	    }

	    // Bestimme die Verbrauchskategorie
	    String consumptionCategory;
	    if (consumption <= 2500) {
	        consumptionCategory = "0-2500";
	    } else if (consumption <= 5000) {
	        consumptionCategory = "2501-5000";
	    } else if (consumption <= 10000) {
	        consumptionCategory = "5001-10000";
	    } else {
	        consumptionCategory = "10000+";
	    }

	    // Definition der Optionen und Faktoren
	    String[] heatingOptions = {heatingType.getValue()};
	    String[] consumptionCategories = {consumptionCategory};

	    String[][] optionsGroups = {heatingOptions, consumptionCategories};

	    String[][] factorsGroups = {
	        {"Fossil", "Erdgas", "Heizöl", "Fernwärme", "erneurbar"},
	        {"0-2500", "2501-5000", "5001-10000", "10000+"}
	    };

	    double[][] factorsValues = {
	        {1.2, 1.1, 1.3, 0.9, 0.5}, // Faktoren für heatingType
	        {0.8, 1.0, 1.2, 1.5} // Faktoren für Jahresverbrauch
	    };

	    // Abgleich der ausgewählten Optionen mit den Faktoren
	    for (int i = 0; i < optionsGroups.length; i++) {
	        String option = optionsGroups[i][0];
	        String[] factors = factorsGroups[i];
	        double[] values = factorsValues[i];

	        for (int j = 0; j < factors.length; j++) {
	            if (option.equals(factors[j])) {
	                emissionFactor *= values[j];
	                break;
	            }
	        }
	    }

	    // Gesamtemission berechnen
	    double totalEmission = baseEmissionValue * emissionFactor + consumption;
	    long roundedTotalEmission = Math.round(totalEmission);
	    datenverwaltung.showAlert("Werte", "CO2-Emission im Sektor Wohnen "+roundedTotalEmission);
	    return totalEmission;
	}


	@FXML
	private boolean eingabenSpeichern() {
		String consumptionValue = heatingConsumption.getText();
		String heizungArt = heatingType.getValue();

		if (consumptionValue != null && !consumptionValue.trim().isEmpty() && heizungArt != null) {
			try {
				String insertConsumptionSql = "INSERT INTO heating(heizungstyp, jahresverbrauch) VALUES (?, ?)";
				datenverwaltung.updateEntry(connection, "heating", insertConsumptionSql, heizungArt, consumptionValue);
				double calculatedEmission = estimateYearlyCO2Emission();
			     String insertSql1 = "UPDATE totalemission SET heating =? WHERE ID=1";
					datenverwaltung.updateEntry(connection, "totalemission", insertSql1, calculatedEmission);
				return true;
			} catch (Exception e) {
				datenverwaltung.showAlert("Fehler",
						"Es gab ein Problem beim Speichern des Heizungsverbrauchs und der Heizungsart.");
				return false;
			}
		}

		String raumTemp = roomTemp.getValue();
		RadioButton selectedRadio = (RadioButton) tgWarmwasser.getSelectedToggle();
		String selectedRadioButton = (selectedRadio != null) ? selectedRadio.getText() : null;

		if (heizungArt == null || raumTemp == null || selectedRadioButton == null) {
			datenverwaltung.showAlert("Fehler", "Bitte geben Sie alle erforderlichen Werte ein.");
			return false;
		}

		try {
			String insertSql = "INSERT INTO heating(heizungstyp, raumtemperatur, warmwasser) VALUES (?, ?, ?)";
			datenverwaltung.saveEntries(connection, insertSql, heizungArt, raumTemp, selectedRadioButton);


			return true;
		} catch (NumberFormatException e) {
			datenverwaltung.showAlert("Fehler", "Bitte geben Sie die fehlenden Werte ein.");
			return false;
		}

	}

	// Methode mit welcher der Nutzer seinen Jahresverbrauch schätzen kann anhand
	// der Raumtemperatur und des Warmwasserverbrauchs
	@FXML
	public void estimateHeatingConsumption() {
		double baseConsumption = 0;

		String tempSelection = roomTemp.getValue();
		RadioButton selectedWarmWater = (RadioButton) tgWarmwasser.getSelectedToggle();

		if (tempSelection != null) {
			switch (tempSelection) {
			case "18°C-20°C":
				baseConsumption = 10000;
				break;
			case "20°C-22°C":
				baseConsumption = 12000;
				break;
			case "22°C-24°C":
				baseConsumption = 14000;
				break;
			}
		}

		if (selectedWarmWater != null) {
			switch (selectedWarmWater.getText()) {
			case "Viel":
				baseConsumption += 2500;
				break;
			case "Durchschnitt":
				baseConsumption += 2000;
				break;
			case "Wenig":
				baseConsumption += 1500;
				break;
			}
		}

		heatingConsumption.setText(String.valueOf(baseConsumption));
	}

}
