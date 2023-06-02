package com.benice.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.benice.api.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
}
