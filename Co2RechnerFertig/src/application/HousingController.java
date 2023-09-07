package application;

import java.io.IOException;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

/**
 * @author Jannik Die Klasse HousingController steuert die Interaktion zwischen
 *         dem GUI für die Wohnungseintragung und der zugrundeliegenden Logik.
 */
public class HousingController {

	// Instanzvariablen
	private DatabaseConnection databaseConnection;
	private Calculator calculator;
	private Connection connection;
	private Datenverwaltung datenverwaltung;

	@FXML
	private ChoiceBox<String> housingType, constructionYear, persons;
	@FXML
	private TextField  area;
	@FXML
	private Button housingForwardButton;

	/**
	 * Konstruktor zur Initialisierung der Instanzvariablen.
	 */
	public HousingController() {
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getDatabaseConnection();
		datenverwaltung = new Datenverwaltung(connection);
		calculator = new Calculator(connection, datenverwaltung);
	}

	/**
	 * Wechseln zur Startseite.
	 */
	@FXML
	private void switchToStart(ActionEvent event) throws IOException {
		Datenverwaltung.switchToPage("/FXML/Start.fxml", event);
	}

	/**
	 * Wechseln zur Seite "Heizung". Aufruf der Methode eingabenSpeichern() um die
	 * eingegeben Werte an die Datenbank zu übergeben.
	 */
	@FXML
	private void switchToHeating(ActionEvent event) throws IOException {
		// Wenn die Eingaben nicht korrekt sind, wird ein Alert angezeigt und die
		// Methode verlassen.
		if (!eingabenSpeichern()) {
			return;
		}

		Datenverwaltung.switchToPage("/FXML/Heating.fxml", event);
	}

	/**
	 * Initialisieren der ChoiceBoxes mit den notwendigen Werten.
	 */
	@FXML
	private void initialize() {
		housingType.setItems(FXCollections.observableArrayList("Einfamilienhaus", "Mehrfamilienhaus", "Reihenhaus"));
		persons.setItems(FXCollections.observableArrayList("1", "2", "3","4","5"));
		constructionYear.setItems(FXCollections.observableArrayList("Bis 1978(unsaniert)", "1979 bis 2001(unsaniert)", "Ab 2012", "Passivhaus","Ab 1919(vollsaniert)"));
	}

	public double estimateYearlyCO2Emission() {
		double baseEmissionValue = 2000;
		double emissionFactor = 1.0;

		String[][] optionsGroups = { { housingType.getValue() }, { constructionYear.getValue() } };

		String[][] factorsGroups = { { "Einfamilienhaus", "Mehrfamilienhaus", "Wohnung" },
				{ "Bis 1978(unsaniert)", "1979 bis 2001(unsaniert)", "Ab 2012", "Passivhaus","Ab 1919(vollsaniert)" } };

		double[][] factorsValues = { { 1.0, 0.8, 0.7 }, // Faktoren für housingType
				{ 1.2, 0.9, 0.8, 1.0 } // Faktoren für constructionYear
		};

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

		try {
			double wohnfläche = Double.parseDouble(area.getText());

			// Faktoren für Wohnfläche
			if (wohnfläche <= 100) {
				emissionFactor *= 0.6;
			} else if (wohnfläche <= 200) {
				emissionFactor *= 1.0;
			} else {
				emissionFactor *= 1.4;
			}

		} catch (NumberFormatException e) {
			System.out.println("Fehler bei der Umwandlung der Wohnfläche.");
		}

		double totalEmission = baseEmissionValue * emissionFactor;
		long roundedTotalEmission = Math.round(totalEmission);
		datenverwaltung.showAlert("Werte", "CO2-Emission im Sektor Wohnen "+roundedTotalEmission);
		return totalEmission;
	}

	/**
	 * Speichern der Benutzereingaben in der Datenbank.
	 */
	@FXML
	private boolean eingabenSpeichern() {
		String anzahlPersonenText = persons.getValue();
		String wohnflächeText = area.getText();
		String hausTypText = housingType.getValue();
		String baujahrText = constructionYear.getValue();

		if (anzahlPersonenText.isEmpty() || wohnflächeText.isEmpty() || hausTypText == null || baujahrText == null) {
			datenverwaltung.showAlert("Fehler", "Bitte geben Sie alle Werte ein, bevor Sie fortfahren.");
			return false;
		}

		try {
			int anzahlPersonen = Integer.parseInt(anzahlPersonenText);
			double wohnfläche = Double.parseDouble(wohnflächeText);

			String insertSql = "INSERT INTO housing (personen, wohnfläche, haustyp, baujahr) VALUES (?, ?, ?, ?)";
			datenverwaltung.saveEntries(connection, "housing", insertSql, anzahlPersonen, wohnfläche, hausTypText,
					baujahrText);
			double calculatedEmission = estimateYearlyCO2Emission();
		     String insertSql1 = "UPDATE totalemission SET housing =? WHERE ID=1";
				datenverwaltung.updateEntry(connection, "totalemission", insertSql1, calculatedEmission);

		} catch (NumberFormatException e) {
			datenverwaltung.showAlert("Fehler", "Bitte geben Sie gültige Werte ein.");
			return false;
		}

		return true;
	}

}
