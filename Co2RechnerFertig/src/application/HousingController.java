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
 * Die Klasse HousingController steuert die Interaktion zwischen dem GUI für die
 * Wohnungseintragung und der zugrundeliegenden Logik.
 */
public class HousingController {

	// Instanzvariablen
	private DatabaseConnection databaseConnection;
	private Calculator calculator;
	private Connection connection;
	private Datenverwaltung datenverwaltung;

	@FXML
	private ChoiceBox<String> housingType,constructionYear;
	@FXML
	private TextField persons, area;
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
	 * Wechseln zur Seite "Heizung". Aufruf der Methode eingabenSpeichern() um die eingegeben Werte an die Datenbank zu übergeben.
	 */
	@FXML
	private void switchToHeating(ActionEvent event) throws IOException {
	    // Wenn die Eingaben nicht korrekt sind, wird ein Alert angezeigt und die Methode verlassen.
	    if (!eingabenSpeichern()) {
	        return;
	    }

	    calculator.calculateCO2();    
	    Datenverwaltung.switchToPage("/FXML/Heating.fxml", event);
	}

	/**
	 * Initialisieren der ChoiceBoxes mit den notwendigen Werten.
	 */
	@FXML
	private void initialize() {
		housingType.setItems(FXCollections.observableArrayList("Einfamilienhaus", "Mehrfamilienhaus", "Wohnung"));
		constructionYear.setItems(FXCollections.observableArrayList("Bis 1978", "Saniert", "Ab 2012", "Vor 2010"));
	}

	/**
	 * Speichern der Benutzereingaben in der Datenbank.
	 */
	@FXML
	private boolean eingabenSpeichern() {
	    String anzahlPersonenText = persons.getText();
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
	        datenverwaltung.saveEntries(connection, insertSql, anzahlPersonen, wohnfläche, hausTypText, baujahrText);

	    } catch (NumberFormatException e) {
	        datenverwaltung.showAlert("Fehler", "Bitte geben Sie gültige Werte ein.");
	        return false;
	    }

	    return true;
	}

}
