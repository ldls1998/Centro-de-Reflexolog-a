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
import modelo.Diagnostico;

/**
 *
 * @author Vlik35
 */
public class DiagnosticoDAO {
    
    private ConexionMySQL conexion;

    public DiagnosticoDAO() {
        this.conexion = new ConexionMySQL();
    }
    
    public Diagnostico buscar(String codigo) {
        Diagnostico diagnostico = new Diagnostico();

        try {

            String select_all = "SELECT * FROM diagnosticos WHERE codigo = ?;";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);

            sentencia.setString(1, codigo);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                diagnostico.setID(rs.getInt(1));
                diagnostico.setCodigo(rs.getString(2));
                diagnostico.setDescripcion(rs.getString(3));
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al buscar diagnostico.");
            System.out.println("Error: " + e);

        }

        return diagnostico;
    }
    
    public boolean eliminar(String codigo) {

        try {

            String SQL = "DELETE FROM diagnosticos WHERE codigo = ?;";

            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(SQL);

            sentencia.setString(1, codigo);

            sentencia.executeUpdate();

            sentencia.close();

            return true;

        } catch (Exception e) {

            System.out.println("Error al eliminar diagnostico.");
            System.out.println("Error: " + e);
            return false;
        }
    }
    
    public List<Diagnostico> listar() {

        List<Diagnostico> listaDiagnostico = new ArrayList<>();

        try {

            String select_all = "SELECT * FROM diagnosticos;";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                Diagnostico diagnostico = new Diagnostico();

                diagnostico.setCodigo(rs.getString(2));
                diagnostico.setDescripcion(rs.getString(3));

                listaDiagnostico.add(diagnostico);
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al mostrar diagnosticos.");
            System.out.println("Error: " + e);

        }

        return listaDiagnostico;
    }
    
    public List<Diagnostico> listarBusquedaCodigo(String codigo) {
        List<Diagnostico> listaDiagnostico = new ArrayList<>();

        try {

            String select_all = "SELECT * FROM diagnosticos WHERE codigo LIKE '" + codigo + "%';";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);
            
            // sentencia.setInt(1, codigo);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                Diagnostico terapeuta = new Diagnostico();

                terapeuta.setCodigo(rs.getString(2));
                terapeuta.setDescripcion(rs.getString(3));

                listaDiagnostico.add(terapeuta);
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al mostrar diagnosticos.");
            System.out.println("Error: " + e);

        }

        return listaDiagnostico;
    }
    
    public List<Diagnostico> listarBusquedaDescripcion(String descripcion) {
        List<Diagnostico> listaDiagnostico = new ArrayList<>();

        try {

            String select_all = "SELECT * FROM diagnosticos WHERE descripcion LIKE '" + descripcion + "%';";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);
            
            // sentencia.setString(1, nombre);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                Diagnostico terapeuta=  new Diagnostico();

                terapeuta.setCodigo(rs.getString(2));
                terapeuta.setDescripcion(rs.getString(3));

                listaDiagnostico.add(terapeuta);
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al mostrar diagnosticos.");
            System.out.println("Error: " + e);

        }

        return listaDiagnostico;
    }

    public boolean registrar(Diagnostico diagnostico) {

        try {

            String SQL = "INSERT INTO diagnosticos(codigo, descripcion"
                    + " values (?, ?)";
            
            Connection connection = this.conexion.getConnection();

            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, diagnostico.getCodigo());
            sentencia.setString(2, diagnostico.getDescripcion());


            sentencia.executeUpdate();
            sentencia.close();

            return true;

        } catch (Exception e) {

            System.err.println("Ocurrio un error al registrar al diagnostico");
            e.printStackTrace();
            return false;

        }

    }
    
    public boolean editar(Diagnostico diagnostico) {

        try {

            String SQL = "UPDATE diagnosticos SET codigo = ?, descipcion = ? WHERE ID = ?;";
            
            Connection conexion = this.conexion.getConnection();
            
            PreparedStatement sentencia = conexion.prepareStatement(SQL);
            
            sentencia.setString(1, diagnostico.getCodigo());
            sentencia.setString(2, diagnostico.getDescripcion());
            sentencia.setInt(3, diagnostico.getID());
            
            sentencia.executeUpdate();
            
            sentencia.close();
            
            return true;

        } catch (Exception e) {

            System.err.println("Error al editar diagnosticos.");
            System.err.println("Error: " + e);
            return false;
        }
    }
    
}
