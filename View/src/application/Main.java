package application;
	
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage startStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/Main.fxml"));			
			Scene startScene = new Scene(root);		
			//startScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			startStage.setResizable(false);
//			String css = this.getClass().getResource("style.css").toExternalForm();
//			sceneStart.getStylesheets().add(css);
			
	
			startStage.setTitle("CO2-Rechner");
			startStage.setScene(startScene);
			startStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
