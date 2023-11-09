package com.example.apipractica.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @Email(message = "El correo es obligatorio")
    private String email;
    @JsonBackReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Producto> productos;
}
