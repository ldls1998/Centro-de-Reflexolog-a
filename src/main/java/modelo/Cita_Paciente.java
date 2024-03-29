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
    private boolean ctodo;
    private boolean sde;
    private String observa;
    private boolean citaBool;
    private String hora;
    private boolean saldo;
    private String op;
    private int num;
    private Boolean Social;

    public Boolean getSocial() {
        return Social;
    }

    public void setSocial(Boolean Social) {
        this.Social = Social;
    }
    
    
    
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
        this.ctodo = this.cita.isCtodo();
        this.sde = this.cita.isSde();
        this.observa = this.cita.getObserva();
        this.citaBool = this.cita.isCitaBool();
        this.hora = this.cita.getHora();
        this.saldo = this.cita.isSaldo();
        this.op = this.cita.getOp();
        this.num = this.cita.getNum();
        this.Social = this.cita.isSocial();
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
        this.ctodo = this.cita.isCtodo();
        this.sde = this.cita.isSde();
        this.observa = this.cita.getObserva();
        this.citaBool = this.cita.isCitaBool();
        this.hora = this.cita.getHora();
        this.saldo = this.cita.isSaldo();
        this.op = this.cita.getOp();
        this.num = this.cita.getNum();
        this.Social = this.cita.isSocial();
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
        this.ctodo = this.cita.isCtodo();
        this.sde = this.cita.isSde();
        this.observa = this.cita.getObserva();
        this.citaBool = this.cita.isCitaBool();
        this.hora = this.cita.getHora();
        this.saldo = this.cita.isSaldo();
        this.op = this.cita.getOp();
        this.num = this.cita.getNum();
        this.Social = this.cita.isSocial();
        
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

    public boolean isCtodo() {
        return ctodo;
    }

    public void setCtodo(boolean ctodo) {
        this.cita.setCtodo(ctodo);
        this.ctodo = ctodo;
    }

    public boolean isSde() {
        return sde;
    }

    public void setSde(boolean sde) {
        this.cita.setSde(sde);
        this.sde = sde;
    }

    public String getObserva() {
        return observa;
    }

    public void setObserva(String observa) {
        this.cita.setObserva(observa);
        this.observa = observa;
    }

    public boolean isCitaBool() {
        return citaBool;
    }

    public void setCitaBool(boolean citaBool) {
        this.cita.setCitaBool(citaBool);
        this.citaBool = citaBool;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.cita.setHora(hora);
        this.hora = hora;
    }

    public boolean isSaldo() {
        return saldo;
    }

    public void setSaldo(boolean saldo) {
        this.cita.setSaldo(saldo);
        this.saldo = saldo;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.cita.setOp(op);
        this.op = op;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.cita.setNum(num);
        this.num = num;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cita_Paciente{");
        sb.append("cita=").append(cita);
        sb.append(", paciente=").append(paciente);
        sb.append(", registro=").append(registro);
        sb.append(", nombre=").append(nombre);
        sb.append(", codigo_paciente=").append(codigo_paciente);
        sb.append(", fecha_cita=").append(fecha_cita);
        sb.append(", importe=").append(importe);
        sb.append(", fecha_nacimiento=").append(fecha_nacimiento);
        sb.append(", sexo=").append(sexo);
        sb.append(", direccion=").append(direccion);
        sb.append(", prov=").append(prov);
        sb.append(", dpto=").append(dpto);
        sb.append(", dist=").append(dist);
        sb.append(", testimonio=").append(testimonio);
        sb.append(", resultado=").append(resultado);
        sb.append(", observacion=").append(observacion);
        sb.append(", ocupacion=").append(ocupacion);
        sb.append(", telefono=").append(telefono);
        sb.append(", email=").append(email);
        sb.append(", especial=").append(especial);
        sb.append(", ctodo=").append(ctodo);
        sb.append(", sde=").append(sde);
        sb.append(", observa=").append(observa);
        sb.append(", citaBool=").append(citaBool);
        sb.append(", hora=").append(hora);
        sb.append(", saldo=").append(saldo);
        sb.append(", op=").append(op);
        sb.append(", num=").append(num);
        sb.append('}');
        return sb.toString();
    }
    
}
