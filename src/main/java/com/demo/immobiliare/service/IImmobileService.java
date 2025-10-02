package com.demo.immobiliare.service;

import java.util.List;
import java.util.Optional;

import com.demo.immobiliare.model.Immobile;

public interface IImmobileService {

    Immobile creaImmobile(Immobile immobile);

    Immobile aggiornaImmobile(Immobile immobile) throws Exception;

    void eliminaImmobile(Long id) throws Exception;

    Optional<Immobile> trovaPerId(Long id);

	List<Immobile> trovaTutti();
}
