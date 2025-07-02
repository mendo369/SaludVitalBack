package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.Cita;
import com.example.SaludVital.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Cita>> getCitasByPaciente(@PathVariable Integer idPaciente) {
        List<Cita> citas = citaService.getCitasByPacienteId(idPaciente);
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @GetMapping("/estadisticas/canceladas")
    public ResponseEntity<Long> countCanceledAppointments() {
        Long cantidadCanceladas = citaService.countCanceledAppointments();
        return ResponseEntity.ok(cantidadCanceladas);
    }

    @GetMapping("/canceladas")
    public ResponseEntity<List<Cita>> getAllCanceledAppointments() {
        List<Cita> citasCanceladas = citaService.getAllCanceledAppointments();
        return ResponseEntity.ok(citasCanceladas);
    }

    @GetMapping("/estadisticas/por-medico")
    public ResponseEntity<List<Map<String, Object>>> getCitasPorMedico() {
        List<Map<String, Object>> stats = citaService.getMedicoCitasStats();
        return ResponseEntity.ok(stats);
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