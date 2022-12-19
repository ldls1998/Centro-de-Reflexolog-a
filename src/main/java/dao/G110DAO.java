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
import modelo.G110;

/**
 *
 * @author Vlik35
 */
public class G110DAO {
    
    private ConexionMySQL conexion;
    
    public G110DAO() {
        this.conexion = new ConexionMySQL();
    }
    
    public boolean registrar(G110 g110) {
        
        try {
            
            String SQL = "INSERT INTO paciente(codigo, nombre, dnice, fecha_nacimiento, "
                    + "sexo, direccion, dpto, prov, dist,"
                    + "especial, testimonio, resultado, observacion, ocupacion, telefono, email)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = this.conexion.getConnection();
            
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            
            sentencia.setInt(1, g110.getCodigo());
            sentencia.setString(2, g110.getNombre());
            sentencia.setInt(3, g110.getDNICE());
            sentencia.setDate(4, (Date) g110.getFecha_nacimiento());
            sentencia.setString(5, String.valueOf(g110.getSexo().charAt(0)));
            sentencia.setString(6, g110.getDireccion());
            sentencia.setString(7, g110.getDpto());
            sentencia.setString(8, g110.getProv());
            sentencia.setString(9, g110.getDist());
            sentencia.setBoolean(10, g110.getEspecial());
            sentencia.setString(11, g110.getTestimonio());
            sentencia.setString(12, g110.getResultado());
            sentencia.setString(13, g110.getObservacion());
            sentencia.setString(14, g110.getOcupacion());
            sentencia.setInt(15, g110.getTelefono());
            sentencia.setString(16, g110.getEmail());
            
            sentencia.executeUpdate();
            sentencia.close();
            
            return true;
            
        } catch (Exception e) {
        
            System.err.println("Ocurrio un error al registrar al paciente");
            e.printStackTrace();
            return false;
            
        }

    }
    
}
