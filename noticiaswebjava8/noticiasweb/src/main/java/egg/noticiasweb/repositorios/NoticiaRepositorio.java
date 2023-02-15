
package egg.noticiasweb.repositorios;

import egg.noticiasweb.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia,String> {

}
