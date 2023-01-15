/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import conexion.ConexionMySQL;
import dao.G110DAO;
import dao.PacienteDAO;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import modelo.Paciente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
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

    @FXML
    private TableView<Paciente> tvPacientes;

    private G110DAO g110dao;

    private ContextMenu cmOpciones;

    private Paciente pacienteSelected;

    private ConexionMySQL conexion;

    private PacienteDAO pacienteDAO;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        String[] opciones = {"Masculino", "Femenino"};

        this.pacienteDAO = new PacienteDAO();

        this.conexion = new ConexionMySQL();

        ObservableList<String> items = FXCollections.observableArrayList(opciones);

        cbSexo.setItems(items);
        cbSexo.setValue("Seleccione");

        this.g110dao = new G110DAO();

        cargarPacientes();

        cmOpciones = new ContextMenu();

        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");

        cmOpciones.getItems().addAll(miEditar);

//        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent t) {
//                int index = tvPacientes.getSelectionModel().getSelectedIndex();
//
//                int codigoPacienteEliminar = tvPacientes.getItems().get(index).getCodigo();
//
//                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
//
//                alerta.setTitle("Confirmación");
//                alerta.setHeaderText(null);
//                alerta.setContentText("¿Realmente desea eliminar al paciente de código: " + codigoPacienteEliminar + "?");
//                alerta.initStyle(StageStyle.UTILITY);
//                alerta.initOwner(btnDatosVisita.getScene().getWindow());
//
//                Optional<ButtonType> result = alerta.showAndWait();
//
//                if (result.get() == ButtonType.OK) {
//                    boolean rpta = pacienteDAO.eliminar(codigoPacienteEliminar);
//
//                    if (rpta) {
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//
//                        alert.setTitle("Exito");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Se eliminó el paciente correctamente");
//                        alert.initStyle(StageStyle.UTILITY);
//                        alert.showAndWait();
//                        cargarPacientes();
//                    } else {
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//
//                        alert.setTitle("Error");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Hubo un error al eliminar.");
//                        alert.initStyle(StageStyle.UTILITY);
//                        alert.showAndWait();
//                    }
//                }
//                alerta.initOwner(btnDatosVisita.getScene().getWindow());
//                alerta.showAndWait();
//                limpiarCampos();
//            }
//
//        });

        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                txtCodigo.setDisable(true);
                btnMarcarDisponible.setText("Modificar");
                btnCancelar.setDisable(false);

                int index = tvPacientes.getSelectionModel().getSelectedIndex();

                pacienteSelected = tvPacientes.getItems().get(index);

                txtCodigo.setText(Integer.toString(pacienteSelected.getCodigo()));

                Paciente paciente = pacienteDAO.buscar(pacienteSelected.getCodigo());

                txtNombre.setText(paciente.getNombre());
                txtDNICE.setText(Integer.toString(paciente.getDNICE()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = sdf.format(paciente.getFecha_nacimiento());
                LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
                dpFecNac.setValue(localDate);
                cbSexo.getSelectionModel().select("F".equals(paciente.getSexo()) ? "Femenino" : "Masculino");
                txtDireccion.setText(paciente.getDireccion());
                txtDpto.setText(paciente.getDpto());
                txtProv.setText(paciente.getProv());
                txtDist.setText(paciente.getDist());
                cbEspecial.setSelected(paciente.getEspecial() == true ? true : false);
                txtTestimonio.setText(paciente.getTestimonio());
                txtResultado.setText(paciente.getResultado());
                txtObservacion.setText(paciente.getObservacion());
                txtOcupacion.setText(paciente.getOcupacion());
                txtTelefono.setText(Integer.toString(paciente.getTelefono()));
                txtEmail.setText(paciente.getEmail());
            }

        });

        tvPacientes.setContextMenu(cmOpciones);
    }

    @FXML
    void btnRegistrarOnAction(ActionEvent event) {

        if (pacienteSelected == null) {
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
                alerta.initOwner(btnDatosVisita.getScene().getWindow());
                alerta.showAndWait();
            }
        } else {

            pacienteSelected.setCodigo(pacienteSelected.getCodigo());
            pacienteSelected.setNombre(txtNombre.getText());
            pacienteSelected.setDNICE(Integer.parseInt(txtDNICE.getText()));
            pacienteSelected.setFecha_nacimiento(java.sql.Date.valueOf(dpFecNac.getValue()));
            pacienteSelected.setSexo(cbSexo.getSelectionModel().getSelectedItem());
            pacienteSelected.setDireccion(txtDireccion.getText());
            pacienteSelected.setDpto(txtDpto.getText());
            pacienteSelected.setProv(txtProv.getText());
            pacienteSelected.setDist(txtDist.getText());
            pacienteSelected.setEspecial(cbEspecial.isSelected() ? true : false);
            pacienteSelected.setTestimonio(txtTestimonio.getText());
            pacienteSelected.setResultado(txtResultado.getText());
            pacienteSelected.setObservacion(txtObservacion.getText());
            pacienteSelected.setOcupacion(txtOcupacion.getText());
            pacienteSelected.setTelefono(Integer.parseInt(txtTelefono.getText()));
            pacienteSelected.setEmail(txtEmail.getText());

            boolean rpta = this.g110dao.editar(pacienteSelected);

            if (rpta) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);

                alerta.setTitle("Exito");
                alerta.setHeaderText(null);
                alerta.setContentText("Se editó el paciente correctamente");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnDatosVisita.getScene().getWindow());
                alerta.showAndWait();
                limpiarCampos();
                cargarPacientes();
                pacienteSelected = null;
                txtCodigo.setDisable(false);
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);

                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Hubo un error al editar.");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnDatosVisita.getScene().getWindow());
                alerta.showAndWait();
            }

        }

    }

    private void limpiarCampos() {

        txtNombre.setText("");
        txtCodigo.setText("");
        txtDNICE.setText("");
        dpFecNac.setValue(null);
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

        ObservableList<Paciente> data = FXCollections.observableArrayList(pacientes);

        TableColumn codColumn = new TableColumn("Código");
        codColumn.setCellValueFactory(new PropertyValueFactory("codigo"));

        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));

        tvPacientes.setItems(data);
        tvPacientes.getColumns().addAll(codColumn, nombreColumn);

    }

    @FXML
    private void btnCancelarOnAction(ActionEvent event) {

        pacienteSelected = null;
        txtCodigo.setDisable(false);
        btnMarcarDisponible.setText("Marcar Disponible");
        btnCancelar.setDisable(true);
        limpiarCampos();
    }

}
