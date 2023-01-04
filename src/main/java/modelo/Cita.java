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
    
    private int registro;
    private int codigo_paciente;
    private Date fecha_cita;
    private float importe;
    

    public Cita() {
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
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

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cita{registro=").append(registro);
        sb.append(", codigo_paciente=").append(codigo_paciente);
        sb.append(", fecha_cita=").append(fecha_cita);
        sb.append(", importe=").append(importe);
        sb.append('}');
        return sb.toString();
    }
    
}
