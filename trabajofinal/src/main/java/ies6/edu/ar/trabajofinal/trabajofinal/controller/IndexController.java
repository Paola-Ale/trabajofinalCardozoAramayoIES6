package ies6.edu.ar.trabajofinal.trabajofinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {
    @GetMapping("/")
    public String getVehiculo() {
        return "index";
    }
    
}