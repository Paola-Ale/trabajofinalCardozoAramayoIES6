package ies6.edu.ar.trabajofinal.trabajofinal.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Component
@Entity
public class Conductor {
    @Id
    private String dni;

    @Column
    private String nombre;

    @Column
    @NotBlank(message = "apellido es un campo requerido")
    @NotNull(message = "apellido is required")
    @Size(min = 2, max = 20, message = "debe tener mas de 2 caracteres y menos de 20")
    private String apellido;

    @Column
    private String telefono;

    @Column
    private boolean estado = true;

    @OneToOne(mappedBy = "conductor", fetch = FetchType.LAZY)
    private Vehiculo vehiculo;

    public Conductor() {
    }

    public Conductor(String dni, String nombre,
            @NotBlank(message = "apellido es un campo requerido") @NotNull(message = "apellido is required") @Size(min = 2, max = 20, message = "debe tener mas de 2 caracteres y menos de 20") String apellido,
            String telefono, boolean estado, Vehiculo vehiculo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.estado = estado;
        this.vehiculo = vehiculo;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    
}
