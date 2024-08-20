package com.events.desafio.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.events.desafio.dto.UsuarioDTO;
import com.events.desafio.entities.Role;
import com.events.desafio.entities.Usuario;
import com.events.desafio.projection.UserDetailsProjection;
import com.events.desafio.repository.UsuarioRepository;


@Service
public class UsuarioServices implements UserDetailsService{
	
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<UserDetailsProjection> result = usuarioRepository.searchUserAndRolesByEmail(username);
		if (result.size() == 0) {
			throw new UsernameNotFoundException("User not found");
		}
		Usuario usuario = new Usuario();
		usuario.setEmail(username);
		usuario.setSenha(result.get(0).getSenha());
		for(UserDetailsProjection projection : result) {
			usuario.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}
		return usuario;
	}
	
}
