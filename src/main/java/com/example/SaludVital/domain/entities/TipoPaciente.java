package com.example.SaludVital.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tipos_paciente")
public class TipoPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoPaciente;

    @Column(name = "nombre_tipo", nullable = false, unique = true)
    private String nombreTipo;

    @Column(name = "porcentaje_descuento", precision = 5, scale = 2)
    private BigDecimal porcentajeDescuento;

    private String descripcion;
}