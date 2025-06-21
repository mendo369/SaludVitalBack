package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.Factura;
import com.example.SaludVital.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    // CREAR una nueva factura
    @PostMapping
    public ResponseEntity<Factura> createFactura(@RequestBody Factura factura) {
        Factura created = facturaService.createFactura(factura);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // OBTENER todas las facturas
    @GetMapping
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> list = facturaService.getAllFacturas();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // OBTENER una factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Integer id) {
        Factura factura = facturaService.getFacturaById(id);
        return new ResponseEntity<>(factura, HttpStatus.OK);
    }

    // ACTUALIZAR una factura
    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(
            @PathVariable Integer id,
            @RequestBody Factura facturaDetails) {
        Factura updated = facturaService.updateFactura(id, facturaDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ELIMINAR una factura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Integer id) {
        facturaService.deleteFactura(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}