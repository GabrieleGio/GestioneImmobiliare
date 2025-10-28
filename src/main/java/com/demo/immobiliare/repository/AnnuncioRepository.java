package com.demo.immobiliare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.immobiliare.model.Annuncio;

public interface AnnuncioRepository extends JpaRepository<Annuncio, Long>{
	Page<Annuncio> findAllByCreatore_IdUtente(Long creatoreId, Pageable pageable);
}
