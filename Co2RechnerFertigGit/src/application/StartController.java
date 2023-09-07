package application;

import java.io.IOException;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Der Controller für die Startseite der Anwendung. Diese Klasse ist
 * verantwortlich für die Verarbeitung der Benutzereingaben für Name, Alter und
 * Geschlecht und speichert diese Daten in der Datenbank.
 */
public class StartController {

	private DatabaseConnection databaseConnection;
	private Connection connection;
	private Datenverwaltung datenverwaltung;

	/**
	 * Konstruktor für den StartController. Initialisiert die Datenbankverbindung
	 * und Datenverwaltung.
	 */
	public StartController() {
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getDatabaseConnection();
		datenverwaltung = new Datenverwaltung(connection);
	}

	@FXML
	TextField nameField;
	@FXML
	TextField ageField;
	@FXML
	ToggleGroup tgGeschlecht;

	/**
	 * Wechselt zur Wohnseite und speichert die getätigten Nutzereingaben.
	 * 
	 * @param event Das auslösende Ereignis.
	 * @throws IOException Wenn ein Fehler beim Laden der Seite auftritt.
	 */
	@FXML
	public void switchToHousing(ActionEvent event) throws IOException {
		if (eingabenSpeichern()) {
			Datenverwaltung.switchToPage("/FXML/Housing.fxml", event);
		}
	}

	/**
	 * Speichert die Benutzereingaben in der Datenbank.
	 * 
	 * @return true, wenn die Eingaben erfolgreich gespeichert wurden, sonst false.
	 */
	private boolean eingabenSpeichern() {
		RadioButton selectedRadio = (RadioButton) tgGeschlecht.getSelectedToggle();
		String selectedRadiobutton = (selectedRadio != null) ? selectedRadio.getText() : null;

		String nameText = nameField.getText();
		String ageText = ageField.getText();
		double ageTextValue = 0;

		if (nameText == null || nameText.trim().isEmpty() || ageText == null || ageText.trim().isEmpty()
				|| selectedRadiobutton == null) {
			datenverwaltung.showAlert("Fehler", "Bitte die noch fehlenden Angaben tätigen");
			return false;
		}

		try {
			ageTextValue = Integer.parseInt(ageText);

			String insertSql = "INSERT INTO user(name,age,geschlecht) VALUES (?,?,?)";
			datenverwaltung.saveEntries(connection,"user", insertSql, nameText, ageTextValue, selectedRadiobutton);
			return true;
		} catch (NumberFormatException e) {
			datenverwaltung.showAlert("Fehler", "Bitte gültige numerische Werte im Feld Alter eingeben.");
			return false;
		}
	}
}
