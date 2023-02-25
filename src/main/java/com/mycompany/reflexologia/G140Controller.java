/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import conexion.ConexionMySQL;
import dao.DiagnosticoMedicoDAO;
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

        String[] opciones_busqueda = {"Código", "Nombre", "Tipo"};
        ObservableList<String> items_busqueda = FXCollections.observableArrayList(opciones_busqueda);
        chbCampo.setItems(items_busqueda);
        chbCampo.setValue("Código");

        this.diagnosticoMedicoDao = new DiagnosticoMedicoDAO();

        this.conexion = new ConexionMySQL();

        cargarDiagnosticosMedicos();

        cmOpciones = new ContextMenu();

        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");

        cmOpciones.getItems().addAll(miEditar, miEliminar);

        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                // btnMarcarDisponible.setText("Modificar");
                // btnCancelar.setDisable(false);
                tfCod.setDisable(true);
                btnNuevo.setText("Modificar");
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
                        cargarDiagnosticosMedicos();
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

    public void cargarDiagnosticosMedicos() {

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

    @FXML
    public void cargarDiagnosticosBusqueda() {

        if (tfBuscar.getText().equals("")) {
            cargarDiagnosticosMedicos();
        } else {
            tvDiagnosticosMedicos.getItems().clear();
            tvDiagnosticosMedicos.getColumns().clear();

            List<DiagnosticoMedico> diagnosticos = null;

            if ("Código".equals(chbCampo.getSelectionModel().getSelectedItem())) {
                diagnosticos = this.diagnosticoMedicoDao.listarBusquedaCodigo(tfBuscar.getText());
            } else if ("Nombre".equals(chbCampo.getSelectionModel().getSelectedItem())) {
                diagnosticos = this.diagnosticoMedicoDao.listarBusquedaNombre(tfBuscar.getText());
            } else if ("Tipo".equals(chbCampo.getSelectionModel().getSelectedItem())) {
                diagnosticos = this.diagnosticoMedicoDao.listarBusquedaTipo(tfBuscar.getText());
            }

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

    @FXML
    private void irAtras(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        stage.close();

        cargarScene cargarScene = new cargarScene();

        String scene_name = "G130.fxml";
        String titulo = "G130. - Diagnósticos";

        cargarScene.loadScene(scene_name, 1080, 620, titulo, false, true);

    }

    @FXML
    private void irAdelante(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        stage.close();

        cargarScene cargarScene = new cargarScene();

        String scene_name = "G210.fxml";
        String titulo = "G210. - Recepción y Cobranza Caja 1";

        cargarScene.loadScene(scene_name, 1080, 650, titulo, false, true);

    }

    @FXML
    private void nuevo(ActionEvent event) {
    }

    @FXML
    private void buscar(ActionEvent event) {
    }

    private void limpiarCampos() {

        tfBuscar.setText("");
        tfCod.setText("");
        tfNombre.setText("");
        tfTipo.setText("");
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

        if (diagnosticoMedicoSelected == null) {
            DiagnosticoMedico diagnosticoMedico = new DiagnosticoMedico();

            diagnosticoMedico.setCodigo(tfCod.getText());
            diagnosticoMedico.setNombre(tfNombre.getText());
            diagnosticoMedico.setTipo(tfTipo.getText());

            boolean rpta = this.diagnosticoMedicoDao.registrar(diagnosticoMedico);

            if (rpta) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);

                alerta.setTitle("Exito");
                alerta.setHeaderText(null);
                alerta.setContentText("Se registró el diagnóstico médico correctamente");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnNuevo.getScene().getWindow());
                alerta.showAndWait();
                limpiarCampos();
                cargarDiagnosticosMedicos();
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

            this.diagnosticoMedicoSelected = this.diagnosticoMedicoDao.buscar(tfCod.getText());
            this.diagnosticoMedicoSelected.setNombre(tfNombre.getText());
            this.diagnosticoMedicoSelected.setTipo(tfTipo.getText());

            boolean rpta = this.diagnosticoMedicoDao.editar(diagnosticoMedicoSelected);
            System.out.println(rpta);
            
            if (rpta) {
                System.out.println(rpta);
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);

                alerta.setTitle("Exito");
                alerta.setHeaderText(null);
                alerta.setContentText("Se registró el diagnóstico médico correctamente");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnNuevo.getScene().getWindow());
                alerta.showAndWait();
                
                diagnosticoMedicoSelected = null;
                tfCod.setDisable(false);
                btnNuevo.setText("Nuevo");
                //btnCancelar.setDisable(true);
                limpiarCampos();
                cargarDiagnosticosMedicos();
                
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

    private String validar() {

        if ("".equals(tfCod.getText())) {
            return "El código no puede estar vacío";
        }

        DiagnosticoMedico diagnosticomedico = this.diagnosticoMedicoDao.buscar(tfCod.getText());

        if (!btnNuevo.getText().equals("Modificar")) {
            if ("".equals(tfCod.getText())) {
                return "El código no puede estar vacío";
            }

            if (diagnosticomedico.getID() != 0) {
                return "Paciente ya existe";
            }
        }

        if (tfNombre.getText().length() >= 100 || tfNombre.getText().length() <= 0) {
            return "Error en la descripción";
        }

        if (tfTipo.getText().length() >= 100 || tfTipo.getText().length() <= 0) {
            return "Error en la descripción";
        }

        return "";

    }

}
