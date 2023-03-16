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
                paciente.setFecha_nacimiento(rs.getDate(3));
                paciente.setSexo(rs.getString(4));
                paciente.setDireccion(rs.getString(5));
                paciente.setDpto(rs.getString(6));
                paciente.setProv(rs.getString(7));
                paciente.setDist(rs.getString(8));
                paciente.setEspecial(rs.getBoolean(9));
                paciente.setTestimonio(rs.getString(10));
                paciente.setResultado(rs.getString(11));
                paciente.setObservacion(rs.getString(12));
                paciente.setOcupacion(rs.getString(13));
                paciente.setTelefono(rs.getInt(14));
                paciente.setEmail(rs.getString(15));
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
    
    public List<Paciente> listarBusquedaDNI(int codigo) {
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
