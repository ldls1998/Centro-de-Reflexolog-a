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

    cargarScene cargarScene = new cargarScene();
    
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
        
        cargarScene.loadScene(scene_name, 1080, 620, titulo, false, true);
    }
    
    @FXML
    private void cambiarAG120(ActionEvent e) throws IOException {
        
        String scene_name = "G120.fxml";
        String titulo = "G120. - Relación de Terapeutas";
        
        cargarScene.loadScene(scene_name, 1080, 620, titulo, false, true);
    }
    
    @FXML
    private void cambiarAG130(ActionEvent e) throws IOException {
        
        String scene_name = "G130.fxml";
        String titulo = "G130. - Diagnósticos";
        
        cargarScene.loadScene(scene_name, 1080, 620, titulo, false, true);
    }

    @FXML
    private void cambiarAG210(ActionEvent e) throws IOException {
        
        String scene_name = "G210.fxml";
        String titulo = "G210. - Recepción y Cobranza Caja 1";
        
        cargarScene.loadScene(scene_name, 1080, 620, titulo, false, true);
    }

    @FXML
    public void salir(ActionEvent e) {
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.close();
    }

}
