package com.benice.api.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.benice.api.domain.Paciente;
import com.benice.api.dtos.PacienteDto;
import com.benice.api.mappers.PacienteMapper;
import com.benice.api.repositories.PacienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;
    PacienteMapper pacienteMapper = Mappers.getMapper(PacienteMapper.class);
    
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PacienteDto pacienteDto, UriComponentsBuilder uriComponentsBuilder) {

        Paciente paciente = this.pacienteMapper.toEntity(pacienteDto);
        this.pacienteRepository.save(paciente);
        URI uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(this.pacienteMapper.toDto(paciente));
    }

    @GetMapping
    public ResponseEntity<?> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable ) {

        List<PacienteDto> pacienteDtoList = new ArrayList<>();
        this.pacienteRepository.findAll(pageable).forEach(p -> pacienteDtoList.add(this.pacienteMapper.toDto(p)));

        return ResponseEntity.ok(pacienteDtoList);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid PacienteDto pacienteDto, @PathVariable("id") Long id) {

        pacienteDto.setId(id);
        Paciente paciente = this.pacienteMapper.toEntity(pacienteDto);
        this.pacienteRepository.save(paciente);

        return ResponseEntity.ok(this.pacienteMapper.toDto(paciente));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable("id") Long id) {
        
        Paciente paciente = this.pacienteRepository.getReferenceById(id);

        this.pacienteRepository.delete(paciente);
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<?> detalhar(@PathVariable("id") Long id) {

        Paciente paciente = this.pacienteRepository.getReferenceById(id);

        return ResponseEntity.ok(this.pacienteMapper.toDto(paciente));

    }
}