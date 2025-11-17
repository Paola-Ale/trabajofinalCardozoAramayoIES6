package ies6.edu.ar.trabajofinal.trabajofinal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Usuario;

@Service
@Qualifier("servicioUsuarioArrayList")

public class UsuarioServiceImp implements UsuarioServiceI {
    
    List<Usuario> listadoDeUsuarios=new ArrayList<Usuario>();
    
    @Autowired
    Usuario nuevoUsuario;
    

    @Override
    public void borrarUsuario (String dni) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnAlumno'");
    }
    @Override

    public void agregarUsuario (Usuario usuario) {
        listadoDeUsuarios.add(usuario);
        System.out.println(listadoDeUsuarios.size());
    }

    @Override
    public void modificarUsuario (Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnAlumno'");
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return listadoDeUsuarios;
    }

    @Override
    public Usuario buscarUnUsuario(String dni) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnAlumno'");
    }

    @Override
    public Usuario buscarUnPorNombreUsuario(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnPorNombreAlumno'");
    }

    @Override
    public Usuario crearNuevoUsuario() {
        //Alumno nuevoAlumno= new Alumno();
        return nuevoUsuario;
    }
    @Override
    public List<Usuario> listarTodosUsuariosActivos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodosUsuariosActivos'");
    }
    
}