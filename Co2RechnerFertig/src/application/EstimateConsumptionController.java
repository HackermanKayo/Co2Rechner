package application;

import java.io.IOException;
import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * 
 * @author Jannik Schubert Die Klasse EstimateConsumptionController behandelt
 *         die funktionalität der Seite estimateConsumption.fxml Der Nutzer kann
 *         hier über verschiedene Eingaben seinen jährlichen Stromverbrauch
 *         schätzen lassen falls er diesen nicht kennt.
 *
 */
public class EstimateConsumptionController {
	private DatabaseConnection databaseConnection;
	private Calculator calculator;
	private Connection connection;
	private Datenverwaltung datenverwaltung;

	public EstimateConsumptionController() {
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getDatabaseConnection();
		datenverwaltung = new Datenverwaltung(connection);
		calculator = new Calculator(connection, datenverwaltung);
	}

	@FXML
	ToggleGroup tgLED, tgKleingeraete, tgFernseher, tgKlimaanlage, tgGefrierschrank, tgKuehlschrank, tgHerd, tgOfen,
			tgTrockner, tgWaschmaschine, tgSonstige;
	@FXML
	RadioButton keinLED, meistLED, alleLED, wenigKleingeräte, mediumKleingeräte, vielKleingeräte, einFernseher,
			zweiFernseher, mehrFernseher, klimaJa, klimaNein, gefrierNein, gefrierAlt, gefrierJung, gefrierNeu,
			kühlschrankNein, kühlschrankAlt, kühlschrankJung, kühlschrankNeu, herdGas, herdElektro, herdCeran,
			herdInduktion, ofenNein, ofenAlt, ofenJung, ofenNeu, trocknerNein, trocknerAlt, trocknerJung, trocknerNeu,
			waschmaschineNein, waschmaschineAlt, waschmaschineJung, waschmaschineNeu, großgerätWenig, großgerätMedium,
			großgerätViel;
	@FXML
	TextField estimatedValueLabel;
	@FXML
	Button consumptionValueButton, backToEleButton;

	@FXML
	public void switchToElectricity(ActionEvent event) throws IOException {
		Datenverwaltung.switchToPage("/FXML/Electricity.fxml", event);
	}

	/**
	 * Dem Label estimatedValueLabel wird der geschätzte Jahresverbrauch zugewiesen.
	 * Anhand der eingaben des Nutzers wird dieser berechnet und dann noch je nach
	 * dem wie viele Personen im Haushalt leben angepasst.
	 */

	@FXML
	public void calculateAndDisplayConsumption(ActionEvent event) {
		int numberOfPersons = datenverwaltung.getLastNumberOfPersons();
		double yearlyConsumption = estimateYearlyConsumption();
		double totalyearlyConsumption = yearlyConsumption * numberOfPersons;
		estimatedValueLabel.setText(String.format("%d", Math.round(totalyearlyConsumption)));


	}

	/**
	 * In dieser Methode wird anhand der eingaben des Nutzers ein geschätzter
	 * jährlicher Stromverbrauch ermittelt.
	 * 
	 */
	public double estimateYearlyConsumption() {
		double yearlyConsumption = 0;

		// LED-Verbrauchsschätzung
		if (meistLED.isSelected()) {
			yearlyConsumption += 100;
		} else if (alleLED.isSelected()) {
			yearlyConsumption += 200;
		}
		// Kleingeräte
		if (wenigKleingeräte.isSelected()) {
			yearlyConsumption += 50;
		} else if (mediumKleingeräte.isSelected()) {
			yearlyConsumption += 100;
		} else if (vielKleingeräte.isSelected()) {
			yearlyConsumption += 150;
		}
		// Fernseher
		if (einFernseher.isSelected()) {
			yearlyConsumption += 300;
		} else if (zweiFernseher.isSelected()) {
			yearlyConsumption += 600;
		} else if (mehrFernseher.isSelected()) {
			yearlyConsumption += 900;
		}
		// Klimaanlage
		if (klimaJa.isSelected()) {
			yearlyConsumption += 1200;
		}
		// Gefrierschrank
		if (gefrierAlt.isSelected()) {
			yearlyConsumption += 500;
		} else if (gefrierJung.isSelected()) {
			yearlyConsumption += 400;
		} else if (gefrierNeu.isSelected()) {
			yearlyConsumption += 300;
		}
		// Kühlschrank
		if (kühlschrankAlt.isSelected()) {
			yearlyConsumption += 400;
		} else if (kühlschrankJung.isSelected()) {
			yearlyConsumption += 300;
		} else if (kühlschrankNeu.isSelected()) {
			yearlyConsumption += 200;
		}
		// Herd
		if (herdGas.isSelected()) {
			yearlyConsumption += 100;
		} else if (herdElektro.isSelected()) {
			yearlyConsumption += 300;
		} else if (herdCeran.isSelected()) {
			yearlyConsumption += 250;
		} else if (herdInduktion.isSelected()) {
			yearlyConsumption += 220;
		}
		// Ofen
		if (ofenAlt.isSelected()) {
			yearlyConsumption += 400;
		} else if (ofenJung.isSelected()) {
			yearlyConsumption += 350;
		} else if (ofenNeu.isSelected()) {
			yearlyConsumption += 300;
		}
		// Trockner
		if (trocknerAlt.isSelected()) {
			yearlyConsumption += 600;
		} else if (trocknerJung.isSelected()) {
			yearlyConsumption += 550;
		} else if (trocknerNeu.isSelected()) {
			yearlyConsumption += 500;
		}
		// Waschmaschine
		if (waschmaschineAlt.isSelected()) {
			yearlyConsumption += 500;
		} else if (waschmaschineJung.isSelected()) {
			yearlyConsumption += 450;
		} else if (waschmaschineNeu.isSelected()) {
			yearlyConsumption += 400;
		}
		// Sonstige Großgeräte
		if (großgerätWenig.isSelected()) {
			yearlyConsumption += 200;
		} else if (großgerätMedium.isSelected()) {
			yearlyConsumption += 400;
		} else if (großgerätViel.isSelected()) {
			yearlyConsumption += 600;
		}

		return yearlyConsumption;
	}



}
