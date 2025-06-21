package com.example.SaludVital.controllers;

import com.example.SaludVital.domain.entities.TipoConsulta;
import com.example.SaludVital.services.TipoConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-consulta")
public class TipoConsultaController {

    @Autowired
    private TipoConsultaService tipoConsultaService;

    // CREAR un nuevo tipo de consulta
    @PostMapping
    public ResponseEntity<TipoConsulta> createTipoConsulta(@RequestBody TipoConsulta tipoConsulta) {
        TipoConsulta created = tipoConsultaService.createTipoConsulta(tipoConsulta);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // OBTENER todos los tipos de consulta
    @GetMapping
    public ResponseEntity<List<TipoConsulta>> getAllTiposConsulta() {
        List<TipoConsulta> list = tipoConsultaService.getAllTiposConsulta();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // OBTENER tipo de consulta por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoConsulta> getTipoConsultaById(@PathVariable Integer id) {
        TipoConsulta tipoConsulta = tipoConsultaService.getTipoConsultaById(id);
        return new ResponseEntity<>(tipoConsulta, HttpStatus.OK);
    }

    // ACTUALIZAR tipo de consulta
    @PutMapping("/{id}")
    public ResponseEntity<TipoConsulta> updateTipoConsulta(
            @PathVariable Integer id,
            @RequestBody TipoConsulta tipoConsultaDetails) {
        TipoConsulta updated = tipoConsultaService.updateTipoConsulta(id, tipoConsultaDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ELIMINAR tipo de consulta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoConsulta(@PathVariable Integer id) {
        tipoConsultaService.deleteTipoConsulta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}