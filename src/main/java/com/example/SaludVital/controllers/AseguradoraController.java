package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.Aseguradora;
import com.example.SaludVital.services.AseguradoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aseguradoras")
public class AseguradoraController {

    @Autowired
    private AseguradoraService aseguradoraService;

    // CREAR una nueva aseguradora
    @PostMapping
    public ResponseEntity<Aseguradora> createAseguradora(@RequestBody Aseguradora aseguradora) {
        Aseguradora created = aseguradoraService.createAseguradora(aseguradora);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // OBTENER todas las aseguradoras
    @GetMapping
    public ResponseEntity<List<Aseguradora>> getAllAseguradoras() {
        List<Aseguradora> list = aseguradoraService.getAllAseguradoras();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // OBTENER aseguradora por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aseguradora> getAseguradoraById(@PathVariable Integer id) {
        Aseguradora aseguradora = aseguradoraService.getAseguradoraById(id);
        return new ResponseEntity<>(aseguradora, HttpStatus.OK);
    }

    // ACTUALIZAR una aseguradora
    @PutMapping("/{id}")
    public ResponseEntity<Aseguradora> updateAseguradora(
            @PathVariable Integer id,
            @RequestBody Aseguradora aseguradoraDetails) {
        Aseguradora updated = aseguradoraService.updateAseguradora(id, aseguradoraDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ELIMINAR una aseguradora
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAseguradora(@PathVariable Integer id) {
        aseguradoraService.deleteAseguradora(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}