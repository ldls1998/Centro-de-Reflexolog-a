/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vlik35
 */
public class Cita {
    
    private int registro;
    private String hora;
    private int codigo_paciente;
    private Date fecha_cita;
    private float importe;
    private boolean ctodo;
    private boolean sde;
    private boolean social;

  
    
    private String observa;
    private boolean citaBool;
    private boolean saldo;
    private String op;
    private int num;
    private String tipo;



    public boolean isCtodo() {
        return ctodo;
    }

    public void setCtodo(boolean ctodo) {
        this.ctodo = ctodo;
    }
    
    public boolean isSocial() {
        return social;
    }

    public void setSocial(boolean social) {
        this.social = social;
    }
    
    public boolean isSde() {
        return sde;
    }

    public void setSde(boolean sde) {
        this.sde = sde;
    }

    public String getObserva() {
        return observa;
    }

    public void setObserva(String observa) {
        this.observa = observa;
    }

    public boolean isCitaBool() {
        return citaBool;
    }

    public void setCitaBool(boolean citaBool) {
        this.citaBool = citaBool;
    }

    public boolean isSaldo() {
        return saldo;
    }

    public void setSaldo(boolean saldo) {
        this.saldo = saldo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cita() {
    }

    public Cita(int registro, int codigo_paciente, Date fecha_cita, float importe, boolean ctodo, boolean sde, String observa, boolean citaBool, String hora, boolean saldo, String op, int num, boolean social) {
        this.registro = registro;
        this.codigo_paciente = codigo_paciente;
        this.fecha_cita = fecha_cita;
        this.importe = importe;
        this.ctodo = ctodo;
        this.sde = sde;
        this.observa = observa;
        this.citaBool = citaBool;
        this.hora = hora;
        this.saldo = saldo;
        this.op = op;
        this.num = num;
        this.social = social;

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

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cita{");
        sb.append("registro=").append(registro);
        sb.append(", codigo_paciente=").append(codigo_paciente);
        sb.append(", fecha_cita=").append(fecha_cita);
        sb.append(", importe=").append(importe);
        sb.append(", ctodo=").append(ctodo);
        sb.append(", sde=").append(sde);
        sb.append(", observa=").append(observa);
        sb.append(", citaBool=").append(citaBool);
        sb.append(", hora=").append(hora);
        sb.append(", saldo=").append(saldo);
        sb.append(", op=").append(op);
        sb.append(", num=").append(num);
        sb.append(", Social=").append(social);
        sb.append('}');
        return sb.toString();
    }
    
}
