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

public class VehiclesController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
//GUI Wechsel auf die Seite Fahrzeuge
@FXML
public void  switchToRides(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Rides.fxml"));
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
	
	
//Liste die Werte für die ChoiceBox fahrzeugTyp speichert
ObservableList<String> fahrzeugTypList = FXCollections
	.observableArrayList("Kleinwagen mit Verbrennungsmotor","Oberklasse mit Verbrennungsmotor","Hybrid","Elektrofahrzeug","Motorrad");
	// Liste die Werte für die ChoiceBox fahrzeugAlter speichert
ObservableList<String> fahrzeugAlterList = FXCollections
	.observableArrayList("Bis 3 Jahre","4-6 Jahre","7-10 Jahre","11-15 Jahre","Oldtimer");
		// Liste die Werte für die ChoiceBox kraftstoffArt speichert
ObservableList<String> kraftstoffArtList = FXCollections
	.observableArrayList("Benzin","Diesel","Erdgas","Flüssiggas","Strom");

// Initialisierung der ChoiceBoxes
@FXML
ChoiceBox<String> artFahrzeugEins = new ChoiceBox<>();
@FXML
ChoiceBox<String> artFahrzeugZwei = new ChoiceBox<>();
@FXML
ChoiceBox<String> artFahrzeugDrei = new ChoiceBox<>();

@FXML
ChoiceBox<String> alterFahrzeugEins = new ChoiceBox<>();
@FXML
ChoiceBox<String> alterFahrzeugZwei = new ChoiceBox<>();
@FXML
ChoiceBox<String> alterFahrzeugDrei = new ChoiceBox<>();

@FXML
ChoiceBox<String> kraftstoffFahrzeugEins = new ChoiceBox<>();
@FXML
ChoiceBox<String> kraftstoffFahrzeugZwei = new ChoiceBox<>();
@FXML
ChoiceBox<String> kraftstoffFahrzeugDrei = new ChoiceBox<>();


//Methode um die ChoiceBoxes mit den Werten aus den Listen befüllen
public void initialize() {
	
	artFahrzeugEins.setItems(fahrzeugTypList);
	artFahrzeugZwei.setItems(fahrzeugTypList);
	artFahrzeugDrei.setItems(fahrzeugTypList);
	
	alterFahrzeugEins.setItems(fahrzeugAlterList);
	alterFahrzeugZwei.setItems(fahrzeugAlterList);
	alterFahrzeugDrei.setItems(fahrzeugAlterList);
	
	kraftstoffFahrzeugEins.setItems(kraftstoffArtList);
	kraftstoffFahrzeugZwei.setItems(kraftstoffArtList);
	kraftstoffFahrzeugDrei.setItems(kraftstoffArtList);
	
	
}
	
}
