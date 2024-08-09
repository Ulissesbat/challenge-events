package com.events.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.events.desafio.dto.EventsDTO;
import com.events.desafio.entities.Evento;
import com.events.desafio.repository.EventsRepository;


@Service
public class EventsServices {
	
	@Autowired
	private EventsRepository repository;
	
	@Transactional
	public EventsDTO insert(EventsDTO dto) {
		Evento entity = new Evento();
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setDataHora(dto.getDataHora());
		entity.setLocalizacao(dto.getLocalizacao());
		entity = repository.save(entity);
		return new EventsDTO(entity);
		
	}

}
