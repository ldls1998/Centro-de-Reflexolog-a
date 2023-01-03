/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import dao.LoginDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Administrador;

/**
 * FXML Controller class
 *
 * @author Vlik35
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Button btnSalir;
    @FXML
    private Label lblOlvidasteContrasena;
    @FXML
    private Label lblMensageError;
    @FXML
    private PasswordField psfContrasena;

    private LoginDAO loginDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loginDAO = new LoginDAO();
    }

    @FXML
    public void loggin(ActionEvent e) throws IOException {

        Administrador admin = new Administrador();
        admin.setUsuario(txtUsuario.getText());
        admin.setContrasena(psfContrasena.getText());

        if (txtUsuario.getText().isBlank() && psfContrasena.getText().isBlank()) {
            lblMensageError.setText("Llene los campos");
        } else if (txtUsuario.getText().isBlank()) {
            lblMensageError.setText("Ingrese su usuario");
        } else if (psfContrasena.getText().isBlank()) {
            lblMensageError.setText("Ingrese su contraseña");
        } else {
            boolean rpta = this.loginDAO.iniciarSesion(admin);

            if (rpta) {
                
                Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
                stage.close();
                
                cargarScene cargarScene = new cargarScene();
                
                String scene_name = "primary.fxml";
                String titulo = "ReflexoPerú";
        
                cargarScene.loadScene(scene_name, 1280, 720, titulo, false, true);
                
            } else {
                lblMensageError.setText("Datos incorrectos");
            }

        }
    }

    @FXML
    public void salir(ActionEvent e) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

}
