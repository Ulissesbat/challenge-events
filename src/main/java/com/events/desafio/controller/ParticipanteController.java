package com.events.desafio.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.events.desafio.dto.ParticipanteDTO;
import com.events.desafio.service.ParticipanteServices;

@RestController
@RequestMapping(value = "/participante")
public class ParticipanteController {
	
	@Autowired
	private ParticipanteServices service;
	
	@PostMapping
	public ResponseEntity<ParticipanteDTO> insert (@RequestBody ParticipanteDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
	}

}
