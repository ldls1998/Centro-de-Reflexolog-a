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
                
                Boolean citas;
               if(rs.getInt(8) == 1){
                    citas=true;
                }else{
                   citas=false;
               }
               Boolean sde;
               if(rs.getInt(6) == 1){
                    sde=true;
                }else{
                   sde=false;
               }
               Boolean saldo;
                 if(rs.getInt(10) == 1){
                    saldo=true;
                }else{
                   saldo=false;
               }
               Boolean todo;
                 if(rs.getInt(5) == 1){
                    todo=true;
                }else{
                   todo=false;
               }
                 
               Boolean social;
               if(rs.getInt(14) == 1){
                    social=true;
                }
               else{
                   social=false;
                  
               }
                

                cita.setRegistro(rs.getInt(1));
                cita.setFecha_cita(rs.getDate(2));
                cita.setImporte(rs.getFloat(3));
                cita.setCodigo_paciente(rs.getInt(4));
                cita.setCtodo(todo);
                cita.setSde(sde);
                cita.setObserva(rs.getString(7));
                cita.setCitaBool(citas);
                cita.setHora(rs.getString(9));
                cita.setSaldo(saldo);
                cita.setOp(rs.getString(11));
                cita.setNum(rs.getInt(12));
                cita.setSocial(social);              

                System.out.println(cita.toString());

                String select_nombre = "SELECT * FROM paciente "
                        + "WHERE codigo = ?;";

                PreparedStatement sentencia_2 = conexion.prepareStatement(select_nombre);

                sentencia_2.setInt(1, cita.getCodigo_paciente());

                ResultSet rs_2 = sentencia_2.executeQuery();

                while (rs_2.next()) {
                    paciente.setCodigo(rs_2.getInt(1));
                    paciente.setNombre(rs_2.getString(2));
                    paciente.setFecha_nacimiento(rs_2.getDate(3));
                    paciente.setSexo(rs_2.getString(4));
                    paciente.setDireccion(rs_2.getString(5));
                    paciente.setDpto(rs_2.getString(6));
                    paciente.setProv(rs_2.getString(7));
                    paciente.setDist(rs_2.getString(8));
                    paciente.setEspecial(rs_2.getBoolean(9));
                    paciente.setTestimonio(rs_2.getString(10));
                    paciente.setResultado(rs_2.getString(11));
                    paciente.setObservacion(rs_2.getString(12));
                    paciente.setOcupacion(rs_2.getString(13));
                    paciente.setTelefono(rs_2.getInt(14));
                    paciente.setEmail(rs_2.getString(15));
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

            String SQL = "UPDATE citas SET fecha_cita = ?, importe = ?, pacienteID = ?, ctodo = ?, sde = ?, "
                    + "observa = ?, cita_bool = ?, hora = ?, saldo = ?, op = ?, num = ?, social = ? WHERE registro = ?;";

            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(SQL);
             int citas;
             
            if(cita.isCitaBool() == true){
                citas = 1;
             }
            else{
                citas=0;
           }
            
             int Ctodo;
            if(cita.isCtodo() == true){
                Ctodo = 1;
             }
            else{
                Ctodo=0;
           }
            int sde;
             if(cita.isSde() == true){
                sde = 1;
             }
            else{
                sde=0;
           }
             
            int saldo;
             if(cita.isSaldo() == true){
                saldo = 1;
             }
            else{
                saldo=0;
           }
             
            int social;
             if(cita.isSocial() == true){
                social = 1;
             }
            else{
                social=0;
           }
            sentencia.setDate(1, (Date) cita.getFecha_cita());
            sentencia.setFloat(2, cita.getImporte());
            sentencia.setInt(3, cita.getCodigo_paciente());
            sentencia.setInt(4, Ctodo);
            sentencia.setInt(5, sde);
            sentencia.setString(6, cita.getObserva());
            sentencia.setInt(7, citas);
            sentencia.setString(8, cita.getHora());
            sentencia.setInt(9, saldo);
            sentencia.setString(10, cita.getOp());
            sentencia.setInt(11, cita.getNum());
            sentencia.setInt(13, cita.getRegistro());
            sentencia.setInt(12, social);
            
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
                cita.setCtodo(rs.getBoolean(5));
                cita.setSde(rs.getBoolean(6));
                cita.setObserva(rs.getString(7));
                cita.setCitaBool(rs.getBoolean(8));
                cita.setHora(rs.getString(9));
                cita.setSaldo(rs.getBoolean(10));
                cita.setOp(rs.getString(11));
                cita.setNum(rs.getInt(12));
            }

            rs.close();
            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al buscar cita.");
            System.out.println("Error: " + e);

        }

        return cita;
    }

    public Cita crear(Cita cita) {

        try {

            String select_all = "INSERT INTO citas (`fecha_cita`, `tipo`,`importe`, `pacienteID`, `ctodo`, `sde`, "
                    + "`observa`, `cita_bool`, `hora`, `saldo`, `op`, `num`, `social`)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?);";
            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(select_all);
            
            int citas;
            if(cita.isCitaBool() == true){
                citas = 1;
             }
            else{
                citas = 0;
           }
            
            int Ctodo;
            if(cita.isCtodo() == true){
                Ctodo = 1;
             }
            else{
                Ctodo=0;
           }
            int sde;
             if(cita.isSde() == true){
                sde = 1;
             }
            else{
                sde=0;
           }
             
             int saldo;
             if(cita.isSaldo() == true){
                saldo = 1;
             }
            else{
                saldo=0;
           }
             
            int social;
            
            if(cita.isSocial() == true){
                social = 1;
             }
            else{
                social=0;
           }
             
            sentencia.setDate(1, cita.getFecha_cita());
            sentencia.setString(2, cita.getTipo());
            sentencia.setFloat(3, cita.getImporte());
            sentencia.setInt(4, cita.getCodigo_paciente());
            sentencia.setInt(5, Ctodo);
            sentencia.setInt(6, sde);
            sentencia.setString(7, cita.getObserva());
            sentencia.setInt(8, citas);
            sentencia.setString(9, cita.getHora());
            sentencia.setInt(10, saldo);
            sentencia.setString(11, cita.getOp());
            sentencia.setInt(12, cita.getNum());
            sentencia.setInt(13, 1);

            sentencia.execute();

            sentencia.close();

        } catch (SQLException e) {

            System.out.println("Error al crear cita.");
            System.out.println("Error: " + e);

        }

        return cita;
    }

}
