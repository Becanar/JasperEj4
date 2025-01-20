module com.benat.cano.jasperej4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    exports com.benat.cano.jasperej4.controller;
    opens com.benat.cano.jasperej4.controller to javafx.fxml;
    exports com.benat.cano.jasperej4.app;
    opens com.benat.cano.jasperej4.app to javafx.fxml;
}