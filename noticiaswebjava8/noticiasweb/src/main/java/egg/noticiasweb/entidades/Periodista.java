/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egg.noticiasweb.entidades;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Periodista {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    
    private String apellido_nombre;
    private String documento;
    private ArrayList<Noticia> misnoticias;
    private Integer salario;
    
    @OneToOne
    private Imagen imagen;
    

    public Periodista() {
    }

    public String getApellido_nombre() {
        return apellido_nombre;
    }

    public void setApellido_nombre(String apellido_nombre) {
        this.apellido_nombre = apellido_nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getId() {
        return id;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public ArrayList<Noticia> getMisnoticias() {
        return misnoticias;
    }

    public void setMisnoticias(ArrayList<Noticia> misnoticias) {
        this.misnoticias = misnoticias;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }
    
    
    
    
}
