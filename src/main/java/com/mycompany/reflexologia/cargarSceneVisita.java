/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reflexologia;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Visita;

/**
 *
 * @author Vlik35
 */
public class cargarSceneVisita {

    public Scene loadScene(String scene_name, int width, int height, String title, boolean resizable, boolean onTop, String parametro, String parametro2, Visita visita) throws IOException {

    // Cargar la vista desde el archivo FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource(scene_name));
    Parent root = loader.load();

    // Establecer los datos de usuario en el nodo raíz de la vista
    root.setUserData(parametro);
    root.setUserData(parametro2);
    root.setUserData(visita);
    
    // Obtener el controlador de la vista cargada y establecer el valor del parámetro en la etiqueta
    VisitaController controller = loader.getController();
    controller.setParametro(parametro, parametro2, visita);

    // Crear la escena con la raíz de la jerarquía de nodos como raíz
    Scene scene = new Scene(root, width, height);

    // Crear una nueva ventana con la escena
    Stage new_stage = new Stage();
    new_stage.setScene(scene);
    new_stage.setTitle(title);
    new_stage.setResizable(resizable);
    new_stage.setAlwaysOnTop(onTop);

    return scene;
}

}
