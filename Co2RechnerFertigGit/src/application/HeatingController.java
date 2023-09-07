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

	@FXML
	private boolean eingabenSpeichern() {
		String consumptionValue = heatingConsumption.getText();
		String heizungArt = heatingType.getValue();

		if (consumptionValue != null && !consumptionValue.trim().isEmpty() && heizungArt != null) {
			try {
				String insertConsumptionSql = "INSERT INTO heating(heizungstyp, jahresverbrauch) VALUES (?, ?)";
				datenverwaltung.saveEntries(connection,"heating", insertConsumptionSql, heizungArt, consumptionValue);
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
}
