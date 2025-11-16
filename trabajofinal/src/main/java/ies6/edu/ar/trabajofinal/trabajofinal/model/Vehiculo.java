package ies6.edu.ar.trabajofinal.trabajofinal.model;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;


@Component
@Entity
public class Vehiculo {

    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private Integer vehiculoId;
    @Column
    private String patente;
    @Column
    private String marca;
    @Column
    private String color;
    @Column
    private String modelo;
    @Column
    private boolean estado;
    
    
    //constructor por defecto
    public Vehiculo() {
    }

    //constructor parametrizado
    public Vehiculo(Integer vehiculoId, String patente, String marca, String color, String modelo, boolean estado) {
        this.vehiculoId = vehiculoId;
        this.patente = patente;
        this.marca = marca;
        this.color = color;
        this.modelo = modelo;
        this.estado = estado;
    }

    //metodos accesores
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    

}

