package com.events.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
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
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity = repository.save(entity);
		return new ParticipanteDTO(entity);
		
	}

}
