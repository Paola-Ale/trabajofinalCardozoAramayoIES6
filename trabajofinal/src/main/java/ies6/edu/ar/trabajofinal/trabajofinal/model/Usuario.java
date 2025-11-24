package ies6.edu.ar.trabajofinal.trabajofinal.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Component
@Entity

public class Usuario {
    @Id
    private String dni;
    @Column 
    private String nombre;
   
    private String apellido;
    @Column
    private String telefono;
    @Column
    private String email;
    @Column
    private Boolean estado=true;
   
    //constructores por defecto
   
    public Usuario() {
    }

    //constructor parametrado

    public Usuario(String dni, String nombre, String apellido, String telefono, String email, boolean estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }
    //metodos accesores Getters y Setters

    public String getDni() {
        return dni;
    }


    public void setDni(String dni) {
    
        this.dni = dni;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }



    public boolean getEstado() {
        return estado;
    }


    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
