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

import ies6.edu.ar.trabajofinal.trabajofinal.model.Conductor;
import ies6.edu.ar.trabajofinal.trabajofinal.service.ConductorServiceI;
import jakarta.validation.Valid;

@Controller
//@RequestMapping("/conductor")
public class ConductorController {
    @Qualifier ("servicioConductorMySQL")
    @Autowired
    ConductorServiceI conductorService;


    @GetMapping("/conductor")
    public ModelAndView getConductor() {

        ModelAndView carrito = new ModelAndView("conductor");
        carrito.addObject("nuevoConductor", conductorService.crearNuevoConductor());
        carrito.addObject("band", false);
        return carrito;
    }

    @PostMapping("/guardarConductor")
    public ModelAndView saveConductor(@Valid @ModelAttribute("nuevoConductor") Conductor conductorParaGuardar,
            BindingResult result) {
        System.out.println("estoy ingresando al metodo de guardar");
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("conductor");
            modelAndView.addObject("nuevoConductor", conductorParaGuardar);
        } else {
            try {
                conductorService.agregarConductor(conductorParaGuardar);
                modelAndView.setViewName("listaConductor");
                modelAndView.addObject("correcto", "¡Conductor registrado con éxito!");
            } catch (Exception e) {
                // Mensaje de ERROR
                modelAndView.addObject("errorConductor", "Error al guardar el conductor: " + e.getMessage());
            }
            modelAndView.addObject("lista", conductorService.listarTodosConductoresActivos());
            System.out.println("estoy saliendo al metodo de guardar");
        }

        return modelAndView;

    }

    @GetMapping("/eliminarConductor/{dni}")
    public ModelAndView eliminarConductor(@PathVariable(name = "dni") String dni) throws Exception {
        ModelAndView carritoDeEliminar = new ModelAndView("listaConductor");
        conductorService.borrarConductor(dni);
        carritoDeEliminar.addObject("lista", conductorService.listarTodosConductoresActivos());

        return carritoDeEliminar;
    }

    // MODIFICAR
    @GetMapping("/modificarConductor/{dni}")
    public ModelAndView buscarConductorParaModificar(@PathVariable(name = "dni") String dni) throws Exception {
        ModelAndView carritoParaModificarConductor = new ModelAndView("conductor");
        carritoParaModificarConductor.addObject("nuevoConductor", conductorService.buscarUnConductor(dni));
        carritoParaModificarConductor.addObject("band", true);
        return carritoParaModificarConductor;
    }

    @PostMapping("/modificarConductor")
    public ModelAndView modificarConductor(@ModelAttribute("nuevoConductor") Conductor conductorModificado) {
        ModelAndView listadoEditado = new ModelAndView("listaConductor");
        conductorService.agregarConductor(conductorModificado);
        listadoEditado.addObject("lista", conductorService.listarTodosConductoresActivos());

        return listadoEditado;
    }

    @GetMapping("/listarConductores")
    public ModelAndView listarConductoresActivos() {
        ModelAndView carritoParaMostrarConductor = new ModelAndView("listaConductor");
        carritoParaMostrarConductor.addObject("lista", conductorService.listarTodosConductoresActivos());

        return carritoParaMostrarConductor;
    }

   @GetMapping("/conductor/index")
    public String getIndex() {
        return "conductor";
    }
    
}