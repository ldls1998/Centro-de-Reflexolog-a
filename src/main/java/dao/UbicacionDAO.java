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
public class UbicacionDAO {

    private ConexionMySQL conexion;

    public UbicacionDAO() {
        this.conexion = new ConexionMySQL();
    }

    public List<String> listarDepartamentos() {

        List<String> lista = new ArrayList<>();

        try {

            String select_all = "SELECT name FROM reflexologia.ubigeo_peru_departments;";

            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString(1));
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al buscar departamentos.");
            System.out.println("Error: " + e);

        }

        return lista;
    }

    public List<String> listarProvincias(String depa) {

        List<String> lista = new ArrayList<>();

        try {

            String select_all = "SELECT p.name FROM reflexologia.ubigeo_peru_provinces p JOIN \n"
                    + "	ubigeo_peru_departments d on p.department_id = d.id WHERE \n"
                    + "	d.name = ?;";

            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);

            sentencia.setString(1, depa);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString(1));
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al buscar departamentos.");
            System.out.println("Error: " + e);

        }

        return lista;
    }

    public List<String> listarDistritos(String depa, String prov) {

        List<String> lista = new ArrayList<>();

        try {

            String select_all = "SELECT di.name FROM ubigeo_peru_departments d JOIN \n"
                    + "	ubigeo_peru_provinces p ON d.id = p.department_id JOIN\n"
                    + "    ubigeo_peru_districts di ON di.province_id = p.id WHERE d.name = ? and p.name = ?;";

            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);

            sentencia.setString(1, depa);
            sentencia.setString(2, prov);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString(1));
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al buscar departamentos.");
            System.out.println("Error: " + e);

        }

        return lista;
    }

}