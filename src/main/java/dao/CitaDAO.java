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
import modelo.Paciente;
import modelo.Cita_Paciente;

/**
 *
 * @author Vlik35
 */
public class CitaDAO {

    private ConexionMySQL conexion;

    public CitaDAO() {
        this.conexion = new ConexionMySQL();
    }

    public List<Cita_Paciente> listar() {

        List<Cita_Paciente> lista = new ArrayList<>();

        try {

            String select_all = "SELECT * FROM citas;";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);

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

                PreparedStatement sentencia_2 = conexion.prepareStatement(select_nombre);

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

        } catch (SQLException e) {

            System.out.println("Error al mostrar la tabla.");
            System.out.println("Error: " + e);

        }

        return lista;
    }

    public boolean editar(Cita cita) {

        try {

            String SQL = "UPDATE citas SET fecha_cita = ?, importe = ?, pacienteID = ? WHERE registro = ?;";

            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(SQL);

            sentencia.setDate(1, (Date) cita.getFecha_cita());
            sentencia.setFloat(2, cita.getImporte());
            sentencia.setInt(3, cita.getCodigo_paciente());
            sentencia.setInt(4, cita.getRegistro());
            
            sentencia.executeUpdate();

            sentencia.close();

            return true;

        } catch (Exception e) {

            System.err.println("Error al editar cita.");
            System.err.println("Error: " + e);
            return false;
        }
    }
    
    public Cita buscar(int registro) {
        Cita cita = new Cita();
        
        try {
            
            String select_all = "SELECT * FROM citas WHERE registro = ?;";
            Connection conexion = this.conexion.getConnection();
            
            PreparedStatement sentencia = conexion.prepareStatement(select_all);
            
            sentencia.setInt(1, registro);
            
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {
                cita.setRegistro(rs.getInt(1));;
                cita.setFecha_cita(rs.getDate(2));
                cita.setImporte(rs.getFloat(3));
                cita.setCodigo_paciente(rs.getInt(4));
            }
            
            rs.close();
            sentencia.close();
            
        } catch (SQLException e) {
            
            System.out.println("Error al buscar cita.");
            System.out.println("Error: " + e);
            
        }
        
        return cita;
    }
    
}
