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

	

@FXML
public void  switchToStart(ActionEvent event) throws IOException {
	Datenverwaltung.switchToPage("/FXML/Start.fxml", event);
		}
}
