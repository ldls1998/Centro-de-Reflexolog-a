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
import modelo.Paciente;

/**
 *
 * @author Vlik35
 */
public class G110DAO {

    private ConexionMySQL conexion;

    public G110DAO() {
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

    public boolean editar(Paciente paciente) {

        try {

            String SQL = "UPDATE paciente SET nombre = ?, dnice = ?, fecha_nacimiento = ?, sexo = ?,\n"
                    + "	direccion = ?, dpto = ?, prov = ?, dist = ?, especial = ?, testimonio = ?, \n"
                    + "    resultado = ?, observacion = ?, ocupacion = ?, telefono = ?, email = ?\n"
                    + "    WHERE codigo = ?;";
            
            Connection conexion = this.conexion.getConnection();
            
            PreparedStatement sentencia = conexion.prepareStatement(SQL);
            
            sentencia.setString(1, paciente.getNombre());
            sentencia.setInt(2, paciente.getDNICE());
            sentencia.setDate(3, (Date) paciente.getFecha_nacimiento());
            sentencia.setString(4, String.valueOf(paciente.getSexo().charAt(0)));
            sentencia.setString(5, paciente.getDireccion());
            sentencia.setString(6, paciente.getDpto());
            sentencia.setString(7, paciente.getProv());
            sentencia.setString(8, paciente.getDist());
            sentencia.setBoolean(9, paciente.getEspecial());
            sentencia.setString(10, paciente.getTestimonio());
            sentencia.setString(11, paciente.getResultado());
            sentencia.setString(12, paciente.getObservacion());
            sentencia.setString(13, paciente.getOcupacion());
            sentencia.setInt(14, paciente.getTelefono());
            sentencia.setString(15, paciente.getEmail());
            sentencia.setInt(16, paciente.getCodigo());
            
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
