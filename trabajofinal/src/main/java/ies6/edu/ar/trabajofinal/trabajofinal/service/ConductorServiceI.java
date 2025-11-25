package ies6.edu.ar.trabajofinal.trabajofinal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Conductor;

@Service
public interface ConductorServiceI {
    public void borrarConductor(String dni) throws Exception;
    public void agregarConductor(Conductor conductor);
    public void modificarConductor(Conductor conductor);
    public List<Conductor> listarTodosConductor();
    public Conductor buscarUnConductor(String dni) throws Exception;
    public Conductor buscarUnConductorPorNombre(String nombre);
    public Conductor crearNuevoConductor();
    public List<Conductor> listarTodosConductoresActivos();
}