package ies6.edu.ar.trabajofinal.trabajofinal.model;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;


@Component
@Entity
public class Vehiculo {
    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private Integer vehiculoId;
    @Column
    @NotBlank(message="patente es un campo requerido")
    @NotNull(message="patente is required")
    @Size(min=2, max=10, message="debe tener mas de 2 caracteres y menos de 10")
    private String patente;
    @Column
    private String marca;
    @Column
    private String color;
    @Column
    private String modelo;
    @Column
    private String tipoVehiculo;
    @Column
    private boolean estado=true;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conductorDni")
    private Conductor conductor;



    public Vehiculo() {
    }


    public Vehiculo(Integer vehiculoId,
            @NotBlank(message = "patente es un campo requerido") @NotNull(message = "patente is required") @Size(min = 2, max = 10, message = "debe tener mas de 2 caracteres y menos de 10") String patente,
            String marca, String color, String modelo, String tipoVehiculo, boolean estado, Conductor conductor) {
        this.vehiculoId = vehiculoId;
        this.patente = patente;
        this.marca = marca;
        this.color = color;
        this.modelo = modelo;
        this.tipoVehiculo = tipoVehiculo;
        this.estado = estado;
        this.conductor = conductor;
    }


    public Integer getVehiculoId() {
        return vehiculoId;
    }


    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }


    public String getPatente() {
        return patente;
    }


    public void setPatente(String patente) {
        this.patente = patente;
    }


    public String getMarca() {
        return marca;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }


    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }


    public String getModelo() {
        return modelo;
    }


    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    public String getTipoVehiculo() {
        return tipoVehiculo;
    }


    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }


    public boolean isEstado() {
        return estado;
    }


    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    public Conductor getConductor() {
        return conductor;
    }


    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

}
   

  