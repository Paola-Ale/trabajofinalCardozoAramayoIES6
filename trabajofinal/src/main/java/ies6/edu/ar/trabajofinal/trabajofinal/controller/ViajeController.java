package ies6.edu.ar.trabajofinal.trabajofinal.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ies6.edu.ar.trabajofinal.trabajofinal.model.Usuario;
import ies6.edu.ar.trabajofinal.trabajofinal.model.Vehiculo;
import ies6.edu.ar.trabajofinal.trabajofinal.model.Viaje;
import ies6.edu.ar.trabajofinal.trabajofinal.service.ViajeServiceI;
import jakarta.validation.Valid;
import ies6.edu.ar.trabajofinal.trabajofinal.service.VehiculoServiceI;
import ies6.edu.ar.trabajofinal.trabajofinal.service.UsuarioServiceI;

@Controller
public class ViajeController {

    @Qualifier("servicioViajeMySQL")
    @Autowired
    ViajeServiceI viajeService;

    @Qualifier("servicioUsuarioMySQL")
    @Autowired
    UsuarioServiceI usuarioService;

    @Qualifier("servicioVehiculoMySQL")
    @Autowired
    VehiculoServiceI vehiculoService;

    @GetMapping("/Viaje")
    public ModelAndView getViaje() {
        ModelAndView carrito = new ModelAndView("Viaje");
        carrito.addObject("usuarios", usuarioService.listarTodosUsuarios());
        carrito.addObject("vehiculos", vehiculoService.listarTodosVehiculos());
        carrito.addObject("listaVehiculos", vehiculoService.listarTodosVehiculosActivos());
        carrito.addObject("band", false);
        return carrito;
    }

    @PostMapping("/guardarViaje")
    public ModelAndView guardarViaje(
            @Valid @ModelAttribute("nuevoViaje") Viaje viajeParaGuardar,
            BindingResult result) {

        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("viaje");
            modelAndView.addObject("nuevoViaje", viajeParaGuardar);
        } else {
            try {

                Usuario usuarioBD = usuarioService.buscarUnUsuario(
                        viajeParaGuardar.getUsuario().getDni());
                viajeParaGuardar.setUsuario(usuarioBD);

                Vehiculo vehiculoBD = vehiculoService.buscarUnVehiculo(
                        viajeParaGuardar.getVehiculo().getVehiculoId());
                viajeParaGuardar.setVehiculo(vehiculoBD);

                Double costoCalculado = viajeService.calcularCosto(
                        vehiculoBD.getTipoVehiculo(),
                        viajeParaGuardar.getTipoViaje());
                viajeParaGuardar.setCosto(costoCalculado);

                viajeParaGuardar.setFecha(LocalDate.now());
                viajeParaGuardar.setEstado(true);

                viajeService.agregarViaje(viajeParaGuardar);

                modelAndView.setViewName("listaViajes");
                modelAndView.addObject("correcto", "¡Viaje registrado con éxito!");

            } catch (Exception e) {
                modelAndView.setViewName("viaje");
                modelAndView.addObject("errorViaje", "Error al guardar el viaje: " + e.getMessage());
            }
        }

        modelAndView.addObject("lista", viajeService.listarTodosViajesActivos());
        return modelAndView;
    }

    @GetMapping("/listarViajes")
    public ModelAndView listarViajes() {
        ModelAndView vista = new ModelAndView("listaViajes");
        vista.addObject("lista", viajeService.listarTodosViajesActivos());
        return vista;
    }

    @GetMapping("/eliminarViaje/{viajeId}")
    public ModelAndView eliminarViaje(@PathVariable(name = "viajeId") Integer viajeId) throws Exception {
        ModelAndView vista = new ModelAndView("listaViajes");
        viajeService.borrarViaje(viajeId);
        vista.addObject("lista", viajeService.listarTodosViajesActivos());
        return vista;
    }

    @GetMapping("/modificarViaje/{viajeId}")
    public ModelAndView buscarViajeParaModificar(@PathVariable(name = "viajeId") Integer viajeId) throws Exception {
        ModelAndView carritoParaModificarViaje = new ModelAndView("viaje");

        carritoParaModificarViaje.addObject("nuevoViaje", viajeService.buscarUnViaje(viajeId));
        carritoParaModificarViaje.addObject("usuarios", usuarioService.listarTodosUsuariosActivos());
        carritoParaModificarViaje.addObject("vehiculos", vehiculoService.listarTodosVehiculosActivos());
        carritoParaModificarViaje.addObject("band", true);

        return carritoParaModificarViaje;
    }

    @PostMapping("/modificarViaje")
    public ModelAndView modificarViaje(Viaje viajeModificado) {

        ModelAndView vista = new ModelAndView("listaViajes");

        try {
            Viaje viajeBD = viajeService.buscarUnViaje(viajeModificado.getViajeId());

            Usuario usuarioBD = usuarioService.buscarUnUsuario(
                    viajeModificado.getUsuario().getDni());
            viajeBD.setUsuario(usuarioBD);

            Vehiculo vehiculoBD = vehiculoService.buscarUnVehiculo(
                    viajeModificado.getVehiculo().getVehiculoId());
            viajeBD.setVehiculo(vehiculoBD);

            viajeBD.setTipoViaje(viajeModificado.getTipoViaje());

            Double nuevoCosto = viajeService.calcularCosto(
                    vehiculoBD.getTipoVehiculo(),
                    viajeModificado.getTipoViaje());
            viajeBD.setCosto(nuevoCosto);

            viajeService.modificarViaje(viajeBD);

            vista.addObject("correcto", "¡Viaje modificado con éxito!");

        } catch (Exception e) {
            vista.addObject("error", "Error al modificar el viaje: " + e.getMessage());
        }

        vista.addObject("lista", viajeService.listarTodosViajesActivos());
        return vista;
    }

}