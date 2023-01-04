/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import dao.Cita_PacienteDAO;
import dao.G210DAO;
import java.net.URL;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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

    @FXML
    private ComboBox<String> cbMes;
    @FXML
    private ComboBox<String> cbAnio;

    private G210DAO g210dao;

    private ContextMenu cmOpciones;

    private Cita_PacienteDAO cita_pacienteDAO;

    private static final Map<String, Integer> monthMap = new HashMap<>();

    static {
        monthMap.put("ENERO", 1);
        monthMap.put("FEBRERO", 2);
        monthMap.put("MARZO", 3);
        monthMap.put("ABRIL", 4);
        monthMap.put("MAYO", 5);
        monthMap.put("JUNIO", 6);
        monthMap.put("JULIO", 7);
        monthMap.put("AGOSTO", 8);
        monthMap.put("SEPTIEMBRE", 9);
        monthMap.put("OCTUBRE", 10);
        monthMap.put("NOVIEMBRE", 11);
        monthMap.put("DICIEMBRE", 12);
    }
    @FXML
    private Button btnLimpiarFechas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.g210dao = new G210DAO();
        this.cita_pacienteDAO = new Cita_PacienteDAO();
        List<Cita_Paciente> cita_paciente = this.g210dao.listar();
        ObservableList<Cita_Paciente> data = FXCollections.observableArrayList(cita_paciente);
        cargarTabla(data);

        ObservableList<String> anios = FXCollections.observableArrayList();
        for (int i = 2000; i <= Year.now().getValue(); i++) {
            anios.add(String.valueOf(i));
        }

        cbAnio.setItems(anios);
        cbAnio.setValue("Año");

        ObservableList<String> meses = FXCollections.observableArrayList();
        for (Month month : Month.values()) {
            meses.add(month.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es")));
        }

        cbMes.setItems(meses);
        cbMes.setValue("Mes");

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
                        g210dao = new G210DAO();
                        List<Cita_Paciente> lista = g210dao.listar();
                        ObservableList<Cita_Paciente> data = FXCollections.observableArrayList(lista);
                        cargarTabla(data);
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

    public void cargarTabla(ObservableList<Cita_Paciente> cita_paciente) {

        tvCitasyPacientes.getItems().clear();
        tvCitasyPacientes.getColumns().clear();

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

        tvCitasyPacientes.setItems(cita_paciente);
        tvCitasyPacientes.getColumns().addAll(registroColumn, codigoColumn, nombreColumn, fechaColumn, importeColumn);

    }

    @FXML
    void btnConsultarCitas(ActionEvent event) {
        
        if (dpFecha.getValue() != null) {
            
            String dateString = dpFecha.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            List<Cita_Paciente> fecha = cita_pacienteDAO.buscarPorFecha(dateString);
            ObservableList<Cita_Paciente> datos = FXCollections.observableArrayList(fecha);
            cargarTabla(datos);
            return;
        }

        if ((!cbMes.getValue().equals("Mes") && cbMes.getValue() != null)
                && (!cbAnio.getValue().equals("Año") && cbAnio.getValue() != null)) {
            int monthNumber = monthMap.get(cbMes.getValue().toUpperCase());
            int yearNumber = Integer.parseInt(cbAnio.getValue());
            List<Cita_Paciente> mesYanio = cita_pacienteDAO.buscarPorMesYAnio(monthNumber, yearNumber);
            ObservableList<Cita_Paciente> datos = FXCollections.observableArrayList(mesYanio);
            cargarTabla(datos);
        } else if ((!cbMes.getValue().equals("Mes") && cbMes.getValue() != null)
                && (cbAnio.getValue().equals("Año") || cbAnio.getValue() == null)) {

            int monthNumber = monthMap.get(cbMes.getValue().toUpperCase());
            List<Cita_Paciente> mes = cita_pacienteDAO.buscarPorMes(monthNumber);
            ObservableList<Cita_Paciente> datos = FXCollections.observableArrayList(mes);
            cargarTabla(datos);

        } else if ((cbMes.getValue().equals("Mes") || cbMes.getValue() == null)
                && (!cbAnio.getValue().equals("Año") && cbAnio.getValue() != null)) {

            int yearNumber = Integer.parseInt(cbAnio.getValue());
            List<Cita_Paciente> anio = cita_pacienteDAO.buscarPorAnio(yearNumber);
            ObservableList<Cita_Paciente> datos = FXCollections.observableArrayList(anio);
            cargarTabla(datos);

        } else {
            List<Cita_Paciente> cita_paciente = this.g210dao.listar();
            ObservableList<Cita_Paciente> data = FXCollections.observableArrayList(cita_paciente);
            cargarTabla(data);
        }

    }

    @FXML
    void btnLimpiarFechas(ActionEvent event) {

        dpFecha.setValue(null);
        cbAnio.setValue("Año");
        cbMes.setValue("Mes");
        btnConsultarCitas(event);
        
    }
}
