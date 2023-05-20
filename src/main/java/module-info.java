module com.example.summitpartnersmaterialmanegmentsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.summitpartnersmaterialmanegmentsystem to javafx.fxml;
    exports com.example.summitpartnersmaterialmanegmentsystem;
}