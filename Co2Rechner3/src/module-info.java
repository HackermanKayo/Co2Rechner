module Co2Rechner {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	requires javafx.base;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
}
