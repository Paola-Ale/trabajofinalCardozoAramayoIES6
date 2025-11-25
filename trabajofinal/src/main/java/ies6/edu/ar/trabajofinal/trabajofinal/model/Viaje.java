package ies6.edu.ar.trabajofinal.trabajofinal.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer viajeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculoId")
    private Vehiculo vehiculo;
    @Column(length = 20)
    private String tipoViaje;
    @Column
    private LocalDate fecha;
    @Column
    private Double costo;
    @Column
    private Boolean estado = true;

   
   
   
    public Viaje() {
    }


    public Viaje(Integer viajeId, Usuario usuario, Vehiculo vehiculo, String tipoViaje, LocalDate fecha, Double costo,
            Boolean estado) {
        this.viajeId = viajeId;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.tipoViaje = tipoViaje;
        this.fecha = fecha;
        this.costo = costo;
        this.estado = estado;
    }


    public Integer getViajeId() {
        return viajeId;
    }


    public void setViajeId(Integer viajeId) {
        this.viajeId = viajeId;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Vehiculo getVehiculo() {
        return vehiculo;
    }


    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


    public String getTipoViaje() {
        return tipoViaje;
    }


    public void setTipoViaje(String tipoViaje) {
        this.tipoViaje = tipoViaje;
    }


    public LocalDate getFecha() {
        return fecha;
    }


    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public Double getCosto() {
        return costo;
    }


    public void setCosto(Double costo) {
        this.costo = costo;
    }


    public Boolean getEstado() {
        return estado;
    }


    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    
    

    
}
