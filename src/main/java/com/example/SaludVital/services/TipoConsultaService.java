package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.TipoConsulta;
import com.example.SaludVital.domain.repositories.TipoConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoConsultaService {

    @Autowired
    private TipoConsultaRepository tipoConsultaRepository;

    // Crear un nuevo tipo de consulta
    public TipoConsulta createTipoConsulta(TipoConsulta tipoConsulta) {
        return tipoConsultaRepository.save(tipoConsulta);
    }

    // Obtener todos los tipos de consulta
    public List<TipoConsulta> getAllTiposConsulta() {
        return tipoConsultaRepository.findAll();
    }

    // Obtener un tipo de consulta por ID
    public TipoConsulta getTipoConsultaById(Integer id) {
        return tipoConsultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de consulta no encontrado con ID: " + id));
    }

    // Actualizar un tipo de consulta
    public TipoConsulta updateTipoConsulta(Integer id, TipoConsulta tipoConsultaDetails) {
        TipoConsulta tipoConsulta = getTipoConsultaById(id);

        tipoConsulta.setNombreTipo(tipoConsultaDetails.getNombreTipo());
        tipoConsulta.setCostoBase(tipoConsultaDetails.getCostoBase());
        tipoConsulta.setDescripcion(tipoConsultaDetails.getDescripcion());

        return tipoConsultaRepository.save(tipoConsulta);
    }

    // Eliminar un tipo de consulta
    public void deleteTipoConsulta(Integer id) {
        if (!tipoConsultaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Tipo de consulta no encontrado");
        }
        tipoConsultaRepository.deleteById(id);
    }
}