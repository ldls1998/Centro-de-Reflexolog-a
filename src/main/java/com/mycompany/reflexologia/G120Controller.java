/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import conexion.ConexionMySQL;
import dao.TerapeutaDAO;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import modelo.Paciente;
import modelo.Terapeuta;

/**
 * FXML Controller class
 *
 * @author Vlik35
 */
public class G120Controller implements Initializable {

    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfNombre;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private ComboBox<String> cbSexo;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfDpto;
    @FXML
    private TextField tfDist;
    @FXML
    private TextField tfCarnet;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfEmail;
    @FXML
    private CheckBox chbPermanente;
    @FXML
    private TableView<Terapeuta> tvTerapeutas;
    @FXML
    private ComboBox<String> cbBuscar;
    private TextField tfBuscarNombre;

    private ContextMenu cmOpciones;

    private ConexionMySQL conexion;
    @FXML
    private TextField tfProv;

    private TerapeutaDAO terapeutadao;

    private Terapeuta terapeutaSelected;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField tfBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] opciones = {"Masculino", "Femenino"};
        String[] opciones_busqueda = {"Nombre", "Código"};

        // this.pacienteDAO = new PacienteDAO();
        this.terapeutadao = new TerapeutaDAO();

        this.conexion = new ConexionMySQL();

        ObservableList<String> items = FXCollections.observableArrayList(opciones);
        ObservableList<String> items_busqueda = FXCollections.observableArrayList(opciones_busqueda);

        cbSexo.setItems(items);
        cbBuscar.setItems(items_busqueda);
        cbSexo.setValue("Seleccione");
        cbBuscar.setValue("Nombre");

        // this.g110dao = new G110DAO();
        cargarTerapeutas();

        cmOpciones = new ContextMenu();

        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");

        cmOpciones.getItems().addAll(miEditar, miEliminar);

        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                tfNumero.setDisable(true);
                btnRegistrar.setText("Modificar");
                btnCancelar.setDisable(false);

                int index = tvTerapeutas.getSelectionModel().getSelectedIndex();

                terapeutaSelected = tvTerapeutas.getItems().get(index);

                tfNumero.setText(Integer.toString(terapeutaSelected.getNumero()));

                Terapeuta terapeuta = terapeutadao.buscar(terapeutaSelected.getNumero());

                tfNombre.setText(terapeuta.getNombre());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = sdf.format(terapeuta.getFecha_nacimiento());
                LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
                dpFecha.setValue(localDate);
                cbSexo.getSelectionModel().select("F".equals(terapeuta.getSexo()) ? "Femenino" : "Masculino");
                tfDireccion.setText(terapeuta.getDireccion());
                tfDpto.setText(terapeuta.getDpto());
                tfProv.setText(terapeuta.getProv());
                tfDist.setText(terapeuta.getDist());
                tfCarnet.setText(terapeuta.getCarnet());
                chbPermanente.setSelected(terapeuta.getPermanente() == true ? true : false);
                tfTelefono.setText(Integer.toString(terapeuta.getTelefono()));
                tfEmail.setText(terapeuta.getEmail());
            }

        });

        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                int index = tvTerapeutas.getSelectionModel().getSelectedIndex();

                int numeroTerapeutaEliminar = tvTerapeutas.getItems().get(index).getNumero();

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

                alerta.setTitle("Confirmación");
                alerta.setHeaderText(null);
                alerta.setContentText("¿Realmente desea eliminar al terapeuta de número: " + numeroTerapeutaEliminar + "?");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnRegistrar.getScene().getWindow());

                Optional<ButtonType> result = alerta.showAndWait();

                if (result.get() == ButtonType.OK) {
                    boolean rpta = terapeutadao.eliminar(numeroTerapeutaEliminar);

                    if (rpta) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Exito");
                        alert.setHeaderText(null);
                        alert.setContentText("Se eliminó la cita correctamente");
                        alert.initStyle(StageStyle.UTILITY);
                        alert.initOwner(btnRegistrar.getScene().getWindow());
                        alert.showAndWait();
                        cargarTerapeutas();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Hubo un error al eliminar.");
                        alert.initStyle(StageStyle.UTILITY);
                        alert.initOwner(btnRegistrar.getScene().getWindow());
                        alert.showAndWait();
                    }
                }

            }

        });

        tvTerapeutas.setContextMenu(cmOpciones);
    }

    @FXML
    void btnRegistrarOnAction(ActionEvent event) {

        if (terapeutaSelected == null) {
            Terapeuta terapeuta = new Terapeuta();

            terapeuta.setNombre(tfNombre.getText());
            terapeuta.setNumero(Integer.parseInt(tfNumero.getText()));
            terapeuta.setFecha_nacimiento(java.sql.Date.valueOf(dpFecha.getValue()));
            terapeuta.setSexo(cbSexo.getSelectionModel().getSelectedItem());
            terapeuta.setDireccion(tfDireccion.getText());
            terapeuta.setDpto(tfDpto.getText());
            terapeuta.setProv(tfProv.getText());
            terapeuta.setDist(tfDist.getText());
            terapeuta.setCarnet(tfCarnet.getText());
            terapeuta.setPermanente(chbPermanente.isSelected() ? true : false);
            terapeuta.setTelefono(Integer.parseInt(tfTelefono.getText()));
            terapeuta.setEmail(tfEmail.getText());

            boolean rpta = this.terapeutadao.registrar(terapeuta);

            if (rpta) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);

                alerta.setTitle("Exito");
                alerta.setHeaderText(null);
                alerta.setContentText("Se registró el paciente correctamente");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnRegistrar.getScene().getWindow());
                alerta.showAndWait();
                limpiarCampos();
                cargarTerapeutas();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);

                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Hubo un error al registrar.");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnRegistrar.getScene().getWindow());
                alerta.showAndWait();
            }
        } else {

            terapeutaSelected.setNumero(terapeutaSelected.getNumero());
            terapeutaSelected.setNombre(tfNombre.getText());
            terapeutaSelected.setFecha_nacimiento(java.sql.Date.valueOf(dpFecha.getValue()));
            terapeutaSelected.setSexo(cbSexo.getSelectionModel().getSelectedItem());
            terapeutaSelected.setDireccion(tfDireccion.getText());
            terapeutaSelected.setDpto(tfDpto.getText());
            terapeutaSelected.setProv(tfProv.getText());
            terapeutaSelected.setDist(tfDist.getText());
            terapeutaSelected.setCarnet(tfCarnet.getText());
            terapeutaSelected.setPermanente(chbPermanente.isSelected() ? true : false);
            terapeutaSelected.setTelefono(Integer.parseInt(tfTelefono.getText()));
            terapeutaSelected.setEmail(tfEmail.getText());

            boolean rpta = this.terapeutadao.editar(terapeutaSelected);

            if (rpta) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);

                alerta.setTitle("Exito");
                alerta.setHeaderText(null);
                alerta.setContentText("Se editó el paciente correctamente");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnRegistrar.getScene().getWindow());
                alerta.showAndWait();
                limpiarCampos();
                cargarTerapeutas();
                terapeutaSelected = null;
                tfNumero.setDisable(false);
                btnRegistrar.setText("Registrar");
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);

                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Hubo un error al editar.");
                alerta.initStyle(StageStyle.UTILITY);
                alerta.initOwner(btnRegistrar.getScene().getWindow());
                alerta.showAndWait();
            }

        }

    }

    private void limpiarCampos() {

        tfBuscarNombre.setText("");
        cbBuscar.getSelectionModel().select(0);
        tfNumero.setText("");
        tfNombre.setText("");
        tfCarnet.setText("");
        tfDireccion.setText("");
        dpFecha.setValue(null);
        cbSexo.getSelectionModel().select("Seleccione");
        tfDist.setText("");
        tfDpto.setText("");
        tfEmail.setText("");
        chbPermanente.setSelected(false);
        tfProv.setText("");
        tfTelefono.setText("");

    }

    public void cargarTerapeutas() {

        tvTerapeutas.getItems().clear();
        tvTerapeutas.getColumns().clear();

        List<Terapeuta> terapeutas = this.terapeutadao.listar();

        ObservableList<Terapeuta> data = FXCollections.observableArrayList(terapeutas);

        TableColumn numColumn = new TableColumn("Número");
        numColumn.setCellValueFactory(new PropertyValueFactory("numero"));

        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));

        tvTerapeutas.setItems(data);
        tvTerapeutas.getColumns().addAll(numColumn, nombreColumn);

    }
    
    @FXML
    public void cargarTerapeutasBusqueda() {

        if (tfBuscar.getText().equals("")) {
            cargarTerapeutas();
        } else {
            tvTerapeutas.getItems().clear();
            tvTerapeutas.getColumns().clear();

            List<Terapeuta> terapeuta = null;

            if ("Nombre".equals(cbBuscar.getSelectionModel().getSelectedItem())) {
                terapeuta = this.terapeutadao.listarBusquedaNombre(tfBuscar.getText());
            } else if ("Código".equals(cbBuscar.getSelectionModel().getSelectedItem())) {
                terapeuta = this.terapeutadao.listarBusquedaCodigo(Integer.parseInt(tfBuscar.getText()));
            }

            ObservableList<Terapeuta> data = FXCollections.observableArrayList(terapeuta);

            TableColumn codColumn = new TableColumn("Número");
            codColumn.setCellValueFactory(new PropertyValueFactory("numero"));

            TableColumn nombreColumn = new TableColumn("Nombre");
            nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));

            tvTerapeutas.setItems(data);
            tvTerapeutas.getColumns().addAll(codColumn, nombreColumn);
        }
    }

    @FXML
    private void btnCancelarOnAction(ActionEvent event) {

        terapeutaSelected = null;
        tfNumero.setDisable(false);
        btnRegistrar.setText("Registrar");
        btnCancelar.setDisable(true);
        limpiarCampos();
    }

}
