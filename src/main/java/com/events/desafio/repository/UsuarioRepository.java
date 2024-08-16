package com.events.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.desafio.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
