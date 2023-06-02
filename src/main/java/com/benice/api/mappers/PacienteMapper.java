package com.benice.api.mappers;

import org.mapstruct.Mapper;

import com.benice.api.domain.Paciente;
import com.benice.api.dtos.PacienteDto;

@Mapper
public interface PacienteMapper {
    
    Paciente toEntity(PacienteDto pacienteDto);
    PacienteDto toDto(Paciente paciente);
    
}
