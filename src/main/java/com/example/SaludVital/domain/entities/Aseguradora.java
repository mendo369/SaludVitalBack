package com.example.SaludVital.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "aseguradoras")
public class Aseguradora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAseguradora;

    @Column(name = "nombre_aseguradora", nullable = false, unique = true)
    private String nombreAseguradora;

    private String telefono;
    private String email;
    private Boolean activa;
}