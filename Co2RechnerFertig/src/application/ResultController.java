package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ResultController {
	private DatabaseConnection databaseConnection;
	private Calculator calculator;
	private Connection connection;
	private Datenverwaltung datenverwaltung;

	@FXML
	private ChoiceBox<String> housingType, constructionYear, persons;
	@FXML
	private TextField area;
	@FXML
	private Button housingForwardButton;

	public ResultController() {
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getDatabaseConnection();
		datenverwaltung = new Datenverwaltung(connection);
		calculator = new Calculator(connection, datenverwaltung);
	}

	/**
	 * Konstruktor zur Initialisierung der Instanzvariablen.
	 */

	@FXML
	private BarChart<String, Number> barchart;

	public void showChart() {
	    displayDataInBarChart(barchart, connection);
	}

	@FXML
	public void switchToStart(ActionEvent event) throws IOException {
		Datenverwaltung.switchToPage("/FXML/Start.fxml", event);
	}

	public void displayDataInBarChart(BarChart<String, Number> barChart, Connection connection) {
		try {
			String[] columns = { "housing", "heating", "electricity", "food", "consumption", "planeandship",
					"vehicles" };
			List<Object[]> results = datenverwaltung.fetchAllDataFromTable(connection, "totalemission", columns);

			if (!results.isEmpty()) {
				Object[] row = results.get(0);

				Double housingValue = row[0] != null ? Double.valueOf(row[0].toString()) : 0.0;
				Double heatingValue = row[1] != null ? Double.valueOf(row[1].toString()) : 0.0;
				Double electricityValue = row[2] != null ? Double.valueOf(row[2].toString()) : 0.0;
				Double foodValue = row[3] != null ? Double.valueOf(row[3].toString()) : 0.0;
				Double consumptionValue = row[4] != null ? Double.valueOf(row[4].toString()) : 0.0;
				Double planeandshipValue = row[5] != null ? Double.valueOf(row[5].toString()) : 0.0;
				Double vehiclesValue = row[6] != null ? Double.valueOf(row[6].toString()) : 0.0;



				XYChart.Series<String, Number> series = new XYChart.Series<>();

				series.getData().add(new XYChart.Data<>("Housing", housingValue));
				series.getData().add(new XYChart.Data<>("Heating", heatingValue));
				series.getData().add(new XYChart.Data<>("Electricity", electricityValue));
				series.getData().add(new XYChart.Data<>("Vehicles", vehiclesValue));
				series.getData().add(new XYChart.Data<>("Plane and Ship", planeandshipValue));
				series.getData().add(new XYChart.Data<>("Food", foodValue));
				series.getData().add(new XYChart.Data<>("Consumption", consumptionValue));

				barChart.getData().clear(); // Vorherige Daten entfernen
				barChart.getData().add(series); // Neue Daten hinzufügen
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Geeignete Fehlerbehandlung implementieren
		}
	}
}

