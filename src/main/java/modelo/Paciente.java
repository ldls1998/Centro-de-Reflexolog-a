/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author Vlik35
 */
public class Paciente {

    private int ID;
    private int codigo;
    private String nombre;
    private int DNICE;
    private Date fecha_nacimiento;
    private String sexo;
    private String direccion;
    private String prov;
    private String dpto;
    private String dist;
    private String testimonio;
    private String resultado;
    private String observacion;
    private String ocupacion;
    private int telefono;
    private String email;
    private Boolean especial;

    public Paciente() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public int getDNICE() {
        return DNICE;
    }

    public void setDNICE(int DNICE) {
        this.DNICE = DNICE;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getTestimonio() {
        return testimonio;
    }

    public void setTestimonio(String testimonio) {
        this.testimonio = testimonio;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEspecial() {
        return especial;
    }

    public void setEspecial(Boolean especial) {
        this.especial = especial;
    }

    @Override
    public String toString() {
        return "G110{" + "ID=" + ID + ", codigo=" + codigo + ", nombre=" + nombre + ", DNICE=" + DNICE + ", fecha_nacimiento=" + fecha_nacimiento + ", sexo=" + sexo + ", direccion=" + direccion + ", prov=" + prov + ", dpto=" + dpto + ", dist=" + dist + ", testimonio=" + testimonio + ", resultado=" + resultado + ", observacion=" + observacion + ", ocupacion=" + ocupacion + ", telefono=" + telefono + ", email=" + email + ", especial=" + especial + '}';
    }

}
