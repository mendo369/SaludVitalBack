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

    @Column(name = "id_paciente")
    private Integer idPaciente;

    @Column(name = "id_cita")
    private Integer idCita;

    @Column(name = "fecha_factura")
    private LocalDateTime fechaFactura;

    private Double subtotal;
    private Double descuentos;
    private Double impuestos;
    private Double total;

    @Column(name = "estado_pago")
    private String estadoPago = "PENDIENTE";

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @Column(name = "metodo_pago")
    private String metodoPago;

    private String observaciones;
}