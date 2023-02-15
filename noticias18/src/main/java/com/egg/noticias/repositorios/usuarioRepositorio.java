
package com.egg.noticias.repositorios;

import com.egg.noticias.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface usuarioRepositorio extends JpaRepository<Usuario, String>
 {
    
    @Query("SELEC U FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email")String email);


}
