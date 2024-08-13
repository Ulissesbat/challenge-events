package com.events.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.desafio.entities.Evento;
import com.events.desafio.entities.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long>{

}
