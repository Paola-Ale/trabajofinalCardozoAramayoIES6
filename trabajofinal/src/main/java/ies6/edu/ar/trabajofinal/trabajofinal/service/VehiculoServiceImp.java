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
public class VehiculoServiceImp implements VehiculoServiceI {

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Override
    public void borrarVehiculo(Integer vehiculoId) throws Exception {
        Vehiculo v = buscarUnVehiculo(vehiculoId);
        v.setEstado(false); 
        vehiculoRepository.save(v);
    }

    @Override
    public void agregarVehiculo(Vehiculo vehiculo) {
        
        vehiculo.setEstado(true);
        System.out.println(vehiculo.toString());
        vehiculoRepository.save(vehiculo);
    }

    @Override
    public void modificarVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo buscarUnVehiculo(Integer vehiculoId) throws Exception {
        return vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new Exception("Vehículo no encontrado"));
    }

    @Override
    public List<Vehiculo> listarTodosVehiculos() {
        return (List<Vehiculo>) vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo buscarVehiculoPorPatente(String patente) {
        Optional<Vehiculo> v = vehiculoRepository.findByPatente(patente);
        return v.orElse(null);
    }

    @Override
    public Vehiculo crearNuevoVehiculo() {
        return new Vehiculo();
    }

    @Override
    public List<Vehiculo> listarTodosVehiculosActivos() {
        return vehiculoRepository.findByEstado(true);
    }
}

    

