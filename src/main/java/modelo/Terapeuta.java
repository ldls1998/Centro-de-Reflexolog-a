/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author Vlik35
 */
public class Terapeuta {
    
    private String nombre;
    private String sexo;
    private Date fecha_nacimiento;
    private String direccion;
    private String prov;
    private String dpto;
    private String dist;
    private int numero;
    private int telefono;
    private String email;
    private Boolean permanente;

    public Terapeuta() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Boolean getPermanente() {
        return permanente;
    }

    public void setPermanente(Boolean permanente) {
        this.permanente = permanente;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Terapeuta{");
        sb.append("numero=").append(numero);
        sb.append(", nombre=").append(nombre);
        sb.append(", fecha_nacimiento=").append(fecha_nacimiento);
        sb.append(", sexo=").append(sexo);
        sb.append(", direccion=").append(direccion);
        sb.append(", prov=").append(prov);
        sb.append(", dpto=").append(dpto);
        sb.append(", dist=").append(dist);
        sb.append(", telefono=").append(telefono);
        sb.append(", email=").append(email);
        sb.append(", permanente=").append(permanente);
        sb.append('}');
        return sb.toString();
    }
    
}
