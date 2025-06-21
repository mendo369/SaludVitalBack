package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.ServicioPaciente;
import com.example.SaludVital.services.ServicioPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios-paciente")
public class ServicioPacienteController {

    @Autowired
    private ServicioPacienteService servicioPacienteService;

    // CREAR un nuevo registro de servicio paciente
    @PostMapping
    public ResponseEntity<ServicioPaciente> createServicioPaciente(@RequestBody ServicioPaciente servicioPaciente) {
        ServicioPaciente created = servicioPacienteService.createServicioPaciente(servicioPaciente);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // OBTENER todos los servicios pacientes
    @GetMapping
    public ResponseEntity<List<ServicioPaciente>> getAllServiciosPaciente() {
        List<ServicioPaciente> list = servicioPacienteService.getAllServiciosPaciente();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // OBTENER un servicio paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicioPaciente> getServicioPacienteById(@PathVariable Integer id) {
        ServicioPaciente servicioPaciente = servicioPacienteService.getServicioPacienteById(id);
        return new ResponseEntity<>(servicioPaciente, HttpStatus.OK);
    }

    // ACTUALIZAR un servicio paciente
    @PutMapping("/{id}")
    public ResponseEntity<ServicioPaciente> updateServicioPaciente(
            @PathVariable Integer id,
            @RequestBody ServicioPaciente servicioPacienteDetails) {
        ServicioPaciente updated = servicioPacienteService.updateServicioPaciente(id, servicioPacienteDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ELIMINAR un servicio paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicioPaciente(@PathVariable Integer id) {
        servicioPacienteService.deleteServicioPaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}