package application;

import java.io.IOException;

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

public class HeatingController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	// Liste die Werte für die ChoiceBox heatingType speichert
	ObservableList<String> heatingTypeList = FXCollections
			.observableArrayList("Fossil","Erdgas","Heizöl","Fernwärme","erneurbar");
	
	// Liste die Werte für die ChoiceBox roomTemp speichert
	ObservableList<String> roomTempList = FXCollections
			.observableArrayList("18°C-20°C","20°C-22°C","22°C-24°C");
	
	
	
//GUI Wechsel auf die Seite Wohnen
@FXML
public void  switchToHousing(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Housing.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
	}

//GUI Wechsel auf die Seite Strom
@FXML
public void  switchToElectricity(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Electricity.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	
	
// Initialisierung der ChoiceBoxes zur Auswahl des Heizungssystems
@FXML
	ChoiceBox<String> heatingType = new ChoiceBox<>();

//ChoiceBox zur Auwahl der durchschnttlichen Raumtemperatur
@FXML
	ChoiceBox<String> roomTemp = new ChoiceBox<>();


// Methode um die ChoiceBoxes mit den Werten aus den Listen befüllen
	public void initialize() {
		heatingType.setItems(heatingTypeList);
		
		roomTemp.setItems(roomTempList);
	}
	
}
