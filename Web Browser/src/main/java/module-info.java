module evsey.novikov.web_browser {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens evsey.novikov.web_browser to javafx.fxml;
    exports evsey.novikov.web_browser;
}