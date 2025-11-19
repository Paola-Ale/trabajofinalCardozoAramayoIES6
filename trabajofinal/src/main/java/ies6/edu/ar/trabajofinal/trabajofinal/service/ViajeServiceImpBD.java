package ies6.edu.ar.trabajofinal.trabajofinal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Viaje;
import ies6.edu.ar.trabajofinal.trabajofinal.repository.ConductorRepository;
import ies6.edu.ar.trabajofinal.trabajofinal.repository.UsuarioRepository;
import ies6.edu.ar.trabajofinal.trabajofinal.repository.VehiculoRepository;
import ies6.edu.ar.trabajofinal.trabajofinal.repository.ViajeRepository;

@Service
@Qualifier("servicioViajeMySQL")
public class ViajeServiceImpBD implements ViajeServiceI {

public List<Viaje> listadoDeViajes = new ArrayList<Viaje>();

@Autowired
public ViajeRepository viajeRepository;

@Override
public void borrarViaje(Integer viajeId) throws Exception {
    Viaje viaje = buscarUnViaje(viajeId);
    viaje.setEstado(false);
}

@Override
public void agregarViaje(Viaje viaje) {
    viajeRepository.save(viaje);
}

@Override
public void modificarViaje(Viaje viaje) {
    viajeRepository.save(viaje);
}

@Override
public List<Viaje> listarTodosViajes() {
    return (List<Viaje>) viajeRepository.findAll();
}

@Override
public Viaje buscarUnViaje(Integer viajeId) throws Exception {
    return viajeRepository.findById(viajeId)
            .orElseThrow(() -> new Exception("Viaje no encontrado"));
}

@Override
public Viaje crearNuevoViaje() {
    return new Viaje();
}

@Override
public List<Viaje> listarTodosViajesActivos() {
    return viajeRepository.findByEstado(true);
}

@Override
public Double calcularCosto(String tipoVehiculo, String tipoViaje) throws Exception {

    double costoBase = 0;

    switch (tipoViaje.toUpperCase()) {
        case "CORTA":
            costoBase = 7000;
            break;
        case "MEDIA":
            costoBase = 7000;
            break;
        case "LARGA":
            costoBase = 20000;
            break;
        default:
            throw new Exception("Tipo de viaje inválido");
    }

    switch (tipoVehiculo.toUpperCase()) {
        case "X":
            break;
        case "LUXE":
            costoBase *= 1.10;
            break;
        case "PREMIUM":
            costoBase *= 1.20;
            break;
        default:
            throw new Exception("Tipo de vehículo inválido");
    }

    return costoBase;
}

}

