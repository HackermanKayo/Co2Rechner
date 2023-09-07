package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Der Controller für die GUI-Seite zur Erfassung von Ernährungsdaten.
 */
public class FoodController {

	private DatabaseConnection databaseConnection;
	private Calculator calculator;
	private Connection connection;
	private Datenverwaltung datenverwaltung = new Datenverwaltung(connection);

	/**
	 * Konstruktor für den Controller, der die Datenbankverbindung initialisiert.
	 */
	public FoodController() {
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getDatabaseConnection();
		datenverwaltung = new Datenverwaltung(connection);
		calculator = new Calculator(connection, datenverwaltung);
	}

	@FXML
	ToggleGroup tgTaetigkeit, tgSport, tgErnaehrungsform, tgBio, tgRegional, tgSaison;
	@FXML
	RadioButton wenigKoerperlich, mediumKoerperlich, vielKoerperlich, keinSport, mediumSport, vielSport, fleischBetont,
			mischkost, fleischreduziert, vegetarisch, vegan, vielBio, mediumBio, wenigBio, wenigRegional,
			mediumRegional, vielRegional, vielSaisonal, mediumSaisonal, wenigSaisonal;
	@FXML
	TextField weight;

	/**
	 * Wechselt zur Seite "Konsum" und speichert die eingegebenen Ernährungsdaten.
	 *
	 * @param event Das auslösende Ereignis.
	 * @throws IOException Wenn ein Fehler beim Laden der Seite auftritt.
	 */
	@FXML
	public void switchToConsumption(ActionEvent event) throws IOException {
		if (eingabenSpeichern()) {
			Datenverwaltung.switchToPage("/FXML/Consumption.fxml", event);
		}
	}

	/**
	 * Wechselt zur Seite "Flug und Schiffreisen".
	 *
	 * @param event Das auslösende Ereignis.
	 * @throws IOException Wenn ein Fehler beim Laden der Seite auftritt.
	 */
	@FXML
	public void switchToPlaneShip(ActionEvent event) throws IOException {
		Datenverwaltung.switchToPage("/FXML/PlaneAndShip.fxml", event);
	}

	/**
	 * Initialisiert die ToggleGroups für die RadioButtons.
	 */
	public void initialize() {
		tgTaetigkeit = new ToggleGroup();
		wenigKoerperlich.setToggleGroup(tgTaetigkeit);
		mediumKoerperlich.setToggleGroup(tgTaetigkeit);
		vielKoerperlich.setToggleGroup(tgTaetigkeit);
		tgSport = new ToggleGroup();
		keinSport.setToggleGroup(tgSport);
		mediumSport.setToggleGroup(tgSport);
		vielSport.setToggleGroup(tgSport);
		tgErnaehrungsform = new ToggleGroup();
		fleischBetont.setToggleGroup(tgErnaehrungsform);
		mischkost.setToggleGroup(tgErnaehrungsform);
		fleischreduziert.setToggleGroup(tgErnaehrungsform);
		vegetarisch.setToggleGroup(tgErnaehrungsform);
		vegan.setToggleGroup(tgErnaehrungsform);
		tgBio = new ToggleGroup();
		vielBio.setToggleGroup(tgBio);
		mediumBio.setToggleGroup(tgBio);
		wenigBio.setToggleGroup(tgBio);
		tgRegional = new ToggleGroup();
		vielRegional.setToggleGroup(tgRegional);
		mediumRegional.setToggleGroup(tgRegional);
		wenigRegional.setToggleGroup(tgRegional);
		tgSaison = new ToggleGroup();
		vielSaisonal.setToggleGroup(tgSaison);
		mediumSaisonal.setToggleGroup(tgSaison);
		wenigSaisonal.setToggleGroup(tgSaison);
	}

	public double estimateYearlyCO2Emission() {
		double baseEmissionValue = 2000;
		double emissionFactor = 1.0;

		RadioButton[][] optionsGroups = { { wenigKoerperlich, mediumKoerperlich, vielKoerperlich },
				{ keinSport, mediumSport, vielSport },
				{ fleischBetont, mischkost, fleischreduziert, vegetarisch, vegan }, { vielBio, mediumBio, wenigBio },
				{ vielRegional, mediumRegional, wenigRegional }, { vielSaisonal, mediumSaisonal, wenigSaisonal } };

		double[][] factorsGroups = { { 1.0, 1.2, 1.3 }, { 1.0, 1.2, 1.3 }, { 1.6, 1.3, 1.1, 0.8, 0.6 },
				{ 0.9, 0.95, 1.05 }, { 0.85, 0.95, 1.05 }, { 0.85, 0.95, 1.05 } };

		for (int i = 0; i < optionsGroups.length; i++) {
			RadioButton[] options = optionsGroups[i];
			double[] factors = factorsGroups[i];

			for (int j = 0; j < options.length; j++) {
				if (options[j].isSelected()) {
					emissionFactor *= factors[j];
					break; // Exit the inner loop as soon as a match is found.
				}
			}
		}
		double totalEmission = baseEmissionValue * emissionFactor;
		System.out.println("essen: " + totalEmission);
		return baseEmissionValue * emissionFactor;
	}

	/**
	 * Speichert die eingegebenen Ernährungsdaten in der Datenbank.
	 */
	private boolean eingabenSpeichern() {
		String gewichtText = weight.getText();
		RadioButton selectedRadioButton = (RadioButton) tgTaetigkeit.getSelectedToggle();
		RadioButton selectedRadioButton2 = (RadioButton) tgSport.getSelectedToggle();
		RadioButton selectedRadioButton3 = (RadioButton) tgErnaehrungsform.getSelectedToggle();
		RadioButton selectedRadioButton4 = (RadioButton) tgBio.getSelectedToggle();
		RadioButton selectedRadioButton5 = (RadioButton) tgRegional.getSelectedToggle();
		RadioButton selectedRadioButton6 = (RadioButton) tgSaison.getSelectedToggle();

		// Überprüfung, ob das Gewichtsfeld leer ist.
		if (gewichtText == null || gewichtText.trim().isEmpty()) {
			datenverwaltung.showAlert("Fehler", "Bitte geben Sie Ihr Gewicht ein.");
			return false;
		}

		if (selectedRadioButton == null || selectedRadioButton2 == null || selectedRadioButton3 == null
				|| selectedRadioButton4 == null || selectedRadioButton5 == null || selectedRadioButton6 == null) {
			datenverwaltung.showAlert("Fehler", "Bitte geben Sie alle erforderlichen Werte ein.");
			return false;
		}

		try {
			// Aufruf der Methode saveEntries() in der Klasse Datenverwaltung
			String insertSql = "INSERT INTO food(gewicht, tätigkeit, sport, ernährungsform, bio, regional, saisonal) VALUES (?,?,?,?,?,?,?)";
			datenverwaltung.saveEntries(connection, "food", insertSql, gewichtText, selectedRadioButton.getText(),
					selectedRadioButton2.getText(), selectedRadioButton3.getText(), selectedRadioButton4.getText(),
					selectedRadioButton5.getText(), selectedRadioButton6.getText());

			double calculatedEmission = estimateYearlyCO2Emission();
			String insertSql1 = "UPDATE totalemission SET food =? WHERE ID=1";
			datenverwaltung.updateEntry(connection, "totalemission", insertSql1, calculatedEmission);

		} catch (NumberFormatException e) {
			datenverwaltung.showAlert("Fehler", "Bitte geben Sie gültige Werte ein.");
			return false;
		}

		return true; // Erfolgreich gespeichert
	}

}
