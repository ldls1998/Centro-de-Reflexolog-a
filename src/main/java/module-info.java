module com.mycompany.reflexologia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.reflexologia to javafx.fxml;
    exports com.mycompany.reflexologia;
}
