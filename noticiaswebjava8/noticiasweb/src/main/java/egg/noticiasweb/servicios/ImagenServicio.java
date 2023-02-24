/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egg.noticiasweb.servicios;

import egg.noticiasweb.entidades.Imagen;
import egg.noticiasweb.excepciones.MiException;
import egg.noticiasweb.repositorios.ImagenRepositorio;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {

    @Autowired
    ImagenRepositorio irepo;

    @Transactional
    public Imagen guardarImagen(MultipartFile archivo) throws MiException {
     if (archivo != null) {
          try {
               Imagen img = new Imagen();
               img.setMime(archivo.getContentType());
               img.setNombre(archivo.getName());
               img.setContenido(archivo.getBytes());
              return irepo.save(img);
          } catch (IOException ex) {
               throw new MiException("No puede Procesarse la Carga de la imagen");
          }
     }
                 return null;
     }

    @Transactional
    public Imagen actualizarImagen(String idImagen, MultipartFile archivo) throws MiException {

        if (archivo != null) {
            try {
                Imagen img = new Imagen();
                if (idImagen != null) {
                    Optional<Imagen> foto = irepo.findById(idImagen);
                    if (foto.isPresent()) {
                        img = foto.get();
                    }
                }
                img.setMime(archivo.getContentType());
                img.setNombre(archivo.getName());
                img.setContenido(archivo.getBytes());
                return irepo.save(img);
            } catch (IOException ex) {
                throw new MiException("No puede Procesarse la Carga de la imagen");
            }
        }
        return null;
    }
}
