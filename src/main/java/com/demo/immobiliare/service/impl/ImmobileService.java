package com.demo.immobiliare.service.impl;

import com.demo.immobiliare.dto.ImmobileDTO;
import com.demo.immobiliare.dto.ImmobilePersonaleDTO;
import com.demo.immobiliare.exception.ImmobileNotFoundException;
import com.demo.immobiliare.exception.ImmobileOwnershipException;
import com.demo.immobiliare.exception.UserNotFoundException;
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

    //FIXME di base l'utente proprietario dell'immobile è l'utente registrato, devo permettere di metttere anche un utente diverso
    @Override
    public ImmobileDTO creaImmobile(ImmobileDTO immobileDTO) {
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	Utente utenteReg = utenteRepository.findByEmail(email)
    	    .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));


        Immobile immobile = ImmobileMapper.toEntity(immobileDTO);
        immobile.setProprietario(utenteReg);

        Immobile saved = immobileRepository.save(immobile);
        return ImmobileMapper.toDto(saved);
    }

    @Override
    public ImmobileDTO aggiornaImmobile(ImmobileDTO immobileDTO) {
        Immobile immobileEsistente = immobileRepository.findById(immobileDTO.getIdImmobile())
            .orElseThrow(() -> new ImmobileNotFoundException("Immobile con ID " + immobileDTO.getIdImmobile() + " non trovato"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente proprietario = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        if (!immobileEsistente.getProprietario().getIdUtente().equals(proprietario.getIdUtente())) {
            throw new ImmobileOwnershipException("Non puoi modificare un immobile che non ti appartiene");
        }

        immobileEsistente.setIndirizzo(immobileDTO.getIndirizzo());
        immobileEsistente.setPrezzo(immobileDTO.getPrezzo());
        immobileEsistente.setStato(immobileDTO.getStato());
        immobileEsistente.setDescrizione(immobileDTO.getDescrizione());

        Immobile updated = immobileRepository.save(immobileEsistente);
        return ImmobileMapper.toDto(updated);
    }

    @Override
    public void eliminaImmobile(Long id) {
        if (!immobileRepository.existsById(id)) {
            throw new ImmobileNotFoundException("Immobile con ID " + id + " non trovato");
        }
        immobileRepository.deleteById(id);
    }

    @Override
    public Optional<ImmobileDTO> trovaPerId(Long id) {
    	Immobile immobile = immobileRepository.findById(id)
    	        .orElseThrow(() -> new ImmobileNotFoundException("Immobile con ID " + id + " non trovato"));

    	return Optional.of(ImmobileMapper.toDto(immobile));
    }
    
	@Override
	public List<ImmobileDTO> trovaTutti() {
		    List<Immobile> immobili = immobileRepository.findAll();
		    if (immobili.isEmpty()) {
		        throw new ImmobileNotFoundException("Nessun immobile trovato");
		    }
		    return immobili.stream()
		                   .map(ImmobileMapper::toDto)
		                   .collect(Collectors.toList());
	}
    
    public Page<ImmobilePersonaleDTO> trovaTuttiPersonaliPaginati(Pageable pageable) {
    	String emailUtenteLog = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utenteLog = utenteRepository.findByEmail(emailUtenteLog)
            .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));
        
        return immobileRepository.findAllByProprietario_IdUtente(utenteLog.getIdUtente(), pageable)
                .map(immobile -> new ImmobilePersonaleDTO(
                        immobile.getIdImmobile(),
                        immobile.getTitolo(),
                        immobile.getDescrizione(),
                        immobile.getPrezzo(),
                        immobile.getTipologia(),
                        immobile.getStato(),
                        immobile.getSuperficie(),
                        immobile.getIndirizzo()
                ));
    }
}
