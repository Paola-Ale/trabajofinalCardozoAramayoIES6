package ies6.edu.ar.trabajofinal.trabajofinal.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ies6.edu.ar.trabajofinal.trabajofinal.model.Usuario;
import ies6.edu.ar.trabajofinal.trabajofinal.repository.UsuarioRepository;


@Service
@Qualifier("servicioUsuarioMySQL")
public class UsuarioServiceImpBD implements UsuarioServiceI {
    
    @Autowired
    Usuario nuevoUsuario;

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public void borrarUsuario(String dni) throws Exception {
        Usuario usuarioBorrar = usuarioRepository.findById(dni).orElseThrow(()-> new Exception("usuario no encontrado"));
        usuarioBorrar.setEstado(false);
        usuarioRepository.save(usuarioBorrar);
    }

    @Override
    public void agregarUsuario(Usuario usuario){
        usuario.setEstado(true);
        usuarioRepository.save(usuario);
    }
    

    @Override
    public void modificarUsuario (Usuario usuario) {
         // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnUsuario'");
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUnUsuario(String dni) throws Exception {
        return usuarioRepository.findById(dni).orElseThrow(()-> new Exception("usuario no encontrado"));
        
    }

    @Override
    public Usuario buscarUnPorNombreUsuario(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnPorNombreUsuario'");
    }

    @Override
    public Usuario crearNuevoUsuario() {
        return nuevoUsuario;
    }

    @Override
    public List<Usuario> listarTodosUsuariosActivos() {
        return usuarioRepository.findByEstado(true);
    }
    
}


    
