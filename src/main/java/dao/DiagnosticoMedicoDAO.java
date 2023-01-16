/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DiagnosticoMedico;

/**
 *
 * @author Vlik35
 */
public class DiagnosticoMedicoDAO {
    
    private ConexionMySQL conexion;

    public DiagnosticoMedicoDAO() {
        this.conexion = new ConexionMySQL();
    }
    
    public DiagnosticoMedico buscar(String codigo) {
        DiagnosticoMedico diagnosticoMedico = new DiagnosticoMedico();

        try {

            String select_all = "SELECT * FROM diagnosticos_medicos WHERE codigo = ?;";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);

            sentencia.setString(1, codigo);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                diagnosticoMedico.setID(rs.getInt(1));
                diagnosticoMedico.setCodigo(rs.getString(2));
                diagnosticoMedico.setNombre(rs.getString(3));
                diagnosticoMedico.setTipo(rs.getString(4));
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al buscar diagnóstico médico.");
            System.out.println("Error: " + e);

        }

        return diagnosticoMedico;
    }
    
    public boolean eliminar(String codigo) {

        try {

            String SQL = "DELETE FROM diagnosticos_medicos WHERE codigo = ?;";

            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(SQL);

            sentencia.setString(1, codigo);

            sentencia.executeUpdate();

            sentencia.close();

            return true;

        } catch (Exception e) {

            System.out.println("Error al eliminar diagnóstico médico.");
            System.out.println("Error: " + e);
            return false;
        }
    }
    
    public List<DiagnosticoMedico> listar() {

        List<DiagnosticoMedico> listaDiagnosticoMedico = new ArrayList<>();

        try {

            String select_all = "SELECT * FROM diagnosticos_medicos;";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                DiagnosticoMedico diagnosticoMedico = new DiagnosticoMedico();

                diagnosticoMedico.setCodigo(rs.getString(2));
                diagnosticoMedico.setNombre(rs.getString(3));
                diagnosticoMedico.setTipo(rs.getString(4));

                listaDiagnosticoMedico.add(diagnosticoMedico);
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al mostrar diagnosticos médicos.");
            System.out.println("Error: " + e);

        }

        return listaDiagnosticoMedico;
    }

    public boolean registrar(DiagnosticoMedico diagnosticoMedico) {

        try {

            String SQL = "INSERT INTO diagnosticos_medicos(codigo, descripcion, tipo"
                    + " values (?, ?, ?)";
            
            Connection connection = this.conexion.getConnection();

            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, diagnosticoMedico.getCodigo());
            sentencia.setString(2, diagnosticoMedico.getNombre());
            sentencia.setString(3, diagnosticoMedico.getTipo());


            sentencia.executeUpdate();
            sentencia.close();

            return true;

        } catch (Exception e) {

            System.err.println("Ocurrio un error al registrar al diagnóstico médico.");
            e.printStackTrace();
            return false;

        }

    }
    
    public boolean editar(DiagnosticoMedico diagnosticoMedico) {

        try {

            String SQL = "UPDATE diagnosticos_medicos SET codigo = ?, descipcion = ?, tipo = ? WHERE ID = ?;";
            
            Connection conexion = this.conexion.getConnection();
            
            PreparedStatement sentencia = conexion.prepareStatement(SQL);
            
            sentencia.setString(1, diagnosticoMedico.getCodigo());
            sentencia.setString(2, diagnosticoMedico.getNombre());
            sentencia.setString(2, diagnosticoMedico.getTipo());
            sentencia.setInt(3, diagnosticoMedico.getID());
            
            sentencia.executeUpdate();
            
            sentencia.close();
            
            return true;

        } catch (Exception e) {

            System.err.println("Error al editar diagnosticos médicos.");
            System.err.println("Error: " + e);
            return false;
        }
    }
    
}
