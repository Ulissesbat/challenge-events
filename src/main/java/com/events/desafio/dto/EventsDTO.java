package com.events.desafio.dto;

import java.time.LocalDateTime;

import com.events.desafio.entities.Evento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EventsDTO {

	private Long id;

	@Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String nome;

	@Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
	@NotBlank(message = "Campo requerido")
	private String descricao;

	@NotNull(message = "A data e hora não podem ser nulas")
	@Future(message = "A data e hora devem estar no futuro")
	private LocalDateTime dataHora;

	private String localizacao;

	public EventsDTO(Long id, String nome, String descricao, LocalDateTime dataHora, String localizacao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataHora = dataHora;
		this.localizacao = localizacao;
	}

	public EventsDTO(Evento entity) {
		id = entity.getId();
		nome = entity.getNome();
		descricao = entity.getDescricao();
		dataHora = entity.getDataHora();
		localizacao = entity.getLocalizacao();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public String getLocalizacao() {
		return localizacao;
	}

}
