package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class HousingController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	private DatabaseConnection databaseConnection;
//	private DatabaseQuery databaseQuery;
	private Calculator calculator;
	private Connection connection;
	private Datenverwaltung datenverwaltung = new Datenverwaltung(connection);

	public HousingController() {

		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getDatabaseConnection();
		calculator = new Calculator();
		datenverwaltung = new Datenverwaltung(connection);
	}

	// ChoiceBox zur Auswahl des Haustyps
	@FXML
	ChoiceBox<String> housingType;
	// ChoiceBox zur Auwahl des Baujahres und Sanierungsstandarts
	@FXML
	ChoiceBox<String> constructionYear;
	@FXML
	TextField persons;
	@FXML
	TextField area;
	@FXML
	Button housingForwardButton;

	// ------------- METHODEN ---------------

//GUI Wechsel zurück auf die Startseite
	@FXML
	public void switchToStart(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/FXML/Start.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

//GUI Wechsel weiter zur Seite "Heizung"		
	@FXML
	public void switchToHeating(ActionEvent event) throws IOException {

		eingabenSpeichern(); // Aufruf der Methode zum speichern der werte
		eingabenAusgeben();
		// eingabenAusgeben(); // Aufruf der Methode zum berechnen
		// calculator.berechnung(anzahlPersonen, wohnfläche);// Aufruf der methode zum
		// berechnen.
		root = FXMLLoader.load(getClass().getResource("/FXML/Heating.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

//Initialisieren der ChoiceBoxes
	@FXML
	public void initialize() {

		// um die ChoiceBoxes mit den Werten aus den Listen befüllen
		ObservableList<String> housingTypeList = FXCollections.observableArrayList("Einfamilienhaus",
				"Mehrfamilienhaus", "Wohnung");
		ObservableList<String> constructionYearList = FXCollections.observableArrayList("Bis 1978", "Saniert",
				"Ab 2012", "Vor 2010");
		housingType.setItems(housingTypeList);
		constructionYear.setItems(constructionYearList);

	}

	// Methode um die Nutzereingaben in der Datenbank zu speichern

	@FXML
	private void eingabenSpeichern() {
		String anzahlPersonenText = persons.getText();
		String wohnflächeText = area.getText();
		String hausTypText = housingType.getValue();
		String baujahrText = constructionYear.getValue();

		// Validierung der Eingabe (optional)
		if (anzahlPersonenText.isEmpty() || wohnflächeText.isEmpty() || hausTypText.isEmpty()
				|| baujahrText.isEmpty()) {
			datenverwaltung.showAlert("Fehler", "Bitte geben Sie die noch fehlenden Werte ein.");
			return; // Breche den Speichervorgang ab, wenn die Eingabe ungültig ist.
		}

		try {
			int anzahlPersonen = Integer.parseInt(anzahlPersonenText); // Versuche, den Text in eine Ganzzahl
																		// umzuwandeln

			double wohnfläche = Double.parseDouble(wohnflächeText); // Versuche, den Text in eine Ganzzahl umzuwandeln

			// Rufen Sie die Methode saveEntries() in der Datenverwaltung auf
			String insertSql = "INSERT INTO housing (personen, wohnfläche, haustyp, baujahr) VALUES (?, ?, ?, ?)";
			datenverwaltung.saveEntries(connection, insertSql, anzahlPersonen, wohnfläche, hausTypText, baujahrText);

		} catch (NumberFormatException e) {
			datenverwaltung.showAlert("Fehler", "Bitte geben Sie die fehlenden Werte ein.");
		}
	}

	@FXML
	private void eingabenAusgeben() {
		// Rufe die Methode fetchDataFromTable in Datenverwaltung auf
		List<Object[]> housingDataList = datenverwaltung.fetchDataFromTable(connection, "housing",
				new String[] { "personen", "wohnfläche", "haustyp", "baujahr" }, "id", 1);

		for (Object[] data : housingDataList) {
			int anzahlPersonen = (int) data[0]; // Index 0 entspricht "personen" in der Abfrage
			double wohnfläche = (double) data[1]; // Index 1 entspricht "wohnfläche" in der Abfrage
			String haustyp = (String) data[2]; // Index 2 entspricht "haustyp" in der Abfrage
			String baujahr = (String) data[3]; // Index 3 entspricht "baujahr" in der Abfrage
			
			//Für Textzwecke Konsolenausgabe
			System.out.println("Die eingegebenen Werte lauten:");
			System.out.println("Anzahl Personen: " + anzahlPersonen);
			System.out.println("Wohnfläche: " + wohnfläche);
			System.out.println("Haustyp: " + haustyp);
			System.out.println("Baujahr: " + baujahr);
		}
	}

}
