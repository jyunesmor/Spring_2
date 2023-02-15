package com.egg.noticias.servicios;


import com.egg.noticias.excepciones.MiException;
import com.egg.noticias.entidades.Noticia;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egg.noticias.repositorios.noticiaRepositorio;

@Service
public class noticiaServicio {

    @Autowired
    noticiaRepositorio notrepo;

    /* intanciamos el repositorio una sola vez */

    @Transactional
    public void CrearNoticia(String titulo, String contenido) throws MiException {

        if(titulo.isEmpty() || titulo == null){
           throw new MiException("El titulo no puede estar Vacio");
        }
        if(contenido.isEmpty() || contenido == null){
            throw new MiException("El contenido no puede estar Vacio");
        }
        // intanciamos la noticia y cargamos los parametros
        Noticia not = new Noticia();
        not.setTitulo(titulo);
        not.setContenido(contenido);
        not.setFecha(new Date());
     
        notrepo.save(not);
    }

    @Transactional
    public void EliminarNoticia(String id) {
        Noticia not = notrepo.findById(id).get();
        notrepo.delete(not);
    }

    public List<Noticia> listarNoticias() {
        List<Noticia> listado = notrepo.findAll();
        return listado;
    }

}
