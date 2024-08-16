package com.events.desafio.dto;

import com.events.desafio.entities.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {

	private Long id;

	@Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String nome;

	@NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
	private String email;

	@NotBlank(message = "O campo de senha é obrigatório")
	private String senha;

	public UsuarioDTO(Long id, String nome, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
		senha = entity.getSenha();
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

	public String getSenha() {
		return senha;
	}

}
