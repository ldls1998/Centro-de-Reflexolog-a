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
public class Visita {
    //detalle de visita
    private int pacienteID;
    private String diag_ext;
    private String medicamentos;
    private String operaciones;
    private String observa;
    private String dolencias;
    private String diagnosticos_reflexologicos;
    private String os_priv;
    private float peso_ini;
    private Date fecha_ini;
    private float peso_fin;
    private Date fecha_fin;
    private float talla;
    private String diagnostico1;
    private String diagnostico2;
    private String diagnostico3;
    private String diagnostico4;
    private String diagnostico5;
    private String diagnostico6;
    private Boolean menstru;
    private Boolean testimonio;
    private String resultado;
    private String observacion;
    
    private int nvisitas;
    private int ftterapeuta;
    
    public Visita() {
    }

    public int getFtterapeuta() {
        return ftterapeuta;
    }

    public void setFtterapeuta(int ftterapeuta) {
        this.ftterapeuta = ftterapeuta;
    }

    public int getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(int pacienteID) {
        this.pacienteID = pacienteID;
    }

    public int getNvisitas() {
        return nvisitas;
    }

    public void setNvisitas(int nvisitas) {
        this.nvisitas = nvisitas;
    }
    
    


    public String getDiag_ext() {
        return diag_ext;
    }

    public void setDiag_ext(String diag_ext) {
        this.diag_ext = diag_ext;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public float getTalla() {
        return talla;
    }

    public void setTalla(float talla) {
        this.talla = talla;
    }

    public String getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(String operaciones) {
        this.operaciones = operaciones;
    }

    public String getObserva() {
        return observa;
    }

    public void setObserva(String observa) {
        this.observa = observa;
    }

    public String getDolencias() {
        return dolencias;
    }

    public void setDolencias(String dolencias) {
        this.dolencias = dolencias;
    }

    public String getDiagnosticos_reflexologicos() {
        return diagnosticos_reflexologicos;
    }

    public void setDiagnosticos_reflexologicos(String diagnosticos_reflexologicos) {
        this.diagnosticos_reflexologicos = diagnosticos_reflexologicos;
    }

    public String getOs_priv() {
        return os_priv;
    }

    public void setOs_priv(String os_priv) {
        this.os_priv = os_priv;
    }

    public float getPeso_ini() {
        return peso_ini;
    }

    public void setPeso_ini(float peso_ini) {
        this.peso_ini = peso_ini;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public float getPeso_fin() {
        return peso_fin;
    }

    public void setPeso_fin(float peso_fin) {
        this.peso_fin = peso_fin;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getDiagnostico1() {
        return diagnostico1;
    }

    public void setDiagnostico1(String diagnostico1) {
        this.diagnostico1 = diagnostico1;
    }

    public String getDiagnostico2() {
        return diagnostico2;
    }

    public void setDiagnostico2(String diagnostico2) {
        this.diagnostico2 = diagnostico2;
    }

    public String getDiagnostico3() {
        return diagnostico3;
    }

    public void setDiagnostico3(String diagnostico3) {
        this.diagnostico3 = diagnostico3;
    }

    public String getDiagnostico4() {
        return diagnostico4;
    }

    public void setDiagnostico4(String diagnostico4) {
        this.diagnostico4 = diagnostico4;
    }

    public String getDiagnostico5() {
        return diagnostico5;
    }

    public void setDiagnostico5(String diagnostico5) {
        this.diagnostico5 = diagnostico5;
    }

    public String getDiagnostico6() {
        return diagnostico6;
    }

    public void setDiagnostico6(String diagnostico6) {
        this.diagnostico6 = diagnostico6;
    }

    public Boolean getMenstru() {
        return menstru;
    }

    public void setMenstru(Boolean menstru) {
        this.menstru = menstru;
    }

    public Boolean getTestimonio() {
        return testimonio;
    }

    public void setTestimonio(Boolean testimonio) {
        this.testimonio = testimonio;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    
    
    /*
    @Override
    public String toString() {
        return "G110{" + "Codigo="  + codigo + ", nombre=" + nombre + ", fecha_nacimiento=" + fecha_nacimiento + ", sexo=" + sexo + ", direccion=" + direccion + ", prov=" + prov + ", dpto=" + dpto + ", dist=" + dist + ", testimonio=" + testimonio + ", resultado=" + resultado + ", observacion=" + observacion + ", ocupacion=" + ocupacion + ", telefono=" + telefono + ", email=" + email + ", especial=" + especial + '}';
    }*/

}
