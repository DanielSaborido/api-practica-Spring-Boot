package com.example.apipractica.controladores;

import com.example.apipractica.error.UsuarioNotFoundException;
import com.example.apipractica.modelo.Usuario;
import com.example.apipractica.repositorios.UsuarioRepositorio;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioControlador {
    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioControlador(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @GetMapping
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

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario){
        return usuarioRepositorio.findById(id)
                .map(existingUsuario -> {
                    existingUsuario.setName(usuario.getName());
                    existingUsuario.setEmail(usuario.getEmail());
                    return usuarioRepositorio.save(existingUsuario);
                })
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        return usuarioRepositorio.findById(id)
                .map(existingUsuario -> {
                    usuarioRepositorio.delete(existingUsuario);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }
}
