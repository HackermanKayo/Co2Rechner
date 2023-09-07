package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
		public void saveEntries(Connection connection, String tableName, String sqlQuery, Object... parameters) {
			// Schritt 1: Es werden alle bisherigen Einträge aus der Tabelle gelöscht.
			String deleteQuery = "DELETE FROM " + tableName;
			try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
				deleteStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				// Hier können Sie eine geeignete Fehlerbehandlung implementieren
			}

			// Schritt 2: Fügen Sie den neuen Eintrag hinzu
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
			} catch (NumberFormatException e) {
				// Das Textfeld enthält keine gültige Ganzzahl
				// Hier können Sie eine geeignete Fehlerbehandlung implementieren
			}
		}

	// Methode um die Daten aus der Datenbank abzurufen
		public static List<Object[]> fetchAllDataFromTable(Connection connection, String tableName, String[] columns) {
		    List<Object[]> dataList = new ArrayList<>();

		    try {
		        String selectSql;
		        if (columns == null || columns.length == 0) {
		            selectSql = "SELECT * FROM " + tableName;
		        } else {
		            selectSql = "SELECT " + String.join(", ", columns) + " FROM " + tableName;
		        }

		        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
		             ResultSet resultSet = preparedStatement.executeQuery()) {

		            while (resultSet.next()) {
		                if (columns == null || columns.length == 0) {
		                    ResultSetMetaData metaData = resultSet.getMetaData();
		                    int columnCount = metaData.getColumnCount();
		                    Object[] row = new Object[columnCount];
		                    for (int i = 1; i <= columnCount; i++) {
		                        row[i - 1] = resultSet.getObject(i);
		                    }
		                    dataList.add(row);
		                } else {
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
		public static List<Object[]> fetchDataByColumnValue(Connection connection, String tableName, String columnName,
				Object value, String[] columns) {
			List<Object[]> dataList = new ArrayList<>();

			try {
				String selectSql = "SELECT " + String.join(", ", columns) + " FROM " + tableName + " WHERE " + columnName
						+ " = ?";

				try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
					if (value instanceof Integer) {
						preparedStatement.setInt(1, (Integer) value);
					} else if (value instanceof BigDecimal) {
						preparedStatement.setBigDecimal(1, (BigDecimal) value);
					} else if (value instanceof String) {
						preparedStatement.setString(1, (String) value);
					} else {
						throw new IllegalArgumentException("Nicht unterstützter Wertetyp: " + value.getClass());
					}

					try (ResultSet resultSet = preparedStatement.executeQuery()) {
						while (resultSet.next()) {
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
