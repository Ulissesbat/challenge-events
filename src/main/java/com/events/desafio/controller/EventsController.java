package com.events.desafio.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.events.desafio.dto.EventsDTO;
import com.events.desafio.dto.ParticipanteDTO;
import com.events.desafio.service.EventsServices;

@RestController
@RequestMapping(value = "/evento")
public class EventsController {
	
	@Autowired
	private EventsServices service;
	
	@PostMapping
	public ResponseEntity<EventsDTO> insert (@RequestBody EventsDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		service.delete(id);
        return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EventsDTO> update (@PathVariable Long id, @RequestBody EventsDTO dto){
		dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
	}
	@GetMapping
	public ResponseEntity <Page<EventsDTO>> findAll (Pageable pageable){
		Page<EventsDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
	}
	
}
