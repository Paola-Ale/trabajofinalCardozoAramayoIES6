package ies6.edu.ar.trabajofinal.trabajofinal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Conductor;

@Repository
public interface ConductorRepository extends CrudRepository<Conductor, String> {

public List<Conductor> findByEstado (Boolean estado); Optional<Conductor> findByDni(String dni);
}

