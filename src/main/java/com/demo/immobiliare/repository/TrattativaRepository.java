package com.demo.immobiliare.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.immobiliare.model.Trattativa;

public interface TrattativaRepository extends JpaRepository<Trattativa, Long> {
	Page<Trattativa> findAllByUtente_IdUtente(Long utenteId, Pageable pageable);
	Page<Trattativa> findAllByAnnuncio_IdAnnuncio(Long annuncioId, Pageable pageable);
	List<Trattativa> findAllByAnnuncio_IdAnnuncio(Long annuncioId);
}
