package application;

import java.io.IOException;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Der Controller für die GUI-Seite zur Erfassung von Ernährungsdaten.
 */
public class FoodController {

    private DatabaseConnection databaseConnection;
    private Calculator calculator;
    private Connection connection;
    private Datenverwaltung datenverwaltung = new Datenverwaltung(connection);

    /**
     * Konstruktor für den Controller, der die Datenbankverbindung initialisiert.
     */
    public FoodController() {
        databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getDatabaseConnection();
        datenverwaltung = new Datenverwaltung(connection);
        calculator = new Calculator(connection, datenverwaltung);
    }

    @FXML
    ToggleGroup tgTaetigkeit, tgSport, tgErnaehrungsform, tgBio, tgRegional, tgSaison;
    @FXML
    RadioButton wenigKoerperlich, mediumKoerperlich, vielKoerperlich, keinSport, mediumSport, vielSport, fleischBetont,
            mischkost, fleischreduziert, vegetarisch, vegan, vielBio, mediumBio, wenigBio, wenigRegional,
            mediumRegional, vielRegional, vielSaisonal, mediumSaisonal, wenigSaisonal;
    @FXML
    TextField weight;

    /**
     * Wechselt zur Seite "Konsum" und speichert die eingegebenen Ernährungsdaten.
     *
     * @param event Das auslösende Ereignis.
     * @throws IOException Wenn ein Fehler beim Laden der Seite auftritt.
     */
    @FXML
    public void switchToConsumption(ActionEvent event) throws IOException {
        if (eingabenSpeichern()) {
        Datenverwaltung.switchToPage("/FXML/Consumption.fxml", event);
        }
    }

    /**
     * Wechselt zur Seite "Flug und Schiffreisen".
     *
     * @param event Das auslösende Ereignis.
     * @throws IOException Wenn ein Fehler beim Laden der Seite auftritt.
     */
    @FXML
    public void switchToPlaneShip(ActionEvent event) throws IOException {
        Datenverwaltung.switchToPage("/FXML/PlaneAndShip.fxml", event);
    }

    /**
     * Initialisiert die ToggleGroups für die RadioButtons.
     */
    public void initialize() {
    	tgTaetigkeit = new ToggleGroup();
	    	wenigKoerperlich.setToggleGroup(tgTaetigkeit);
	        mediumKoerperlich.setToggleGroup(tgTaetigkeit);
	        vielKoerperlich.setToggleGroup(tgTaetigkeit);
        tgSport = new ToggleGroup();
	        keinSport.setToggleGroup(tgSport);
	        mediumSport.setToggleGroup(tgSport);
	        vielSport.setToggleGroup(tgSport);
        tgErnaehrungsform = new ToggleGroup();
	        fleischBetont.setToggleGroup(tgErnaehrungsform);
	        mischkost.setToggleGroup(tgErnaehrungsform);
	        fleischreduziert.setToggleGroup(tgErnaehrungsform);
	        vegetarisch.setToggleGroup(tgErnaehrungsform);
	        vegan.setToggleGroup(tgErnaehrungsform);
        tgBio = new ToggleGroup();
	        vielBio.setToggleGroup(tgBio);
	        mediumBio.setToggleGroup(tgBio);
	        wenigBio.setToggleGroup(tgBio);
        tgRegional = new ToggleGroup();
	        vielRegional.setToggleGroup(tgRegional);
	        mediumRegional.setToggleGroup(tgRegional);
	        wenigRegional.setToggleGroup(tgRegional);
        tgSaison = new ToggleGroup();
	        vielSaisonal.setToggleGroup(tgSaison);
	        mediumSaisonal.setToggleGroup(tgSaison);
	        wenigSaisonal.setToggleGroup(tgSaison);
    }

    /**
     * Speichert die eingegebenen Ernährungsdaten in der Datenbank.
     */
    private boolean eingabenSpeichern() {
        String gewichtText = weight.getText();
        RadioButton selectedRadioButton = (RadioButton) tgTaetigkeit.getSelectedToggle();
        RadioButton selectedRadioButton2 = (RadioButton) tgSport.getSelectedToggle();
        RadioButton selectedRadioButton3 = (RadioButton) tgErnaehrungsform.getSelectedToggle();
        RadioButton selectedRadioButton4 = (RadioButton) tgBio.getSelectedToggle();
        RadioButton selectedRadioButton5 = (RadioButton) tgRegional.getSelectedToggle();
        RadioButton selectedRadioButton6 = (RadioButton) tgSaison.getSelectedToggle();

        if (gewichtText == null || selectedRadioButton == null || selectedRadioButton2 == null
                || selectedRadioButton3 == null || selectedRadioButton4 == null || selectedRadioButton5 == null
                || selectedRadioButton6 == null) {
            datenverwaltung.showAlert("Fehler", "Bitte geben Sie alle erforderlichen Werte ein.");
            return false;
        }

        try {
            // Aufruf der Methode saveEntries() in der Klasse Datenverwaltung
            String insertSql = "INSERT INTO food(gewicht, tätigkeit, sport, ernährungsform, bio, regional, saisonal) VALUES (?,?,?,?,?,?,?)";
            datenverwaltung.saveEntries(connection, insertSql, gewichtText, selectedRadioButton.getText(),
                    selectedRadioButton2.getText(), selectedRadioButton3.getText(), selectedRadioButton4.getText(),
                    selectedRadioButton5.getText(), selectedRadioButton6.getText());
        } catch (NumberFormatException e) {
            datenverwaltung.showAlert("Fehler", "Bitte geben Sie gültige Werte ein.");
            return false;
        }
        
        return true; // Erfolgreich gespeichert
    }

}
