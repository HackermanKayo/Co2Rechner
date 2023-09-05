package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class DatabaseQuery {
		
    private int anzahlPersonen;
    private double wohnfläche;
	private String haustyp;
	private String baujahr;
	private String selectedRadioButton;
	private Connection connection;

    
	  public DatabaseQuery(Connection connection) {
	    	this.connection = connection;     
	    }
    
    
    public int getAnzahlPersonen() {
        return this.anzahlPersonen;
    }
    
    public double getWohnfläche() {
        return this.wohnfläche;
    }
    
    public String getHaustyp() {
        return haustyp;
    }
    
    public String getBaujahr() {
        return baujahr;
    }
    
    public String getselectedRadioButton() {
    	return selectedRadioButton;
    }

	
    public List<DatabaseQuery> getHousingDataFromDatabase() {
    	
        List<DatabaseQuery> housingDataList = new ArrayList<>();

        try {
            String selectSql = "SELECT personen, wohnfläche, haustyp, baujahr FROM housing ORDER BY id DESC LIMIT 1"; //Um nur den letzten Eintrag in der Datenbank abzurufen

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    anzahlPersonen = resultSet.getInt("personen");
                    wohnfläche = resultSet.getDouble("wohnfläche");
                    haustyp = resultSet.getString("haustyp");
                    baujahr = resultSet.getString("baujahr");
                    selectedRadioButton = resultSet.getString("selectedRadioButton");

                    // Erstelle ein HousingData-Objekt und füge es zur Liste hinzu
                    DatabaseQuery data = new DatabaseQuery(connection);
                    data.getAnzahlPersonen();
                    data.getWohnfläche();
                    data.getHaustyp();
                    data.getBaujahr();
                    data.getselectedRadioButton();
                    housingDataList.add(data);

                    //Für Testzwecke
                    System.out.println("Die eingegebenen Werte lauten:");
                    System.out.println("Anzahl Personen: " + anzahlPersonen);
                    System.out.println("Wohnfläche: " + wohnfläche);
                    System.out.println("Haustyp: " + haustyp);
                    System.out.println("Baujahr: " + baujahr);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return housingDataList;
    }
	

}
