package com.example.apipractica.repositorios;

import com.example.apipractica.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    Optional<Producto> findByName(String name);
    boolean existsByName(String name);
}