package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private TextField nameField;
	private TextField ageField;
	private Button button;
	
////GUI Wechsel auf die Seite Wohnen	
@FXML
public void  switchToHousing(ActionEvent event) throws IOException {
	root = FXMLLoader.load(getClass().getResource("/FXML/Housing.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
}
	


private static Connection connection; // Annahme: Die Verbindung zur Datenbank ist bereits hergestellt.

public void personenDatenSpeichern() {


    // Textfelder für Name und Alter
    TextField nameField = new TextField();
    TextField ageField = new TextField();

    // Füge einen Event Listener für das Verlassen des Namensfelds hinzu
    nameField.setOnKeyPressed(event -> {
        if (event.getCode().isLetterKey()) { // Speichern, wenn eine Buchstabe-Taste gedrückt wird
            speichern(nameField.getText(), ageField.getText());
        }
    });

    // Füge einen Event Listener für das Verlassen des Altersfelds hinzu
    ageField.setOnKeyPressed(event -> {
        if (event.getCode().isLetterKey()) { // Speichern, wenn eine Buchstabe-Taste gedrückt wird
            speichern(nameField.getText(), ageField.getText());
        }
    });


}

// Methode zum Speichern der Daten in die Datenbank
private void speichern(String name, String alterStr) {
    try {
        int alter = Integer.parseInt(alterStr);

        // SQL-Query zum Einfügen der Daten in die Datenbank
        String sql = "INSERT INTO personendaten (name, alter) VALUES (?, ?)";
        
        // PreparedStatement erstellen, um SQL-Injektionen zu verhindern
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, alter);

        // SQL-Query ausführen
        preparedStatement.executeUpdate();

        // Aufräumen
        preparedStatement.close();

        // Erfolgsmeldung oder andere Aktionen hier ausführen
        System.out.println("Benutzerdaten erfolgreich gespeichert.");

    } catch (NumberFormatException e) {
        // Fehlerbehandlung, wenn die Alters-Eingabe keine gültige Zahl ist
        System.err.println("Ungültiges Alter: " + alterStr);
    } catch (SQLException e) {
        // Fehlerbehandlung für SQL-Fehler
        e.printStackTrace();
    }
}

}
 
