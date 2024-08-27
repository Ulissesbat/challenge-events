package com.events.desafio.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.events.desafio.dto.UsuarioDTO;
import com.events.desafio.dto.UsuarioDTOMe;
import com.events.desafio.service.UsuarioServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioServices usuarioServices;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<@Valid UsuarioDTO> insert (@Valid @RequestBody UsuarioDTO dto){
		dto = usuarioServices.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
	@GetMapping(value = "/me")
	public ResponseEntity<UsuarioDTOMe> getMe (){
		UsuarioDTOMe dto = usuarioServices.getMe();
        return ResponseEntity.ok(dto);
	}
	
	/*@GetMapping
	public ResponseEntity <Page<UsuarioDTO>> findAll (Pageable pageable){
		Page<UsuarioDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
	}*/
}
