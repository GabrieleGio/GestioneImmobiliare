package com.demo.immobiliare.service.impl;

import com.demo.immobiliare.model.Immobile;
import com.demo.immobiliare.repository.ImmobileRepository;
import com.demo.immobiliare.service.IImmobileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImmobileService implements IImmobileService {

    private final ImmobileRepository immobileRepository;

    @Autowired
    public ImmobileService(ImmobileRepository immobileRepository) {
        this.immobileRepository = immobileRepository;
    }

    @Override
    public Immobile creaImmobile(Immobile immobile) {
        return immobileRepository.save(immobile);
    }

    @Override
    public Immobile aggiornaImmobile(Immobile immobile) throws Exception {
        if (!immobileRepository.existsById(immobile.getIdImmobile())) {
            throw new Exception("Immobile con ID " + immobile.getIdImmobile() + " non trovato");
        }
        return immobileRepository.save(immobile);
    }

    @Override
    public void eliminaImmobile(Long id) throws Exception {
        if (!immobileRepository.existsById(id)) {
            throw new Exception("Immobile con ID " + id + " non trovato");
        }
        immobileRepository.deleteById(id);
    }

    @Override
    public Optional<Immobile> trovaPerId(Long id) {
        return immobileRepository.findById(id);
    }
    
    @Override
	public List<Immobile> trovaTutti() {
		return immobileRepository.findAll();
	}
}
