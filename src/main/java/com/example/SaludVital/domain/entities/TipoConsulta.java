package com.example.SaludVital.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tipos_consulta")
public class TipoConsulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoConsulta;

    @Column(name = "nombre_tipo", nullable = false, unique = true)
    private String nombreTipo;

    @Column(name = "costo_base", nullable = false)
    private BigDecimal costoBase;

    private String descripcion;
}