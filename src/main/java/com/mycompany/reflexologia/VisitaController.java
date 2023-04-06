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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    private TextField pacienteID;
    @FXML
    private TextField dia_ext;
    @FXML
    private TextField medicamentos;
    @FXML
    private TextField operaciones;
    @FXML
    private TextField observa;
    @FXML
    private TextArea dolencias;
    @FXML
    private TextArea diagnosticos_reflexologicos;
    @FXML
    private TextArea os_priv;
    @FXML
    private TextField peso_ini;
    @FXML
    private DatePicker fecha_ini;
    @FXML
    private TextField peso_fin;
    @FXML
    private DatePicker fecha_fin;

    @FXML
    private Label nvisitas;

    private Visita visitaSelected;
    
    @FXML
    private TextField parametroTextField;
      
    @FXML
    private TextField parametroTextField2;
    
     @FXML
    private TextField terapeuta;
    
    @FXML
    private TextField talla;
    
    @FXML
    private Visita visitadatos;

    public void setParametro(String parametro,String parametro2, Visita visita) {
    parametroTextField.setText(parametro);
    parametroTextField2.setText(parametro2);
 
    if(visita!=null){
                LocalDate fechaActual = LocalDate.now(); // Obtenemos la fecha actual

    medicamentos.setText(visita.getMedicamentos());
        operaciones.setText(visita.getOperaciones());
        dia_ext.setText(visita.getDiag_ext());
        observa.setText(visita.getObserva());
        dolencias.setText(visita.getDolencias());
        diagnosticos_reflexologicos.setText(visita.getDiagnosticos_reflexologicos());
        os_priv.setText(visita.getOs_priv());
        peso_ini.setText(String.valueOf(visita.getPeso_ini()));
        fecha_ini.setValue(visita.getFecha_ini().toLocalDate());
        peso_fin.setText(String.valueOf(visita.getPeso_fin()));
        fecha_fin.setValue(fechaActual);
        talla.setText(String.valueOf(visita.getTalla()));}
        terapeuta.setText(String.valueOf(visita.getFtterapeuta()));
        nvisitas.setText(String.valueOf(visita.getNvisitas()));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      // Obtener el valor del parámetro de la vista anterior

        this.VisitaDAO = new VisitaDAO();
        this.conexion = new ConexionMySQL();

        cmOpciones = new ContextMenu();
        
       
        MenuItem miEditar = new MenuItem("Editar");
        
    
        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent t) {


                dia_ext.setDisable(true);
                parametroTextField.setDisable(true);
                medicamentos.setDisable(true);
                operaciones.setDisable(true);
                observa.setDisable(true);
                dolencias.setDisable(true);
                diagnosticos_reflexologicos.setDisable(true);
                os_priv.setDisable(true);
                peso_ini.setDisable(true);
                fecha_ini.setDisable(true);
                peso_fin.setDisable(true);
                fecha_fin.setDisable(true);
                registrar.setDisable(true);
           
            }

        });
/*
        tvPacientes.setContextMenu(cmOpciones);*/
    }
    
    
    @FXML
    void btnRegistrarOnAction(ActionEvent event) throws SQLException {
            
            Visita visita = new Visita();
            System.out.println("    AQUIII ENTRAAA");
            
            visita.setPacienteID(Integer.parseInt(parametroTextField.getText()));
            visita.setMedicamentos(medicamentos.getText());
            visita.setOperaciones(operaciones.getText());
            visita.setObserva(observa.getText());
            visita.setDiag_ext(dia_ext.getText());
            visita.setDolencias(dolencias.getText());
            visita.setDiagnosticos_reflexologicos(diagnosticos_reflexologicos.getText());
            visita.setOs_priv(os_priv.getText());
            visita.setPeso_ini(Float.parseFloat(peso_ini.getText()));
            visita.setFecha_ini(Date.valueOf(fecha_ini.getValue()));
            visita.setPeso_fin(Float.parseFloat(peso_fin.getText()));
            visita.setFecha_fin(Date.valueOf(fecha_fin.getValue()));
            visita.setTalla(Float.parseFloat(talla.getText()));
            visita.setFecha_ini(Date.valueOf(fecha_ini.getValue()));
            visita.setPeso_fin(Float.parseFloat(peso_fin.getText()));
            visita.setFecha_fin(Date.valueOf(fecha_fin.getValue()));
            visita.setFtterapeuta(Integer.parseInt(terapeuta.getText()));
            // Establecer valores de las variables de diagnóstico
            visita.setDiagnostico1("valor del diagnóstico 1");
            visita.setDiagnostico2("valor del diagnóstico 2");
            visita.setDiagnostico3("valor del diagnóstico 3");
            visita.setDiagnostico4("valor del diagnóstico 4");
            visita.setDiagnostico5("valor del diagnóstico 5");
            visita.setDiagnostico6("valor del diagnóstico 6");

            // Establecer valores de las variables booleanas
            visita.setMenstru(true); // O false si el valor es falso
            visita.setTestimonio(true); // O false si el valor es falso

            // Establecer valores de las variables de resultado y observación
            visita.setResultado("valor del resultado");
            visita.setObservacion("valor de la observación");

            System.out.println(visita);
            boolean rpta = this.VisitaDAO.registrar(visita);
            System.out.println(rpta);
            if (rpta) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Exito");
                alerta.setHeaderText(null);
                alerta.setContentText("Se registró el paciente correctamente");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.show();
                limpiarCampos();
  
            } else {
                
          
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Hubo un error al registrar.");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.show();
            }
    }
    
    @FXML
    private void limpiarCampos() {
        
       

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
private void buscarOnAction(TextField parametroTextField) {
    
    
    int pacienteID = Integer.parseInt(parametroTextField.getText());
    Visita visita = this.VisitaDAO.buscarPorPacienteID(pacienteID);
    System.out.println(pacienteID);
    
    if (visita != null) {
        // Mostrar los datos obtenidos en los campos correspondientes
    
        medicamentos.setText(visita.getMedicamentos());
        operaciones.setText(visita.getOperaciones());
        observa.setText(visita.getObserva());
        dolencias.setText(visita.getDolencias());
        diagnosticos_reflexologicos.setText(visita.getDiagnosticos_reflexologicos());
        os_priv.setText(visita.getOs_priv());
        peso_ini.setText(String.valueOf(visita.getPeso_ini()));
        fecha_ini.setValue(visita.getFecha_ini().toLocalDate());
        peso_fin.setText(String.valueOf(visita.getPeso_fin()));
        fecha_fin.setValue(visita.getFecha_fin().toLocalDate());
            
    } else {
            
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setHeaderText(null);
        alerta.setContentText("No se encontró ningún registro con ese ID de paciente.");
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
            
    }
}


}
