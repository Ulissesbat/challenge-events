package com.events.desafio.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.events.desafio.dto.InscricaoDTO;
import com.events.desafio.service.InscricaoService;

@RestController
@RequestMapping(value = "/inscricao")
public class InscricaoController {
	
	@Autowired
	private InscricaoService inscricaoService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<InscricaoDTO> insert (@RequestBody InscricaoDTO dto){
		dto = inscricaoService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping
	public ResponseEntity <Page<InscricaoDTO>> findAll (Pageable pageable){
		Page<InscricaoDTO> dto = inscricaoService.findAll(pageable);
        return ResponseEntity.ok(dto);
	}
}
