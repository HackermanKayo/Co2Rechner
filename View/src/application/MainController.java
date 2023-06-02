package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	public void  switchToHousing(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Housing.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	@FXML
	public void  switchToStart(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Main.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	@FXML
	public void  switchToElectricity(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Electricity.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	@FXML
	public void  switchToHeating(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Heating.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	@FXML
	public void  switchToConsumption(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/estimateConsumption.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	@FXML
	public void  switchToVehicles(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Vehicles.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	@FXML
	public void  switchToRides(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Rides.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	@FXML
	public void  switchToPlaneShip(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/PlaneAndShip.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
}
