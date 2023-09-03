package application;
import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class RidesController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
//GUI Wechsel auf die Seite Farhzeuge
@FXML
public void  switchToVehicles(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Vehicles.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	
//GUI Wechsel auf die Seite FlugUndSchiffreisen
@FXML
public void  switchToPlaneShip(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/PlaneAndShip.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	
}
