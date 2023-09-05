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

public class ResultController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
@FXML
public void  switchToConsumption(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Consumption.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
	
@FXML
public void  switchToStart(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Start.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		}
}
