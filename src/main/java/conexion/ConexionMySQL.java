/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vlik35
 */
public class ConexionMySQL {

    private Connection connection;

    public ConexionMySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/reflexologia";
            String username = "root";
            String password = "puertanegra";
            connection = DriverManager.getConnection(url, username, password);
            
        } catch (ClassNotFoundException ex) {
            // Handle the error
            System.out.println("Erro al conectarse: + " + ex);
        } catch (SQLException ex) {
            // Handle the error
            System.out.println("Erro al conectarse: + " + ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
