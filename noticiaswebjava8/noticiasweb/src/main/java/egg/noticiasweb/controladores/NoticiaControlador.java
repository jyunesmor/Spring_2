
package egg.noticiasweb.controladores;

import egg.noticiasweb.excepciones.MiException;
import egg.noticiasweb.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {
    
    @Autowired
    NoticiaServicio ns;
    
    @GetMapping("/registrar")
    public String registrar(){
        
        return "carganoticia.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String titulo,@RequestParam String contenido, ModelMap modelo){
        
        try {
            ns.crearNoticia(titulo,contenido);
            modelo.put("exito","La Noticia se cargo exitosamente");
        } catch (MiException ex) {
            modelo.put("error",ex.getMessage());
            return "redirect:/noticia/registrar";
        }
        return "redirect:/noticia/registrar"; // Volver al Inicio
    }
}
