package com.mycompany.reflexologia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Scene login;
    private static Scene main_scene;

    @Override
    public void start(Stage stage) throws IOException {
        
        // Mostrar login
        Stage login_stage = new Stage();
        login = new Scene(loadFXML("Login"));
        login_stage.setScene(login);
        login_stage.setTitle("Iniciar Sesión");
        login_stage.setResizable(false);
        login_stage.show();
        
    }

    static void setRoot(String fxml) throws IOException {
        login.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) {
        launch();
    }

}