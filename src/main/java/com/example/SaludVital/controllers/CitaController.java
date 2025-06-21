package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.Cita;
import com.example.SaludVital.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    // CREAR una nueva cita
    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        Cita created = citaService.createCita(cita);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // OBTENER todas las citas
    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas() {
        List<Cita> citas = citaService.getAllCitas();
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    // OBTENER una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable Integer id) {
        Cita cita = citaService.getCitaById(id);
        return new ResponseEntity<>(cita, HttpStatus.OK);
    }

    // ACTUALIZAR una cita
    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(
            @PathVariable Integer id,
            @RequestBody Cita citaDetails) {
        Cita updated = citaService.updateCita(id, citaDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ELIMINAR una cita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Integer id) {
        citaService.deleteCita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}