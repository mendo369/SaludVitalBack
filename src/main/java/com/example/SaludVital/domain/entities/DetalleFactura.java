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

    @Column(name = "tipo_item", nullable = false)
    private String tipoItem;

    @Column(name = "id_referencia", nullable = false)
    private Integer idReferencia;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;
}