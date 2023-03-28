/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import conexion.ConexionMySQL;
import dao.VisitaDAO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modelo.Visita;

/**
 * FXML Controller class
 *
 * @author Vlik35
 */
public class VisitaController implements Initializable {

    @FXML
    private TextField txtMedicamentos;
    
    @FXML
    private TextField txtDiag_ext;
   
    
    @FXML
    private Button registrar;

    
    private TableView<Visita> tvVisitas;

    
    private VisitaDAO VisitaDAO;
    private ConexionMySQL conexion;
    @FXML
    private ChoiceBox<String> chbBusqueda;
    private ContextMenu cmOpciones;
              
    @FXML
    private TextField txtoperaciones;

    @FXML
    private TextField txtobserva;
    
    private Visita visitaSelected;

    private int codigo;
    /**
     * Initializes the controller class.
     */
    
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        this.VisitaDAO = new VisitaDAO();
        this.conexion = new ConexionMySQL();

        cmOpciones = new ContextMenu();
        MenuItem miEditar = new MenuItem("Editar");
        
        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent t) {

    
                txtMedicamentos.setDisable(true);
                registrar.setText("Guardar");
                txtoperaciones.setDisable(true);
                txtobserva.setDisable(true);
                txtDiag_ext.setDisable(true);
           
                txtMedicamentos.setText(visitaSelected.getMedicamentos());
                txtDiag_ext.setText(visitaSelected.getDiag_ext());
                txtobserva.setText(visitaSelected.getObserva());
                txtoperaciones.setText(visitaSelected.getOperaciones());
            }

        });
/*
        tvPacientes.setContextMenu(cmOpciones);*/
    }

    
    @FXML
    void btnRegistrarOnAction(ActionEvent event) {
            
            Visita visita = new Visita();
            
             txtMedicamentos.setText(visita.getMedicamentos());
             txtDiag_ext.setText(visita.getDiag_ext());
             txtobserva.setText(visita.getObserva());
             txtoperaciones.setText(visita.getOperaciones());

            boolean rpta = this.VisitaDAO.registrar(visita);

            if (rpta) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Exito");
                alerta.setHeaderText(null);
                alerta.setContentText("Se registró el paciente correctamente");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.showAndWait();
                limpiarCampos();
  
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Hubo un error al registrar.");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.showAndWait();
            }
    }
    
    @FXML
    private void limpiarCampos() {
        
        txtMedicamentos.setText("");
        txtDiag_ext.setText("");
        txtobserva.setText("");
        txtoperaciones.setText("");

    }
    
    @FXML
    private void imprimir(ActionEvent e) throws IOException {
        
     File file = new File("C:/Users/maria/OneDrive/Documentos/historia.pdf");
     try {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", file.getAbsolutePath());
        builder.start();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
    }


}
