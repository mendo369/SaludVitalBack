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

    @Column(name = "id_factura")
    private Integer idFactura;

    @Column(name = "tipo_item")
    private String tipoItem; // CONSULTA o SERVICIO

    @Column(name = "id_referencia")
    private Integer idReferencia; // id_cita o id_servicio_paciente

    private String descripcion;
    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "subtotal")
    private Double subtotal;
}