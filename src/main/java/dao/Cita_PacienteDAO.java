/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cita;
import modelo.Cita_Paciente;
import modelo.Paciente;

/**
 *
 * @author Vlik35
 */
public class Cita_PacienteDAO {
    
    private ConexionMySQL conexion;
    
    public Cita_PacienteDAO() {
        this.conexion = new ConexionMySQL();
    }
    
    public boolean registrar(Paciente paciente) {
        
        try {
            
            String SQL = "INSERT INTO paciente(codigo, nombre, dnice, fecha_nacimiento, "
                    + "sexo, direccion, dpto, prov, dist,"
                    + "especial, testimonio, resultado, observacion, ocupacion, telefono, email)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = this.conexion.getConnection();
            
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            
            sentencia.setInt(1, paciente.getCodigo());
            sentencia.setString(2, paciente.getNombre());
            sentencia.setInt(3, paciente.getDNICE());
            sentencia.setDate(4, (Date) paciente.getFecha_nacimiento());
            sentencia.setString(5, String.valueOf(paciente.getSexo().charAt(0)));
            sentencia.setString(6, paciente.getDireccion());
            sentencia.setString(7, paciente.getDpto());
            sentencia.setString(8, paciente.getProv());
            sentencia.setString(9, paciente.getDist());
            sentencia.setBoolean(10, paciente.getEspecial());
            sentencia.setString(11, paciente.getTestimonio());
            sentencia.setString(12, paciente.getResultado());
            sentencia.setString(13, paciente.getObservacion());
            sentencia.setString(14, paciente.getOcupacion());
            sentencia.setInt(15, paciente.getTelefono());
            sentencia.setString(16, paciente.getEmail());
            
            sentencia.executeUpdate();
            sentencia.close();
            
            return true;
            
        } catch (Exception e) {
        
            System.err.println("Ocurrio un error al registrar al paciente");
            e.printStackTrace();
            return false;
            
        }

    }
    
    public List<Paciente> listar() {
        
        List<Paciente> listaPaciente = new ArrayList<>();
        
        try {
            
            String select_all = "SELECT * FROM paciente;";
            Connection conexion = this.conexion.getConnection();
            
            PreparedStatement sentencia = conexion.prepareStatement(select_all);
            
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {
                
                Paciente paciente = new Paciente();
                
                paciente.setCodigo(rs.getInt(2));
                paciente.setNombre(rs.getString(3));

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
    
    public boolean eliminar(int registro) {
        
        try {
            
            String SQL = "DELETE FROM citas WHERE registro = ?;";
            
            Connection conexion = this.conexion.getConnection();
            
            PreparedStatement sentencia = conexion.prepareStatement(SQL);
            
            sentencia.setInt(1, registro);
            
            sentencia.executeUpdate();
            
            sentencia.close();
            
            return true;
            
            
        } catch (Exception e) {
            
            System.out.println("Error al eliminar cita.");
            System.out.println("Error: " + e);
            return false;
        }
    }
    
    public List<Cita_Paciente> buscarPorFecha(String fecha) {
        
        List<Cita_Paciente> lista = new ArrayList<>();
        
        try {
            
            String SQL = "SELECT * FROM citas WHERE fecha_cita = ?;";
            Connection connection = this.conexion.getConnection();
            
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            
            sentencia.setString(1, fecha);
            
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {

                Cita cita = new Cita();
                Paciente paciente = new Paciente();
                
                cita.setRegistro(rs.getInt(1));
                cita.setFecha_cita(rs.getDate(2));
                cita.setImporte(rs.getFloat(3));
                cita.setCodigo_paciente(rs.getInt(4));

                String select_nombre = "SELECT * FROM paciente "
                        + "WHERE codigo = ?;";

                PreparedStatement sentencia_2 = connection.prepareStatement(select_nombre);

                sentencia_2.setInt(1, cita.getCodigo_paciente());

                ResultSet rs_2 = sentencia_2.executeQuery();

                while (rs_2.next()) {
                    paciente.setCodigo(rs_2.getInt(1));
                    paciente.setNombre(rs_2.getString(2));
                    paciente.setDNICE(rs_2.getInt(3));
                    paciente.setFecha_nacimiento(rs_2.getDate(4));
                    paciente.setSexo(rs_2.getString(5));
                    paciente.setDireccion(rs_2.getString(6));
                    paciente.setDpto(rs_2.getString(7));
                    paciente.setProv(rs_2.getString(8));
                    paciente.setDist(rs_2.getString(9));
                    paciente.setEspecial(rs_2.getBoolean(10));
                    paciente.setTestimonio(rs_2.getString(11));
                    paciente.setResultado(rs_2.getString(12));
                    paciente.setObservacion(rs_2.getString(13));
                    paciente.setOcupacion(rs_2.getString(14));
                    paciente.setTelefono(rs_2.getInt(15));
                    paciente.setEmail(rs_2.getString(16));
                }

                Cita_Paciente cp = new Cita_Paciente(cita, paciente);

                lista.add(cp);
                rs_2.close();
                sentencia_2.close();
            }

            rs.close();
            sentencia.close();
            
            
        } catch (Exception e) {
            
            System.out.println("Error al buscar por fecha.");
            System.out.println("Error: " + e);
            
        }
        
        return lista;
    }
    public List<Cita_Paciente> buscarPorMesYAnio(int mes, int anio) {
        
        List<Cita_Paciente> lista = new ArrayList<>();
        
        try {
            
            String SQL = "SELECT * FROM citas WHERE MONTH(fecha_cita) = ? AND YEAR(fecha_cita) = ?;";
            Connection connection = this.conexion.getConnection();
            
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            
            sentencia.setInt(1, mes);
            sentencia.setInt(2, anio);
            
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {

                Cita cita = new Cita();
                Paciente paciente = new Paciente();
                
                cita.setRegistro(rs.getInt(1));
                cita.setFecha_cita(rs.getDate(2));
                cita.setImporte(rs.getFloat(3));
                cita.setCodigo_paciente(rs.getInt(4));

                String select_nombre = "SELECT * FROM paciente "
                        + "WHERE codigo = ?;";

                PreparedStatement sentencia_2 = connection.prepareStatement(select_nombre);

                sentencia_2.setInt(1, cita.getCodigo_paciente());

                ResultSet rs_2 = sentencia_2.executeQuery();

                while (rs_2.next()) {
                    paciente.setCodigo(rs_2.getInt(1));
                    paciente.setNombre(rs_2.getString(2));
                    paciente.setDNICE(rs_2.getInt(3));
                    paciente.setFecha_nacimiento(rs_2.getDate(4));
                    paciente.setSexo(rs_2.getString(5));
                    paciente.setDireccion(rs_2.getString(6));
                    paciente.setDpto(rs_2.getString(7));
                    paciente.setProv(rs_2.getString(8));
                    paciente.setDist(rs_2.getString(9));
                    paciente.setEspecial(rs_2.getBoolean(10));
                    paciente.setTestimonio(rs_2.getString(11));
                    paciente.setResultado(rs_2.getString(12));
                    paciente.setObservacion(rs_2.getString(13));
                    paciente.setOcupacion(rs_2.getString(14));
                    paciente.setTelefono(rs_2.getInt(15));
                    paciente.setEmail(rs_2.getString(16));
                }

                Cita_Paciente cp = new Cita_Paciente(cita, paciente);

                lista.add(cp);
                rs_2.close();
                sentencia_2.close();
            }

            rs.close();
            sentencia.close();
            
            
        } catch (Exception e) {
            
            System.out.println("Error al buscar por fecha.");
            System.out.println("Error: " + e);
            
        }
        
        return lista;
    }
    
    public List<Cita_Paciente> buscarPorMes(int mes) {
        
        List<Cita_Paciente> lista = new ArrayList<>();
        
        try {
            
            String SQL = "SELECT * FROM citas WHERE MONTH(fecha_cita) = ?;";
            Connection connection = this.conexion.getConnection();
            
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            
            sentencia.setInt(1, mes);
            
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {

                Cita cita = new Cita();
                Paciente paciente = new Paciente();
                
                cita.setRegistro(rs.getInt(1));
                cita.setFecha_cita(rs.getDate(2));
                cita.setImporte(rs.getFloat(3));
                cita.setCodigo_paciente(rs.getInt(4));

                String select_nombre = "SELECT * FROM paciente "
                        + "WHERE codigo = ?;";

                PreparedStatement sentencia_2 = connection.prepareStatement(select_nombre);

                sentencia_2.setInt(1, cita.getCodigo_paciente());

                ResultSet rs_2 = sentencia_2.executeQuery();

                while (rs_2.next()) {
                    paciente.setCodigo(rs_2.getInt(1));
                    paciente.setNombre(rs_2.getString(2));
                    paciente.setDNICE(rs_2.getInt(3));
                    paciente.setFecha_nacimiento(rs_2.getDate(4));
                    paciente.setSexo(rs_2.getString(5));
                    paciente.setDireccion(rs_2.getString(6));
                    paciente.setDpto(rs_2.getString(7));
                    paciente.setProv(rs_2.getString(8));
                    paciente.setDist(rs_2.getString(9));
                    paciente.setEspecial(rs_2.getBoolean(10));
                    paciente.setTestimonio(rs_2.getString(11));
                    paciente.setResultado(rs_2.getString(12));
                    paciente.setObservacion(rs_2.getString(13));
                    paciente.setOcupacion(rs_2.getString(14));
                    paciente.setTelefono(rs_2.getInt(15));
                    paciente.setEmail(rs_2.getString(16));
                }

                Cita_Paciente cp = new Cita_Paciente(cita, paciente);

                lista.add(cp);
                rs_2.close();
                sentencia_2.close();
            }

            rs.close();
            sentencia.close();
            
            
        } catch (Exception e) {
            
            System.out.println("Error al buscar por fecha.");
            System.out.println("Error: " + e);
            
        }
        
        return lista;
    }
    
    public List<Cita_Paciente> buscarPorAnio(int anio) {
        
        List<Cita_Paciente> lista = new ArrayList<>();
        
        try {
            
            String SQL = "SELECT * FROM citas WHERE YEAR(fecha_cita) = ?;";
            Connection connection = this.conexion.getConnection();
            
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            
            sentencia.setInt(1, anio);
            
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {

                Cita cita = new Cita();
                Paciente paciente = new Paciente();
                
                cita.setRegistro(rs.getInt(1));
                cita.setFecha_cita(rs.getDate(2));
                cita.setImporte(rs.getFloat(3));
                cita.setCodigo_paciente(rs.getInt(4));

                String select_nombre = "SELECT * FROM paciente "
                        + "WHERE codigo = ?;";

                PreparedStatement sentencia_2 = connection.prepareStatement(select_nombre);

                sentencia_2.setInt(1, cita.getCodigo_paciente());

                ResultSet rs_2 = sentencia_2.executeQuery();

                while (rs_2.next()) {
                    paciente.setCodigo(rs_2.getInt(1));
                    paciente.setNombre(rs_2.getString(2));
                    paciente.setDNICE(rs_2.getInt(3));
                    paciente.setFecha_nacimiento(rs_2.getDate(4));
                    paciente.setSexo(rs_2.getString(5));
                    paciente.setDireccion(rs_2.getString(6));
                    paciente.setDpto(rs_2.getString(7));
                    paciente.setProv(rs_2.getString(8));
                    paciente.setDist(rs_2.getString(9));
                    paciente.setEspecial(rs_2.getBoolean(10));
                    paciente.setTestimonio(rs_2.getString(11));
                    paciente.setResultado(rs_2.getString(12));
                    paciente.setObservacion(rs_2.getString(13));
                    paciente.setOcupacion(rs_2.getString(14));
                    paciente.setTelefono(rs_2.getInt(15));
                    paciente.setEmail(rs_2.getString(16));
                }

                Cita_Paciente cp = new Cita_Paciente(cita, paciente);

                lista.add(cp);
                rs_2.close();
                sentencia_2.close();
            }

            rs.close();
            sentencia.close();
            
            
        } catch (Exception e) {
            
            System.out.println("Error al buscar por fecha.");
            System.out.println("Error: " + e);
            
        }
        
        return lista;
    }
}
