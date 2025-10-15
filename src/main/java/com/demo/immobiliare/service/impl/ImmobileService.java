package com.demo.immobiliare.service.impl;

import com.demo.immobiliare.dto.ImmobileDTO;
import com.demo.immobiliare.mapper.ImmobileMapper;
import com.demo.immobiliare.model.Immobile;
import com.demo.immobiliare.model.Utente;
import com.demo.immobiliare.repository.ImmobileRepository;
import com.demo.immobiliare.repository.UtenteRepository;
import com.demo.immobiliare.service.IImmobileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImmobileService implements IImmobileService {

    private final ImmobileRepository immobileRepository;
    private final UtenteRepository utenteRepository;

    @Autowired
    public ImmobileService(ImmobileRepository immobileRepository, UtenteRepository utenteRepository) {
        this.immobileRepository = immobileRepository;
        this.utenteRepository = utenteRepository;
    }

    @Override
    public ImmobileDTO creaImmobile(ImmobileDTO immobileDTO) throws Exception {
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	Utente proprietario = utenteRepository.findByEmail(email)
    	    .orElseThrow(() -> new Exception("Utente non trovato"));


        Immobile immobile = ImmobileMapper.toEntity(immobileDTO);
        immobile.setProprietario(proprietario);

        Immobile saved = immobileRepository.save(immobile);
        return ImmobileMapper.toDto(saved);
    }


    @Override
    public ImmobileDTO aggiornaImmobile(ImmobileDTO immobileDTO) throws Exception {
        Immobile immobileEsistente = immobileRepository.findById(immobileDTO.getIdImmobile())
            .orElseThrow(() -> new Exception("Immobile con ID " + immobileDTO.getIdImmobile() + " non trovato"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente proprietario = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new Exception("Utente non trovato"));

        if (!immobileEsistente.getProprietario().getIdUtente().equals(proprietario.getIdUtente())) {
            throw new Exception("Non puoi modificare un immobile che non ti appartiene");
        }

        immobileEsistente.setIndirizzo(immobileDTO.getIndirizzo());
        immobileEsistente.setPrezzo(immobileDTO.getPrezzo());
        immobileEsistente.setStato(immobileDTO.getStato());
        immobileEsistente.setDescrizione(immobileDTO.getDescrizione());

        Immobile updated = immobileRepository.save(immobileEsistente);
        return ImmobileMapper.toDto(updated);
    }


    @Override
    public void eliminaImmobile(Long id) throws Exception {
        if (!immobileRepository.existsById(id)) {
            throw new Exception("Immobile con ID " + id + " non trovato");
        }
        immobileRepository.deleteById(id);
    }

    @Override
    public Optional<ImmobileDTO> trovaPerId(Long id) {
        return immobileRepository.findById(id)
                .map(ImmobileMapper::toDto);
    }

    @Override
    public List<ImmobileDTO> trovaTutti() {
        return immobileRepository.findAll()
                .stream()
                .map(ImmobileMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Page<ImmobileDTO> trovaTuttiPaginati(Pageable pageable) {
        return immobileRepository.findAll(pageable)
                .map(ImmobileMapper::toDto);
    }

}
