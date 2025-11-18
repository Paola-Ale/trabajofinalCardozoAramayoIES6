package ies6.edu.ar.trabajofinal.trabajofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Vehiculo;
import ies6.edu.ar.trabajofinal.trabajofinal.service.VehiculoServiceI;
import jakarta.validation.Valid;
import java.util.List;

@Controller
public class VehiculoController {
    // atributos
    @Qualifier("servicioVehiculoMySQL")
    @Autowired
    VehiculoServiceI vehiculoService;

    // crear

    @GetMapping("/vehiculo")
    public ModelAndView nuevoVehiculo() {
        ModelAndView ModelAndView = new ModelAndView("vehiculos/nuevo");
        ModelAndView.addObject("nuevoVehiculo", vehiculoService.crearNuevoVehiculo());
        ModelAndView.addObject("band", false);
        return ModelAndView;
    }


    @PostMapping("/guardarVehiculo")
    public ModelAndView saveVehiculo(
            @Valid @ModelAttribute("nuevoVehiculo") Vehiculo vehiculoParaGuardar,
            BindingResult result) {

        System.out.println("Ingresando al método de guardar");
        ModelAndView ModelAndView = new ModelAndView();

        if (result.hasErrors()) {
            ModelAndView.setViewName("vehiculos/nuevo");
            ModelAndView.addObject("nuevoVehiculo", vehiculoParaGuardar);
            return ModelAndView;
        }

        try {
            vehiculoService.agregarVehiculo(vehiculoParaGuardar);
            ModelAndView.setViewName("listaVehiculos");
            ModelAndView.addObject("correcto", "¡Vehiculo registrado con éxito!");
        } catch (Exception e) {
            ModelAndView.addObject("errorVehiculo", "Error al guardar el vehículo: " + e.getMessage());
        }

        ModelAndView.addObject("lista", vehiculoService.listarTodosVehiculosActivos());
        System.out.println("Saliendo del método de guardar");
        return ModelAndView;
    }

    // eliminar
    @GetMapping("/eliminarVehiculo/{vehiculoId}")
    public ModelAndView borrarVehiculo(@PathVariable("vehiculoId") Integer vehiculoId) throws Exception {
        ModelAndView carritoDeEliminar = new ModelAndView("listaVehiculo");
        vehiculoService.borrarVehiculo(vehiculoId);
        carritoDeEliminar.addObject("listadoVehiculo", vehiculoService.listarTodosVehiculos());
        return carritoDeEliminar;
    }

    // listado
    @GetMapping("/listaVehiculo")
    public ModelAndView listarTodosVehiculosActivos() {
        ModelAndView carritoParaMostrarVehiculos = new ModelAndView("listaVehiculos");
        carritoParaMostrarVehiculos.addObject("lista", vehiculoService.listarTodosVehiculosActivos());
        return carritoParaMostrarVehiculos;
    }

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

}
