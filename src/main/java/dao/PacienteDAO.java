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
import java.sql.SQLException;
import modelo.Paciente;

/**
 *
 * @author Vlik35
 */
public class PacienteDAO {
    private ConexionMySQL conexion;
    
    public PacienteDAO() {
        this.conexion = new ConexionMySQL();
    }
    
    public Paciente buscar(int codigo) {
        Paciente paciente = new Paciente();
        
        try {
            
            String select_all = "SELECT * FROM paciente WHERE codigo = ?;";
            Connection conexion = this.conexion.getConnection();
            
            PreparedStatement sentencia = conexion.prepareStatement(select_all);
            
            sentencia.setInt(1, codigo);
            
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {
                paciente.setCodigo(rs.getInt(2));
                paciente.setNombre(rs.getString(3));
                paciente.setDNICE(rs.getInt(4));
                paciente.setFecha_nacimiento(rs.getDate(5));
                paciente.setSexo(rs.getString(6));
                paciente.setDireccion(rs.getString(7));
                paciente.setDpto(rs.getString(8));
                paciente.setProv(rs.getString(9));
                paciente.setDist(rs.getString(10));
                paciente.setEspecial(rs.getBoolean(11));
                paciente.setTestimonio(rs.getString(12));
                paciente.setResultado(rs.getString(13));
                paciente.setObservacion(rs.getString(14));
                paciente.setOcupacion(rs.getString(15));
                paciente.setTelefono(rs.getInt(16));
                paciente.setEmail(rs.getString(17));
            }
            
            rs.close();
            sentencia.close();
            
        } catch (SQLException e) {
            
            System.out.println("Error al buscar pacientes.");
            System.out.println("Error: " + e);
            
        }
        
        return paciente;
    }
    
    public boolean eliminar(int codigo) {
        
        try {
            
            String SQL = "DELETE FROM paciente WHERE codigo = ?;";
            
            Connection conexion = this.conexion.getConnection();
            
            PreparedStatement sentencia = conexion.prepareStatement(SQL);
            
            sentencia.setInt(1, codigo);
            
            sentencia.executeUpdate();
            
            sentencia.close();
            
            return true;
            
            
        } catch (Exception e) {
            
            System.out.println("Error al eliminar pacientes.");
            System.out.println("Error: " + e);
            return false;
        }
    }
}
