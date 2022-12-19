/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vlik35
 */
public class ConexionMySQL {

    private Connection connection;

    public ConexionMySQL() {
        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            String url = "jdbc:mysql://localhost:3306/reflexologia";
            String username = "root";
            String password = "admin";
            connection = DriverManager.getConnection(url, username, password);

            // Do something with the connection here, such as executing a query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM paciente");

            if (connection != null) {
                System.out.println("CONEXIÓN CORRECTA.");
            }

            // Close the connection
            
        } catch (ClassNotFoundException ex) {
            // Handle the error
        } catch (SQLException ex) {
            // Handle the error
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
