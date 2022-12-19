/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Vlik35
 */
public class Cita {
    
    private int codigo_terapueta;
    private int codigo_paciente;
    private Date fecha_cita;

    public Cita() {
    }

    public int getCodigo_terapueta() {
        return codigo_terapueta;
    }

    public void setCodigo_terapueta(int codigo_terapueta) {
        this.codigo_terapueta = codigo_terapueta;
    }

    public int getCodigo_paciente() {
        return codigo_paciente;
    }

    public void setCodigo_paciente(int codigo_paciente) {
        this.codigo_paciente = codigo_paciente;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    @Override
    public String toString() {
        return "Cita{" + "codigo_terapueta=" + codigo_terapueta + ", codigo_paciente=" + codigo_paciente + ", fecha_cita=" + fecha_cita + '}';
    }
    
}
