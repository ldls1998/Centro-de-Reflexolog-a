package com.mycompany.reflexologia;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class InformesController {

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
    
    
      /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void reportediario(ActionEvent e) throws IOException {
        
     File file = new File("C:/Users/maria/OneDrive/Documentos/reportedecitasdiario.pdf");

        try {
          ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", file.getAbsolutePath());
          builder.start();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
}
    @FXML
    private void diarioterapeuta(ActionEvent e) throws IOException {
        
     File file = new File("C:/Users/maria/OneDrive/Documentos/atencionesdiariasporterapeuta.pdf");

        try {
          ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", file.getAbsolutePath());
          builder.start();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
}
    
    @FXML
    private void enfermedades(ActionEvent e) throws IOException {

          File file = new File("C:/Users/maria/OneDrive/Documentos/enfermedades.pdf");
          
        try {
          ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", file.getAbsolutePath());
          builder.start();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
    }
}
