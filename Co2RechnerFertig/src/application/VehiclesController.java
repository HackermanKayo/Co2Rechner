package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Jannik Die Klasse VehiclesController steuert die funktionalität der
 *         GUI-Seite Vehicles(Mobilität) Nutzereingaben werden
 *         erkannt,verarbeitet und in der Datenbank gespeichert.
 */
public class VehiclesController {

	private DatabaseConnection databaseConnection;
	private Calculator calculator;
	private Connection connection;
	private Datenverwaltung datenverwaltung = new Datenverwaltung(connection);

	@FXML
	ChoiceBox<String> artFahrzeugEins, artFahrzeugZwei, artFahrzeugDrei, alterFahrzeugEins, alterFahrzeugZwei,
			alterFahrzeugDrei, kraftstoffFahrzeugEins, kraftstoffFahrzeugZwei, kraftstoffFahrzeugDrei;
	@FXML
	TextField verbrauchFahrzeugEins, verbrauchFahrzeugZwei, verbrauchFahrzeugDrei, fahrleistungFahrzeugEins,
			fahrleistungFahrzeugZwei, fahrleistungFahrzeugDrei, fahrleistungBus, fahrleistungFahrrad;

	public VehiclesController() {
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getDatabaseConnection();
		datenverwaltung = new Datenverwaltung(connection);
		calculator = new Calculator(connection, datenverwaltung);
	}

	@FXML
	private void initialize() {
		ObservableList<String> fahrzeugTypList = FXCollections.observableArrayList("Kleinwagen mit Verbrennungsmotor",
				"Oberklasse mit Verbrennungsmotor", "Hybrid", "Elektrofahrzeug", "Motorrad");

		ObservableList<String> fahrzeugAlterList = FXCollections.observableArrayList("Bis 3 Jahre", "4-6 Jahre",
				"7-10 Jahre", "11-15 Jahre", "16-25 Jahre", "Oldtimer");

		ObservableList<String> kraftstoffArtList = FXCollections.observableArrayList("Benzin", "Diesel", "Erdgas",
				"Flüssiggas", "Strom");

		artFahrzeugEins.setItems(fahrzeugTypList);
		artFahrzeugZwei.setItems(fahrzeugTypList);
		artFahrzeugDrei.setItems(fahrzeugTypList);

		alterFahrzeugEins.setItems(fahrzeugAlterList);
		alterFahrzeugZwei.setItems(fahrzeugAlterList);
		alterFahrzeugDrei.setItems(fahrzeugAlterList);

		kraftstoffFahrzeugEins.setItems(kraftstoffArtList);
		kraftstoffFahrzeugZwei.setItems(kraftstoffArtList);
		kraftstoffFahrzeugDrei.setItems(kraftstoffArtList);
	}

//GUI Wechsel auf die Seite FlugUndSchiffsreisen
	@FXML
	public void switchToPlaneAndShip(ActionEvent event) throws IOException {
		eingabenSpeichern();
		Datenverwaltung.switchToPage("/FXML/PlaneAndShip.fxml", event);
	}

//GUI Wechsel auf die Seite Strom
	@FXML
	public void switchToElectricity(ActionEvent event) throws IOException {
		Datenverwaltung.switchToPage("/FXML/Electricity.fxml", event);
	}

	public double estimateVehicleYearlyCO2Emission() {
		double totalEmission = 0;

		// Beispielwerte für Emissionsraten.
		Map<String, Double> typeEmissionRates = Map.of("Kleinwagen mit Verbrennungsmotor", 100.0,
				"Oberklasse mit Verbrennungsmotor", 200.0, "Hybrid", 70.0, "Elektrofahrzeug", 30.0, "Motorrad", 80.0);

		Map<String, Double> ageEmissionRates = Map.of("Bis 3 Jahre", 1.0, "4-6 Jahre", 1.2, "7-10 Jahre", 1.3,
				"11-15 Jahre", 1.5, "16-25 Jahre", 1.8, "Oldtimer", 2.0);

		Map<String, Double> fuelEmissionRates = Map.of("Benzin", 1.0, "Diesel", 1.2, "Erdgas", 0.9, "Flüssiggas", 0.8,
				"Strom", 0.5);

		// Fahrzeugdaten
		ChoiceBox<String>[] typeChoiceBoxes = new ChoiceBox[] { artFahrzeugEins, artFahrzeugZwei, artFahrzeugDrei };
		ChoiceBox<String>[] ageChoiceBoxes = new ChoiceBox[] { alterFahrzeugEins, alterFahrzeugZwei,
				alterFahrzeugDrei };
		ChoiceBox<String>[] fuelChoiceBoxes = new ChoiceBox[] { kraftstoffFahrzeugEins, kraftstoffFahrzeugZwei,
				kraftstoffFahrzeugDrei };
		TextField[] consumptionFields = new TextField[] { verbrauchFahrzeugEins, verbrauchFahrzeugZwei,
				verbrauchFahrzeugDrei };
		TextField[] distanceFields = new TextField[] { fahrleistungFahrzeugEins, fahrleistungFahrzeugZwei,
				fahrleistungFahrzeugDrei };

		for (int i = 0; i < 3; i++) {
			try {
				if (!distanceFields[i].getText().trim().isEmpty()) {
					double typeRate = typeEmissionRates.get(typeChoiceBoxes[i].getValue());
					double ageRate = ageEmissionRates.get(ageChoiceBoxes[i].getValue());
					double fuelRate = fuelEmissionRates.get(fuelChoiceBoxes[i].getValue());
					double consumption = Double.parseDouble(consumptionFields[i].getText());
					double distance = Double.parseDouble(distanceFields[i].getText());

					totalEmission += typeRate * ageRate * fuelRate * consumption * distance;
				}
			} catch (NumberFormatException e) {
				System.out.println("Fehler bei der Umwandlung der Fahrzeugdaten für Fahrzeug " + (i + 1));
			} catch (NullPointerException e) {
				System.out.println("Einer der benötigten Werte für Fahrzeug " + (i + 1)
						+ " fehlt oder wurde nicht korrekt ausgewählt.");
			}
		}

		// Berechnung für den ÖPNV
		try {
			if (!fahrleistungBus.getText().trim().isEmpty()) {
				double busDistance = Double.parseDouble(fahrleistungBus.getText());
				double busEmissionPerKm = 0.5;
				totalEmission += busDistance * busEmissionPerKm;
			}
		} catch (NumberFormatException e) {
			System.out.println("Fehler bei der Umwandlung der Busfahrleistung.");
		}

		// Emissionsreduktion für das Fahrrad
		try {
			if (!fahrleistungFahrrad.getText().trim().isEmpty()) {
				double bikeDistance = Double.parseDouble(fahrleistungFahrrad.getText());
				double bikeEmissionReductionPerKm = 0.1;
				totalEmission -= bikeDistance * bikeEmissionReductionPerKm;
			}
		} catch (NumberFormatException e) {
			System.out.println("Fehler bei der Umwandlung der Fahrradfahrleistung.");
		}

		System.out.println("Fahrzeug CO2-Ausstoß: " + totalEmission);

		double estimatedEmission = estimateVehicleYearlyCO2Emission();
		String insertSql1 = "INSERT INTO totalemission (vehicles) VALUES (?)";
		datenverwaltung.saveEntries(connection, "totalemission", insertSql1, estimatedEmission);

		return totalEmission;
	}

	// Methode um die Nutzereingaben auf der Seite Mobilität zu speichern. Dabei
	// können zwischen 0-3 Fahrzeugen eingetragen werden.
	// Diese werden dann in der Datenbank zusammen in einer Zeile abgespeichert
	@FXML
	private void eingabenSpeichern() {
		List<String> columns = new ArrayList<>();
		List<String> placeholders = new ArrayList<>();
		List<Object> values = new ArrayList<>();

		String[] fahrzeugNames = { "Eins", "Zwei", "Drei" };

		for (String fahrzeugName : fahrzeugNames) {
			ChoiceBox<String> artFahrzeug = getArtFahrzeug(fahrzeugName);
			ChoiceBox<String> alterFahrzeug = getAlterFahrzeug(fahrzeugName);
			ChoiceBox<String> kraftstoffFahrzeug = getKraftstoffFahrzeug(fahrzeugName);
			TextField verbrauchFahrzeug = getVerbrauchFahrzeug(fahrzeugName);
			TextField fahrleistungFahrzeug = getFahrleistungFahrzeug(fahrzeugName);

			if (artFahrzeug != null && alterFahrzeug != null && kraftstoffFahrzeug != null && verbrauchFahrzeug != null
					&& fahrleistungFahrzeug != null) {
				String art = artFahrzeug.getValue();
				String alter = alterFahrzeug.getValue();
				String kraftstoff = kraftstoffFahrzeug.getValue();
				String verbrauch = verbrauchFahrzeug.getText();
				String fahrleistung = fahrleistungFahrzeug.getText();

				if (art != null && alter != null && kraftstoff != null && verbrauch != null && fahrleistung != null) {
					columns.addAll(Arrays.asList("artFahrzeug" + fahrzeugName, "alterFahrzeug" + fahrzeugName,
							"kraftstoffFahrzeug" + fahrzeugName, "verbrauchFahrzeug" + fahrzeugName,
							"fahrleistungFahrzeug" + fahrzeugName));
					placeholders.addAll(Arrays.asList("?", "?", "?", "?", "?"));
					values.addAll(Arrays.asList(art, alter, kraftstoff, verbrauch, fahrleistung));
				}
			}
		}

		// Weitere Eingaben für Fahrrad und Bus
		String fahrleistungFahrradF = fahrleistungFahrrad.getText();
		String fahrleistungBusB = fahrleistungBus.getText();

		if (fahrleistungFahrradF != null) {
			columns.add("fahrleistungFahrrad");
			placeholders.add("?");
			values.add(fahrleistungFahrradF);
		}

		if (fahrleistungBusB != null) {
			columns.add("fahrleistungBus");
			placeholders.add("?");
			values.add(fahrleistungBusB);
		}

		try {
			if (!columns.isEmpty()) {
				String insertSql = "INSERT INTO vehicles(" + String.join(", ", columns) + ") VALUES ("
						+ String.join(", ", placeholders) + ")";
				datenverwaltung.saveEntries(connection, "vehicles", insertSql, values.toArray());
			}
		} catch (Exception e) {
			datenverwaltung.showAlert("Fehler",
					"Beim Speichern der Fahrzeuge ist ein Fehler aufgetreten: " + e.getMessage());
		}
	}

	// Hilfsmethoden, um auf ChoiceBox und TextField zuzugreifen
	private ChoiceBox<String> getArtFahrzeug(String fahrzeugName) {
		return switch (fahrzeugName) {
		case "Eins" -> artFahrzeugEins;
		case "Zwei" -> artFahrzeugZwei;
		case "Drei" -> artFahrzeugDrei;
		default -> null;
		};
	}

	private ChoiceBox<String> getAlterFahrzeug(String fahrzeugName) {
		return switch (fahrzeugName) {
		case "Eins" -> alterFahrzeugEins;
		case "Zwei" -> alterFahrzeugZwei;
		case "Drei" -> alterFahrzeugDrei;
		default -> null;
		};
	}

	private ChoiceBox<String> getKraftstoffFahrzeug(String fahrzeugName) {
		return switch (fahrzeugName) {
		case "Eins" -> kraftstoffFahrzeugEins;
		case "Zwei" -> kraftstoffFahrzeugZwei;
		case "Drei" -> kraftstoffFahrzeugDrei;
		default -> null;
		};
	}

	private TextField getVerbrauchFahrzeug(String fahrzeugName) {
		return switch (fahrzeugName) {
		case "Eins" -> verbrauchFahrzeugEins;
		case "Zwei" -> verbrauchFahrzeugZwei;
		case "Drei" -> verbrauchFahrzeugDrei;
		default -> null;
		};
	}

	private TextField getFahrleistungFahrzeug(String fahrzeugName) {
		return switch (fahrzeugName) {
		case "Eins" -> fahrleistungFahrzeugEins;
		case "Zwei" -> fahrleistungFahrzeugZwei;
		case "Drei" -> fahrleistungFahrzeugDrei;
		default -> null;
		};
	}
}
