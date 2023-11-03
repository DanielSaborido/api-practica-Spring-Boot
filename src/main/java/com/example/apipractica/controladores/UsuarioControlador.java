package com.example.apipractica.controladores;

import com.example.apipractica.modelo.Usuario;
import com.example.apipractica.repositorios.UsuarioRepositorio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioControlador {
    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioControlador(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @GetMapping("/")
    public List<Usuario> getUsuarios(){
        return usuarioRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public  Usuario getUsuario(@PathVariable Long id){
        return usuarioRepositorio.findById(id).orElse(null);
    }
}
