package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Calculator {

    public double berechnung(int anzahlPersonen, double wohnfläche) {
        double result = wohnfläche * anzahlPersonen;
        return result;
    }
}


