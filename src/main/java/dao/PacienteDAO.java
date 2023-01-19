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
import java.util.ArrayList;
import java.util.List;
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
                paciente.setCodigo(rs.getInt(1));
                paciente.setNombre(rs.getString(2));
                paciente.setDNICE(rs.getInt(3));
                paciente.setFecha_nacimiento(rs.getDate(4));
                paciente.setSexo(rs.getString(5));
                paciente.setDireccion(rs.getString(6));
                paciente.setDpto(rs.getString(7));
                paciente.setProv(rs.getString(8));
                paciente.setDist(rs.getString(9));
                paciente.setEspecial(rs.getBoolean(10));
                paciente.setTestimonio(rs.getString(11));
                paciente.setResultado(rs.getString(12));
                paciente.setObservacion(rs.getString(13));
                paciente.setOcupacion(rs.getString(14));
                paciente.setTelefono(rs.getInt(15));
                paciente.setEmail(rs.getString(16));
            }
            
            rs.close();
            sentencia.close();
            
        } catch (SQLException e) {
            
            System.out.println("Error al buscar pacientes.");
            System.out.println("Error: " + e);
            
        }
        
        return paciente;
    }
    
    public List<Paciente> listarBusquedaCodigo(int codigo) {
        List<Paciente> listaPaciente = new ArrayList<>();

        try {

            String select_all = "SELECT * FROM paciente WHERE codigo LIKE '" + codigo + "%';";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);
            
            // sentencia.setInt(1, codigo);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                Paciente paciente = new Paciente();

                paciente.setCodigo(rs.getInt(1));
                paciente.setNombre(rs.getString(2));

                listaPaciente.add(paciente);
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al mostrar pacientes.");
            System.out.println("Error: " + e);

        }

        return listaPaciente;
    }
    
    public List<Paciente> listarBusquedaDNI(int dni) {
        List<Paciente> listaPaciente = new ArrayList<>();

        try {

            String select_all = "SELECT * FROM paciente WHERE dnice LIKE '" + dni + "%';";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);
            
            // sentencia.setInt(1, codigo);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                Paciente paciente = new Paciente();

                paciente.setCodigo(rs.getInt(1));
                paciente.setNombre(rs.getString(2));
                paciente.setDNICE(rs.getInt(3));

                listaPaciente.add(paciente);
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al mostrar pacientes.");
            System.out.println("Error: " + e);

        }

        return listaPaciente;
    }
    
    public List<Paciente> listarBusquedaNombre(String nombre) {
        List<Paciente> listaPaciente = new ArrayList<>();

        try {

            String select_all = "SELECT * FROM paciente WHERE nombre LIKE '" + nombre + "%';";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);
            
            // sentencia.setString(1, nombre);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                Paciente paciente = new Paciente();

                paciente.setCodigo(rs.getInt(1));
                paciente.setNombre(rs.getString(2));

                listaPaciente.add(paciente);
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al mostrar pacientes.");
            System.out.println("Error: " + e);

        }

        return listaPaciente;
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
    
    public List<String> listarNombrePacientes() {

        List<String> lista = new ArrayList<>();

        try {

            String select_all = "SELECT nombre FROM paciente;";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString(1));
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al mostrar la tabla.");
            System.out.println("Error: " + e);

        }

        return lista;
    }
}
