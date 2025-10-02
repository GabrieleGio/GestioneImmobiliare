package com.demo.immobiliare.service;

import java.util.List;
import java.util.Optional;

import com.demo.immobiliare.model.Trattativa;

public interface ITrattativaService {
	
	Trattativa creaTrattativa(Trattativa trattativa);

	Trattativa aggiornaTrattativa(Trattativa trattativa) throws Exception;

	void eliminaTrattativa(Long id) throws Exception;

	Optional<Trattativa> trovaPerId(Long id);

	List<Trattativa> trovaTutti();
}
