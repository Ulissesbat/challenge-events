package com.events.desafio.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.events.desafio.dto.ParticipanteDTO;
import com.events.desafio.service.ParticipanteServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/participante")
public class ParticipanteController {
	
	@Autowired
	private ParticipanteServices service;
	
	@PostMapping
	public ResponseEntity<ParticipanteDTO> insert (@Valid @RequestBody ParticipanteDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<ParticipanteDTO> update (@PathVariable Long id, @Valid @RequestBody ParticipanteDTO dto){
		dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
	}
	@GetMapping
	public ResponseEntity <Page<ParticipanteDTO>> findAll (Pageable pageable){
		Page<ParticipanteDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
	}
}
