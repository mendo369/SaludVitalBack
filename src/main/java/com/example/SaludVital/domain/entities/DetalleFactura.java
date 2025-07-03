package com.example.SaludVital.domain.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "detalles_factura")
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false)
    private Factura factura;

    @Column(nullable = false)
    private String tipoItem;

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double subtotal;
}