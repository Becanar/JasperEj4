module com.benat.cano.jasperej4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.benat.cano.jasperej4 to javafx.fxml;
    exports com.benat.cano.jasperej4;
}