package com.egg.noticias.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class portalControlador {

    @GetMapping("/principal")
    public String principal() {
        return "form_inicio.html";
    }

}
