package com.events.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.events.desafio.dto.EventsDTO;
import com.events.desafio.entities.Evento;
import com.events.desafio.repository.EventsRepository;
import com.events.desafio.service.exception.DatabaseException;
import com.events.desafio.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


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
		
		try {
		Evento entity = repository.getReferenceById(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new EventsDTO(entity);	
		}
		catch(EntityNotFoundException e) {
			 throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete (Long id) {
		if(! repository.existsById(id)){
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("falha de integridade");
		}
		
	}
	
	@Transactional(readOnly = true)
	public Page<EventsDTO> findAll(Pageable pageable) {
		Page<Evento> result = repository.findAll(pageable);
		return result.map(x -> new EventsDTO(x));
	}


	private void copyDtoToEntity(EventsDTO dto, Evento entity) {
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setDataHora(dto.getDataHora());
		entity.setLocalizacao(dto.getLocalizacao());
	}
}
