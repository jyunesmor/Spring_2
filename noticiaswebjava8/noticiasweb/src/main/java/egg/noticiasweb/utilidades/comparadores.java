/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egg.noticiasweb.utilidades;

import egg.noticiasweb.entidades.Noticia;
import egg.noticiasweb.entidades.Periodista;
import java.util.Comparator;

/**
 *
 * @author home
 */
public class comparadores {

    public static Comparator<Noticia> ordenarPorFecha = new Comparator<Noticia>() {
      
        @Override
        public int compare(Noticia t, Noticia t1) {
            
            return t1.getAlta().compareTo(t.getAlta());
        }
    };
    
       public static Comparator<Periodista> ordenarPorNombre = new Comparator<Periodista>() {
        @Override
        public int compare(Periodista t, Periodista t1) {
             return t.getApellido_nombre().compareTo(t1.getApellido_nombre());
        }
       }; 
    
}
