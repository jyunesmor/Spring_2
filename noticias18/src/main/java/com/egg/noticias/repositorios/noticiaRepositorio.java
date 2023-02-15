/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.noticias.repositorios;

import com.egg.noticias.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author home
 */
@Repository
public interface noticiaRepositorio extends JpaRepository<Noticia,String>{
    
    /*@Query("SELECT n FROM NOTICIA n WHERE n.titulo = :titulo")
    public Noticia BuscarNoticiaPorTitulo(@Param("titulo") String titulo);*/
    
}
