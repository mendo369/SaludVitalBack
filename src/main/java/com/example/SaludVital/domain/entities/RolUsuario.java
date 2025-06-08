package com.example.SaludVital.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles_usuario")
public class RolUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @Column(name = "nombre_rol", nullable = false, unique = true)
    private String nombreRol;

    private String descripcion;
}