package ies6.edu.ar.trabajofinal.trabajofinal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ies6.edu.ar.trabajofinal.trabajofinal.model.Vehiculo;

@Repository
public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer> {
    Optional<Vehiculo> findByPatente(String patente);

    public List<Vehiculo> findByEstado(boolean estado);

}
