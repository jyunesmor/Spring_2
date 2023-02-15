package com.egg.noticias.controladores;

import com.egg.noticias.entidades.Noticia;
import com.egg.noticias.excepciones.MiException;
import com.egg.noticias.servicios.noticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noticia")
public class noticiaControlador {

    @Autowired
    noticiaServicio ns;

    @GetMapping("/carganoticia")
    public String index() {
        return "CargaNoticias.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String titulo, @RequestParam String contenido, ModelMap mod) {
       try{
             ns.CrearNoticia(titulo,contenido);   
             mod.put("exito","La Noticia se Cargo con exito");
       } catch (MiException ex) {
             mod.put("error",ex.getMessage());  
             return "redirect:/noticia/carganoticia";
        }
             return "redirect:/noticia/carganoticia"; // devolver el index
    }

    @GetMapping("/listar")
    public String listar(ModelMap mod) {
        List<Noticia> listado = ns.listarNoticias();
        mod.addAttribute("noticias",listado);
        return "form_listar.html";
    }
}
