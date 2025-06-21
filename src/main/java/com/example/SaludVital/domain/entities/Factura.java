package com.example.SaludVital.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFactura;

    @Column(name = "numero_factura", nullable = false, unique = true)
    private String numeroFactura;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;

    @Column(name = "fecha_factura", nullable = false)
    private LocalDateTime fechaFactura;

    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Double descuentos;

    @Column(nullable = false)
    private Double impuestos;

    @Column(nullable = false)
    private Double total;

    @Column(name = "estado_pago", nullable = false)
    private String estadoPago;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @Column(name = "metodo_pago")
    private String metodoPago;

    private String observaciones;
}