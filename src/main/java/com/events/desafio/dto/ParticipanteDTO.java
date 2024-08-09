package com.events.desafio.dto;

import com.events.desafio.entities.Participante;

public class ParticipanteDTO {
	
	private Long id;

	private String nome;
	
	private String email;


	public ParticipanteDTO(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public ParticipanteDTO(Participante entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
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

}
