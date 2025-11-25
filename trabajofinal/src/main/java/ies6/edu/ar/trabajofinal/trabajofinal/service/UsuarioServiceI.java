package ies6.edu.ar.trabajofinal.trabajofinal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Usuario;


@Service
public interface UsuarioServiceI {
    public void borrarUsuario(String dni) throws Exception;
    public void agregarUsuario(Usuario usuario);
    public void modificarUsuario(Usuario usuario);
    public List<Usuario> listarTodosUsuarios();
    public Usuario buscarUnUsuario(String dni) throws Exception;
    public Usuario buscarUnPorNombreUsuario(String nombre);
    public Usuario crearNuevoUsuario();
    public List<Usuario> listarTodosUsuariosActivos();
}
    