/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.reflexologia;

import dao.G110DAO;
import dao.PacienteDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Cita_Paciente;
import modelo.Paciente;

/**
 * FXML Controller class
 *
 * @author Vlik35
 */
public class G210BuscarPacienteController implements Initializable {

    @FXML
    private AnchorPane apBuscarNombre;
    @FXML
    private TextField tfDNI;
    @FXML
    private TableView<Paciente> tvPacientes;

    private PacienteDAO pacienteDAO;

    private G110DAO g110dao;
    @FXML
    private Button btnBoton;

    private PacienteDAO pacientedao;

    TableView<Cita_Paciente> tabla;

    public void setTablaPaciente(TableView<Cita_Paciente> tvCitasyPacientes) {
        this.tabla = tvCitasyPacientes;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.pacienteDAO = new PacienteDAO();
        this.g110dao = new G110DAO();
        cargarPacientes();
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

        TableColumn dniColumn = new TableColumn("DNI");
        dniColumn.setCellValueFactory(new PropertyValueFactory("DNICE"));

        tvPacientes.setItems(data);
        tvPacientes.getColumns().addAll(codColumn, nombreColumn, dniColumn);
    }

    @FXML
    public void cargarPacientesBusqueda() {

        if (tfDNI.getText().equals("")) {
            cargarPacientes();
        } else {
            tvPacientes.getItems().clear();
            tvPacientes.getColumns().clear();

            List<Paciente> pacientes = null;

            pacientes = this.pacienteDAO.listarBusquedaDNI(Integer.parseInt(tfDNI.getText()));

            ObservableList<Paciente> data = FXCollections.observableArrayList(pacientes);

            TableColumn codColumn = new TableColumn("Código");
            codColumn.setCellValueFactory(new PropertyValueFactory("codigo"));

            TableColumn nombreColumn = new TableColumn("Nombre");
            nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));

            TableColumn dniColumn = new TableColumn("DNI");
            dniColumn.setCellValueFactory(new PropertyValueFactory("DNICE"));

            tvPacientes.setItems(data);
            tvPacientes.getColumns().addAll(codColumn, nombreColumn, dniColumn);
        }
    }

    @FXML
    private void enterKey(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            Stage previeousStage = (Stage) Stage.getWindows().get(1);
            Scene scene = previeousStage.getScene();
            AnchorPane anchorPane = (AnchorPane) scene.getRoot();
            anchorPane.setDisable(false);
            Stage actualStage = (Stage) tfDNI.getScene().getWindow();
            actualStage.close();
        }

    }

    @FXML
    private void elegirPaciente(MouseEvent event) {

        if (event.getClickCount() == 2) {
            Paciente selectedItem = tvPacientes.getSelectionModel().getSelectedItem();
            Paciente pacienteBusqueda = pacienteDAO.buscar(selectedItem.getCodigo());
            // PacienteSingleton.getInstance().setData(pacienteBusqueda);
            Stage previeousStage = (Stage) Stage.getWindows().get(1);
            Stage actualStage = (Stage) tfDNI.getScene().getWindow();
            Scene scene = previeousStage.getScene();

            // Paciente p = PacienteSingleton.getInstance().getData();
            int index = this.tabla.getSelectionModel().getSelectedIndex();

            this.tabla.getItems().get(index).setPaciente(pacienteBusqueda);
//            this.tabla.getItems().get(index).setCodigo_paciente(p.getCodigo());
//            this.tabla.getItems().get(index).setNombre(p.getNombre());
            this.tabla.refresh();
            AnchorPane anchorPane = (AnchorPane) scene.getRoot();
            anchorPane.setDisable(false);

            actualStage.close();
        }

    }
}
