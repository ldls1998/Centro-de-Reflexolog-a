/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.reflexologia;

import dao.Cita_PacienteDAO;
import dao.PacienteDAO;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Cita;
import modelo.Cita_Paciente;
import modelo.DataSingleton;
import modelo.Paciente;

/**
 * FXML Controller class
 *
 * @author Vlik35
 */
public class G210ModificarCitaController implements Initializable {

    @FXML
    private DatePicker dpFecha;
    @FXML
    private TextField tfImporte;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<String> cbNombrePaciente;
    @FXML
    private AnchorPane rootPane;

    private Cita_Paciente cp;

    private Cita_PacienteDAO cita_pacienteDAO;
    private PacienteDAO pacienteDAO;
    private G210Controller g210;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cp = DataSingleton.getInstance().getData();
        Date date = cp.getCita().getFecha_cita();
        SimpleDateFormat sdformatter = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fecha = sdformatter.format(date);
        dpFecha.setValue(LocalDate.parse(fecha, dtformatter));
        tfImporte.setText(String.valueOf(cp.getImporte()));
        cbNombrePaciente.setValue(cp.getNombre());
        this.pacienteDAO = new PacienteDAO();
        this.cita_pacienteDAO = new Cita_PacienteDAO();
        List<String> lista = this.pacienteDAO.listarNombrePacientes();

        ObservableList<String> nombres = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            nombres.add(lista.get(i));
        }

        cbNombrePaciente.setItems(nombres);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = sdf.format(fechaCitaModificar);
//        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
//        dpFecha.setValue(localDate);
//        cbNombrePaciente.setValue(nombreCitaModificar);
//        tfImporte.setText(String. valueOf(importeCitaModificar));

    }

    @FXML
    private void btnModificar(ActionEvent event) {

        cp.setImporte(Float.parseFloat(tfImporte.getText()));
        cp.setNombre(cbNombrePaciente.getValue());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = dpFecha.getValue().format(formatter);
        cp.setFecha_cita(java.sql.Date.valueOf(formattedDate));

        boolean rpta = cita_pacienteDAO.editar(this.cp.getCita(), this.cp.getNombre());

        if (rpta) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);

            alerta.setTitle("Exito");
            alerta.setHeaderText(null);
            alerta.setContentText("Se editó la cita.");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.initOwner(btnModificar.getScene().getWindow());
            alerta.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Hubo un error al editar.");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.initOwner(btnModificar.getScene().getWindow());
            alerta.showAndWait();
        }

        // Regresamos al anterior fxml
        String titulo = "G210. - Recepción y Cobranza Caja 1";
        try {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("G210.fxml"));
            rootPane.getChildren().setAll(pane);
            stage.setWidth(1080);
            stage.setHeight(620);
            stage.setTitle(titulo);
            stage.centerOnScreen();
        } catch (IOException ex) {
            System.err.println("Error al cargar.");
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        String titulo = "G210. - Recepción y Cobranza Caja 1";
        try {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("G210.fxml"));
            rootPane.getChildren().setAll(pane);
            stage.setWidth(1080);
            stage.setHeight(620);
            stage.setTitle(titulo);
            stage.centerOnScreen();
        } catch (IOException ex) {
            System.err.println("Error al cargar.");
        }
    }

}
