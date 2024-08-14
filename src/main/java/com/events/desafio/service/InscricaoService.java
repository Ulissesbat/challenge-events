package com.events.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.events.desafio.dto.InscricaoDTO;
import com.events.desafio.entities.Evento;
import com.events.desafio.entities.Inscricao;
import com.events.desafio.entities.Participante;
import com.events.desafio.repository.EventsRepository;
import com.events.desafio.repository.InscricaoRepository;
import com.events.desafio.repository.ParticipanteRepository;
import com.events.desafio.service.exception.ResourceNotFoundException;

@Service
public class InscricaoService {
	
	@Autowired
	private ParticipanteRepository participanteRepository;
	
	@Autowired
	private EventsRepository eventsRepository;
	
	@Autowired
	private InscricaoRepository inscricaoRepository;
	
	@Transactional
	public InscricaoDTO insert(InscricaoDTO dto) {
		
	    Inscricao entity = new Inscricao();
	    Evento evento = eventsRepository.findById(dto.getEvento().getId())
	    		.orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + dto.getEvento().getId()));
	    Participante participante = participanteRepository.findById(dto.getParticipante().getId())
	    		.orElseThrow(() -> new ResourceNotFoundException("Participante não encontrado com ID: " + dto.getParticipante().getId()));

	    entity.setEvento(evento);
	    entity.setParticipante(participante);
	    entity.setDataHora(dto.getDataHora());

	    entity = inscricaoRepository.save(entity);

	    return new InscricaoDTO(entity);
	}
	@Transactional(readOnly = true)
	public Page<InscricaoDTO> findAll(Pageable pageable) {
		Page<Inscricao> result = inscricaoRepository.findAll(pageable);
		return result.map(x -> new InscricaoDTO(x));
	}
}
