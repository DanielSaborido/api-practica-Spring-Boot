package com.example.apipractica.controladores;

import com.example.apipractica.modelo.Producto;
import com.example.apipractica.repositorios.ProductoRepositorio;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/producto")
public class ProductoControlador {
    private final ProductoRepositorio productoRepositorio;

    public ProductoControlador(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @GetMapping
    public List<Producto> getProductos(){
        return productoRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Long id){
        return productoRepositorio.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Producto createProducto(@Valid @RequestBody Producto producto){
        return productoRepositorio.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @Valid @RequestBody Producto producto){
        return productoRepositorio.findById(id)
                .map(existingProducto -> {
                    existingProducto.setName(producto.getName());
                    existingProducto.setPrice(producto.getPrice());
                    return productoRepositorio.save(existingProducto);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id:" + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id){
        return productoRepositorio.findById(id)
                .map(existingProducto -> {
                    productoRepositorio.delete(existingProducto);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id:" + id));
    }

    //@PostMapping("/{id}/")
    
}
