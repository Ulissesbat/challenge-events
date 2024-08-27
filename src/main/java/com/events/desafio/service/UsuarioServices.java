package com.events.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.events.desafio.dto.UsuarioDTO;
import com.events.desafio.dto.UsuarioDTOMe;
import com.events.desafio.entities.Role;
import com.events.desafio.entities.Usuario;
import com.events.desafio.projection.UserDetailsProjection;
import com.events.desafio.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioServices implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public @Valid UsuarioDTO insert(UsuarioDTO dto) {
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
		for (UserDetailsProjection projection : result) {
			usuario.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}
		return usuario;
	}

	protected Usuario authenticated() {

		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");

			return usuarioRepository.findByEmail(username).get();

		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found");
		}
	}

	@Transactional(readOnly = true)
	public UsuarioDTOMe getMe() {
		Usuario usuario = authenticated();
		return new UsuarioDTOMe(usuario);
	}
}
