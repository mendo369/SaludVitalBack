package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.ServicioAdicional;
import com.example.SaludVital.services.ServicioAdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios-adicionales")
public class ServicioAdicionalController {

    @Autowired
    private ServicioAdicionalService servicioAdicionalService;

    // CREAR un nuevo servicio adicional
    @PostMapping
    public ResponseEntity<ServicioAdicional> createServicioAdicional(@RequestBody ServicioAdicional servicioAdicional) {
        ServicioAdicional created = servicioAdicionalService.createServicioAdicional(servicioAdicional);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // OBTENER todos los servicios adicionales
    @GetMapping
    public ResponseEntity<List<ServicioAdicional>> getAllServiciosAdicionales() {
        List<ServicioAdicional> list = servicioAdicionalService.getAllServiciosAdicionales();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // OBTENER un servicio adicional por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicioAdicional> getServicioAdicionalById(@PathVariable Integer id) {
        ServicioAdicional servicioAdicional = servicioAdicionalService.getServicioAdicionalById(id);
        return new ResponseEntity<>(servicioAdicional, HttpStatus.OK);
    }

    // ACTUALIZAR un servicio adicional
    @PutMapping("/{id}")
    public ResponseEntity<ServicioAdicional> updateServicioAdicional(
            @PathVariable Integer id,
            @RequestBody ServicioAdicional servicioAdicionalDetails) {
        ServicioAdicional updated = servicioAdicionalService.updateServicioAdicional(id, servicioAdicionalDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ELIMINAR un servicio adicional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicioAdicional(@PathVariable Integer id) {
        servicioAdicionalService.deleteServicioAdicional(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}