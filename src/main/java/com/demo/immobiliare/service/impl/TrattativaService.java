package com.demo.immobiliare.service.impl;

import com.demo.immobiliare.model.Trattativa;
import com.demo.immobiliare.repository.TrattativaRepository;
import com.demo.immobiliare.service.ITrattativaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrattativaService implements ITrattativaService {

    private final TrattativaRepository trattativaRepository;

    @Autowired
    public TrattativaService(TrattativaRepository trattativaRepository) {
        this.trattativaRepository = trattativaRepository;
    }

    @Override
    public Trattativa creaTrattativa(Trattativa trattativa) {
        return trattativaRepository.save(trattativa);
    }

    @Override
    public Trattativa aggiornaTrattativa(Trattativa trattativa) throws Exception {
        if (!trattativaRepository.existsById(trattativa.getIdTrattativa())) {
            throw new Exception("Trattativa con ID " + trattativa.getIdTrattativa() + " non trovata");
        }
        return trattativaRepository.save(trattativa);
    }

    @Override
    public void eliminaTrattativa(Long id) throws Exception {
        if (!trattativaRepository.existsById(id)) {
            throw new Exception("Trattativa con ID " + id + " non trovata");
        }
        trattativaRepository.deleteById(id);
    }

    @Override
    public Optional<Trattativa> trovaPerId(Long id) {
        return trattativaRepository.findById(id);
    }
    
    @Override
	public List<Trattativa> trovaTutti() {
		return trattativaRepository.findAll();
	}
}
