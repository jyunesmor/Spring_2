
package egg.noticiasweb.servicios;

import egg.noticiasweb.entidades.Noticia;
import egg.noticiasweb.excepciones.MiException;
import egg.noticiasweb.repositorios.NoticiaRepositorio;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class NoticiaServicio {
    
    @Autowired
    NoticiaRepositorio nrepo;
  
   @Transactional
   public void crearNoticia(String titulo,String contenido) throws MiException{
       
       validar(titulo, contenido);
       
       Noticia noti = new Noticia();
       
       noti.setTitulo(titulo);
       noti.setContenido(contenido);
       
       noti.setAlta(new Date());
       
       nrepo.save(noti);
       
   }

   public List<Noticia> listarnoticias(){
       
       List<Noticia> noticias = nrepo.findAll();
             
       return noticias;
   }
   
   private void validar(String titulo, String contenido) throws MiException{
     
       if (titulo == null || titulo.isEmpty()) {
           throw new MiException("El titulo no debe estar vacio!!!");
       }
       if (contenido == null || contenido.isEmpty()) {
           throw new MiException("El contenido no debe estar vacio!!!");
       } 
   }
    
}
