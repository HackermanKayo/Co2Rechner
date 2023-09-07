package application;

import java.io.IOException;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Diese Klasse ist der Controller für die GUI-Seite, die den Stromverbrauch und
 * andere strombezogene Eingaben des Benutzers verwaltet.
 */
public class ElectricityController {

	@FXML
	private ToggleGroup tgStrombezug;
	@FXML
	private TextField stromJahresverbrauch, erzeugungPV, eigenverbrauchPV;
	@FXML
	RadioButton ökoStrom,stromMix;

	private final Connection connection;
	private final Datenverwaltung datenverwaltung;
	private EstimateConsumptionController consumptioncontroller;

	/**
	 * Konstruktor zur Initialisierung der Datenbankverbindung und der
	 * Datenverwaltung.
	 */
	public ElectricityController() {
		this.connection = new DatabaseConnection().getDatabaseConnection();
		this.datenverwaltung = new Datenverwaltung(connection);
		this.consumptioncontroller = new EstimateConsumptionController();
	}

	/**
	 * Wechselt zur Seite "Heizung".
	 */
	@FXML
	public void switchToHeating(ActionEvent event) throws IOException {
		Datenverwaltung.switchToPage("/FXML/Heating.fxml", event);
	}

	/**
	 * Speichert die Nutzereingaben und wechselt zur Seite "Mobilität".
	 */
	@FXML
	public void switchToVehicles(ActionEvent event) throws IOException {
		if (eingabenSpeichern()) {
			Datenverwaltung.switchToPage("/FXML/Vehicles.fxml", event);
		}
	}

	/**
	 * Wechselt zur Seite "Strombedarf berechnen".
	 */
	@FXML
	public void switchToPowerConsumption(ActionEvent event) throws IOException {
		Datenverwaltung.switchToPage("/FXML/estimateConsumption.fxml", event);
	}

	
	public double estimateYearlyCO2Emission() {
	    double baseEmissionValue = 2000; // Ein Basiswert
	    double emissionFactor = 1.0;

	    // Extrahiere den Verbrauchswert und die PV-Werte aus den Textfeldern
	    double consumption = 0.0;
	    double pvProduction = 0.0;
	    double pvSelfConsumption = 0.0;
	    try {
	        consumption = Double.parseDouble(stromJahresverbrauch.getText());
	        pvProduction = Double.parseDouble(erzeugungPV.getText());
	        pvSelfConsumption = Double.parseDouble(eigenverbrauchPV.getText());
	    } catch (NumberFormatException e) {
	        System.out.println("Fehler bei der Umwandlung der Werte.");
	    }

	    // Bestimme die Verbrauchskategorie
	    String consumptionCategory;
	    if (consumption <= 2500) {
	        consumptionCategory = "0-2500";
	    } else if (consumption <= 5000) {
	        consumptionCategory = "2501-5000";
	    } else if (consumption <= 10000) {
	        consumptionCategory = "5001-10000";
	    } else {
	        consumptionCategory = "10000+";
	    }

	    // Definition der Optionen und Faktoren
	    RadioButton mix = stromMix; 
	    RadioButton öko = ökoStrom; 
	    // ... Weitere Radiobuttons

	    RadioButton[][] optionsGroups = {
	        {mix, öko} // Erweitern Sie dies entsprechend den Radiobuttons
	    };

	    String[][] factorsGroups = {
	        {"erneurbar", "fossil"} // Erweitern Sie dies entsprechend den Radiobuttons
	    };

	    double[][] factorsValues = {
	        {1.2, 0.5} // Faktoren für Stromquelle
	    };

	    // Abgleich der ausgewählten Optionen mit den Faktoren
	    for (int i = 0; i < optionsGroups.length; i++) {
	        RadioButton[] optionGroup = optionsGroups[i];
	        String[] factors = factorsGroups[i];
	        double[] values = factorsValues[i];

	        for (int j = 0; j < optionGroup.length; j++) {
	            if (optionGroup[j].isSelected()) {
	                emissionFactor *= values[j];
	                break;
	            }
	        }
	    }

	    // Gesamtemission berechnen und den positiven Effekt von PV berücksichtigen
	    double totalEmission = (baseEmissionValue * emissionFactor + consumption) 
	                            - (pvProduction - pvSelfConsumption);

	    System.out.println("Strom CO2-Ausstoß: " + totalEmission);
	    return totalEmission;
	}

	
	
	
	
	/**
	 * Speichert die Nutzereingaben in der Datenbank.
	 * 
	 * @return true, wenn die Eingaben erfolgreich gespeichert wurden, sonst false.
	 */
	private boolean eingabenSpeichern() {
		RadioButton selectedRadio = (RadioButton) tgStrombezug.getSelectedToggle();
		String selectedRadiobutton = (selectedRadio != null) ? selectedRadio.getText() : null;

		String jahresverbrauch = stromJahresverbrauch.getText();
		String pvErzeugung = erzeugungPV.getText();
		String pvEigenverbrauch = eigenverbrauchPV.getText();

		// Überprüfen, ob der Radiobutton ausgewählt ist
		if (selectedRadiobutton == null) {
			datenverwaltung.showAlert("Fehler", "Bitte den Strombezug auswählen.");
			return false;
		}

		// Überprüfen, ob das Textfeld stromJahresverbrauch leer ist
		if (jahresverbrauch.trim().isEmpty()) {
			datenverwaltung.showAlert("Fehler", "Bitte den Jahresverbrauch in kWh angeben oder schätzen. "
					+ "Durch klick auf den Button gelangen Sie zur Ansicht zum schätzen");
			return false;
		}

		double jahresverbrauchValue;
		double pvErzeugungValue = 0.0; 
		double pvEigenverbrauchValue = 0.0;

		try {
			// Überprüfen, ob der Jahresverbrauch eine gültige numerische Zahl ist
			jahresverbrauchValue = Double.parseDouble(jahresverbrauch);

			// Überprüfen, ob pvErzeugung und pvEigenverbrauch gültige numerische Werte
			// enthalten, wenn sie nicht leer sind
			if (!pvErzeugung.trim().isEmpty()) {
				pvErzeugungValue = Double.parseDouble(pvErzeugung);
			}

			if (!pvEigenverbrauch.trim().isEmpty()) {
				pvEigenverbrauchValue = Double.parseDouble(pvEigenverbrauch);
			}

			String insertSql = "INSERT INTO electricity(strombezug, jahresverbrauch, erzeugungPV, eigenverbrauchPV) VALUES (?,?,?,?)";
			datenverwaltung.saveEntries(connection,"electricity", insertSql, selectedRadiobutton, jahresverbrauchValue,
			pvErzeugungValue, pvEigenverbrauchValue);
			
			double estimatedEmission = estimateYearlyCO2Emission();
		    String insertSql1 = "UPDATE totalemission SET electricity =? WHERE ID=1";
			datenverwaltung.updateEntry(connection, "totalemission", insertSql1, estimatedEmission);

			return true;
		} catch (NumberFormatException e) {
			datenverwaltung.showAlert("Fehler", "Bitte gültige numerische Werte eingeben.");
			return false;
		}
	}
	
	// ...





}
