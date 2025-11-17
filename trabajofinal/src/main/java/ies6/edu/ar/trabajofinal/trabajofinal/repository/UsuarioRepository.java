package ies6.edu.ar.trabajofinal.trabajofinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ies6.edu.ar.trabajofinal.trabajofinal.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,String>{

    public List<Usuario> findByEstado (Boolean estado);
    
    
}