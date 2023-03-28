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
import modelo.Visita;

/**
 *
 * @author Vlik35
 */
public class VisitaDAO {

    private ConexionMySQL conexion;

    public VisitaDAO() {
        this.conexion = new ConexionMySQL();
    }

    public boolean registrar(Visita visita) {

        try {
               // Registro de visitas de visita
            String SQL = "INSERT INTO visita(pacienteID, codigo, dia_ext, "
                    + "medicamentos, operaciones, observa, dolencias, diagnosticos_reflexologicos,"
                    + "os_priv, peso_ini, fecha_ini, peso_fin, fecha_fin, diagnostico1, diagnostico2,"
                    + "diagnostico3, diagnostico4, diagnostico5, diagnostico6, menstru, testimonio, resultado, observacion)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = this.conexion.getConnection();

            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setInt(1, visita.getPacienteID());
            sentencia.setInt(2, visita.getCodigo());
            sentencia.setString(3, String.valueOf(visita.getDiag_ext()));
            sentencia.setString(4, String.valueOf(visita.getMedicamentos()));
            sentencia.setString(5, visita.getOperaciones());
            sentencia.setString(6, visita.getObserva());
            sentencia.setString(7, visita.getDolencias());
            sentencia.setString(8, visita.getDiagnosticos_reflexologicos());
            sentencia.setString(9, visita.getOs_priv());
            sentencia.setFloat(10, visita.getPeso_ini());
            sentencia.setDate(11, (Date) visita.getFecha_ini());
            sentencia.setFloat(12, visita.getPeso_fin());
            sentencia.setDate(13, (Date) visita.getFecha_fin());
            sentencia.setString(14, visita.getDiagnostico1());
            sentencia.setString(15, visita.getDiagnostico2());
            sentencia.setString(16, visita.getDiagnostico3());
            sentencia.setString(17, visita.getDiagnostico4());
            sentencia.setString(18, visita.getDiagnostico5());
            sentencia.setString(19, visita.getDiagnostico6());
            sentencia.setBoolean(19, visita.getMenstru());
            sentencia.setBoolean(19, visita.getTestimonio());
            sentencia.setString(19, visita.getResultado());
            sentencia.setString(19, visita.getObservacion());


            sentencia.executeUpdate();
            sentencia.close();

            return true;

        } catch (Exception e) {

            System.err.println("Ocurrio un error al registrar la historia");
            e.printStackTrace();
            return false;

        }

    }
/*
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
*/
    public boolean editar(Visita visita) {

        try {
            
            String SQL = "UPDATE INTO visita SET dia_ext = ?, medicamentos = ?, operaciones = ?,\n"
                    + "	observa = ?, dolencias = ?, diagnosticos_reflexologicos = ?, os_priv = ?, peso_ini = ?, fecha_fin = ?, \n"
                    + "    diagnostico1 = ?, diagnostico2 = ?, diagnostico3 = ?, diagnostico4 = ?, diagnostico5 = ?,  diagnostico6 = ?, \n"
                    + "    menstru = ?, testimonio = ?, resultado = ?, observacion = ? WHERE codigo = ?;";

            Connection conexion = this.conexion.getConnection();

            PreparedStatement sentencia = conexion.prepareStatement(SQL);

            
            sentencia.setInt(1, visita.getPacienteID());
            sentencia.setInt(2, visita.getCodigo());
            sentencia.setString(3, String.valueOf(visita.getDiag_ext()));
            sentencia.setString(4, String.valueOf(visita.getMedicamentos()));
            sentencia.setString(5, visita.getOperaciones());
            sentencia.setString(6, visita.getObserva());
            sentencia.setString(7, visita.getDolencias());
            sentencia.setString(8, visita.getDiagnosticos_reflexologicos());
            sentencia.setString(9, visita.getOs_priv());
            sentencia.setFloat(10, visita.getPeso_ini());
            sentencia.setDate(11, (Date) visita.getFecha_ini());
            sentencia.setFloat(12, visita.getPeso_fin());
            sentencia.setDate(13, (Date) visita.getFecha_fin());
            sentencia.setString(14, visita.getDiagnostico1());
            sentencia.setString(15, visita.getDiagnostico2());
            sentencia.setString(16, visita.getDiagnostico3());
            sentencia.setString(17, visita.getDiagnostico4());
            sentencia.setString(18, visita.getDiagnostico5());
            sentencia.setString(19, visita.getDiagnostico6());
            sentencia.setBoolean(19, visita.getMenstru());
            sentencia.setBoolean(19, visita.getTestimonio());
            sentencia.setString(19, visita.getResultado());
            sentencia.setString(19, visita.getObservacion());

            sentencia.executeUpdate();

            sentencia.close();

            return true;

        } catch (Exception e) {

            System.err.println("Error al editar el registro del paciente.");
            System.err.println("Error: " + e);
            return false;
        }
    }
}
