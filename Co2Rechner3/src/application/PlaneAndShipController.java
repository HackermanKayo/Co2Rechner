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

public class PlaneAndShipController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	
	
	
//GUI Wechsel auf die Seite Mobilität
@FXML
public void  switchToRides(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Rides.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
//GUI Wechsel auf die Seite Ernährung
@FXML
public void  switchToFood(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Food.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
}
