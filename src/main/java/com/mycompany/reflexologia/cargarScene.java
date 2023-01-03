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

/**
 *
 * @author Vlik35
 */
public class cargarScene {
    
    public void loadScene(String scene_name, int width, int height, String title, boolean resizable, boolean onTop) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(scene_name));

        Scene scene_show = new Scene(root);
        Stage new_stage = new Stage();
        // Creamos la escena con la raíz de la jerarquía de nodos como raíz
        new_stage.setScene(scene_show);
        new_stage.setWidth(width);
        new_stage.setHeight(height);
        new_stage.setTitle(title);
        new_stage.centerOnScreen();
        new_stage.setResizable(resizable);
        new_stage.setAlwaysOnTop(onTop);
        new_stage.show();

    }
    
}
