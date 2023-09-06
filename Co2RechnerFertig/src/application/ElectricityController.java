package application;

import java.io.IOException;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Diese Klasse ist der Controller für die GUI-Seite, die den Stromverbrauch und
 * andere strombezogene Eingaben des Benutzers verwaltet.
 */
public class ElectricityController {

	@FXML
	private ToggleGroup tgStrombezug;
	@FXML
	private TextField stromJahresverbrauch, erzeugungPV, eigenverbrauchPV;

	private final Connection connection;
	private final Datenverwaltung datenverwaltung;

	/**
	 * Konstruktor zur Initialisierung der Datenbankverbindung und der
	 * Datenverwaltung.
	 */
	public ElectricityController() {
		this.connection = new DatabaseConnection().getDatabaseConnection();
		this.datenverwaltung = new Datenverwaltung(connection);
	}

	/**
	 * Wechselt zur Seite "Heizung".
	 */
	@FXML
	public void switchToHeating(ActionEvent event) throws IOException {
		Datenverwaltung.switchToPage("/FXML/Heating.fxml", event);
	}

	/**
	 * Speichert die Nutzereingaben und wechselt zur Seite "Mobilität".
	 */
	@FXML
	public void switchToVehicles(ActionEvent event) throws IOException {
		if (eingabenSpeichern()) {
			Datenverwaltung.switchToPage("/FXML/Vehicles.fxml", event);
		}
	}

	/**
	 * Wechselt zur Seite "Strombedarf berechnen".
	 */
	@FXML
	public void switchToPowerConsumption(ActionEvent event) throws IOException {
		Datenverwaltung.switchToPage("/FXML/estimateConsumption.fxml", event);
	}

	/**
	 * Speichert die Nutzereingaben in der Datenbank.
	 * 
	 * @return true, wenn die Eingaben erfolgreich gespeichert wurden, sonst false.
	 */
	private boolean eingabenSpeichern() {
		RadioButton selectedRadio = (RadioButton) tgStrombezug.getSelectedToggle();
		String selectedRadiobutton = (selectedRadio != null) ? selectedRadio.getText() : null;

		String jahresverbrauch = stromJahresverbrauch.getText();
		String pvErzeugung = erzeugungPV.getText();
		String pvEigenverbrauch = eigenverbrauchPV.getText();

		// Überprüfen, ob der Radiobutton ausgewählt ist
		if (selectedRadiobutton == null) {
			datenverwaltung.showAlert("Fehler", "Bitte den Strombezug auswählen.");
			return false;
		}

		// Überprüfen, ob das Textfeld stromJahresverbrauch leer ist
		if (jahresverbrauch.trim().isEmpty()) {
			datenverwaltung.showAlert("Fehler", "Bitte den Jahresverbrauch in kWh angeben oder schätzen. "
					+ "Durch klick auf den Button gelangen Sie zur Ansicht zum schätzen");
			return false;
		}

		double jahresverbrauchValue;
		double pvErzeugungValue = 0.0; 
		double pvEigenverbrauchValue = 0.0;

		try {
			// Überprüfen, ob der Jahresverbrauch eine gültige numerische Zahl ist
			jahresverbrauchValue = Double.parseDouble(jahresverbrauch);

			// Überprüfen, ob pvErzeugung und pvEigenverbrauch gültige numerische Werte
			// enthalten, wenn sie nicht leer sind
			if (!pvErzeugung.trim().isEmpty()) {
				pvErzeugungValue = Double.parseDouble(pvErzeugung);
			}

			if (!pvEigenverbrauch.trim().isEmpty()) {
				pvEigenverbrauchValue = Double.parseDouble(pvEigenverbrauch);
			}

			String insertSql = "INSERT INTO electricity(strombezug, jahresverbrauch, erzeugungPV, eigenverbrauchPV) VALUES (?,?,?,?)";
			datenverwaltung.saveEntries(connection, insertSql, selectedRadiobutton, jahresverbrauchValue,
					pvErzeugungValue, pvEigenverbrauchValue);

			return true;
		} catch (NumberFormatException e) {
			datenverwaltung.showAlert("Fehler", "Bitte gültige numerische Werte eingeben.");
			return false;
		}
	}

}
