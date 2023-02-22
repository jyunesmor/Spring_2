/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egg.noticiasweb.servicios;

import egg.noticiasweb.entidades.Imagen;
import egg.noticiasweb.entidades.Noticia;
import egg.noticiasweb.entidades.Periodista;
import egg.noticiasweb.excepciones.MiException;
import egg.noticiasweb.repositorios.PeriodistaRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PeriodistaServicio {

    @Autowired
    PeriodistaRepositorio prepo;

    @Autowired
    ImagenServicio is;

    @Transactional
    public void creaPeriodista(String apellido_nombre, String documento, String mail, MultipartFile archivo) throws MiException {

        validar(apellido_nombre, documento, mail);

        Periodista periodista = new Periodista();

        periodista.setApellido_nombre(apellido_nombre);
        periodista.setDocumento(documento);
        periodista.setMail(mail);

        Imagen img = is.guardarImagen(archivo);
        periodista.setImagen(img);
        prepo.save(periodista);

    }

    public List<Periodista> listarPeriodistas() {

        List<Periodista> lista = prepo.findAll();

        return lista;
    }

    private void validar(String nombre_apellido, String documento, String mail) throws MiException {

        if (nombre_apellido == null || nombre_apellido.isEmpty()) {
            throw new MiException("El nombre y apellido no debe estar vacio!!!");
        }
        if (documento == null || documento.isEmpty()) {
            throw new MiException("El Documento no debe estar vacio!!!");
        }
        if (mail == null || mail.isEmpty()) {
            throw new MiException("El E-Mail no debe estar vacio!!!");
        }
    }

    public Periodista getOne(String id) {
        Periodista p = prepo.getOne(id);
        return p;
    }

    public void modificarImagen(String id, MultipartFile archivo) throws MiException {

        Optional<Periodista> respuesta = prepo.findById(id);
        if (respuesta.isPresent()) {
            Periodista p = respuesta.get();

            String idImagen = null;
            if (p.getImagen() != null) {
                idImagen = p.getImagen().getId();
            }
            Imagen img = is.actualizarImagen(idImagen, archivo);
            p.setImagen(img);
            prepo.save(p);
        }
    }

    public void modificarNombre(String id, String nombre) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El Nombre no debe estar vacio!!!");
        }

        Optional<Periodista> respuesta = prepo.findById(id);
        if (respuesta.isPresent()) {

            Periodista p = respuesta.get();
            p.setApellido_nombre(nombre);

            prepo.save(p);
        }
    }

    public void modificarMail(String id, String mail) throws MiException {

        if (mail == null || mail.isEmpty()) {
            throw new MiException("El E-mail no debe estar vacio!!!");
        }

        Optional<Periodista> respuesta = prepo.findById(id);
        if (respuesta.isPresent()) {

            Periodista p = respuesta.get();
            p.setMail(mail);

            prepo.save(p);
        }

    }

    public void modificarDocuemnto(String id, String documento) throws MiException {

        if (documento == null || documento.isEmpty()) {
            throw new MiException("El Documento no debe estar vacio!!!");
        }

        Optional<Periodista> respuesta = prepo.findById(id);
        if (respuesta.isPresent()) {

            Periodista p = respuesta.get();
            p.setDocumento(documento);

            prepo.save(p);
        }

    }

    public void eliminarPeriodista(String id) throws MiException{

        Optional<Periodista> respuesta = prepo.findById(id);
        
        if (respuesta.isPresent()) {

            Periodista p = respuesta.get();
            
            prepo.delete(p);
        }

    }
}
