module com.example.bookaroom {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookaroom to javafx.fxml;
    exports com.example.bookaroom;
}