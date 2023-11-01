module com.example.lab4visualization {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab4visualization to javafx.fxml;
    exports com.example.lab4visualization;
}