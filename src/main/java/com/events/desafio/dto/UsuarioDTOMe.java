package com.events.desafio.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.events.desafio.entities.Usuario;

public class UsuarioDTOMe {

	private Long id;

	private String nome;

	private String email;
	
	private List<String> roles = new ArrayList<>();


	public UsuarioDTOMe(Usuario entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
		for(GrantedAuthority role : entity.getRoles()) {
			roles.add(role.getAuthority());
		}
	
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public List<String> getRoles() {
		return roles;
	}

}
