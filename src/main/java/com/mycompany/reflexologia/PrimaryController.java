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

        Parent root = FXMLLoader.load(getClass().getResource("G110.fxml"));

        Scene scene_show = new Scene(root);
        Stage new_stage = new Stage();
        // Creamos la escena con la raíz de la jerarquía de nodos como raíz
        new_stage.setScene(scene_show);
        new_stage.setWidth(1080);
        new_stage.setHeight(620);
        new_stage.setTitle("G110. - Registro de Pacientes");
        new_stage.centerOnScreen();
        new_stage.setResizable(false);
        new_stage.setAlwaysOnTop(true);
        new_stage.show();
    }

    @FXML
    public void salir(ActionEvent e) {
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.close();
    }

}
