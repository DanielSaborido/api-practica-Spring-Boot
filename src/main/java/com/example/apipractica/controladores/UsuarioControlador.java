package com.example.apipractica.controladores;

import com.example.apipractica.dto.UsuarioDTO;
import com.example.apipractica.error.UsuarioNotFoundException;
import com.example.apipractica.modelo.Usuario;
import com.example.apipractica.repositorios.UsuarioRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public List<UsuarioDTO> getUsuarios(){
        List<UsuarioDTO> resultado = new ArrayList<>();
        for (Usuario usuario: usuarioRepositorio.findAll()) resultado.add(new UsuarioDTO(usuario));
        return resultado;
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
