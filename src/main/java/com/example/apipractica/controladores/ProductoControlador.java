package com.example.apipractica.controladores;

import com.example.apipractica.modelo.Producto;
import com.example.apipractica.repositorios.ProductoRepositorio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
