package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.Paciente;
import com.example.SaludVital.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // CREAR un nuevo paciente
    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        Paciente created = pacienteService.createPaciente(paciente);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // OBTENER todos los pacientes
    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    // OBTENER un paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Integer id) {
        Paciente paciente = pacienteService.getPacienteById(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    // ACTUALIZAR un paciente
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(
            @PathVariable Integer id,
            @RequestBody Paciente pacienteDetails) {
        Paciente updated = pacienteService.updatePaciente(id, pacienteDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ELIMINAR un paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Integer id) {
        pacienteService.deletePaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}