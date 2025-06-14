package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.Especialidad;
import com.example.SaludVital.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    // CREATE - Crear una nueva especialidad
    @PostMapping
    public ResponseEntity<Especialidad> createEspecialidad(@RequestBody Especialidad especialidad) {
        Especialidad created = specialtyService.createEspecialidad(especialidad);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // READ - Obtener todas las especialidades
    @GetMapping
    public ResponseEntity<List<Especialidad>> getAllEspecialidades() {
        List<Especialidad> list = specialtyService.getAllEspecialidades();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // READ - Obtener una especialidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> getEspecialidadById(@PathVariable Integer id) {
        Especialidad especialidad = specialtyService.getEspecialidadById(id);
        return new ResponseEntity<>(especialidad, HttpStatus.OK);
    }

    // UPDATE - Actualizar una especialidad existente
    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> updateEspecialidad(
            @PathVariable Integer id,
            @RequestBody Especialidad especialidadDetails) {
        Especialidad updated = specialtyService.updateEspecialidad(id, especialidadDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // DELETE - Eliminar una especialidad por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidad(@PathVariable Integer id) {
        specialtyService.deleteEspecialidad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}