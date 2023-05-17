package application;
import javafx.application.Application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {

	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	@FXML
	public void  switchToStart(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("Main.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
	}
	
	@FXML
	public void  switchToHousing(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("Housing.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	

	@FXML
	public void  switchToMobility(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("Mobility.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
	}

	
}
