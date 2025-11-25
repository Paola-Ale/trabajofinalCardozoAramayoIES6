package ies6.edu.ar.trabajofinal.trabajofinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Viaje;

@Repository
public interface ViajeRepository extends CrudRepository<Viaje, Integer> {
     List<Viaje> findByEstado(Boolean estado);

}
    

