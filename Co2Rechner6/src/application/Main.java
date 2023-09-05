package application;
	
import java.sql.Connection;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage startStage) {
		
// Erzeugt die Startseite(Scene) mit dem Titel CO2-Rechner
	try {	
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/Start.fxml"));
		Scene startScene = new Scene(root);		
		startStage.setResizable(false);
		startStage.setTitle("CO2-Rechner");
		startStage.setScene(startScene);
		startStage.show();
			
//Aufruf der Methode zum verbinden zur MySQL-Datenbank aus der Klasse DatabaseConnection.
		DatabaseConnection connection = new DatabaseConnection();
		Connection dbConnection = connection.getDatabaseConnection(); // Verbindung holen

			
		} 
	catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	

}
