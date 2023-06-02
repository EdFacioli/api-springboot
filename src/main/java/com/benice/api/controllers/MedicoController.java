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

import com.benice.api.domain.Medico;
import com.benice.api.dtos.MedicoDto;
import com.benice.api.mappers.MedicoMapper;
import com.benice.api.repositories.MedicoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    
    @Autowired
    private MedicoRepository medicoRepository;
    private MedicoMapper medicoMapper = Mappers.getMapper(MedicoMapper.class);

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid MedicoDto dados, UriComponentsBuilder uriComponentsBuilder) {
        
        Medico medico = this.medicoMapper.toEntity(dados);
        this.medicoRepository.save(medico);
        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
       
        return ResponseEntity.created(uri).body(medicoMapper.toDto(medico));
    }

    @GetMapping
    public ResponseEntity<?> listar(
        @PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {

        List<MedicoDto> medicoDtoList = new ArrayList<>();
        this.medicoRepository.findAll(pageable).forEach(m -> medicoDtoList.add(this.medicoMapper.toDto(m)));

        return ResponseEntity.ok(medicoDtoList);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@Valid @RequestBody MedicoDto medicoDto, @PathVariable("id") Long id) { 

        medicoDto.setId(id);
        this.medicoRepository.save(this.medicoMapper.toEntity(medicoDto));
        
        return ResponseEntity.ok(medicoDto);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        
        Medico medico = this.medicoRepository.getReferenceById(id);
        this.medicoRepository.delete(medico);
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<?> detalhar(@PathVariable("id") Long id) {
        
        Medico medico = this.medicoRepository.getReferenceById(id);

        return ResponseEntity.ok(this.medicoMapper.toDto(medico));
    }
}