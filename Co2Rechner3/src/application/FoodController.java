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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
public class FoodController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	
	
//GUI Wechsel auf die Seite Konsum
@FXML
public void  switchToConsumption(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Consumption.fxml"));
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
