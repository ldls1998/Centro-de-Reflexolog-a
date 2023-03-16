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
public class Cita_Paciente {

    private Cita cita;
    private Paciente paciente;
    private int registro;
    private String nombre;
    private int codigo_paciente;
    private Date fecha_cita;
    private float importe;
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
    
    public Cita_Paciente() {
        this.cita = new Cita();
        this.paciente = new Paciente();
    }

    public Cita_Paciente(Cita cita) {
        this.paciente = new Paciente();
        this.cita = cita;
        this.registro = this.cita.getRegistro();
        this.codigo_paciente = this.cita.getCodigo_paciente();
        this.fecha_cita = this.cita.getFecha_cita();
        this.importe = this.cita.getImporte();
    }
    
    public Cita_Paciente(Paciente paciente) {
        this.cita = new Cita();
        this.paciente = paciente;
        this.nombre = this.paciente.getNombre();
        this.fecha_nacimiento = this.paciente.getFecha_nacimiento();
        this.sexo = this.paciente.getSexo();
        this.direccion = this.paciente.getDireccion();
        this.prov = this.paciente.getProv();
        this.dpto = this.paciente.getDpto();
        this.dist = this.paciente.getDist();
        this.testimonio = this.paciente.getTestimonio();
        this.resultado = this.paciente.getResultado();
        this.observacion = this.paciente.getObservacion();
        this.ocupacion = this.paciente.getOcupacion();
        this.telefono = this.paciente.getTelefono();
        this.email = this.paciente.getEmail();
        this.especial = this.paciente.getEspecial();
    }
    
    public Cita_Paciente(Cita cita, Paciente paciente) {
        this.cita = cita;
        this.paciente = paciente;
        this.registro = this.cita.getRegistro();
        this.nombre = this.paciente.getNombre();
        this.codigo_paciente = this.cita.getCodigo_paciente();
        this.fecha_cita = this.cita.getFecha_cita();
        this.importe = this.cita.getImporte();
        this.fecha_nacimiento = this.paciente.getFecha_nacimiento();
        this.sexo = this.paciente.getSexo();
        this.direccion = this.paciente.getDireccion();
        this.prov = this.paciente.getProv();
        this.dpto = this.paciente.getDpto();
        this.dist = this.paciente.getDist();
        this.testimonio = this.paciente.getTestimonio();
        this.resultado = this.paciente.getResultado();
        this.observacion = this.paciente.getObservacion();
        this.ocupacion = this.paciente.getOcupacion();
        this.telefono = this.paciente.getTelefono();
        this.email = this.paciente.getEmail();
        this.especial = this.paciente.getEspecial();
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
        this.registro = this.cita.getRegistro();
        this.codigo_paciente = this.cita.getCodigo_paciente();
        this.fecha_cita = this.cita.getFecha_cita();
        this.importe = this.cita.getImporte();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.codigo_paciente = this.paciente.getCodigo();
        this.nombre = this.paciente.getNombre();
        this.fecha_nacimiento = this.paciente.getFecha_nacimiento();
        this.sexo = this.paciente.getSexo();
        this.direccion = this.paciente.getDireccion();
        this.prov = this.paciente.getProv();
        this.dpto = this.paciente.getDpto();
        this.dist = this.paciente.getDist();
        this.testimonio = this.paciente.getTestimonio();
        this.resultado = this.paciente.getResultado();
        this.observacion = this.paciente.getObservacion();
        this.ocupacion = this.paciente.getOcupacion();
        this.telefono = this.paciente.getTelefono();
        this.email = this.paciente.getEmail();
        this.especial = this.paciente.getEspecial();
    }
    
    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.cita.setRegistro(registro);
        this.registro = registro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.paciente.setNombre(nombre);
        this.nombre = nombre;
    }

    public int getCodigo_paciente() {
        return codigo_paciente;
    }

    public void setCodigo_paciente(int codigo_paciente) {
        this.paciente.setCodigo(codigo_paciente);
        this.codigo_paciente = codigo_paciente;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.cita.setFecha_cita(fecha_cita);
        this.fecha_cita = fecha_cita;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.cita.setImporte(importe);
        this.importe = importe;
    }

    public int getDNICE() {
        return DNICE;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.paciente.setFecha_nacimiento(fecha_nacimiento);
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.paciente.setSexo(sexo);
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.paciente.setDireccion(direccion);
        this.direccion = direccion;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.paciente.setProv(prov);
        this.prov = prov;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.paciente.setDpto(dpto);
        this.dpto = dpto;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.paciente.setDist(dist);        
        this.dist = dist;
    }

    public String getTestimonio() {
        return testimonio;
    }

    public void setTestimonio(String testimonio) {
        this.paciente.setTestimonio(testimonio);
        this.testimonio = testimonio;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.paciente.setResultado(resultado);
        this.resultado = resultado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.paciente.setObservacion(observacion);
        this.observacion = observacion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.paciente.setOcupacion(ocupacion);
        this.ocupacion = ocupacion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.paciente.setTelefono(telefono);
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.paciente.setEmail(email);
        this.email = email;
    }

    public Boolean getEspecial() {
        return especial;
    }

    public void setEspecial(Boolean especial) {
        this.paciente.setEspecial(especial);
        this.especial = especial;
    }
    
}
