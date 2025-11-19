package ies6.edu.ar.trabajofinal.trabajofinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ies6.edu.ar.trabajofinal.trabajofinal.model.Conductor;
import ies6.edu.ar.trabajofinal.trabajofinal.repository.ConductorRepository;

@Service
@Qualifier ("servicioConductorMySQL")
public class ConductorServiceImpBD implements ConductorServiceI {

@Autowired
Conductor nuevoConductor;

@Autowired
ConductorRepository conductorRepository;

@Override
public void borrarConductor(String dni) throws Exception {
        Conductor conductorBorrar = conductorRepository.findByDni(dni).orElseThrow(()-> new Exception("conductor no encontrado"));
        conductorBorrar.setEstado(false);
        conductorRepository.save(conductorBorrar);
    }


    public void agregarConductor(Conductor conductor){
        conductor.setEstado(true);
        conductorRepository.save(conductor);
    }
    

    public void modificarConductor (Conductor conductor) {
         // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnConductor'");
    }

    public List<Conductor> listarTodosConductor() {
        return conductorRepository.findByEstado(true);
    }

    public Conductor buscarUnConductor(String dni) throws Exception {
        return conductorRepository.findByDni(dni).orElseThrow(()-> new Exception("conductor no encontrado"));
        
    }

    public Conductor buscarUnConductorPorNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnConductorPorNombre'");
    }

    public Conductor crearNuevoConductor() {
        return nuevoConductor;
    }

    public List<Conductor> listarTodosConductoresActivos() {
        return (List<Conductor>) conductorRepository;
    }
    
}


