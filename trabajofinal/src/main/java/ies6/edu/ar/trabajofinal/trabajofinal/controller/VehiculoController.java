package ies6.edu.ar.trabajofinal.trabajofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Vehiculo;

@Controller
public class VehiculoController {
    
    //atributos
    @Qualifier ("servicioVehiculoMysql")
    @Autowired
    VehiculoServiceI vehiculoService;


    @GetMapping("/vehiculo")
    public ModelAndView getvehiculo() { 

        //nuevoVehiculo.setNombre ("Geovana");
        //Vehiculo nuevoVehiculo = new Vehiculo();
       ModelAndView modelAndView =new ModelAndView("vehiculo");
       modelAndView.addObject("nuevoVehiculo", vehiculoService.crearNuevoVehiculo());
       modelAndView.addObject("bands", false);
        return modelAndView;
    }

    @PostMapping("/guardarVehiculo")
    public ModelAndView saveVehiculo (@ModelAttribute("nuevoVehiculo") Vehiculo vehiculoParaGuardar){
         
        vehiculoService.agregarVehiculo(vehiculoParaGuardar);

        ModelAndView modelAndView = new ModelAndView("listaVehiculo");
        modelAndView.addObject("listadoVehiculo", vehiculoService.listarTodosVehiculo());
        return modelAndView;
    }

    
    //eliminar
    @GetMapping("/eliminarVehiculo/{vehiculoId}")
    public ModelAndView eliminarVehiculo(@PathVariable("vehiculoId") Integer vehiculoId) {
        ModelAndView carritoDeEliminar = new ModelAndView("listaVehiculo");
        vehiculoService.borraVehiculo(vehiculoId);
        carritoDeEliminar.addObject("listadoVehiculo", vehiculoService.listarTodosVehiculo());
        return carritoDeEliminar;
    }

    
      //modificar
    @GetMapping("/modificarVehiculo/{vehiculoId}")
    public ModelAndView buscarVehiculoParaModificar(@PathVariable("vehiculoId") Integer vehiculoId) throws Exception {
        ModelAndView carritoParaModificarVehiculo = new ModelAndView("vehiculo");
        carritoParaModificarVehiculo.addObject("nuevoVehiculo", vehiculoService.buscarUnVehiculo(vehiculoId));
        carritoParaModificarVehiculo.addObject("band", true);
        return carritoParaModificarVehiculo;
    }

  @PostMapping("/modificarVehiculo")
    public ModelAndView modificarVehiculo(@ModelAttribute("nuevoVehiculo") Vehiculo vehiculoModificado){
        ModelAndView listadoEditado = new ModelAndView("listaVehiculo");
        vehiculoService.agregarVehiculo(vehiculoModificado);
        listadoEditado.addObject("listadoVehiculo", vehiculoService.listarTodosVehiculo());
        
        return listadoEditado;
    }

}
