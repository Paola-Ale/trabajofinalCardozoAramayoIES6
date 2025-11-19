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
    public ModelAndView getVehiculo() {
        ModelAndView carrito = new ModelAndView("vehiculo");
        carrito.addObject("nuevoVehiculo", vehiculoService.crearNuevoVehiculo());
        carrito.addObject("tipos", List.of("X", "LUXE", "PREMIUM"));
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
                modelAndView.setViewName("listaVehiculo");
                modelAndView.addObject("correcto", "¡Vehiculo registrado con éxito!");
            } catch (Exception e) {
                // Mensaje de ERROR
                modelAndView.addObject("errorVehiculo", "Error al guardar el vehiculo: " + e.getMessage());
            }
            modelAndView.addObject("lista", vehiculoService.listarTodosVehiculosActivos());
            System.out.println("estoy saliendo al metodo de guardar");
        }

        return modelAndView;

    }

    @GetMapping("/eliminarVehiculo/{vehiculoId}")
    public ModelAndView eliminarVehiculo(@PathVariable(name = "vehiculoId") Integer vehiculoId) throws Exception {
        ModelAndView carritoDeEliminar = new ModelAndView("listaVehiculo");
        vehiculoService.borrarVehiculo(vehiculoId);
        carritoDeEliminar.addObject("lista", vehiculoService.listarTodosVehiculos());

        return carritoDeEliminar;
    }


    // MODIFICAR
    @GetMapping("/modificarVehiculo/{vehiculoId}")
    public ModelAndView buscarVehiculoParaModificar(@PathVariable(name = "vehiculoId") Integer vehiculoId) throws Exception {
        ModelAndView carritoParaModificarVehiculo = new ModelAndView("vehiculo");
        carritoParaModificarVehiculo.addObject("nuevoVehiculo", vehiculoService.buscarUnVehiculo(vehiculoId));
        carritoParaModificarVehiculo.addObject("band", true);
        return carritoParaModificarVehiculo;
    }

    @PostMapping("/modificarVehiculo")
    public ModelAndView modificarVehiculo(@ModelAttribute("nuevoVehiculo") Vehiculo vehiculoModificado) {
        ModelAndView listadoEditado = new ModelAndView("listaVehiculo");
        vehiculoService.agregarVehiculo(vehiculoModificado);
        listadoEditado.addObject("lista", vehiculoService.listarTodosVehiculosActivos());

        return listadoEditado;
    }

    @GetMapping("/listaVehiculo")
    public ModelAndView listarVehiculoActivos() {
        ModelAndView carritoParaMostrarVehiculos = new ModelAndView("listaVehiculo");
        carritoParaMostrarVehiculos.addObject("lista", vehiculoService.listarTodosVehiculosActivos());
        return carritoParaMostrarVehiculos;
    }

    @GetMapping("/vehiculo/index")
    public String getIndex() {
        return "vehiculoIndex";
    }

}
