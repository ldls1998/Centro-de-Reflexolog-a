/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import dao.G210DAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.g210dao = new G210DAO();
        cargarTabla();
    }

    public void cargarTabla() {

        tvCitasyPacientes.getItems().clear();
        tvCitasyPacientes.getColumns().clear();

        List<Cita_Paciente> cita_paciente = this.g210dao.listar();
        
        ObservableList<Cita_Paciente> data = FXCollections.observableArrayList( cita_paciente );
        
        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory("codigo_paciente"));
        
        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        TableColumn fechaColumn = new TableColumn("Fecha");
        fechaColumn.setCellValueFactory(new PropertyValueFactory("fecha_cita"));
        
        TableColumn importeColumn = new TableColumn("Imp");
        importeColumn.setCellValueFactory(new PropertyValueFactory("importe"));
        
        tvCitasyPacientes.setItems(data);
        tvCitasyPacientes.getColumns().addAll(idColumn, nombreColumn, fechaColumn, importeColumn);

    }

}
