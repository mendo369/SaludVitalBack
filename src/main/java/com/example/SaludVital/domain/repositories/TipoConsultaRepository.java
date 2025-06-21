package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.TipoConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoConsultaRepository extends JpaRepository<TipoConsulta, Integer> {
}