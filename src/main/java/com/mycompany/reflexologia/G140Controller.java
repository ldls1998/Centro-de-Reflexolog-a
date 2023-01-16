/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import conexion.ConexionMySQL;
import dao.DiagnosticoDAO;
import dao.DiagnosticoMedicoDAO;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import modelo.DiagnosticoMedico;

/**
 * FXML Controller class
 *
 * @author Vlik35
 */
public class G140Controller implements Initializable {

    @FXML
    private TableView<DiagnosticoMedico> tvDiagnosticosMedicos;
    @FXML
    private TextField tfCod;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfTipo;

    private DiagnosticoMedicoDAO diagnosticoMedicoDao;
    
    private DiagnosticoMedico diagnosticoMedicoSelected;
    
    private ContextMenu cmOpciones;
    
    private ConexionMySQL conexion;
    
    @FXML
    private ChoiceBox<String> chbCampo;
    @FXML
    private TextField tfBuscar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.diagnosticoMedicoDao = new DiagnosticoMedicoDAO();

        this.conexion = new ConexionMySQL();

        cargarDiagnosticos();
        
        cmOpciones = new ContextMenu();

        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");
        
        cmOpciones.getItems().addAll(miEditar, miEliminar);
        
        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                // btnMarcarDisponible.setText("Modificar");
                // btnCancelar.setDisable(false);

                int index = tvDiagnosticosMedicos.getSelectionModel().getSelectedIndex();

                diagnosticoMedicoSelected = tvDiagnosticosMedicos.getItems().get(index);

                tfCod.setText(diagnosticoMedicoSelected.getCodigo());

                DiagnosticoMedico diagnosticoMedico = diagnosticoMedicoDao.buscar(diagnosticoMedicoSelected.getCodigo());

                tfNombre.setText(diagnosticoMedico.getNombre());
                
                tfTipo.setText(diagnosticoMedico.getTipo());
            }

        });
        
        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                int index = tvDiagnosticosMedicos.getSelectionModel().getSelectedIndex();

                String registroCitaEliminar = tvDiagnosticosMedicos.getItems().get(index).getCodigo();

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

                alerta.setTitle("Confirmación");
                alerta.setHeaderText(null);
                alerta.setContentText("¿Realmente desea eliminar la cita con número de registro: " + registroCitaEliminar + "?");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(tvDiagnosticosMedicos.getScene().getWindow());

                Optional<ButtonType> result = alerta.showAndWait();

                if (result.get() == ButtonType.OK) {
                    boolean rpta = diagnosticoMedicoDao.eliminar(registroCitaEliminar);

                    if (rpta) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Exito");
                        alert.setHeaderText(null);
                        alert.setContentText("Se eliminó la cita correctamente");
                        alert.initStyle(StageStyle.UTILITY);
                        alert.initOwner(tvDiagnosticosMedicos.getScene().getWindow());
                        alert.showAndWait();
                        cargarDiagnosticos();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Hubo un error al eliminar.");
                        alert.initStyle(StageStyle.UTILITY);
                        alert.initOwner(tvDiagnosticosMedicos.getScene().getWindow());
                        alert.showAndWait();
                    }
                }

            }

        });

        tvDiagnosticosMedicos.setContextMenu(cmOpciones);
        
    }    
    
    public void cargarDiagnosticos() {

        tvDiagnosticosMedicos.getItems().clear();
        tvDiagnosticosMedicos.getColumns().clear();

        List<DiagnosticoMedico> diagnosticos = this.diagnosticoMedicoDao.listar();

        ObservableList<DiagnosticoMedico> data = FXCollections.observableArrayList(diagnosticos);

        TableColumn codColumn = new TableColumn("Código");
        codColumn.setCellValueFactory(new PropertyValueFactory("codigo"));

        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        TableColumn tipoColumn = new TableColumn("Tipo");
        tipoColumn.setCellValueFactory(new PropertyValueFactory("tipo"));

        tvDiagnosticosMedicos.setItems(data);
        tvDiagnosticosMedicos.getColumns().addAll(codColumn, nombreColumn, tipoColumn);

    }
    
}
