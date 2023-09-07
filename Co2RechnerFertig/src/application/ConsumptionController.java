package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ConsumptionController implements Initializable {

	private DatabaseConnection databaseConnection;
	private Connection connection;
	private Datenverwaltung datenverwaltung = new Datenverwaltung(connection);

	public ConsumptionController() {
	    databaseConnection = new DatabaseConnection();
	    connection = databaseConnection.getDatabaseConnection();
	    datenverwaltung = new Datenverwaltung(connection);
	}
	
//GUI Wechsel auf die Seite Auswertung
@FXML
public void  switchToResult(ActionEvent event) throws IOException {
	eingabenSpeichern();
	Datenverwaltung.switchToPage("/FXML/Result.fxml", event);
		}
//GUI Wechsel auf die Seite Ernährung
@FXML
public void  switchToFood(ActionEvent event) throws IOException {

	Datenverwaltung.switchToPage("/FXML/Food.fxml", event);
		}









//Label welches die angegeben Konsumausgaben ausgibt
@FXML
Label ausgabenLabel;
//Regler um die Konsumausgaben einzugeben
@FXML
Slider ausgabenSlider;
@FXML
ToggleGroup tgKaufverhalten,tgKaufkriterien,tgGebrauchtes;
@FXML
RadioButton lowKonsum,mediumKonsum,highKonsum,langlebig,funktionalitaet,billig,highGebraucht,mediumGebraucht,lowGebraucht;


int konsumAusgaben;

//Methode um die Konsumausgaben die mittels Regler angegeben werden, durch aktualisierung des Labels unterhalb des Reglers live anzugeben
@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
	
	tgKaufverhalten = new ToggleGroup();
	lowKonsum.setToggleGroup(tgKaufverhalten);
	mediumKonsum.setToggleGroup(tgKaufverhalten);
	highKonsum.setToggleGroup(tgKaufverhalten);
		tgKaufkriterien = new ToggleGroup();
		langlebig.setToggleGroup(tgKaufkriterien);
		funktionalitaet.setToggleGroup(tgKaufkriterien);
		billig.setToggleGroup(tgKaufkriterien);
		tgGebrauchtes = new ToggleGroup();
		highGebraucht.setToggleGroup(tgGebrauchtes);
		mediumGebraucht.setToggleGroup(tgGebrauchtes);
		lowGebraucht.setToggleGroup(tgGebrauchtes);
	
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


public double estimateYearlyCO2Emission() {
    double baseEmissionValue = 2000;
    double emissionFactor = 1.0;

    RadioButton[][] optionsGroups = {
        {lowKonsum, mediumKonsum, highKonsum},
        {langlebig, funktionalitaet, billig},
        {highGebraucht, mediumGebraucht, lowGebraucht}
    };

    double[][] factorsGroups = {
        {0.8, 1.0, 1.2},
        {0.9, 1.0, 1.1},
        {0.7, 0.9, 1.1}
    };

    for (int i = 0; i < optionsGroups.length; i++) {
        RadioButton[] options = optionsGroups[i];
        double[] factors = factorsGroups[i];

        for (int j = 0; j < options.length; j++) {
            if (options[j].isSelected()) {
                emissionFactor *= factors[j];
                break;  // Exit the inner loop as soon as a match is found.
            }
        }
    }

    double ausgaben = ausgabenSlider.getValue();
    emissionFactor = ausgaben*0.12;

    double totalEmission = baseEmissionValue * emissionFactor;
    long roundedTotalEmission = Math.round(totalEmission);
    datenverwaltung.showAlert("Werte", "CO2-Emission im Sektor Wohnen "+roundedTotalEmission);
    return totalEmission;
}






private void eingabenSpeichern() {
    String ausgabenText = ausgabenLabel.getText();
    String selectedRadioButton = ((RadioButton) tgKaufverhalten.getSelectedToggle()).getText();
    String selectedRadioButton2 = ((RadioButton) tgKaufkriterien.getSelectedToggle()).getText();
    String selectedRadioButton3 = ((RadioButton) tgGebrauchtes.getSelectedToggle()).getText();

    if (ausgabenText == null || selectedRadioButton == null || selectedRadioButton2 == null
            || selectedRadioButton3 == null) {
        datenverwaltung.showAlert("Fehler", "Bitte geben Sie alle erforderlichen Werte ein.");
        return;
    }

    try {
        // Aufruf der Methode saveEntries() in der Klasse Datenverwaltung
        String insertSql = "INSERT INTO consumption(ausgaben, kaufverhalten, kaufkriterien, gebraucht) VALUES (?,?,?,?)";
        datenverwaltung.saveEntries(connection,"consumption", insertSql, ausgabenText, selectedRadioButton, selectedRadioButton2, selectedRadioButton3);
        
        double calculatedEmission = estimateYearlyCO2Emission();
		String insertSql1 = "UPDATE totalemission SET consumption =? WHERE ID=1";
		datenverwaltung.updateEntry(connection, "totalemission", insertSql1, calculatedEmission);

    } catch (NumberFormatException e) {
        datenverwaltung.showAlert("Fehler", "Bitte geben Sie die fehlenden Werte ein.");
    }
}





}
