module com.example.bookaroom {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookaroom.views.gui to javafx.fxml;
    exports com.example.bookaroom.views.gui;
    exports com.example.bookaroom.views.gui.widgets;
    opens com.example.bookaroom.views.gui.widgets to javafx.fxml;
}