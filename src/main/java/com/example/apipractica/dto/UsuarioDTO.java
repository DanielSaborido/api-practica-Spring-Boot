package com.example.apipractica.dto;

import com.example.apipractica.modelo.Producto;
import com.example.apipractica.modelo.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class UsuarioDTO {
    Long id;
    String name;
    String email;
    List<Long> productos = new ArrayList<>();

    public UsuarioDTO(Usuario usuario){
        id = usuario.getId();
        name = usuario.getName();
        email = usuario.getEmail();
        for (Producto producto:
                usuario.getProductos()){
            productos.add(producto.getId());
        }
    }
}
