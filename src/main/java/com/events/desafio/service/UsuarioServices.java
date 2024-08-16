package com.events.desafio.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.events.desafio.dto.UsuarioDTO;
import com.events.desafio.entities.Usuario;
import com.events.desafio.repository.UsuarioRepository;


@Service
public class UsuarioServices {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		Usuario entity = new Usuario();
		copyDtoToEntity(dto, entity);
		entity = usuarioRepository.save(entity);
		return new UsuarioDTO(entity);	
	}
	
	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {	
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setSenha(dto.getSenha());
	}
	
}
