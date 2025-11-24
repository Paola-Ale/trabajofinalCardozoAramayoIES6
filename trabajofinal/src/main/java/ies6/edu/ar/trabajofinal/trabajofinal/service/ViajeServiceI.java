package ies6.edu.ar.trabajofinal.trabajofinal.service;
import java.util.List;
import ies6.edu.ar.trabajofinal.trabajofinal.model.Viaje;
public interface ViajeServiceI {
    public void borrarViaje(Integer viajeId) throws Exception;
    public void agregarViaje(Viaje viaje);
    public void modificarViaje(Viaje viaje) throws Exception;
    public List<Viaje> listarTodosViajes();
    public Viaje buscarUnViaje(Integer viajeId) throws Exception;
    public Viaje crearNuevoViaje();
    public List<Viaje> listarTodosViajesActivos();
    public Double calcularCosto(String tipoVehiculo, String tipoViaje) throws Exception;

}