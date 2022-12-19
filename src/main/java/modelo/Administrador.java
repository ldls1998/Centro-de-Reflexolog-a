/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.security.MessageDigest;
/**
 *
 * @author Vlik35
 */
public class Administrador {
    
    private int DNI;
    private String nombres;
    private String apellido_materno;
    private String apellido_paterno;
    private String usuario;
    private String contrasena;
    private String email;

    public Administrador(int DNI, String nombre, String apellido_materno, String apellido_paterno, String usuario, String email) {
        this.DNI = DNI;
        this.nombres = nombre;
        this.apellido_materno = apellido_materno;
        this.apellido_paterno = apellido_paterno;
        this.usuario = usuario;
        this.email = email;
    }

    public Administrador() {
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombres;
    }

    public void setNombre(String nombre) {
        this.nombres = nombre;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(contrasena.getBytes("UTF-8"));
            this.contrasena = bytesToHex(hash);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
