package com.benice.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.benice.api.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
}
