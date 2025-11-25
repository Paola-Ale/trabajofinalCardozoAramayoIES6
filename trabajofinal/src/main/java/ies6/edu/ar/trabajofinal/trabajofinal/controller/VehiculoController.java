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

import ies6.edu.ar.trabajofinal.trabajofinal.model.Conductor;
import ies6.edu.ar.trabajofinal.trabajofinal.service.ConductorServiceI;
import ies6.edu.ar.trabajofinal.trabajofinal.service.VehiculoServiceI;
import jakarta.validation.Valid;

@Controller

public class VehiculoController {
    @Qualifier("servicioVehiculoMySQL")
    @Autowired
    VehiculoServiceI vehiculoService;
    @Qualifier("servicioConductorMySQL")
    @Autowired
    ConductorServiceI conductorService;

    @GetMapping("/vehiculo")
    public ModelAndView getVehiculo() {

        ModelAndView carrito = new ModelAndView("vehiculo");
        carrito.addObject("nuevoVehiculo", vehiculoService.crearNuevoVehiculo());
        carrito.addObject("band", false);
        // AÑADIDO: Pasar la lista de conductores a la vista 'vehiculo.html'
        carrito.addObject("listaConductores", conductorService.listarTodosConductoresActivos());
        return carrito;
    }

    @PostMapping("/guardarVehiculo")
    public ModelAndView saveVehiculo(@Valid @ModelAttribute("nuevoVehiculo") Vehiculo vehiculoParaGuardar,
            BindingResult result,
            Model model) {

        System.out.println("estoy ingresando al metodo de guardar");
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {

            modelAndView.setViewName("vehiculo");
            modelAndView.addObject("nuevoVehiculo", vehiculoParaGuardar);
            // Recargar la lista de conductores
            modelAndView.addObject("listaConductores", conductorService.listarTodosConductor());
            return modelAndView;
        }

        Conductor conductorFormulario = vehiculoParaGuardar.getConductor();
        boolean conductorAsignadoExitosamente = false;

        if (conductorFormulario != null &&
                conductorFormulario.getDni() != null &&
                !conductorFormulario.getDni().isEmpty()) {

            String dniSeleccionado = conductorFormulario.getDni();
            Conductor conductorEncontrado = null;

            try {
                conductorEncontrado = conductorService.buscarUnConductor(dniSeleccionado);
            } catch (Exception e) {
                System.err.println("Error al buscar conductor por DNI: " + e.getMessage());
                modelAndView.addObject("errorVehiculo",
                        "Error de búsqueda: El DNI seleccionado no es válido o hay un error en el servidor. "
                                + e.getMessage());
            }

            if (conductorEncontrado != null) {
                vehiculoParaGuardar.setConductor(conductorEncontrado);
                conductorAsignadoExitosamente = true;
            } else {
                vehiculoParaGuardar.setConductor(null);

                if (modelAndView.getModel().containsKey("errorVehiculo")) {
                    modelAndView.setViewName("vehiculo");
                    modelAndView.addObject("nuevoVehiculo", vehiculoParaGuardar);
                    modelAndView.addObject("listaConductores", conductorService.listarTodosConductor());
                    return modelAndView;
                }
            }
        } else {
            vehiculoParaGuardar.setConductor(null);
        }

        try {
            vehiculoService.agregarVehiculo(vehiculoParaGuardar);

            modelAndView.setViewName("redirect:/listaVehiculos");

            System.out.println("Vehiculo guardado con éxito. Conductor asignado: " + conductorAsignadoExitosamente);

        } catch (Exception e) {
            modelAndView.addObject("errorVehiculo", "Error al guardar el vehiculo: " + e.getMessage());

            modelAndView.setViewName("vehiculo");
            modelAndView.addObject("nuevoVehiculo", vehiculoParaGuardar);
            modelAndView.addObject("listaConductores", conductorService.listarTodosConductor());
            System.out.println("Error de persistencia: " + e.getMessage());
        }

        return modelAndView;
    }

    @GetMapping("/eliminarVehiculo/{vehiculoId}")
    public ModelAndView eliminarVehiculo(@PathVariable(name = "vehiculoId") Integer vehiculoId) throws Exception {
        ModelAndView carritoDeEliminar = new ModelAndView("listaVehiculo");
        vehiculoService.borrarVehiculo(vehiculoId);
        carritoDeEliminar.addObject("lista", vehiculoService.listarTodosVehiculosActivos());

        return carritoDeEliminar;
    }

    // MODIFICAR
    @GetMapping("/modificarVehiculo/{vehiculoId}")
    public ModelAndView buscarVehiculoParaModificar(@PathVariable(name = "vehiculoId") Integer vehiculoId)
            throws Exception {
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

    @GetMapping("/listaVehiculos")
    public ModelAndView listarVehiculosActivos() {
        ModelAndView carritoParaMostrarVehiculos = new ModelAndView("listaVehiculo");
        carritoParaMostrarVehiculos.addObject("lista", vehiculoService.listarTodosVehiculosActivos());

        return carritoParaMostrarVehiculos;
    }

    @GetMapping("/vehiculo/index")
    public String getIndex() {
        return "vehiculo";
    }

}
