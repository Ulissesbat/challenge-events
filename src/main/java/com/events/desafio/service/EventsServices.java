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
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new EventsDTO(entity);
	}

	@Transactional
	public EventsDTO update(Long id, EventsDTO dto) {
		Evento entity = repository.getReferenceById(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new EventsDTO(entity);	
	}

	private void copyDtoToEntity(EventsDTO dto, Evento entity) {
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setDataHora(dto.getDataHora());
		entity.setLocalizacao(dto.getLocalizacao());
	}
}
