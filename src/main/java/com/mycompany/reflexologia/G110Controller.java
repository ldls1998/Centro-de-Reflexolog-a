/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import conexion.ConexionMySQL;
import dao.G110DAO;
import dao.PacienteDAO;
import java.io.IOException;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    @FXML
    private ChoiceBox<String> chbBusqueda;
    @FXML
    private TextField tfBusqueda;
    @FXML
    private AnchorPane apFooter;
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

        String[] opciones = {"Masculino", "Femenino"};
        String[] opciones_busqueda = {"Nombre", "C??digo"};

        this.pacienteDAO = new PacienteDAO();

        this.conexion = new ConexionMySQL();

        ObservableList<String> items = FXCollections.observableArrayList(opciones);
        ObservableList<String> items_busqueda = FXCollections.observableArrayList(opciones_busqueda);

        cbSexo.setItems(items);
        chbBusqueda.setItems(items_busqueda);
        cbSexo.setValue("Seleccione");
        chbBusqueda.setValue("Nombre");

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
//                alerta.setTitle("Confirmaci??n");
//                alerta.setHeaderText(null);
//                alerta.setContentText("??Realmente desea eliminar al paciente de c??digo: " + codigoPacienteEliminar + "?");
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
//                        alert.setContentText("Se elimin?? el paciente correctamente");
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
                btnNuevo.setText("Modificar");
                btnCancelar.setDisable(false);

                int index = tvPacientes.getSelectionModel().getSelectedIndex();

                pacienteSelected = tvPacientes.getItems().get(index);

                txtCodigo.setText(Integer.toString(pacienteSelected.getCodigo()));

                Paciente paciente = pacienteDAO.buscar(pacienteSelected.getCodigo());

                txtNombre.setText(paciente.getNombre());
                txtDNICE.setText(Integer.toString(paciente.getDNICE()));
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

        if (!"".equals(validar())) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText(validar());
            alerta.initStyle(StageStyle.UTILITY);
            alerta.initOwner(btnDatosVisita.getScene().getWindow());
            alerta.showAndWait();
            return;
        }

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
                alerta.setContentText("Se registr?? el paciente correctamente");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnDatosVisita.getScene().getWindow());
                alerta.showAndWait();
                limpiarCampos();
                cargarPacientes();
                pacienteSelected = null;
                txtCodigo.setDisable(false);
                btnMarcarDisponible.setText("Marcar Disponible");
                btnNuevo.setText("Nuevo");
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
                alerta.setContentText("Se edit?? el paciente correctamente");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnDatosVisita.getScene().getWindow());
                alerta.showAndWait();
                pacienteSelected = null;
                txtCodigo.setDisable(false);
                btnMarcarDisponible.setText("Marcar Disponible");
                btnNuevo.setText("Nuevo");
                btnCancelar.setDisable(true);
                limpiarCampos();
                cargarPacientes();
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

        TableColumn codColumn = new TableColumn("C??digo");
        codColumn.setCellValueFactory(new PropertyValueFactory("codigo"));

        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));

        tvPacientes.setItems(data);
        tvPacientes.getColumns().addAll(codColumn, nombreColumn);

    }

    @FXML
    public void cargarPacientesBusqueda() {

        if (tfBusqueda.getText().equals("")) {
            cargarPacientes();
        } else {
            tvPacientes.getItems().clear();
            tvPacientes.getColumns().clear();

            List<Paciente> pacientes = null;

            if ("Nombre".equals(chbBusqueda.getSelectionModel().getSelectedItem())) {
                pacientes = this.pacienteDAO.listarBusquedaNombre(tfBusqueda.getText());
            } else if ("C??digo".equals(chbBusqueda.getSelectionModel().getSelectedItem())) {
                pacientes = this.pacienteDAO.listarBusquedaCodigo(Integer.parseInt(tfBusqueda.getText()));
            }

            ObservableList<Paciente> data = FXCollections.observableArrayList(pacientes);

            TableColumn codColumn = new TableColumn("C??digo");
            codColumn.setCellValueFactory(new PropertyValueFactory("codigo"));

            TableColumn nombreColumn = new TableColumn("Nombre");
            nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));

            tvPacientes.setItems(data);
            tvPacientes.getColumns().addAll(codColumn, nombreColumn);
        }
    }

    @FXML
    private void btnCancelarOnAction(ActionEvent event) {

        pacienteSelected = null;
        txtCodigo.setDisable(false);
        btnMarcarDisponible.setText("Marcar Disponible");
        btnNuevo.setText("Nuevo");
        btnCancelar.setDisable(true);
        limpiarCampos();
    }

    private String validar() {
        
        if ("".equals(txtCodigo.getText())) {
            return "El c??digo no puede estar vac??o";
        }

        Paciente paciente = this.pacienteDAO.buscar(Integer.parseInt(txtCodigo.getText()));
        

        if (!btnNuevo.getText().equals("Modificar")) {

            if ("".equals(txtCodigo.getText())) {
                return "El c??digo no puede estar vac??o";
            }

            if (Integer.parseInt(txtCodigo.getText()) <= 0) {
                return "El c??digo no puede ser un valor negativo";
            }

            if (!isNumeric(txtCodigo.getText())) {
                return "El c??digo tiene que ser un n??mero";
            }

            if (paciente.getCodigo() != 0) {
                return "Paciente ya existe";
            }

        }

        if (txtNombre.getText().length() >= 50 || txtNombre.getText().length() <= 0) {
            return "Error en el nombre";
        }

        if (!isNumeric(txtDNICE.getText())) {
            return "El DNI no puede estar vac??o";
        }

        if (txtDNICE.getText().length() != 8) {
            return "El DNI debe tener 8 d??gitos";
        }

        if (dpFecNac.getValue() == null) {
            return "Ingrese la fecha";
        }

        LocalDate startDate = LocalDate.of(1900, 1, 1);
        LocalDate endDate = LocalDate.of(2100, 12, 31);
        if (dpFecNac.getValue().isBefore(startDate) || dpFecNac.getValue().isAfter(endDate)) {
            return "La fecha no se encuentra en un rango aceptable";
        }

        if ("Seleccione".equals(cbSexo.getValue())) {
            return "Seleccione el sexo";
        }

        if (txtDireccion.getText().length() > 100) {
            return "La direcci??n no puede tener m??s de 100 caracteres";
        }

        if (txtDpto.getText().length() > 100) {
            return "El departamento no puede tener m??s de 100 caracteres";
        }

        if (txtProv.getText().length() > 100) {
            return "La provincia no puede tener m??s de 100 caracteres";
        }

        if (txtDist.getText().length() > 100) {
            return "El distrito no puede tener m??s de 100 caracteres";
        }

        if (txtTestimonio.getText().length() > 50) {
            return "El testimonio no puede tener m??s de 50 caracteres";
        }

        if (txtResultado.getText().length() > 100) {
            return "El resultado no puede tener m??s de 100 caracteres";
        }

        if (txtObservacion.getText().length() > 500) {
            return "La observaci??n no puede tener m??s de 500 caracteres";
        }

        if (txtOcupacion.getText().length() > 50) {
            return "La ocupaci??n no puede tener m??s de 50 caracteres";
        }

        if (!isNumeric(txtTelefono.getText())) {
            return "El tel??fono no puede contener letras";
        }

        if (txtTelefono.getText().length() != 9) {
            return "El tel??fono debe tener 9 d??gitos";
        }

        if (txtEmail.getText().length() > 64) {
            return "La direcci??n email no puede tener m??s de 64 caracteres";
        }

        return "";

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

    @FXML
    private void irAtras(ActionEvent event) {

    }

    @FXML
    private void irAdelante(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        stage.close();

        cargarScene cargarScene = new cargarScene();

        String scene_name = "G120.fxml";
        String titulo = "G120. - Relaci??n de Terapeutas";

        cargarScene.loadScene(scene_name, 1080, 620, titulo, false, true);

    }

    @FXML
    private void buscar(ActionEvent event) {

    }

}
