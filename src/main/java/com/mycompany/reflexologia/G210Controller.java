/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import dao.Cita_PacienteDAO;
import dao.G110DAO;
import dao.CitaDAO;
import dao.PacienteDAO;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.FloatStringConverter;
import modelo.Cita;
import modelo.Cita_Paciente;
import modelo.DataSingleton;
import modelo.Paciente;
import modelo.PacienteSingleton;

/**
 * FXML Controller class
 *
 * @author Vlik35
 */
public class G210Controller implements Initializable {

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

    private CitaDAO citaDAO;

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
    @FXML
    private AnchorPane rootPane;

    public Cita cita;
    public int registroCitaModificar;
    public Date fechaCitaModificar;
    public float importeCitaModificar;
    public String nombreCitaModificar;

    @FXML
    private Button btnNuevaCita;
    @FXML
    private Button btnGuardar;

    Cita nueva_cita;
    Cita_Paciente nueva_cita_paciente;

    Paciente paciente_modificar;
    private G110DAO g110dao;
    private PacienteDAO pacientedao;
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

        tvCitasyPacientes.setEditable(true);

        dpFecha.setValue(LocalDate.now());

        this.nueva_cita_paciente = new Cita_Paciente();

        this.citaDAO = new CitaDAO();
        this.g110dao = new G110DAO();
        this.cita_pacienteDAO = new Cita_PacienteDAO();
        this.pacientedao = new PacienteDAO();

        String dateString = dpFecha.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Cita_Paciente> listaCitaPacienteporFecha = cita_pacienteDAO.buscarPorFecha(dateString);
        ObservableList<Cita_Paciente> datos = FXCollections.observableArrayList(listaCitaPacienteporFecha);
        cargarTabla(datos);

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

        // MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");

        cmOpciones.getItems().addAll(miEliminar);

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
                        citaDAO = new CitaDAO();
                        List<Cita_Paciente> lista = citaDAO.listar();
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

        nombreColumn.setOnEditStart(
                new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String scene_name = "G210 - BuscarPaciente.fxml";
                String titulo = "G210. - Buscar Paciente";
                cargarScene cargar = new cargarScene();
                // 640 x 480
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource(scene_name));

                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle(titulo);
                    stage.setScene(scene);
                    stage.setWidth(640);
                    stage.setHeight(480);
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.setAlwaysOnTop(true);
                    // stage.initModality(Modality.APPLICATION_MODAL);

                    // Pasamos la lista al controlador usando el método implementado
                    G210BuscarPacienteController controlador = (G210BuscarPacienteController) fxmlLoader.getController();
                    controlador.setTablaPaciente(tvCitasyPacientes);

                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(G210Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );

        nombreColumn.setOnEditCommit(new EventHandler<CellEditEvent<Cita_Paciente, String>>() {
            @Override
            public void handle(CellEditEvent<Cita_Paciente, String> event) {
                Cita_Paciente cp_2 = event.getRowValue();
                cp_2.setPaciente(PacienteSingleton.getInstance().getData());
            }

        });

        TableColumn fechaColumn = new TableColumn("Fecha");
//        fechaColumn.setCellFactory(column -> {
//            TableCell<Cita_Paciente, Date> cell = new TableCell<Cita_Paciente, Date>() {
//                private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//
//                @Override
//                protected void updateItem(Date item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (empty) {
//                        setText(null);
//                    } else {
//                        setText(format.format(item));
//                    }
//                }
//            };
//
//            return cell;
//        });

        fechaColumn.setCellValueFactory(
                new PropertyValueFactory("fecha_cita"));

        TableColumn importeColumn = new TableColumn("Imp");
        importeColumn.setCellValueFactory(
                new PropertyValueFactory("importe"));
        importeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        importeColumn.setOnEditCommit(new EventHandler<CellEditEvent<Cita_Paciente, Float>>() {
            @Override
            public void handle(CellEditEvent<Cita_Paciente, Float> event) {
                Cita_Paciente cp_2 = event.getRowValue();
                cp_2.setImporte(event.getNewValue());
            }

        });

        TableColumn citaColumn = new TableColumn<>("Cita");
        citaColumn.setCellValueFactory(new PropertyValueFactory<>("citaBool"));
        citaColumn.setCellFactory(CheckBoxTableCell.forTableColumn(citaColumn));

        TableColumn ctodoColumn = new TableColumn<>("Ctodo");
        ctodoColumn.setCellValueFactory(new PropertyValueFactory<>("ctodo"));
        ctodoColumn.setCellFactory(CheckBoxTableCell.forTableColumn(ctodoColumn));

        TableColumn adeColumn = new TableColumn<>("Ade");
        adeColumn.setCellValueFactory(new PropertyValueFactory<>("sde"));
        adeColumn.setCellFactory(CheckBoxTableCell.forTableColumn(adeColumn));

        TableColumn saldoColumn = new TableColumn<>("Saldo");
        saldoColumn.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        saldoColumn.setCellFactory(CheckBoxTableCell.forTableColumn(saldoColumn));

        TableColumn obsColumn = new TableColumn("Observa");
        obsColumn.setCellValueFactory(new PropertyValueFactory<>("observa"));
        obsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        obsColumn.setOnEditCommit(new EventHandler<CellEditEvent<Cita_Paciente, String>>() {
            @Override
            public void handle(CellEditEvent<Cita_Paciente, String> event) {
                Cita_Paciente cp_2 = event.getRowValue();
                cp_2.setObserva(event.getNewValue());
            }

        });

        TableColumn opColumn = new TableColumn("Op");
        obsColumn.setCellValueFactory(new PropertyValueFactory<>("op"));

        TableColumn numColumn = new TableColumn("#");
        obsColumn.setCellValueFactory(new PropertyValueFactory<>("num"));

        TableColumn horaColumn = new TableColumn("Hora");
        obsColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));

        tvCitasyPacientes.setItems(cita_paciente);

        tvCitasyPacientes.getColumns()
                .addAll(registroColumn, horaColumn, codigoColumn, nombreColumn, fechaColumn,
                        citaColumn, ctodoColumn, adeColumn, importeColumn,
                        opColumn, saldoColumn, obsColumn, numColumn);

    }

    @FXML
    void btnConsultarCitas(ActionEvent event) {

        PacienteSingleton.getInstance().setData(null);

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
            List<Cita_Paciente> cita_paciente = this.citaDAO.listar();
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

    @FXML
    private void btnCrearCita(ActionEvent event) {

        PacienteSingleton.getInstance().setData(null);

        if (dpFecha.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ingrese una fecha para crear la cita.");
            alert.initStyle(StageStyle.UTILITY);
            alert.initOwner(btnImprimirRecibo.getScene().getWindow());
            alert.showAndWait();
        } else {
            this.nueva_cita = new Cita();
            this.nueva_cita_paciente = new Cita_Paciente(nueva_cita);
            this.nueva_cita_paciente.setFecha_cita(java.sql.Date.valueOf(dpFecha.getValue()));
            this.nueva_cita_paciente.setNombre("Ingrese nombre");
            String dateString = dpFecha.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            List<Cita_Paciente> listaCitaPacienteporFecha = cita_pacienteDAO.buscarPorFecha(dateString);
            listaCitaPacienteporFecha.add(this.nueva_cita_paciente);
            ObservableList<Cita_Paciente> datos = FXCollections.observableArrayList(listaCitaPacienteporFecha);
            cargarTabla(datos);
            btnGuardar.setVisible(true);
        }

    }

    @FXML
    private void btnGuardarCita(ActionEvent event) {

        Paciente p = PacienteSingleton.getInstance().getData();

        int index = -1;

        index = tvCitasyPacientes.getSelectionModel().getSelectedIndex();

        if ((p == null) && (index == -1)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Complete los datos de la cita.");
            alert.initStyle(StageStyle.UTILITY);
            alert.initOwner(btnImprimirRecibo.getScene().getWindow());
            alert.showAndWait();
            return;
        }

        if (p != null) {
            float importeCitaModificar = tvCitasyPacientes.getItems().get(index).getImporte();

            Cita cita = new Cita();
            cita.setCodigo_paciente(p.getCodigo());

            if (dpFecha.getValue() == null) {

                if (tvCitasyPacientes.getItems().get(index).getFecha_cita() == null) {
                    dpFecha.setValue(LocalDate.now());
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = sdf.format(tvCitasyPacientes.getItems().get(index).getFecha_cita());
                    LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
                    dpFecha.setValue(localDate);
                }

            }

            cita.setFecha_cita(java.sql.Date.valueOf(dpFecha.getValue()));
            cita.setImporte(importeCitaModificar);

            tvCitasyPacientes.getItems().get(index).setPaciente(p);
            tvCitasyPacientes.getItems().get(index).setCodigo_paciente(p.getCodigo());
            tvCitasyPacientes.getItems().get(index).setNombre(p.getNombre());

            this.nueva_cita_paciente.setFecha_cita(java.sql.Date.valueOf(dpFecha.getValue()));
            this.nueva_cita_paciente.setCodigo_paciente(p.getCodigo());
            this.nueva_cita_paciente.setImporte(importeCitaModificar);

            this.nueva_cita_paciente.setPaciente(
                    this.pacientedao.buscar(p.getCodigo()));
        }

        float importeCitaModificar = tvCitasyPacientes.getItems().get(index).getImporte();
        Cita cita = new Cita();
        cita.setCodigo_paciente(tvCitasyPacientes.getItems().get(index).getCodigo_paciente());

        if (dpFecha.getValue() == null) {

            if (tvCitasyPacientes.getItems().get(index).getFecha_cita() == null) {
                dpFecha.setValue(LocalDate.now());
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = sdf.format(tvCitasyPacientes.getItems().get(index).getFecha_cita());
                LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
                dpFecha.setValue(localDate);
            }

        }

        cita.setFecha_cita(java.sql.Date.valueOf(dpFecha.getValue()));
        cita.setImporte(importeCitaModificar);

        this.nueva_cita_paciente.setFecha_cita(java.sql.Date.valueOf(dpFecha.getValue()));
        this.nueva_cita_paciente.setCodigo_paciente(tvCitasyPacientes.getItems().get(index).getCodigo_paciente());
        this.nueva_cita_paciente.setImporte(importeCitaModificar);

        this.nueva_cita_paciente.setPaciente(
                this.pacientedao.buscar(tvCitasyPacientes.getItems().get(index).getCodigo_paciente()));

        tvCitasyPacientes.refresh();

        int registroCitaModificar = tvCitasyPacientes.getItems().get(index).getRegistro();

        if (registroCitaModificar == 0) {

            this.citaDAO.crear(cita);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Se ha creado la cita correctamente.");
            alert.initStyle(StageStyle.UTILITY);
            alert.initOwner(btnImprimirRecibo.getScene().getWindow());
            alert.showAndWait();
            tvCitasyPacientes.refresh();
            return;

        } else {

            Cita citaModificar = this.citaDAO.buscar(registroCitaModificar);

            citaModificar.setImporte(importeCitaModificar);

            citaModificar.setCodigo_paciente(tvCitasyPacientes.getItems().get(index).getCodigo_paciente());

            citaModificar.setFecha_cita(java.sql.Date.valueOf(dpFecha.getValue()));
            
            citaModificar.setCtodo(tvCitasyPacientes.getItems().get(index).isCtodo());
            
            citaModificar.setSde(tvCitasyPacientes.getItems().get(index).isSde());
            
            citaModificar.setObserva(tvCitasyPacientes.getItems().get(index).getObserva());
            
            citaModificar.setCitaBool(tvCitasyPacientes.getItems().get(index).isCitaBool());
            
            citaModificar.setHora(tvCitasyPacientes.getItems().get(index).getHora());
            
            citaModificar.setSaldo(tvCitasyPacientes.getItems().get(index).isSaldo());
            
            citaModificar.setOp(tvCitasyPacientes.getItems().get(index).getOp());
            
            citaModificar.setNum(tvCitasyPacientes.getItems().get(index).getNum());

            this.citaDAO.editar(citaModificar);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Se ha guardado la cita correctamente.");
            alert.initStyle(StageStyle.UTILITY);
            alert.initOwner(btnImprimirRecibo.getScene().getWindow());
            alert.showAndWait();
            tvCitasyPacientes.refresh();

        }

    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @FXML
    private void irAtras(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        stage.close();

        cargarScene cargarScene = new cargarScene();

        String scene_name = "G140.fxml";
        String titulo = "G140. - Diagnósticos Médicos";

        cargarScene.loadScene(scene_name, 1080, 620, titulo, false, true);

    }

    @FXML
    private void irAdelante(ActionEvent event) throws IOException {

    }

    @FXML
    private void nuevo(ActionEvent event) {
    }

    @FXML
    private void buscar(ActionEvent event) {
    }

}
