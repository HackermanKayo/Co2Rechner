package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ElectricityController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
//GUI Wechsel auf die Seite Heizung
@FXML	
public void  switchToHeating(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Heating.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}	
//GUI Wechsel auf die Seite Strombedarf berechnen
@FXML
public void  switchToPowerConsumption(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/estimateConsumption.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
//GUI Wechsel auf die Seite Mobilität
@FXML
public void  switchToVehicles(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Vehicles.fxml"));
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
	
	
	














}
