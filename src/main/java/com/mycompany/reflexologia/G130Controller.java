/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import conexion.ConexionMySQL;
import dao.DiagnosticoDAO;
import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    @FXML
    private Button btnAtras;
    @FXML
    private Button btnSiguiente;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.diagnosticodao = new DiagnosticoDAO();

        String[] opciones_busqueda = {"Código", "Descripción"};
        ObservableList<String> items_busqueda = FXCollections.observableArrayList(opciones_busqueda);
        chbCampo.setItems(items_busqueda);
        chbCampo.setValue("Código");

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
                tfAbrev.setDisable(true);
                btnNuevo.setText("Modificar");
                
                int index = tvDiagnosticos.getSelectionModel().getSelectedIndex();

                diagnosticoSelected = tvDiagnosticos.getItems().get(index);

                tfAbrev.setText(diagnosticoSelected.getCodigo());

                Diagnostico diagnostico = diagnosticodao.buscar(diagnosticoSelected.getCodigo());

                tfDescripcion.setText(diagnostico.getDescripcion());
            }

        });

        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                int index = tvDiagnosticos.getSelectionModel().getSelectedIndex();

                String registroCitaEliminar = tvDiagnosticos.getItems().get(index).getCodigo();

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

                alerta.setTitle("Confirmación");
                alerta.setHeaderText(null);
                alerta.setContentText("¿Realmente desea eliminar la cita con número de registro: " + registroCitaEliminar + "?");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(tvDiagnosticos.getScene().getWindow());

                Optional<ButtonType> result = alerta.showAndWait();

                if (result.get() == ButtonType.OK) {
                    boolean rpta = diagnosticodao.eliminar(registroCitaEliminar);

                    if (rpta) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Exito");
                        alert.setHeaderText(null);
                        alert.setContentText("Se eliminó la cita correctamente");
                        alert.initStyle(StageStyle.UTILITY);
                        alert.initOwner(tvDiagnosticos.getScene().getWindow());
                        alert.showAndWait();
                        cargarDiagnosticos();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Hubo un error al eliminar.");
                        alert.initStyle(StageStyle.UTILITY);
                        alert.initOwner(tvDiagnosticos.getScene().getWindow());
                        alert.showAndWait();
                    }
                }

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

    @FXML
    public void cargarDiagnosticosBusqueda() {

        if (tfBuscar.getText().equals("")) {
            cargarDiagnosticos();
        } else {
            tvDiagnosticos.getItems().clear();
            tvDiagnosticos.getColumns().clear();

            List<Diagnostico> diagnosticos = null;

            if ("Código".equals(chbCampo.getSelectionModel().getSelectedItem())) {
                diagnosticos = this.diagnosticodao.listarBusquedaCodigo(tfBuscar.getText());
            } else if ("Descripción".equals(chbCampo.getSelectionModel().getSelectedItem())) {
                diagnosticos = this.diagnosticodao.listarBusquedaDescripcion(tfBuscar.getText());
            }

            ObservableList<Diagnostico> data = FXCollections.observableArrayList(diagnosticos);

            TableColumn codColumn = new TableColumn("Código");
            codColumn.setCellValueFactory(new PropertyValueFactory("codigo"));

            TableColumn descripcionColumn = new TableColumn("Descripcion");
            descripcionColumn.setCellValueFactory(new PropertyValueFactory("descripcion"));

            tvDiagnosticos.setItems(data);
            tvDiagnosticos.getColumns().addAll(codColumn, descripcionColumn);
        }
    }

    @FXML
    private void irAtras(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        stage.close();

        cargarScene cargarScene = new cargarScene();

        String scene_name = "G120.fxml";
        String titulo = "G120. - Relación de Terapeutas";
        
        cargarScene.loadScene(scene_name, 1080, 620, titulo, false, true);

    }
    
    private String validar() {

        if ("".equals(tfAbrev.getText())) {
            return "El código no puede estar vacío";
        }

        Diagnostico diagnostico = this.diagnosticodao.buscar(tfAbrev.getText());

        if (!btnNuevo.getText().equals("Modificar")) {
            if ("".equals(tfAbrev.getText())) {
                return "El código no puede estar vacío";
            }
            
            if (diagnostico.getID() != 0) {
                return "Paciente ya existe";
            }
        }

        if (tfDescripcion.getText().length() >= 100 || tfDescripcion.getText().length() <= 0) {
            return "Error en la descripción";
        }

        return "";

    }

    @FXML
    private void irAdelante(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        stage.close();

        cargarScene cargarScene = new cargarScene();

        String scene_name = "G140.fxml";
        String titulo = "G140. - Diagnósticos Médicos";
        
        cargarScene.loadScene(scene_name, 1080, 620, titulo, false, true);

    }


    @FXML
    private void buscar(ActionEvent event) {
    }
    
    private void limpiarCampos() {

        tfBuscar.setText("");
        tfAbrev.setText("");
        tfDescripcion.setText("");
    }
    
    @FXML
    void btnRegistrarOnAction(ActionEvent event) {

        String validacion = validar();
        
        if (!"".equals(validacion)) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText(validacion);
            alerta.initStyle(StageStyle.UTILITY);
            alerta.initOwner(btnNuevo.getScene().getWindow());
            alerta.showAndWait();
            return;
        }

        if (diagnosticoSelected == null) {
            Diagnostico diagnostico = new Diagnostico();

            diagnostico.setCodigo(tfAbrev.getText());
            diagnostico.setDescripcion(tfDescripcion.getText());

            boolean rpta = this.diagnosticodao.registrar(diagnostico);

            if (rpta) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);

                alerta.setTitle("Exito");
                alerta.setHeaderText(null);
                alerta.setContentText("Se registró el diagnóstico correctamente");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnNuevo.getScene().getWindow());
                alerta.showAndWait();
                limpiarCampos();
                cargarDiagnosticos();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);

                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Hubo un error al registrar.");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnNuevo.getScene().getWindow());
                alerta.showAndWait();
            }
        } else {
            
            this.diagnosticoSelected = this.diagnosticodao.buscar(tfAbrev.getText());
            this.diagnosticoSelected.setDescripcion(tfDescripcion.getText());

            boolean rpta = this.diagnosticodao.editar(diagnosticoSelected);
            System.out.println(rpta);

            if (rpta) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);

                alerta.setTitle("Exito");
                alerta.setHeaderText(null);
                alerta.setContentText("Se registró el diagnóstico correctamente");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnNuevo.getScene().getWindow());
                alerta.showAndWait();
                
                diagnosticoSelected = null;
                tfAbrev.setDisable(false);
                btnNuevo.setText("Nuevo");
                //btnCancelar.setDisable(true);
                limpiarCampos();
                cargarDiagnosticos();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);

                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Hubo un error al editar.");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnNuevo.getScene().getWindow());
                alerta.showAndWait();
            }

        }

    }
    
    public static boolean isNumeric(String string) {
        int intValue;

        if (string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

}
