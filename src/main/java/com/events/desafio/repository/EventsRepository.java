package com.events.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.desafio.entities.Evento;

public interface EventsRepository extends JpaRepository<Evento, Long>{

}
