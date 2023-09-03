package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class WohnenController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	

	
//GUI Wechsel zurück auf die Startseite
@FXML
public void  switchToStart(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Start.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
	}
//GUI Wechsel weiter zur Seite "Heizung"		
@FXML
public void  switchToHeating(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Heating.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}

//Liste die Werte für die ChoiceBox housingType speichert
ObservableList<String> housingTypeList = FXCollections
			.observableArrayList("Einfamilienhaus","Mehrfamilienhaus","Wohnung");
//Liste die Werte für die ChoiceBox Baujahr/Sanierungsstandart speichert
ObservableList<String> constructionYearList = FXCollections
			.observableArrayList("Bis 1978","Saniert","Ab 2012");
	
//ChoiceBox zur Auswahl des Haustyps
@FXML
ChoiceBox<String> housingType = new ChoiceBox<>();
//ChoiceBox zur Auwahl des Baujahres und Sanierungsstandarts
@FXML
ChoiceBox<String> constructionYear = new ChoiceBox<>();

//Methode um die ChoiceBoxes mit den Werten aus den Listen befüllen
public void initialize() {
	housingType.setItems(housingTypeList);
	
	constructionYear.setItems(constructionYearList);
}
	
	
	
}
