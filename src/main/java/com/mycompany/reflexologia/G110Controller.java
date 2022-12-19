/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import dao.G110DAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import modelo.G110;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    }

    @FXML
    void btnRegistrarOnAction(ActionEvent event) {

        G110 g110 = new G110();

        g110.setNombre(txtNombre.getText());
        g110.setCodigo(Integer.parseInt(txtCodigo.getText()));
        g110.setDNICE(Integer.parseInt(txtDNICE.getText()));
        g110.setFecha_nacimiento(java.sql.Date.valueOf(dpFecNac.getValue()));
        g110.setSexo(cbSexo.getSelectionModel().getSelectedItem());
        g110.setDireccion(txtDireccion.getText());
        g110.setDpto(txtDpto.getText());
        g110.setProv(txtProv.getText());
        g110.setDist(txtDist.getText());
        g110.setEspecial(cbEspecial.isSelected() ? true : false);
        g110.setTestimonio(txtTestimonio.getText());
        g110.setResultado(txtResultado.getText());
        g110.setObservacion(txtObservacion.getText());
        g110.setOcupacion(txtOcupacion.getText());
        g110.setTelefono(Integer.parseInt(txtTelefono.getText()));
        g110.setEmail(txtEmail.getText());

        System.out.println(g110.toString());

        boolean rpta = this.g110dao.registrar(g110);

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

}
