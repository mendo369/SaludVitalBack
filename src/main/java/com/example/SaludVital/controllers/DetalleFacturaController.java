package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.DetalleFactura;
import com.example.SaludVital.services.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-factura")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    // CREAR un nuevo detalle de factura
    @PostMapping
    public ResponseEntity<DetalleFactura> createDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
        DetalleFactura created = detalleFacturaService.createDetalleFactura(detalleFactura);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // OBTENER todos los detalles de factura
    @GetMapping
    public ResponseEntity<List<DetalleFactura>> getAllDetallesFactura() {
        List<DetalleFactura> list = detalleFacturaService.getAllDetallesFactura();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // OBTENER un detalle de factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> getDetalleFacturaById(@PathVariable Integer id) {
        DetalleFactura detalleFactura = detalleFacturaService.getDetalleFacturaById(id);
        return new ResponseEntity<>(detalleFactura, HttpStatus.OK);
    }

    // ACTUALIZAR un detalle de factura
    @PutMapping("/{id}")
    public ResponseEntity<DetalleFactura> updateDetalleFactura(
            @PathVariable Integer id,
            @RequestBody DetalleFactura detalleFacturaDetails) {
        DetalleFactura updated = detalleFacturaService.updateDetalleFactura(id, detalleFacturaDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ELIMINAR un detalle de factura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleFactura(@PathVariable Integer id) {
        detalleFacturaService.deleteDetalleFactura(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}