package com.demo.immobiliare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.immobiliare.model.Annuncio;

public interface AnnuncioRepository extends JpaRepository<Annuncio, Long>{

}
