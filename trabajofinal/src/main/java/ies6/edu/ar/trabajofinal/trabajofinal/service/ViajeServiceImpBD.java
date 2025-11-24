package ies6.edu.ar.trabajofinal.trabajofinal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Viaje;
import ies6.edu.ar.trabajofinal.trabajofinal.repository.ViajeRepository;

@Service
@Qualifier("servicioViajeMySQL")
public class ViajeServiceImpBD implements ViajeServiceI {

    @Autowired
    public ViajeRepository viajeRepository;

    @Override
    public void borrarViaje(Integer viajeId) throws Exception {
        Viaje viaje = buscarUnViaje(viajeId);
        viaje.setEstado(false);
        viajeRepository.save(viaje);
    }

    @Override
     public void agregarViaje(Viaje viaje) {
        Double costo = null;
        try {
            if (viaje.getVehiculo() != null) {
                costo = calcularCosto(viaje.getVehiculo().getTipoVehiculo(), viaje.getTipoViaje());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        viaje.setCosto(costo);
        if (viaje.getFecha() == null) {
            viaje.setFecha(LocalDate.now());
        }
        viaje.setEstado(true);
        viajeRepository.save(viaje);
    }

    @Override
    public void modificarViaje(Viaje viaje) throws Exception {
        Double costo = calcularCosto(viaje.getVehiculo().getTipoVehiculo(), viaje.getTipoViaje());
        viaje.setCosto(costo);
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
    // 🛑 REEMPLAZAR el throw por esta lógica:
    
    // Buscamos todos los viajes donde el campo 'estado' sea TRUE
    return viajeRepository.findByEstado(true);
}

    @Override
    public Double calcularCosto(String tipoVehiculo, String tipoViaje) throws Exception {
        double costoBase;
        switch (tipoViaje.toUpperCase()) {
            case "CORTA":
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


