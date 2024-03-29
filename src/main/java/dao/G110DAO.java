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

            String SQL = "INSERT INTO paciente(codigo, nombre, fecha_nacimiento, "
                    + "sexo, direccion, dpto, prov, dist,"
                    + "especial, testimonio, resultado, observacion, ocupacion, telefono, email)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = this.conexion.getConnection();

            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setInt(1, paciente.getCodigo());
            sentencia.setString(2, paciente.getNombre());
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

            String SQL = "UPDATE paciente SET nombre = ?, fecha_nacimiento = ?, sexo = ?,\n"
                    + "	direccion = ?, dpto = ?, prov = ?, dist = ?, especial = ?, testimonio = ?, \n"
                    + "    resultado = ?, observacion = ?, ocupacion = ?, telefono = ?, email = ?\n"
                    + "    WHERE codigo = ?;";

            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(SQL);

            sentencia.setString(1, paciente.getNombre());
            sentencia.setDate(2, (Date) paciente.getFecha_nacimiento());
            sentencia.setString(3, String.valueOf(paciente.getSexo().charAt(0)));
            sentencia.setString(4, paciente.getDireccion());
            sentencia.setString(5, paciente.getDpto());
            sentencia.setString(6, paciente.getProv());
            sentencia.setString(7, paciente.getDist());
            sentencia.setBoolean(8, paciente.getEspecial());
            sentencia.setString(9, paciente.getTestimonio());
            sentencia.setString(10, paciente.getResultado());
            sentencia.setString(11, paciente.getObservacion());
            sentencia.setString(12, paciente.getOcupacion());
            sentencia.setInt(13, paciente.getTelefono());
            sentencia.setString(14, paciente.getEmail());
            sentencia.setInt(15, paciente.getCodigo());

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
