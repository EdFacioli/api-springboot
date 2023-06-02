package com.benice.api.mappers;

import org.mapstruct.Mapper;

import com.benice.api.domain.Medico;
import com.benice.api.dtos.MedicoDto;

@Mapper
public interface MedicoMapper {
    
    Medico toEntity(MedicoDto medicoDto);
    MedicoDto toDto(Medico medico);

}
