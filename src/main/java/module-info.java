module com.mycompany.reflexologia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens modelo to javafx.fxml;
    opens com.mycompany.reflexologia to javafx.fxml;
    
    exports com.mycompany.reflexologia;
    exports modelo;
}
