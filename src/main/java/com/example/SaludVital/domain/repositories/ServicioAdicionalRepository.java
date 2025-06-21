package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.ServicioAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioAdicionalRepository extends JpaRepository<ServicioAdicional, Integer> {
}