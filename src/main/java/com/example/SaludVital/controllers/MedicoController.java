package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.Medico;
import com.example.SaludVital.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    // CREAR un nuevo médico
    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody Medico medico) {
        Medico created = medicoService.saveMedico(medico);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // OBTENER todos los médicos
    @GetMapping
    public ResponseEntity<List<Medico>> getAllMedicos() {
        List<Medico> medicos = medicoService.getAllMedicos();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    // OBTENER un médico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable Integer id) {
        Medico medico = medicoService.getMedicoById(id);
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    // ACTUALIZAR un médico
    @PutMapping("/{id}")
    public ResponseEntity<Medico> updateMedico(
            @PathVariable Integer id,
            @RequestBody Medico medicoDetails) {
        Medico updated = medicoService.updateMedico(id, medicoDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ELIMINAR un médico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Integer id) {
        medicoService.deleteMedico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}