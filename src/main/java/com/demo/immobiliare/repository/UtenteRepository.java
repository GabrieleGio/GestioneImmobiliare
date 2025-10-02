package com.demo.immobiliare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.immobiliare.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
	Optional<Utente> findByUsername(String username);

	Optional<Utente> findByEmail(String email);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
}