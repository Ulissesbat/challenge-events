package com.events.desafio.service;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.events.desafio.dto.ParticipanteDTO;
import com.events.desafio.entities.Participante;
import com.events.desafio.repository.ParticipanteRepository;


@Service
public class ParticipanteServices {
	
	@Autowired
	private ParticipanteRepository repository;
	
	@Transactional
	public ParticipanteDTO insert(ParticipanteDTO dto) {
		Participante entity = new Participante();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ParticipanteDTO(entity);	
	}

	@Transactional
	public ParticipanteDTO update(Long id, ParticipanteDTO dto) {
		Participante entity = repository.getReferenceById(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ParticipanteDTO(entity);	
	}
	
	@Transactional(readOnly = true)
	public Page<ParticipanteDTO> findAll(Pageable pageable) {
		Page<Participante> result = repository.findAll(pageable);
		return result.map(x -> new ParticipanteDTO(x));
	}
	
	private void copyDtoToEntity(ParticipanteDTO dto, Participante entity) {	
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
	}
	
}
