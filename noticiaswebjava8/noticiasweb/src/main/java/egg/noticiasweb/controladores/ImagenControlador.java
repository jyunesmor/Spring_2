/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egg.noticiasweb.controladores;

import egg.noticiasweb.entidades.Noticia;
import egg.noticiasweb.entidades.Periodista;
import egg.noticiasweb.servicios.ImagenServicio;
import egg.noticiasweb.servicios.NoticiaServicio;
import egg.noticiasweb.servicios.PeriodistaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {
    
    @Autowired
    NoticiaServicio ns;
    
    @Autowired
    PeriodistaServicio ps;
    
    @GetMapping("/imgNoti/{id}")
    public ResponseEntity<byte[]> imagenNoticia(@PathVariable String id){
        
        Noticia not = ns.getOne(id);
        byte[] imagen = not.getIdImagen().getContenido();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(imagen,headers,HttpStatus.OK);
    }
    
    
    
        @GetMapping("/imgperio/{id}")
    public ResponseEntity<byte[]> imagenperiodista(@PathVariable String id){
        
        Periodista p = ps.getOne(id);
        byte[] imagen = p.getImagen().getContenido();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(imagen,headers,HttpStatus.OK);
    }
}
