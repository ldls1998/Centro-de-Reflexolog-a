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
import modelo.Terapeuta;

/**
 *
 * @author Vlik35
 */
public class TerapeutaDAO {

    private ConexionMySQL conexion;

    public TerapeutaDAO() {
        this.conexion = new ConexionMySQL();
    }

    public Terapeuta buscar(int numero) {
        Terapeuta terapeuta = new Terapeuta();

        try {

            String select_all = "SELECT * FROM terapeutas WHERE numero = ?;";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);

            sentencia.setInt(1, numero);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                terapeuta.setNumero(rs.getInt(1));
                terapeuta.setNombre(rs.getString(2));
                terapeuta.setFecha_nacimiento(rs.getDate(3));
                terapeuta.setSexo(rs.getString(4));
                terapeuta.setDireccion(rs.getString(5));
                terapeuta.setProv(rs.getString(6));
                terapeuta.setDpto(rs.getString(7));
                terapeuta.setDist(rs.getString(8));
                terapeuta.setCarnet(rs.getString(9));
                terapeuta.setTelefono(rs.getInt(10));
                terapeuta.setEmail(rs.getString(11));
                terapeuta.setPermanente(rs.getBoolean(12));

            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al buscar terapueta.");
            System.out.println("Error: " + e);

        }

        return terapeuta;
    }

    public boolean eliminar(int numero) {

        try {

            String SQL = "DELETE FROM terapeutas WHERE numero = ?;";

            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(SQL);

            sentencia.setInt(1, numero);

            sentencia.executeUpdate();

            sentencia.close();

            return true;

        } catch (Exception e) {

            System.out.println("Error al eliminar pacientes.");
            System.out.println("Error: " + e);
            return false;
        }
    }

    public List<Terapeuta> listar() {

        List<Terapeuta> listaTerapeuta = new ArrayList<>();

        try {

            String select_all = "SELECT * FROM terapeutas;";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {

                Terapeuta terapeuta = new Terapeuta();

                terapeuta.setNumero(rs.getInt(1));
                terapeuta.setNombre(rs.getString(2));

                listaTerapeuta.add(terapeuta);
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al mostrar terapeutas.");
            System.out.println("Error: " + e);

        }

        return listaTerapeuta;
    }
    
    public boolean registrar(Terapeuta terapeuta) {

        try {

            String SQL = "INSERT INTO terapeutas(numero, nombre, fecha_nacimiento, "
                    + "sexo, direccion, prov, dpto, dist, carnet, "
                    + "telefono, email, permanente)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = this.conexion.getConnection();

            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setInt(1, terapeuta.getNumero());
            sentencia.setString(2, terapeuta.getNombre());
            sentencia.setDate(3, (Date) terapeuta.getFecha_nacimiento());
            sentencia.setString(4, String.valueOf(terapeuta.getSexo().charAt(0)));
            sentencia.setString(5, terapeuta.getDireccion());
            sentencia.setString(6, terapeuta.getProv());
            sentencia.setString(7, terapeuta.getDpto());
            sentencia.setString(8, terapeuta.getDist());
            sentencia.setString(9, terapeuta.getCarnet());
            sentencia.setInt(10, terapeuta.getTelefono());
            sentencia.setString(11, terapeuta.getEmail());
            sentencia.setBoolean(12, terapeuta.getPermanente());


            sentencia.executeUpdate();
            sentencia.close();

            return true;

        } catch (Exception e) {

            System.err.println("Ocurrio un error al registrar al terapeuta");
            e.printStackTrace();
            return false;

        }

    }
    
    public boolean editar(Terapeuta terapeuta) {

        try {

            String SQL = "UPDATE terapeutas SET nombre = ?, fecha_nacimiento = ?, sexo = ?,\n"
                    + "	direccion = ?, prov = ?, dpto = ?, dist = ?, carnet = ?, telefono = ?, \n"
                    + " email = ?, permanente = ? WHERE numero = ?;";
            
            Connection conexion = this.conexion.getConnection();
            
            PreparedStatement sentencia = conexion.prepareStatement(SQL);
            
            sentencia.setString(1, terapeuta.getNombre());
            sentencia.setDate(2, (Date) terapeuta.getFecha_nacimiento());
            sentencia.setString(3, String.valueOf(terapeuta.getSexo().charAt(0)));
            sentencia.setString(4, terapeuta.getDireccion());
            sentencia.setString(5, terapeuta.getProv());
            sentencia.setString(6, terapeuta.getDpto());
            sentencia.setString(7, terapeuta.getDist());
            sentencia.setString(8, terapeuta.getCarnet());
            sentencia.setInt(9, terapeuta.getTelefono());
            sentencia.setString(10, terapeuta.getEmail());
            sentencia.setBoolean(11, terapeuta.getPermanente());
            sentencia.setInt(12, terapeuta.getNumero());
            
            sentencia.executeUpdate();
            
            sentencia.close();
            
            return true;

        } catch (Exception e) {

            System.err.println("Error al editar pacientes.");
            System.err.println("Error: " + e);
            return false;
        }
    }
}