module evsey.novikov.task8_fabric {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens evsey.novikov.task8_fabric to javafx.fxml;
    exports evsey.novikov.task8_fabric;
    exports model;
    opens model to javafx.fxml;
}