/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import conexion.ConexionMySQL;
import dao.DiagnosticoDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Diagnostico;
import modelo.Paciente;

/**
 * FXML Controller class
 *
 * @author Vlik35
 */
public class G130Controller implements Initializable {

    @FXML
    private TableView<Diagnostico> tvDiagnosticos;
    @FXML
    private ChoiceBox<String> chbCampo;
    @FXML
    private TextField tfBuscar;
    @FXML
    private TextField tfAbrev;
    @FXML
    private TextField tfDescripcion;

    private DiagnosticoDAO diagnosticodao;
    
    private Diagnostico diagnosticoSelected;
    
    private ContextMenu cmOpciones;
    
    private ConexionMySQL conexion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.diagnosticodao = new DiagnosticoDAO();

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

                int index = tvDiagnosticos.getSelectionModel().getSelectedIndex();

                diagnosticoSelected = tvDiagnosticos.getItems().get(index);

                tfAbrev.setText(diagnosticoSelected.getCodigo());

                Diagnostico diagnostico = diagnosticodao.buscar(diagnosticoSelected.getCodigo());

                tfDescripcion.setText(diagnostico.getDescripcion());
            }

        });

        tvDiagnosticos.setContextMenu(cmOpciones);
        
    }    
    
    public void cargarDiagnosticos() {

        tvDiagnosticos.getItems().clear();
        tvDiagnosticos.getColumns().clear();

        List<Diagnostico> diagnosticos = this.diagnosticodao.listar();

        ObservableList<Diagnostico> data = FXCollections.observableArrayList(diagnosticos);

        TableColumn codColumn = new TableColumn("Código");
        codColumn.setCellValueFactory(new PropertyValueFactory("codigo"));

        TableColumn descripcionColumn = new TableColumn("Descripcion");
        descripcionColumn.setCellValueFactory(new PropertyValueFactory("descripcion"));

        tvDiagnosticos.setItems(data);
        tvDiagnosticos.getColumns().addAll(codColumn, descripcionColumn);

    }
    
}
