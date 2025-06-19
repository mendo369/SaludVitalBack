package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.TipoPaciente;
import com.example.SaludVital.services.TipoPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-paciente")
public class TipoPacienteController {

    @Autowired
    private TipoPacienteService tipoPacienteService;

    // CREAR un nuevo tipo de paciente
    @PostMapping
    public ResponseEntity<TipoPaciente> createTipoPaciente(@RequestBody TipoPaciente tipoPaciente) {
        TipoPaciente created = tipoPacienteService.createTipoPaciente(tipoPaciente);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // OBTENER todos los tipos de pacientes
    @GetMapping
    public ResponseEntity<List<TipoPaciente>> getAllTiposPacientes() {
        List<TipoPaciente> list = tipoPacienteService.getAllTiposPacientes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // OBTENER tipo de paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoPaciente> getTipoPacienteById(@PathVariable Integer id) {
        TipoPaciente tipoPaciente = tipoPacienteService.getTipoPacienteById(id);
        return new ResponseEntity<>(tipoPaciente, HttpStatus.OK);
    }

    // ACTUALIZAR tipo de paciente
    @PutMapping("/{id}")
    public ResponseEntity<TipoPaciente> updateTipoPaciente(
            @PathVariable Integer id,
            @RequestBody TipoPaciente tipoPacienteDetails) {
        TipoPaciente updated = tipoPacienteService.updateTipoPaciente(id, tipoPacienteDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ELIMINAR tipo de paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoPaciente(@PathVariable Integer id) {
        tipoPacienteService.deleteTipoPaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}