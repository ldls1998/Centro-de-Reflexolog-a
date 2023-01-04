/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import dao.Cita_PacienteDAO;
import dao.G210DAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import modelo.Cita_Paciente;

/**
 * FXML Controller class
 *
 * @author Vlik35
 */
public class G210Controller implements Initializable {

    @FXML
    private VBox vbCalendario;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private Button btnImprimirRecibo;
    @FXML
    private Button btnConsultarCitas;
    @FXML
    private Button btnTerminarDia;
    @FXML
    private Button btnImprimirTicket;
    @FXML
    private Label ldlMes;
    @FXML
    private Label lblDNI;
    @FXML
    private TableView<Cita_Paciente> tvCitasyPacientes;

    private G210DAO g210dao;
    
    private ContextMenu cmOpciones;
    
    private Cita_PacienteDAO cita_pacienteDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.g210dao = new G210DAO();
        this.cita_pacienteDAO = new Cita_PacienteDAO();
        cargarTabla();
        
        cmOpciones = new ContextMenu();

        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");

        cmOpciones.getItems().addAll(miEditar, miEliminar);

        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                int index = tvCitasyPacientes.getSelectionModel().getSelectedIndex();

                int registroCitaEliminar = tvCitasyPacientes.getItems().get(index).getRegistro();

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

                alerta.setTitle("Confirmación");
                alerta.setHeaderText(null);
                alerta.setContentText("¿Realmente desea eliminar la cita con número de registro: " + registroCitaEliminar + "?");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnImprimirRecibo.getScene().getWindow());

                Optional<ButtonType> result = alerta.showAndWait();

                if (result.get() == ButtonType.OK) {
                    boolean rpta = cita_pacienteDAO.eliminar(registroCitaEliminar);

                    if (rpta) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Exito");
                        alert.setHeaderText(null);
                        alert.setContentText("Se eliminó la cita correctamente");
                        alert.initStyle(StageStyle.UTILITY);
                        alert.initOwner(btnImprimirRecibo.getScene().getWindow());
                        alert.showAndWait();
                        cargarTabla();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Hubo un error al eliminar.");
                        alert.initStyle(StageStyle.UTILITY);
                        alert.initOwner(btnImprimirRecibo.getScene().getWindow());
                        alert.showAndWait();
                    }
                }
                
            }

        });
        
        tvCitasyPacientes.setContextMenu(cmOpciones);
    }

    public void cargarTabla() {

        tvCitasyPacientes.getItems().clear();
        tvCitasyPacientes.getColumns().clear();

        List<Cita_Paciente> cita_paciente = this.g210dao.listar();
        
        ObservableList<Cita_Paciente> data = FXCollections.observableArrayList( cita_paciente );
        
        TableColumn registroColumn = new TableColumn("Registro");
        registroColumn.setCellValueFactory(new PropertyValueFactory("registro"));
        
        TableColumn codigoColumn = new TableColumn("Código");
        codigoColumn.setCellValueFactory(new PropertyValueFactory("codigo_paciente"));
        
        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        TableColumn fechaColumn = new TableColumn("Fecha");
        fechaColumn.setCellValueFactory(new PropertyValueFactory("fecha_cita"));
        
        TableColumn importeColumn = new TableColumn("Imp");
        importeColumn.setCellValueFactory(new PropertyValueFactory("importe"));
        
        tvCitasyPacientes.setItems(data);
        tvCitasyPacientes.getColumns().addAll(registroColumn, codigoColumn, nombreColumn, fechaColumn, importeColumn);

    }

}
