package com.events.desafio.dto;

import com.events.desafio.entities.Participante;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ParticipanteDTO {
	
	private Long id;
	
	@Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String nome;
	
	@NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
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
