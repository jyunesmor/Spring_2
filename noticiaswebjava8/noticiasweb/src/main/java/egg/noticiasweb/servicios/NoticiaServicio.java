package egg.noticiasweb.servicios;

import egg.noticiasweb.entidades.Imagen;
import egg.noticiasweb.entidades.Noticia;
import egg.noticiasweb.entidades.Periodista;
import egg.noticiasweb.excepciones.MiException;
import egg.noticiasweb.repositorios.ImagenRepositorio;
import egg.noticiasweb.repositorios.NoticiaRepositorio;
import egg.noticiasweb.repositorios.PeriodistaRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NoticiaServicio {

    @Autowired
    NoticiaRepositorio nrepo;

    @Autowired
    PeriodistaRepositorio prepo;

    @Autowired
    ImagenRepositorio irepo;

    @Autowired
    ImagenServicio is;

    @Transactional
    public void crearNoticia(String titulo, String contenido, String idCreador, MultipartFile archivo) throws MiException {

        validar(titulo, contenido, idCreador);

        Periodista creador = prepo.findById(idCreador).get();

        Noticia noti = new Noticia();

        noti.setTitulo(titulo);
        noti.setContenido(contenido);
        noti.setIdCreador(creador);

        Imagen img = is.guardarImagen(archivo);
        noti.setIdImagen(img);

        noti.setAlta(new Date());

        nrepo.save(noti);

    }

    public List<Noticia> listarnoticias() {

        List<Noticia> noticias = nrepo.findAll();

        return noticias;
    }

    public Noticia getOne(String id) {
        return nrepo.getOne(id);
    }

    public void modificarNoticia(String id, String titulo,
            String contenido, String idCreador, MultipartFile archivo) throws MiException {

        validar(titulo, contenido, idCreador);
        Optional<Noticia> respuesta = nrepo.findById(id);
        if (respuesta.isPresent()) {
            Periodista periodista = prepo.findById(idCreador).get();
            Noticia not = respuesta.get();
            not.setTitulo(titulo);
            not.setContenido(contenido);
            not.setIdCreador(periodista);

            String idImagen = null;
            if (not.getIdImagen() != null) {
                idImagen = not.getIdImagen().getId();
            }
            Imagen img = is.actualizarImagen(idImagen, archivo);
            not.setIdImagen(img);
            nrepo.save(not);
        }

    }

    public void modificarTitulo(String id, String titulo) throws MiException {

        if (titulo == null || titulo.isEmpty()) {
            throw new MiException("El titulo no debe estar vacio!!!");
        }

        Optional<Noticia> respuesta = nrepo.findById(id);
        if (respuesta.isPresent()) {

            Noticia not = respuesta.get();
            not.setTitulo(titulo);

            nrepo.save(not);
        }

    }

    public void modificarContenido(String id, String contenido) throws MiException {

        if (contenido == null || contenido.isEmpty()) {
            throw new MiException("El titulo no debe estar vacio!!!");
        }

        Optional<Noticia> respuesta = nrepo.findById(id);
        if (respuesta.isPresent()) {

            Noticia not = respuesta.get();
            not.setContenido(contenido);

            nrepo.save(not);
        }

    }

    public void modificarPeriodista(String id, String idCreador) throws MiException {

        if (idCreador == null || idCreador.isEmpty()) {
            throw new MiException("El contenido no debe estar vacio!!!");
        }

        Optional<Noticia> respuesta = nrepo.findById(id);
        if (respuesta.isPresent()) {
            Periodista periodista = prepo.findById(idCreador).get();
            Noticia not = respuesta.get();

            not.setIdCreador(periodista);

            nrepo.save(not);
        }
    }

    public void modificarImagen(String id, MultipartFile archivo) throws MiException {

        Optional<Noticia> respuesta = nrepo.findById(id);
        if (respuesta.isPresent()) {
            Noticia not = respuesta.get();

            String idImagen = null;
            if (not.getIdImagen() != null) {
                idImagen = not.getIdImagen().getId();
            }
            Imagen img = is.actualizarImagen(idImagen, archivo);
            not.setIdImagen(img);
            nrepo.save(not);
        }

    }

    private void validar(String titulo, String contenido, String idCreador) throws MiException {

        if (titulo == null || titulo.isEmpty()) {
            throw new MiException("El titulo no debe estar vacio!!!");
        }
        if (contenido == null || contenido.isEmpty()) {
            throw new MiException("El contenido no debe estar vacio!!!");
        }
        if (idCreador == null || idCreador.isEmpty()) {
            throw new MiException("El contenido no debe estar vacio!!!");
        }
    }

    public void eliminarNoticia(String id) throws MiException{

        Optional<Noticia> respuesta = nrepo.findById(id);
        
        if (respuesta.isPresent()) {

            Noticia not = respuesta.get();
            
            nrepo.delete(not);
        }

    }

}
