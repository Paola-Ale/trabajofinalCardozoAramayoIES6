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

import ies6.edu.ar.trabajofinal.trabajofinal.model.Vehiculo;
import ies6.edu.ar.trabajofinal.trabajofinal.service.VehiculoServiceI;
import jakarta.validation.Valid;


@Controller
public class VehiculoController {
    //atributos
    @Qualifier ("servicioVehiculoMySQL")
    @Autowired
    VehiculoServiceI vehiculoService;


    //crear
    @GetMapping("/vehiculo")
    public ModelAndView getVehiculo() {

        ModelAndView carrito =new ModelAndView("Vehiculo");
       carrito.addObject("nuevoVehiculo", vehiculoService.crearNuevoVehiculo());
       carrito.addObject("band", false);
        return carrito;
    }

     @PostMapping("/guardarVehiculo")
     public ModelAndView saveVehiculo(@Valid @ModelAttribute("nuevoVehiculo") Vehiculo vehiculoParaGuardar, 
        BindingResult result) {
        System.out.println("estoy ingresando al metodo de guardar");
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("vehiculo");
            modelAndView.addObject("nuevoVehiculo", vehiculoParaGuardar);
        } else {
            try {
                vehiculoService.agregarVehiculo(vehiculoParaGuardar);
                modelAndView.setViewName("listaVehiculos");
                modelAndView.addObject("correcto", "¡Vehiculo registrado con éxito!");
            } catch (Exception e) {
                // Mensaje de ERROR
                modelAndView.addObject("errorVehiculo", "Error al guardar el vehiculo: " + e.getMessage());
            }
            modelAndView.addObject("lista", vehiculoService.listarTodosVehiculosActivos());
            System.out.println("estoy saliendo al metodo de guardar");
            return modelAndView;
        }
        return modelAndView;
        }

    //eliminar
    @GetMapping("/eliminarVehiculo/{vehiculoId}")
    public ModelAndView borrarVehiculo(@PathVariable("vehiculoId") Integer vehiculoId) throws Exception {
        ModelAndView carritoDeEliminar = new ModelAndView("listaVehiculo");
        vehiculoService.borrarVehiculo(vehiculoId);
        carritoDeEliminar.addObject("listadoVehiculo", vehiculoService.listarTodosVehiculos());
        return carritoDeEliminar;
    }

    //listado
   @GetMapping("/listaVehiculo")
    public ModelAndView listaVehiculo() {
    ModelAndView carritoParaMostrarVehiculos = new ModelAndView("listaVehiculo");
    carritoParaMostrarVehiculos.addObject("lista", vehiculoService.listarTodosVehiculos());
    return carritoParaMostrarVehiculos;
    }
}





