package com.events.desafio.dto;

import java.time.LocalDateTime;

import com.events.desafio.entities.Evento;

public class EventsDTO {
	
	private Long id;

	private String nome;

	private String descricao;

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
