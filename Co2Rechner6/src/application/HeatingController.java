package application;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class HeatingController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	private DatabaseConnection databaseConnection;
//	private DatabaseQuery databaseQuery;
	private Calculator calculator;
	private Connection connection;
	private Datenverwaltung datenverwaltung = new Datenverwaltung(connection);
	
	public HeatingController() {

		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getDatabaseConnection();
		calculator = new Calculator();
		datenverwaltung = new Datenverwaltung(connection);
	}

	// Initialisierung der ChoiceBoxes zur Auswahl des Heizungssystems
	@FXML
	ChoiceBox<String> heatingType = new ChoiceBox<>();

	// ChoiceBox zur Auwahl der durchschnttlichen Raumtemperatur
	@FXML
	ChoiceBox<String> roomTemp = new ChoiceBox<>();
	@FXML
	RadioButton waterHigh = new RadioButton();
	@FXML
	RadioButton waterMedium = new RadioButton();
	@FXML
	RadioButton waterLow = new RadioButton();
	@FXML
	ToggleGroup tgWarmwasser;

	// Methode um die ChoiceBoxes mit den Werten aus den Listen befüllen
	public void initialize() {
		ObservableList<String> roomTempList = FXCollections.observableArrayList("18°C-20°C", "20°C-22°C", "22°C-24°C");
		ObservableList<String> heatingTypeList = FXCollections.observableArrayList("Fossil", "Erdgas", "Heizöl",
				"Fernwärme", "erneurbar");
		heatingType.setItems(heatingTypeList);
		roomTemp.setItems(roomTempList);
		tgWarmwasser = new ToggleGroup();
		waterHigh.setToggleGroup(tgWarmwasser);
		waterMedium.setToggleGroup(tgWarmwasser);
		waterLow.setToggleGroup(tgWarmwasser);
	}

//GUI Wechsel auf die Seite Wohnen
	@FXML
	public void switchToHousing(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/FXML/Housing.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

//GUI Wechsel auf die Seite Strom
	@FXML
	public void switchToElectricity(ActionEvent event) throws IOException {
		eingabenSpeichern(); // Aufruf der Methode zum speichern der werte
		eingabenAusgeben();
		root = FXMLLoader.load(getClass().getResource("/FXML/Electricity.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	private void eingabenSpeichern() {
		String heizungArt = heatingType.getValue();
		String raumTemp = roomTemp.getValue();
		String selectedRadioButton = ((RadioButton) tgWarmwasser.getSelectedToggle()).getText();

		if (heizungArt == null || raumTemp == null || selectedRadioButton == null) {
			datenverwaltung.showAlert("Fehler", "Bitte geben Sie alle erforderlichen Werte ein.");
			return;
		}
		try {

			// Aufruf der Methode saveEntries() in der Klasse Datenverwaltung 
			String insertSql = "INSERT INTO heating(heizungstyp, raumtemperatur, warmwasser) VALUES (?, ?, ?)";
			datenverwaltung.saveEntries(connection, insertSql, heizungArt, raumTemp, selectedRadioButton);

			// Weitere Verarbeitung, z.B. Navigation zur nächsten Seite
		} catch (NumberFormatException e) {
			datenverwaltung.showAlert("Fehler", "Bitte geben Sie die fehlenden Werte ein.");
		}

	}
	
	@FXML
	private void eingabenAusgeben() {
		// Rufe die Methode fetchDataFromTable in Datenverwaltung auf
		List<Object[]> heatingDataList = datenverwaltung.fetchDataFromTable(connection, "heating",
				new String[] { "heizungstyp", "raumtemperatur", "warmwasser" }, "id", 1);

		for (Object[] data : heatingDataList) {
			String heizungsArt = (String) data[0]; 
			String raumTemp = (String) data[1]; 
			String selectedRaioButton = (String) data[2]; 
			
			//Für Textzwecke Konsolenausgabe
			System.out.println("Die eingegebenen Werte lauten:");
			System.out.println("Heizungsart: " + heizungsArt);
			System.out.println("Durchschnittliche Raumtemperatur: " + raumTemp);
			System.out.println("Warmwasserverbrauch: " + selectedRaioButton);

		}
	}

}
