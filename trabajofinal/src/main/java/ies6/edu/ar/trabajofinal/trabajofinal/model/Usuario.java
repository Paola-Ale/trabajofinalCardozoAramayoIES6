package ies6.edu.ar.trabajofinal.trabajofinal.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Component
@Entity

public class Usuario {
    @Id
    private String dni;
   
    @Column 
    private String nombre;
    @Column
    @NotBlank(message="apellido es un campo requerido")
    @NotNull(message="apellido is required")
    @Size(min=2, max=10, message="debe tener mas de 2 caracteres y menos de 10")
    private String apellido;
    @Column
    private String telefono;
    @Column
    private String email;
    @Column
    private Boolean estado;
   
    //constructores
   
    public Usuario() {
    }

    public Usuario(String dni, String nombre, String apellido, String telefono, String email, boolean estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }


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
