package ies6.edu.ar.trabajofinal.trabajofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Usuario;
import ies6.edu.ar.trabajofinal.trabajofinal.service.UsuarioServiceI;
import jakarta.validation.Valid;

@Controller

public class UsuarioController {
    @Qualifier("servicioUsuarioMySQL")
    @Autowired
    UsuarioServiceI usuarioService;

    @GetMapping("/usuario")
    public ModelAndView getUsuario() {

        ModelAndView carrito = new ModelAndView("usuario");
        carrito.addObject("nuevoUsuario", usuarioService.crearNuevoUsuario());
        carrito.addObject("band", false);
        return carrito;
    }

    @PostMapping("/guardarUsuario")
    public ModelAndView saveUsuario(@Valid @ModelAttribute("nuevoUsuario") Usuario usuarioParaGuardar,
            BindingResult result) {
        System.out.println("estoy ingresando al metodo de guardar");
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("usuario");
            modelAndView.addObject("nuevoUsuario", usuarioParaGuardar);
        } else {
            try {
                usuarioService.agregarUsuario(usuarioParaGuardar);
                modelAndView.setViewName("listaUsuarios");
                modelAndView.addObject("correcto", "¡Usuario registrado con éxito!");
            } catch (Exception e) {
                // Mensaje de ERROR
                modelAndView.addObject("errorUsuario", "Error al guardar el usuario: " + e.getMessage());
            }
            modelAndView.addObject("lista", usuarioService.listarTodosUsuariosActivos());
            System.out.println("estoy saliendo al metodo de guardar");
        }

        return modelAndView;

    }

    @GetMapping("/eliminarUsuario/{dni}")
    public ModelAndView eliminarUsuario(@PathVariable(name = "dni") String dni) throws Exception {
        ModelAndView carritoDeEliminar = new ModelAndView("listaUsuarios");
        usuarioService.borrarUsuario(dni);
        carritoDeEliminar.addObject("lista", usuarioService.listarTodosUsuariosActivos());

        return carritoDeEliminar;
    }

    // MODIFICAR
    @GetMapping("/modificarUsuario/{dni}")
    public ModelAndView buscarUsuarioParaModificar(@PathVariable(name = "dni") String dni) throws Exception {
        ModelAndView carritoParaModificarUsuario = new ModelAndView("usuario");
        carritoParaModificarUsuario.addObject("nuevoUsuario", usuarioService.buscarUnUsuario(dni));
        carritoParaModificarUsuario.addObject("band", true);
        return carritoParaModificarUsuario;
    }

    @PostMapping("/modificarUsuario")
    public ModelAndView modificarUsuario(@ModelAttribute("nuevoUsuario") Usuario usuarioModificado) {
        ModelAndView listadoEditado = new ModelAndView("listaUsuarios");
        usuarioService.agregarUsuario(usuarioModificado);
        listadoEditado.addObject("lista", usuarioService.listarTodosUsuariosActivos());

        return listadoEditado;
    }

    @GetMapping("/listarUsuarios")
    public ModelAndView listarUsuariosActivos() {
        ModelAndView carritoParaMostrarUsuarios = new ModelAndView("listaUsuarios");
        carritoParaMostrarUsuarios.addObject("lista", usuarioService.listarTodosUsuariosActivos());

        return carritoParaMostrarUsuarios;
    }

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

}
