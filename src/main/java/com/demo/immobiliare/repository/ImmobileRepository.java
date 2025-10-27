package com.demo.immobiliare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.immobiliare.model.Immobile;

public interface ImmobileRepository extends JpaRepository<Immobile, Long>{
	Page<Immobile> findAllByProprietario_IdUtente(Long proprietarioId, Pageable pageable);


}
