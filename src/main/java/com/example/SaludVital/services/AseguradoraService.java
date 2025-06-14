package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.Aseguradora;
import com.example.SaludVital.domain.repositories.AseguradoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AseguradoraService {

    @Autowired
    private AseguradoraRepository aseguradoraRepository;

    // Crear una nueva aseguradora
    public Aseguradora createAseguradora(Aseguradora aseguradora) {
        return aseguradoraRepository.save(aseguradora);
    }

    // Obtener todas las aseguradoras
    public List<Aseguradora> getAllAseguradoras() {
        return aseguradoraRepository.findAll();
    }

    // Obtener una aseguradora por ID
    public Aseguradora getAseguradoraById(Integer id) {
        return aseguradoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aseguradora no encontrada con ID: " + id));
    }

    // Actualizar una aseguradora
    public Aseguradora updateAseguradora(Integer id, Aseguradora aseguradoraDetails) {
        Aseguradora aseguradora = getAseguradoraById(id);

        aseguradora.setNombreAseguradora(aseguradoraDetails.getNombreAseguradora());
        aseguradora.setTelefono(aseguradoraDetails.getTelefono());
        aseguradora.setEmail(aseguradoraDetails.getEmail());
        aseguradora.setActiva(aseguradoraDetails.getActiva());

        return aseguradoraRepository.save(aseguradora);
    }

    // Eliminar una aseguradora
    public void deleteAseguradora(Integer id) {
        if (!aseguradoraRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Aseguradora no encontrada");
        }
        aseguradoraRepository.deleteById(id);
    }
}