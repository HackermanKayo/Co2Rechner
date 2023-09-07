package application;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.util.ArrayList;

public class EstimateConsumptionController {
	private DatabaseConnection databaseConnection;
	private Calculator calculator;
	private Connection connection;
	private Datenverwaltung datenverwaltung = new Datenverwaltung(connection);

	public EstimateConsumptionController() {
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getDatabaseConnection();
		datenverwaltung = new Datenverwaltung(connection);
		calculator = new Calculator(connection, datenverwaltung);
	}
	
	@FXML
	ToggleGroup tgLED,tgKleingeraete,tgFernseher,tgKlimaanlage,tgGefrierschrank,tgKuehlschrank,tgHerd,tgOfen,tgTrockner,tgWaschmaschine,tgSonstige;
	@FXML
	RadioButton keinLED,meistLED,alleLED,wenigKleingeräte,mediumKleingeräte,vielKleingeräte,einFernseher,zweiFernseher,mehrFernseher,klimaJa,klimaNein,
	gefrierNein,gefrierAlt,gefrierJung,gefrierNeu,kühlschrankNein,kühlschrankAlt,kühlschrankJung,kühlschrankNeu,herdGas,herdElektro,herdCeran,herdInduktion,
	ofenNein,ofenAlt,ofenJung,ofenNeu,trocknerNein,trocknerAlt,trocknerJung,trocknerNeu,waschmaschineNein,waschmaschineAlt,waschmaschineJung,waschmaschineNeu,
	großgerätWenig,großgerätMedium,großgerätViel;
	
	
	//GUI Wechsel auf die Seite Strom
		@FXML
		public void switchToElectricity(ActionEvent event) throws IOException {
			eingabenSpeichern();
			Datenverwaltung.switchToPage("/FXML/Electricity.fxml", event);
		}
	
	
		private void eingabenSpeichern() {
		    List<ToggleGroup> toggleGroups = Arrays.asList(
		        tgLED, tgKleingeraete, tgFernseher, tgKlimaanlage, tgGefrierschrank,
		        tgKuehlschrank, tgHerd, tgOfen, tgTrockner, tgWaschmaschine, tgSonstige
		    );

		    List<String> selectedRadioButtons = new ArrayList<>();

		    for (ToggleGroup tg : toggleGroups) {
		        RadioButton selected = (RadioButton) tg.getSelectedToggle();
		        if (selected != null) {
		            selectedRadioButtons.add(selected.getText());
		        } else {
		            datenverwaltung.showAlert("Fehler", "Bitte wählen Sie eine Option für alle Geräte aus.");
		            return;
		        }
		    }

		    try {
		        // Hier wird davon ausgegangen, dass Ihre Datenbank-Tabelle `estimateconsumption` alle Radiobutton-Felder enthält.
		        // Falls die Namen der Spalten in Ihrer Datenbank anders sind, passen Sie diese bitte entsprechend an.
		        String insertSql = "INSERT INTO estimateconsumption(led, kleingeräte, fernseher, klimaanlage, gefrierschrank, kühlschrank, herd, ofen, trockner, waschmaschine, großgeräte) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		        
		        datenverwaltung.saveEntries(connection,"estimateconsumption", insertSql, selectedRadioButtons.toArray());

		    } catch (Exception e) {
		        datenverwaltung.showAlert("Fehler", "Ein Fehler ist aufgetreten beim Speichern der Eingaben.");
		    }
		}

		
}
