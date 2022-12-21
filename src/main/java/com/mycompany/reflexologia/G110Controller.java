/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import dao.G110DAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import modelo.Paciente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vlik35
 */
public class G110Controller implements Initializable {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtDpto;

    @FXML
    private TextField txtProv;

    @FXML
    private TextField txtDist;

    @FXML
    private TextField txtOcupacion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtEmail;

    @FXML
    private CheckBox cbEspecial;

    @FXML
    private TextField txtDNICE;

    @FXML
    private DatePicker dpFecNac;

    @FXML
    private TextField txtTestimonio;

    @FXML
    private TextField txtResultado;

    @FXML
    private TextField txtObservacion;

    @FXML
    private Button btnDatosVisita;

    @FXML
    private Button btnMarcarDisponible;

    @FXML
    private ComboBox<String> cbSexo;

    private G110DAO g110dao;
    @FXML
    private TableView<Paciente> tvPacientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        String[] opciones = {"Masculino", "Femenino"};

        ObservableList<String> items = FXCollections.observableArrayList(opciones);

        cbSexo.setItems(items);
        cbSexo.setValue("Seleccione");

        this.g110dao = new G110DAO();
        
        cargarPacientes();
    }

    @FXML
    void btnRegistrarOnAction(ActionEvent event) {

        Paciente paciente = new Paciente();

        paciente.setNombre(txtNombre.getText());
        paciente.setCodigo(Integer.parseInt(txtCodigo.getText()));
        paciente.setDNICE(Integer.parseInt(txtDNICE.getText()));
        paciente.setFecha_nacimiento(java.sql.Date.valueOf(dpFecNac.getValue()));
        paciente.setSexo(cbSexo.getSelectionModel().getSelectedItem());
        paciente.setDireccion(txtDireccion.getText());
        paciente.setDpto(txtDpto.getText());
        paciente.setProv(txtProv.getText());
        paciente.setDist(txtDist.getText());
        paciente.setEspecial(cbEspecial.isSelected() ? true : false);
        paciente.setTestimonio(txtTestimonio.getText());
        paciente.setResultado(txtResultado.getText());
        paciente.setObservacion(txtObservacion.getText());
        paciente.setOcupacion(txtOcupacion.getText());
        paciente.setTelefono(Integer.parseInt(txtTelefono.getText()));
        paciente.setEmail(txtEmail.getText());

        System.out.println(paciente.toString());

        boolean rpta = this.g110dao.registrar(paciente);

        if (rpta) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);

            alerta.setTitle("Exito");
            alerta.setHeaderText(null);
            alerta.setContentText("Se registró el paciente correctamente");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.initOwner(btnDatosVisita.getScene().getWindow());
            alerta.showAndWait();
            limpiarCampos();
            cargarPacientes();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);

            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Hubo un error al registrar.");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
        }

    }

    private void limpiarCampos() {

        txtNombre.setText("");
        txtCodigo.setText("");
        txtDNICE.setText("");
        dpFecNac.setValue(LocalDate.now());
        cbSexo.getSelectionModel().select("Seleccione");
        txtDireccion.setText("");
        txtDpto.setText("");
        txtProv.setText("");
        txtDist.setText("");
        cbEspecial.setSelected(false);
        txtTestimonio.setText("");
        txtResultado.setText("");
        txtObservacion.setText("");
        txtOcupacion.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");

    }

    public void cargarPacientes() {
        
        tvPacientes.getItems().clear();
        tvPacientes.getColumns().clear();
        
        List<Paciente> pacientes = this.g110dao.listar();
        
        ObservableList<Paciente> data = FXCollections.observableArrayList( pacientes );
        
        TableColumn codColumn = new TableColumn("Código");
        codColumn.setCellValueFactory(new PropertyValueFactory("codigo"));
        
        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        tvPacientes.setItems(data);
        tvPacientes.getColumns().addAll(codColumn, nombreColumn);

    }
    
}
