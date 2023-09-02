package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class ConsumptionController implements Initializable {

	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
//GUI Wechsel auf die Seite Auswertung
@FXML
public void  switchToResult(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Result.fxml"));
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









//Label welches die angegeben Konsumausgaben ausgibt
@FXML
Label ausgabenLabel = new Label();
//Regler um die Konsumausgaben einzugeben
@FXML
Slider ausgabenSlider = new Slider();

int konsumAusgaben;

//Methode um die Konsumausgaben die mittels Regler angegeben werden, durch aktualisierung des Labels unterhalb des Reglers live anzugeben
@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
	konsumAusgaben = (int)ausgabenSlider.getValue();
	ausgabenLabel.setText(Integer.toString(konsumAusgaben)+"€");
	
	ausgabenSlider.valueProperty().addListener(new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldNumber, Number newNumber) {
			
			konsumAusgaben = (int)ausgabenSlider.getValue();
			ausgabenLabel.setText(Integer.toString(konsumAusgaben)+"€");
		}
		
		
	});

}
}
