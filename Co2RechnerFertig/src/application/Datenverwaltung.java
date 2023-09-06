package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Datenverwaltung {
	private Stage stage;
	private Scene scene;
	private Parent root;

	private Connection connection;

	public Datenverwaltung(Connection connection) {
		this.connection = connection;
	}

	// Methode um die Nutzereingaben in der Datenbank zu speichern
	public void saveEntries(Connection connection, String sqlQuery, Object... parameters) {
		try {
			try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
				for (int i = 0; i < parameters.length; i++) {
					if (parameters[i] instanceof Integer) {
						preparedStatement.setInt(i + 1, (Integer) parameters[i]);
					} else if (parameters[i] instanceof Double) {
						preparedStatement.setDouble(i + 1, (Double) parameters[i]);
					} else if (parameters[i] instanceof String) {
						preparedStatement.setString(i + 1, (String) parameters[i]);
					} else {
						// Hier können Sie weitere Datentypen behandeln, falls erforderlich
					}
				}

				// Führe den SQL-Befehl aus, um die Daten in die Datenbank einzufügen
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				// Hier können Sie eine geeignete Fehlerbehandlung implementieren
			}
		} catch (NumberFormatException e) {
			// Das Textfeld enthält keine gültige Ganzzahl
			// Hier können Sie eine geeignete Fehlerbehandlung implementieren
		}
	}

	// Methode um die Daten aus der Datenbank abzurufen
	public List<Object[]> fetchDataFromTable(Connection connection, String tableName, String[] columns,
			String orderByColumn, int limit) {
		List<Object[]> dataList = new ArrayList<>();

		try {
			// Erstelle die SQL-Abfrage dynamisch basierend auf den übergebenen Parametern
			String selectSql = "SELECT " + String.join(", ", columns) + " FROM " + tableName + " ORDER BY "
					+ orderByColumn + " DESC LIMIT ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
				preparedStatement.setInt(1, limit);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						// Hier kannst du die Ergebnisse in ein Object-Array oder andere Datenstrukturen
						// speichern
						Object[] row = new Object[columns.length];
						for (int i = 0; i < columns.length; i++) {
							row[i] = resultSet.getObject(columns[i]);
						}
						dataList.add(row);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataList;
	}

	// Methode um die Fehlermeldungen bei Falscheingabe auszugeben.
	public void showAlert(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();

	}

	// Methode um zwischen den einzelnen Scenen zu wechseln

	public static void switchToPage(String pagePath, ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(Datenverwaltung.class.getResource(pagePath));
		Parent root = loader.load();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
