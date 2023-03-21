module com.example.bookaroom {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookaroom.gui to javafx.fxml;
    exports com.example.bookaroom.gui;
    exports com.example.bookaroom.gui.widgets;
    opens com.example.bookaroom.gui.widgets to javafx.fxml;
}