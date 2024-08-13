package com.events.desafio.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@PostMapping
	public ResponseEntity<InscricaoDTO> insert (@RequestBody InscricaoDTO dto){
		dto = inscricaoService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
	}
}
