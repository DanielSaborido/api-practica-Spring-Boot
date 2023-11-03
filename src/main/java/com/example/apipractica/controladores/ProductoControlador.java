package com.example.apipractica.controladores;

import com.example.apipractica.modelo.Producto;
import com.example.apipractica.modelo.Usuario;
import com.example.apipractica.repositorios.ProductoRepositorio;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoControlador {
    private final ProductoRepositorio productoRepositorio;

    public ProductoControlador(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @GetMapping("/")
    public List<Producto> getProductos(){
        return productoRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Long id){
        return productoRepositorio.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Producto createUsuario(@Valid @RequestBody Producto producto){
        return productoRepositorio.save(producto);
    }
}
