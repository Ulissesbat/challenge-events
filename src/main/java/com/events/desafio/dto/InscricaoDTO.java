package com.events.desafio.dto;

import java.time.LocalDateTime;

import com.events.desafio.entities.Inscricao;

public class InscricaoDTO {

	private Long id;
	private EventsDTO evento;
	private ParticipanteDTO participante;
	private LocalDateTime dataHora;

	public InscricaoDTO(Long id, EventsDTO evento, ParticipanteDTO participante, LocalDateTime dataHora) {
		this.id = id;
		this.evento = evento;
		this.participante = participante;
		this.dataHora = dataHora;
	}

	public InscricaoDTO(Inscricao entity) {
		this.id = entity.getId();
		this.evento = new EventsDTO(entity.getEvento());
		this.participante = new ParticipanteDTO(entity.getParticipante());
		this.dataHora = entity.getDataHora();
	}

	public Long getId() {
		return id;
	}

	public EventsDTO getEvento() {
		return evento;
	}

	public ParticipanteDTO getParticipante() {
		return participante;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

}
