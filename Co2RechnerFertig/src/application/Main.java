package application;

import java.io.IOException;
import java.sql.Connection;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

public class Main extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage startStage) {
        try {    
            // Versuchen, die Startseite zu laden und anzuzeigen
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Start.fxml"));
            Scene startScene = new Scene(root);        
            startStage.setResizable(false);
            startStage.setTitle("CO2-Rechner");
            startStage.setScene(startScene);
            startStage.show();

            // Versuchen, eine Datenbankverbindung herzustellen
            DatabaseConnection connection = new DatabaseConnection();
            Connection dbConnection = connection.getDatabaseConnection();

        } catch (IOException e) {
            // Fehlerbehandlung für Input/Output-Ausnahmen
            System.err.println("Ein Fehler ist beim Laden der FXML-Datei aufgetreten: " + e.getMessage());
            e.printStackTrace();

            // Anzeige eines Dialogs für den Benutzer
            showAlert("Fehler", "Ein Fehler ist beim Laden der Anwendung aufgetreten. Bitte versuchen Sie es später erneut.");

        } catch (Exception e) {
            // Generelle Fehlerbehandlung
            System.err.println("Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage());
            e.printStackTrace();

            // Anzeige eines Dialogs für den Benutzer
            showAlert("Fehler", "Ein unerwarteter Fehler ist aufgetreten. Bitte kontaktieren Sie den Support.");
        }
    }    

    // Hilfsmethode zur Anzeige von Alert-Dialogen
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
