package ies6.edu.ar.trabajofinal.trabajofinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ies6.edu.ar.trabajofinal.trabajofinal.model.Vehiculo;



@Repository
public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer> {
    public List<Vehiculo> findByEstado (Boolean estado);
}

