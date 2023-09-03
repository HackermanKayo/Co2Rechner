package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

	private static Connection connection;

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

// ---------- LISTEN --------------	

	// Liste die Werte für die ChoiceBox housingType speichert
	ObservableList<String> housingTypeList = FXCollections.observableArrayList("Einfamilienhaus", "Mehrfamilienhaus",
			"Wohnung");
	// Liste die Werte für die ChoiceBox Baujahr/Sanierungsstandart speichert
	ObservableList<String> constructionYearList = FXCollections.observableArrayList("Bis 1978", "Saniert", "Ab 2012");

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
		root = FXMLLoader.load(getClass().getResource("/FXML/Heating.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

//Initialisieren / Eventhandler
	@FXML
	public void initialize() {

		connection = DatabaseConnection.getDatabaseConnection(); // Hole die Verbindung von DatabaseConnection
		if (connection == null) {
			// Handle den Fall, wenn die Verbindung nicht erfolgreich hergestellt wurde
			showAlert("Fehler", "Die Verbindung zur Datenbank konnte nicht hergestellt werden.");
		}

		// um die ChoiceBoxes mit den Werten aus den Listen befüllen
		housingType.setItems(housingTypeList);
		constructionYear.setItems(constructionYearList);

		// Füge den Event-Handler für die Enter-Taste hinzu
		persons.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				speichereAnzahlPersonen(); // Rufe die Methode zum Speichern auf
				area.requestFocus(); // Wechsel in das nächste TextField (Wohnfläche)
			}
		});

	}

	//Methode um die Nutzereingaben in der Datenbank zu speichern
	private void speichereAnzahlPersonen() {
		String anzahlPersonenText = persons.getText();

		// Validierung der Eingabe (optional)
		if (anzahlPersonenText.isEmpty()) {
			showAlert("Fehler", "Bitte geben Sie die Anzahl Personen im Haushalt ein.");
			return; // Breche den Speichervorgang ab, wenn die Eingabe ungültig ist.
		}

		try {
			int anzahlPersonen = Integer.parseInt(anzahlPersonenText); // Versuche, den Text in eine Ganzzahl
																		// umzuwandeln

			// SQL-INSERT-Befehl vorbereiten
			String insertSql = "INSERT INTO housing (personen) VALUES (?)";

			try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
				preparedStatement.setInt(1, anzahlPersonen);

				// Führe den SQL-Befehl aus, um die Daten in die Datenbank einzufügen
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				showAlert("Fehler", "Ein Fehler ist beim Speichern der Daten in die Datenbank aufgetreten.");
			}
		} catch (NumberFormatException e) {
			// Das Textfeld enthält keine gültige Ganzzahl
			showAlert("Fehler", "Bitte geben Sie eine gültige Anzahl Personen im Haushalt ein.");
		}
	}
	
// Methode um die Fehlermeldungen bei Falscheingabe auszugeben.
	private void showAlert(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
