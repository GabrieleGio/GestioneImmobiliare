package com.demo.immobiliare.service.impl;

import com.demo.immobiliare.model.Annuncio;
import com.demo.immobiliare.repository.AnnuncioRepository;
import com.demo.immobiliare.service.IAnnuncioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnuncioService implements IAnnuncioService {

    private final AnnuncioRepository annuncioRepository;

    @Autowired
    public AnnuncioService(AnnuncioRepository annuncioRepository) {
        this.annuncioRepository = annuncioRepository;
    }

    @Override
    public Annuncio creaAnnuncio(Annuncio annuncio) {
        return annuncioRepository.save(annuncio);
    }

    @Override
    public Annuncio aggiornaAnnuncio(Annuncio annuncio) throws Exception {
        if (!annuncioRepository.existsById(annuncio.getIdAnnuncio())) {
            throw new Exception("Annuncio con ID " + annuncio.getIdAnnuncio() + " non trovato");
        }
        return annuncioRepository.save(annuncio);
    }

    @Override
    public void eliminaAnnuncio(Long id) throws Exception {
        if (!annuncioRepository.existsById(id)) {
            throw new Exception("Annuncio con ID " + id + " non trovato");
        }
        annuncioRepository.deleteById(id);
    }

    @Override
    public Optional<Annuncio> trovaPerId(Long id) {
        return annuncioRepository.findById(id);
    }
    
    @Override
	public List<Annuncio> trovaTutti() {
		return annuncioRepository.findAll();
	}
}
