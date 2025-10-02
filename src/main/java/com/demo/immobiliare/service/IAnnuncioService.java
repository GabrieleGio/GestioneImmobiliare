package com.demo.immobiliare.service;

import java.util.List;
import java.util.Optional;

import com.demo.immobiliare.model.Annuncio;

public interface IAnnuncioService {

    Annuncio creaAnnuncio(Annuncio annuncio);

    Annuncio aggiornaAnnuncio(Annuncio annuncio) throws Exception;

    void eliminaAnnuncio(Long id) throws Exception;

    Optional<Annuncio> trovaPerId(Long id);

	List<Annuncio> trovaTutti();
}
