module com.mycompany.reflexologia {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.reflexologia to javafx.fxml;
    exports com.mycompany.reflexologia;
}
