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

public class MainController implements Initializable {


	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	@FXML
	public void  switchToStart(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("Main.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	//ageTextfield.setText(String.valueOf(age)); // Um den eingebenen Wert Alter zu wieder einzufügen falls Nutzer zurück auf Startseite geht selber fehler wie geschlecht
	//textField2.setText(userInput.getTextField2Input());
	genderChoicebox.getItems().add(genderPicked); // Um Geschlecht zu speichern , kein Fehler aber geht nicht
	stage.show();
	}
	
	@FXML
	public void  switchToHousing(ActionEvent event) throws IOException {
		//if() { Prüfen ob eingaben getätigt wurden alter geschlecht name
		
		
		//
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

	//Geschlecht auswählen
	@FXML
	private ChoiceBox<String> genderChoicebox;
	@FXML
	private String[] genders = {"Weiblich", "Männlich", "Divers"};
	String genderPicked;



	public void initialize(URL arg0, ResourceBundle arg1) {
		genderChoicebox.getItems().addAll(genders);
		genderChoicebox.setOnAction(this::geschlechtGewählt);
	}
	
	public void geschlechtGewählt(ActionEvent event) {
		String genderPicked = genderChoicebox.getValue();

	}
	
	// Geschlecht Ende	
	//Problem : Choicebox muss auf jeden Scene sonst Cannot invoke "javafx.scene.control.ChoiceBox.getItems()" because "this.genderChoicebox" is null
	
	
	//Alter eingeben
	
	@FXML
	private Label ageLabel;
	private TextField ageTextfield;
	int age;
	
	public void ageEnter(ActionEvent event) {
		age = Integer.parseInt(ageTextfield.getText());
			
	}
//Alter Ende

	
	
	
	
	
	
}
