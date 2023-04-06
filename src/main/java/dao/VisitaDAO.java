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

   

public boolean registrar(Visita visita) throws SQLException {

    try {
        Connection connection = this.conexion.getConnection();

        // Verificar si ya existe una visita para el paciente
        String buscarSQL = "SELECT pacienteID FROM visita WHERE pacienteID=?";
        PreparedStatement buscarSentencia = connection.prepareStatement(buscarSQL);
        buscarSentencia.setInt(1, visita.getPacienteID());
        ResultSet resultado = buscarSentencia.executeQuery();
        
        if (resultado.next()) {
            // Ya existe una visita para el paciente, actualizar registro
 
            String consultaSQL = "SELECT nvisitas FROM visita WHERE pacienteID = ?";
            PreparedStatement statement = connection.prepareStatement(consultaSQL);
            statement.setInt(1, visita.getPacienteID());
            ResultSet rs = statement.executeQuery();
            int visitas;
            if(rs.next()) {
                visitas = rs.getInt("nvisitas") + 1;
            }
            else{
                visitas = 1;
            }
       

            String actualizarSQL = "UPDATE visita SET dia_ext=?, medicamentos=?, operaciones=?, observa=?, dolencias=?, diagnosticos_reflexologicos=?, os_priv=?, peso_ini=?, fecha_ini=?, peso_fin=?, fecha_fin=?, diagnostico1=?, diagnostico2=?, diagnostico3=?, diagnostico4=?, diagnostico5=?, diagnostico6=?, menstru=?, testimonio=?, resultado=?, observacion=?, talla=?, nvisitas=?, ftterapeuta=? WHERE pacienteID=?";
            PreparedStatement actualizarSentencia = connection.prepareStatement(actualizarSQL);

            actualizarSentencia.setString(1, visita.getDiag_ext() == null ? "null" : visita.getDiag_ext());
            actualizarSentencia.setString(2, visita.getMedicamentos() == null ? "null" : visita.getMedicamentos());
            actualizarSentencia.setString(3, visita.getOperaciones() == null ? "null" : visita.getOperaciones());
            actualizarSentencia.setString(4, visita.getObserva() == null ? "null" : visita.getObserva());
            actualizarSentencia.setString(5, visita.getDolencias() == null ? "null" : visita.getDolencias());
            actualizarSentencia.setString(6, visita.getDiagnosticos_reflexologicos() == null ? "null" : visita.getDiagnosticos_reflexologicos());
            actualizarSentencia.setString(7, visita.getOs_priv() == null ? "null" : visita.getOs_priv());
            actualizarSentencia.setFloat(8, visita.getPeso_ini());
            actualizarSentencia.setDate(9, (Date) visita.getFecha_ini());
            actualizarSentencia.setFloat(10, visita.getPeso_fin());
            actualizarSentencia.setDate(11, (Date) visita.getFecha_fin());
            actualizarSentencia.setString(12, visita.getDiagnostico1() == null ? "null" : visita.getDiagnostico1());
            actualizarSentencia.setString(13, visita.getDiagnostico2() == null ? "null" : visita.getDiagnostico2());
            actualizarSentencia.setString(14, visita.getDiagnostico3() == null ? "null" : visita.getDiagnostico3());
            actualizarSentencia.setString(15, visita.getDiagnostico4() == null ? "null" : visita.getDiagnostico4());
            actualizarSentencia.setString(16, visita.getDiagnostico5() == null ? "null" : visita.getDiagnostico5());
            actualizarSentencia.setString(17, visita.getDiagnostico6() == null ? "null" : visita.getDiagnostico6());
            actualizarSentencia.setBoolean(18, visita.getMenstru() == null ? null : visita.getMenstru());
            actualizarSentencia.setBoolean(19, visita.getTestimonio() != null && visita.getTestimonio());
            actualizarSentencia.setString(20, visita.getResultado() == null ? "null" : visita.getResultado());
            actualizarSentencia.setString(21, visita.getObservacion() == null ? "null" : visita.getObservacion());
            actualizarSentencia.setFloat(22, visita.getTalla());
            actualizarSentencia.setFloat(23, visitas);
            actualizarSentencia.setFloat(24, visita.getFtterapeuta());
            actualizarSentencia.setInt(25, visita.getPacienteID());

          actualizarSentencia.executeUpdate();
        actualizarSentencia.close();
        return true;

    } else { // Si no existe, se guarda un nuevo registro con INSERT
        String SQL = "INSERT INTO visita(pacienteID, dia_ext, medicamentos, operaciones, observa, dolencias, diagnosticos_reflexologicos, os_priv, peso_ini, fecha_ini, peso_fin, fecha_fin, diagnostico1, diagnostico2, diagnostico3, diagnostico4, diagnostico5, diagnostico6, menstru, testimonio, resultado, observacion, talla, nvisitas, ftterapeuta) values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertarSentencia = connection.prepareStatement(SQL);

        insertarSentencia.setInt(1, visita.getPacienteID());
        insertarSentencia.setString(2, visita.getDiag_ext() == null ? "null" : visita.getDiag_ext());
        insertarSentencia.setString(3, visita.getMedicamentos() == null ? "null" : visita.getMedicamentos());
        insertarSentencia.setString(4, visita.getOperaciones() == null ? "null" : visita.getOperaciones());
        insertarSentencia.setString(5, visita.getObserva() == null ? "null" : visita.getObserva());
        insertarSentencia.setString(6, visita.getDolencias() == null ? "null" : visita.getDolencias());
        insertarSentencia.setString(7, visita.getDiagnosticos_reflexologicos() == null ? "null" : visita.getDiagnosticos_reflexologicos());
        insertarSentencia.setString(8, visita.getOs_priv() == null ? "null" : visita.getOs_priv());
        insertarSentencia.setFloat(9, visita.getPeso_ini());
        insertarSentencia.setDate(10, (Date) visita.getFecha_ini());
        insertarSentencia.setFloat(11, visita.getPeso_fin());
        insertarSentencia.setDate(12, (Date) visita.getFecha_fin());
        insertarSentencia.setString(13, visita.getDiagnostico1() == null ? "null" : visita.getDiagnostico1());
        insertarSentencia.setString(14, visita.getDiagnostico2() == null ? "null" : visita.getDiagnostico2());
        insertarSentencia.setString(15, visita.getDiagnostico3() == null ? "null" : visita.getDiagnostico3());
        insertarSentencia.setString(16, visita.getDiagnostico4() == null ? "null" : visita.getDiagnostico4());
        insertarSentencia.setString(17, visita.getDiagnostico5() == null ? "null" : visita.getDiagnostico5());
        insertarSentencia.setString(18, visita.getDiagnostico6() == null ? "null" : visita.getDiagnostico6());
        insertarSentencia.setBoolean(19, visita.getMenstru() == null ? null : visita.getMenstru());
        insertarSentencia.setBoolean(20, visita.getTestimonio() != null && visita.getTestimonio());
        insertarSentencia.setString(21, visita.getResultado() == null ? "null" : visita.getResultado());
        insertarSentencia.setString(22, visita.getObservacion() == null ? "null" : visita.getObservacion());
        insertarSentencia.setFloat(23, visita.getTalla());
        insertarSentencia.setFloat(24, visita.getFtterapeuta());
        insertarSentencia.setInt(25, 1);
        insertarSentencia.executeUpdate();
        insertarSentencia.close();
          return true;
       
        }

        } catch (Exception e) {

            System.err.println("Ocurrio un error al registrar al paciente");
            e.printStackTrace();
            return false;

        }

}
    
    public Visita buscarPorPacienteID(int pacienteID) {
    try {
        String SQL = "SELECT * FROM visita WHERE pacienteID = ?;";
        Connection conexion = this.conexion.getConnection();
        PreparedStatement sentencia = conexion.prepareStatement(SQL);
        sentencia.setInt(1, pacienteID);
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            Visita visita = new Visita();
            visita.setPacienteID(resultado.getInt("pacienteID"));
            visita.setDiag_ext(resultado.getString("dia_ext"));
            visita.setMedicamentos(resultado.getString("medicamentos"));
            visita.setOperaciones(resultado.getString("operaciones"));
            visita.setObserva(resultado.getString("observa"));
            visita.setDolencias(resultado.getString("dolencias"));
            visita.setDiagnosticos_reflexologicos(resultado.getString("diagnosticos_reflexologicos"));
            visita.setOs_priv(resultado.getString("os_priv"));
            visita.setPeso_ini(resultado.getFloat("peso_ini"));
            visita.setFecha_ini(resultado.getDate("fecha_ini"));
            visita.setPeso_fin(resultado.getFloat("peso_fin"));
            visita.setFecha_fin(resultado.getDate("fecha_fin"));
            visita.setDiagnostico1(resultado.getString("diagnostico1"));
            visita.setDiagnostico2(resultado.getString("diagnostico2"));
            visita.setDiagnostico3(resultado.getString("diagnostico3"));
            visita.setDiagnostico4(resultado.getString("diagnostico4"));
            visita.setDiagnostico5(resultado.getString("diagnostico5"));
            visita.setDiagnostico6(resultado.getString("diagnostico6"));
            visita.setMenstru(resultado.getBoolean("menstru"));
            visita.setTestimonio(resultado.getBoolean("testimonio"));
            visita.setResultado(resultado.getString("resultado"));
            visita.setObservacion(resultado.getString("observacion"));
            visita.setFtterapeuta(resultado.getInt("ftterapeuta"));
            visita.setNvisitas(resultado.getInt("nvisitas"));
            resultado.close();
            sentencia.close();
            return visita;
        } else {
            resultado.close();
            sentencia.close();
            return null;
        }
    } catch (Exception e) {
        System.err.println("Error al obtener el registro del paciente.");
        System.err.println("Error: " + e);
        return null;
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
            sentencia.setString(2, String.valueOf(visita.getDiag_ext()));
            sentencia.setString(3, String.valueOf(visita.getMedicamentos()));
            sentencia.setString(4, visita.getOperaciones());
            sentencia.setString(5, visita.getObserva());
            sentencia.setString(6, visita.getDolencias());
            sentencia.setString(7, visita.getDiagnosticos_reflexologicos());
            sentencia.setString(8, visita.getOs_priv());
            sentencia.setFloat(9, visita.getPeso_ini());
            sentencia.setDate(10, (Date) visita.getFecha_ini());
            sentencia.setFloat(11, visita.getPeso_fin());
            sentencia.setDate(12, (Date) visita.getFecha_fin());
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
