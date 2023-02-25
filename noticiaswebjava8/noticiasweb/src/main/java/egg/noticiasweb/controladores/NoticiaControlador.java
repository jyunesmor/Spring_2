package egg.noticiasweb.controladores;

import egg.noticiasweb.entidades.Noticia;
import egg.noticiasweb.entidades.Periodista;
import egg.noticiasweb.excepciones.MiException;
import egg.noticiasweb.servicios.NoticiaServicio;
import egg.noticiasweb.servicios.PeriodistaServicio;
import egg.noticiasweb.utilidades.comparadores;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {

    @Autowired
    NoticiaServicio ns;

    @Autowired
    PeriodistaServicio ps;

    @GetMapping("/registrar")
    public String registrar(ModelMap m) {

        List<Periodista> listaPeri = ps.listarPeriodistas();

        m.addAttribute("lista", listaPeri);

        return "CargaNoticias.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String titulo,
            @RequestParam String contenido, @RequestParam String idCreador,
            MultipartFile archivo, RedirectAttributes atrr) {

        // cuando pasamos modelo para error no debemos redirigir (Ver RedirectAttributes)
        try {
            ns.crearNoticia(titulo, contenido, idCreador, archivo);
            atrr.addFlashAttribute("exito", "La Noticia se cargo exitosamente");
        } catch (MiException ex) {
            atrr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/noticia/registrar";
        }
        return "redirect:/noticia/listaNoti"; // Volver al Inicio
    }

    @GetMapping("/listaNoti")
    public String listar(ModelMap modelo) {
        List<Noticia> lista = ns.listarnoticias();
        lista.sort(comparadores.ordenarPorFecha);
        modelo.addAttribute("noticias", lista);

        return "ListadoNoticias.html";
    }

    @GetMapping("/mostrarNoticia/{id}")
    public String nodificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("noticia", ns.getOne(id));
        return "MostrarNoticia.html";
    }

    @GetMapping("/mostrarTitulo/{id}")
    public String modificarTitulo(@PathVariable String id, ModelMap modelo) {
        modelo.put("noticia", ns.getOne(id));
        return "ModificarTituloNoticia.html";
    }

    @PostMapping("/modificarTitulo/{id}")
    public String nodificar(@PathVariable String id,
            @RequestParam String titulo, RedirectAttributes atrr) {
        try {
            ns.modificarTitulo(id, titulo);
            atrr.addFlashAttribute("exito", "La Noticia se Modifico exitosamente");
            return "redirect:/noticia/mostrarNoticia/{id}";
        } catch (MiException ex) {
            atrr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/noticia/listaNoti";
        }
    }

    @GetMapping("/mostrarContenido/{id}")
    public String nodificarContenido(@PathVariable String id, ModelMap modelo) {
        modelo.put("noticia", ns.getOne(id));
        return "ModificarContenido.html";
    }

    @PostMapping("/modificarContenido/{id}")
    public String modificarContenido(@PathVariable String id,
            @RequestParam String contenido, RedirectAttributes atrr) {
        try {
            ns.modificarContenido(id, contenido);
            atrr.addFlashAttribute("exito", "La Noticia se Modifico exitosamente");
            return "redirect:/noticia/listaNoti";
        } catch (MiException ex) {
            atrr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/noticia/listaNoti";
        }
    }

    @GetMapping("/mostrarPeriodista/{id}")
    public String nodificarPeriodista(@PathVariable String id, ModelMap modelo) {
        modelo.put("noticia", ns.getOne(id));

        List<Periodista> listaPeri = ps.listarPeriodistas();

        modelo.addAttribute("lista", listaPeri);
        return "ModificarPeriodista.html";
    }

    @PostMapping("/modificarPeriodista/{id}")
    public String modificarPeriodista(@PathVariable String id,
            @RequestParam String idCreador, RedirectAttributes atrr) {
        try {
            ns.modificarPeriodista(id, idCreador);
            atrr.addFlashAttribute("exito", "La Noticia se Modifico exitosamente");
            return "redirect:/noticia/listaNoti";
        } catch (MiException ex) {
            atrr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/noticia/listaNoti";
        }
    }

    @GetMapping("/mostrarImagen/{id}")
    public String nodificarImagen(@PathVariable String id, ModelMap modelo) {
        modelo.put("noticia", ns.getOne(id));
        return "ModificarImagen.html";
    }

    @PostMapping("/modificarImagen/{id}")
    public String modificarImagen(@PathVariable String id,
            MultipartFile archivo, RedirectAttributes atrr) {
        try {
            ns.modificarImagen(id, archivo);
            atrr.addFlashAttribute("exito", "La Noticia se Modifico exitosamente");
            return "redirect:/noticia/listaNoti";
        } catch (MiException ex) {
            atrr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/noticia/listaNoti";
        }
    }

    @GetMapping("/eliminarNoticia/{id}")
    public String eliminarNoticia(@PathVariable String id, RedirectAttributes atrr) {
       try{
           ns.eliminarNoticia(id);
           atrr.addFlashAttribute("exito", "La Noticia se Modifico exitosamente");
            return "redirect:/noticia/listaNoti";
        } catch (MiException ex) {
            atrr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/noticia/listaNoti";
        }
    }

}
