package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.EstadoCita;
import com.example.SaludVital.services.EstadoCitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados-cita")
public class EstadoCitaController {

    @Autowired
    private EstadoCitaService estadoCitaService;

    // CREAR un nuevo estado de cita
    @PostMapping
    public ResponseEntity<EstadoCita> createEstadoCita(@RequestBody EstadoCita estadoCita) {
        EstadoCita created = estadoCitaService.createEstadoCita(estadoCita);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // OBTENER todos los estados de cita
    @GetMapping
    public ResponseEntity<List<EstadoCita>> getAllEstadosCita() {
        List<EstadoCita> list = estadoCitaService.getAllEstadosCita();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // OBTENER estado de cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstadoCita> getEstadoCitaById(@PathVariable Integer id) {
        EstadoCita estadoCita = estadoCitaService.getEstadoCitaById(id);
        return new ResponseEntity<>(estadoCita, HttpStatus.OK);
    }

    // ACTUALIZAR estado de cita
    @PutMapping("/{id}")
    public ResponseEntity<EstadoCita> updateEstadoCita(
            @PathVariable Integer id,
            @RequestBody EstadoCita estadoCitaDetails) {
        EstadoCita updated = estadoCitaService.updateEstadoCita(id, estadoCitaDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ELIMINAR estado de cita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstadoCita(@PathVariable Integer id) {
        estadoCitaService.deleteEstadoCita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}