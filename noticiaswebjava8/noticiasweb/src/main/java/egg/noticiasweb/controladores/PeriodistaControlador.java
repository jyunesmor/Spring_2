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
@RequestMapping("/periodista")
public class PeriodistaControlador {

    @Autowired
    PeriodistaServicio ps;

    @Autowired
    NoticiaServicio ns;

    @GetMapping("/registrar") // LocalHost:8080/periodista
    public String registrar() {
        return "CargaPeriodista.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String apellido_nombre,
            @RequestParam String documento, @RequestParam String mail,
            MultipartFile archivo, RedirectAttributes atrr) {

        try {
            ps.creaPeriodista(apellido_nombre, documento, mail, archivo);
            atrr.addFlashAttribute("exito", "El Periodista se Cargo exitosamente");
        } catch (MiException ex) {
            atrr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/periodista/registrar";
        }
        return "redirect:/periodista/registrar";
    }

    @GetMapping("/mostrarImagen/{id}")
    public String nodificarImagen(@PathVariable String id, ModelMap modelo) {
        modelo.put("periodista", ps.getOne(id));
        return "ModificarImagen.html";
    }

    @PostMapping("/modificarImagen/{id}")
    public String modificarImagen(@PathVariable String id,
            MultipartFile archivo, RedirectAttributes atrr) {
        try {
            ps.modificarImagen(id, archivo);
            atrr.addFlashAttribute("exito", "La Noticia se Modifico exitosamente");
            return "redirect:/noticia/listaNoti";
        } catch (MiException ex) {
            atrr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/noticia/listaNoti";
        }
    }

    @GetMapping("/mostrarNombre/{id}")
    public String modificarNombre(@PathVariable String id, ModelMap modelo) {
        modelo.put("periodista", ps.getOne(id));
        return "ModificarNombre.html";
    }

    @PostMapping("/modificarNombre/{id}")
    public String modificarContenido(@PathVariable String id,
            @RequestParam String nombre, RedirectAttributes atrr) {
        try {
            ps.modificarNombre(id, nombre);
            atrr.addFlashAttribute("exito", "La Noticia se Modifico exitosamente");
            return "redirect:/periodista/mostrarPeriodista/{id}";
        } catch (MiException ex) {
            atrr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/periodista/mostrarPeriodista/{id}";
        }
    }

    @GetMapping("/mostrarDocumento/{id}")
    public String modificarDocuemnto(@PathVariable String id, ModelMap modelo) {
        modelo.put("periodista", ps.getOne(id));
        return "ModificarDNI.html";
    }

    @PostMapping("/modificarDocumento/{id}")
    public String modificarDocumento(@PathVariable String id,
            @RequestParam String documento, RedirectAttributes atrr) {
        try {
            ps.modificarDocuemnto(id, documento);
            atrr.addFlashAttribute("exito", "La Noticia se Modifico exitosamente");
            return "redirect:/periodista/mostrarPeriodista/{id}";
        } catch (MiException ex) {
            atrr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/periodista/mostrarPeriodista/{id}";
        }
    }

    @GetMapping("/listaPeri")
    public String listar(ModelMap modelo) {
        List<Periodista> lista = ps.listarPeriodistas();
        lista.sort(comparadores.ordenarPorNombre);
        modelo.addAttribute("periodistas", lista);

        return "MostrarPeriodistas.html";
    }

    @GetMapping("/mostrarPeriodista/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("periodista", ps.getOne(id));
        return "MostrarPeriodista.html";
    }

    @GetMapping("/eliminarPeriodista/{id}")
    public String eliminarperiodista(@PathVariable String id, RedirectAttributes atrr) {
        try {
            ps.eliminarPeriodista(id);
            atrr.addFlashAttribute("exito", "La Noticia se Modifico exitosamente");
            return "redirect:/noticia/listaNoti";
        } catch (MiException ex) {
            atrr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/noticia/listaNoti";
        }
    }

    @GetMapping("/listaNotiPeri")
    public String listarNotiPerio(ModelMap modelo) {
        List<Noticia> lista = ns.listarnoticias();
        lista.sort(comparadores.ordenarPorFecha);
        modelo.addAttribute("noticias", lista);

        return "ListadoNoticiasPeriodistas.html";
    }

    @GetMapping("/mostrarNoticiaPeriodista/{id}")
    public String mostrarNotiPeri(@PathVariable String id, ModelMap modelo) {
        modelo.put("noticia", ns.getOne(id));
        return "MostrarNoticiaPeriodista.html";
    }

}
