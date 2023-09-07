package application;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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
		} catch (Exception e) {
			datenverwaltung.showAlert("Fehler",
					"Beim Speichern der Reisedaten ist ein Fehler aufgetreten: " + e.getMessage());
		}
	}
}
