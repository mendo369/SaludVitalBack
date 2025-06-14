package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
    // Puedes agregar consultas personalizadas aquí si es necesario
}
