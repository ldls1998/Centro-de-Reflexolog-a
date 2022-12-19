/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Administrador;

/**
 *
 * @author Vlik35
 */
public class LoginDAO {
    
    private ConexionMySQL conexion;
    
    public LoginDAO() {
        this.conexion = new ConexionMySQL();
    }
    
    public boolean iniciarSesion(Administrador admin) {
        
        try {
            
            String SQL = "SELECT count(*) FROM reflexologia.administradores WHERE usuario = ? AND contrasena = ?;";
            Connection connection = this.conexion.getConnection();
            
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            
            sentencia.setString(1, admin.getUsuario());
            sentencia.setString(2, admin.getContrasena());
            
            ResultSet rs = sentencia.executeQuery();
            
            while(rs.next()) {
                if (rs.getInt("count(*)") == 1) {
                    return true;
                }
                return false;
            }
            
            sentencia.close();
            
            return true;
            
        } catch (Exception e) {
        
            System.err.println("Ocurrio un error al registrar al paciente");
            e.printStackTrace();
            return false;
            
        }

    }
    
}
