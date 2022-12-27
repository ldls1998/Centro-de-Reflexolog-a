package com.mycompany.reflexologia;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private MenuItem miG110;
    @FXML
    private MenuItem miG120;
    @FXML
    private MenuItem miG130;
    @FXML
    private MenuItem miG140;
    @FXML
    private MenuItem mi210;
    @FXML
    private MenuItem mi220;
    @FXML
    private MenuItem mi230;
    @FXML
    private MenuItem mi250;
    @FXML
    private MenuItem mi240;
    @FXML
    private MenuItem mi310;
    @FXML
    private MenuItem miInformes;
    @FXML
    private MenuItem miCopiaSeguridad;
    @FXML
    private MenuItem mi320;
    @FXML
    private MenuItem miMantenimientoIndices;
    @FXML
    private MenuItem miSalir;
    @FXML
    private AnchorPane apRootPane;
    @FXML
    private MenuBar myMenuBar;

    @FXML
    private void cambiarAG110(ActionEvent e) throws IOException {
        
        String scene_name = "G110.fxml";
        String titulo = "G110. - Registro de Pacientes";
        
        loadScene(scene_name, 1080, 620, titulo, false, true);
    }

    @FXML
    private void cambiarAG210(ActionEvent e) throws IOException {
        
        String scene_name = "G210.fxml";
        String titulo = "G210. - Recepción y Cobranza Caja 1";
        
        loadScene(scene_name, 1080, 620, titulo, false, true);
    }

    private void loadScene(String scene_name, int width, int height, String title, boolean resizable, boolean onTop) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(scene_name));

        Scene scene_show = new Scene(root);
        Stage new_stage = new Stage();
        // Creamos la escena con la raíz de la jerarquía de nodos como raíz
        new_stage.setScene(scene_show);
        new_stage.setWidth(width);
        new_stage.setHeight(height);
        new_stage.setTitle(title);
        new_stage.centerOnScreen();
        new_stage.setResizable(resizable);
        new_stage.setAlwaysOnTop(onTop);
        new_stage.show();

    }

    @FXML
    public void salir(ActionEvent e) {
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.close();
    }

}
