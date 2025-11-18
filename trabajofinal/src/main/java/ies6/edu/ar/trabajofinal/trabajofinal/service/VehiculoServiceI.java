package ies6.edu.ar.trabajofinal.trabajofinal.service;

import java.util.List;

import org.springframework.stereotype.Service; 
import ies6.edu.ar.trabajofinal.trabajofinal.model.Vehiculo;


public interface VehiculoServiceI {

    // declaración de métodos
    // nominativo solo nombre

    public void borrarVehiculo(Integer vehiculoId) throws Exception;
    public void agregarVehiculo(Vehiculo vehiculo);
    public void modificarVehiculo(Vehiculo vehiculo);
    public List<Vehiculo> listarTodosVehiculos();
    public Vehiculo buscarUnVehiculo(Integer vehiculoId) throws Exception;
    public Vehiculo buscarVehiculoPorPatente(String patente);
    public Vehiculo crearNuevoVehiculo();
    public List<Vehiculo> listarTodosVehiculosActivos();
}

