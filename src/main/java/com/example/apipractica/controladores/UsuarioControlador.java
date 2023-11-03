package com.example.apipractica.controladores;

import com.example.apipractica.modelo.Usuario;
import com.example.apipractica.repositorios.UsuarioRepositorio;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/")
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario){
        return usuarioRepositorio.save(usuario);
    }
}
