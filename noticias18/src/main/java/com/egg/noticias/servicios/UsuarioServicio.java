/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.noticias.servicios;

import com.egg.noticias.entidades.Usuario;
import com.egg.noticias.enumeraciones.Rol;
import com.egg.noticias.excepciones.MiException;
import com.egg.noticias.repositorios.usuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    usuarioRepositorio urepo;

    @Transactional
    public void loguearse(String nombre, String email, String password, String password2) throws MiException {
        validar(nombre, email, password, password2);
        
        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setRol(Rol.USER);

        urepo.save(usuario);
    }

    private void validar(String nombre, String email, String password, String password2) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no debe estar Vacio");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("El E-mail no debe estar Vacio");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("El Password no debe estar Vacio, y no puede ser menor a 6 Digitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las Contraseñas no son iguales");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
           
        Usuario usuario = urepo.buscarPorEmail(email);
        
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();
           
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());
            permisos.add(p);
  
            return new User(usuario.getEmail(),usuario.getPassword(),permisos);
        } else {
            return null;
        }    
    }

}   




