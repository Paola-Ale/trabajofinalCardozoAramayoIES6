package ies6.edu.ar.trabajofinal.trabajofinal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Vehiculo;
import ies6.edu.ar.trabajofinal.trabajofinal.repository.VehiculoRepository;

@Service
@Qualifier("servicioVehiculoMySQL")
public class VehiculoServiceImp implements VehiculoServiceI {
    List<Vehiculo> listadoDeVehiculo = new ArrayList<Vehiculo>();

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Override
    public void borrarVehiculo(Integer vehiculoId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarVehiculo'");
    }

    @Override
    public void agregarVehiculo(Vehiculo vehiculo) {
        listadoDeVehiculo.add(vehiculo);
        System.out.println(listadoDeVehiculo.size());
    }

    @Override
    public void modificarVehiculo(Vehiculo vehiculo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarVehiculo'");
    }

    @Override
    public Vehiculo buscarUnVehiculo(Integer vehiculoId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnVehiculo'");
    }

    @Override
    public List<Vehiculo> listarTodosVehiculos() {
        return listadoDeVehiculo;
    }

    @Override
    public Vehiculo buscarVehiculoPorPatente(String patente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarVehiculoPorPatente'");
    }

    @Override
    public Vehiculo crearNuevoVehiculo() {
        return new Vehiculo();
    }

    @Override
    public List<Vehiculo> listarTodosVehiculosActivos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodosVehiculosActivos'");
    }

    @Override
    public Vehiculo tipoVehiculo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodosVehiculosActivos'");
    }

}
