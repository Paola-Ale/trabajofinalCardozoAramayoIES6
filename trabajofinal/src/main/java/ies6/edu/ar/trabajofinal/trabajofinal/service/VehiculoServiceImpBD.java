package ies6.edu.ar.trabajofinal.trabajofinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Vehiculo;
import ies6.edu.ar.trabajofinal.trabajofinal.repository.VehiculoRepository;


@Service
@Qualifier("servicioVehiculoMySQL")
public class VehiculoServiceImpBD implements VehiculoServiceI {

    @Autowired
    Vehiculo nuevoVehiculo;

    @Autowired
    VehiculoRepository vehiculoRepository;


    @Override
    public Vehiculo crearNuevoVehiculo() {
        return nuevoVehiculo;
    }


    @Override
    public void borrarVehiculo(Integer vehiculoId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarVehiculo'");
    }


    @Override
    public void agregarVehiculo(Vehiculo vehiculo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarVehiculo'");
    }


    @Override
    public void modificarVehiculo(Vehiculo vehiculo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarVehiculo'");
    }


    @Override
    public List<Vehiculo> listarTodosVehiculos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodosVehiculos'");
    }


    @Override
    public Vehiculo buscarUnVehiculo(Integer vehiculoId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnVehiculo'");
    }


    @Override
    public Vehiculo buscarVehiculoPorPatente(String patente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarVehiculoPorPatente'");
    }


    @Override
    public List<Vehiculo> listarTodosVehiculosActivos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodosVehiculosActivos'");
    }
}

   