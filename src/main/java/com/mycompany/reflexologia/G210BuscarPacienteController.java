/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.reflexologia;

import dao.PacienteDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pacienteDAO = new PacienteDAO();
    }    
    
}
