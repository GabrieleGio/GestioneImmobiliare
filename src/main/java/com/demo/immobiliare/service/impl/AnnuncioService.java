package com.demo.immobiliare.service.impl;

import com.demo.immobiliare.dto.AnnuncioDTO;
import com.demo.immobiliare.dto.AnnuncioHomeDTO;
import com.demo.immobiliare.dto.AnnuncioPersonaleDTO;
import com.demo.immobiliare.exception.AnnuncioNotFoundException;
import com.demo.immobiliare.exception.AnnuncioOwnershipException;
import com.demo.immobiliare.exception.ImmobileAlreadySoldException;
import com.demo.immobiliare.exception.ImmobileOwnershipException;
import com.demo.immobiliare.exception.UserNotFoundException;
import com.demo.immobiliare.mapper.AnnuncioMapper;
import com.demo.immobiliare.model.Annuncio;
import com.demo.immobiliare.model.Immobile;
import com.demo.immobiliare.model.StatoImmobile;
import com.demo.immobiliare.model.Utente;
import com.demo.immobiliare.repository.AnnuncioRepository;
import com.demo.immobiliare.repository.ImmobileRepository;
import com.demo.immobiliare.repository.UtenteRepository;
import com.demo.immobiliare.service.IAnnuncioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnuncioService implements IAnnuncioService {

    private final AnnuncioRepository annuncioRepository;
    private final UtenteRepository utenteRepository;
    private final ImmobileRepository immobileRepository;

    @Autowired
    public AnnuncioService(AnnuncioRepository annuncioRepository, UtenteRepository utenteRepository, ImmobileRepository immobileRepository) {
        this.annuncioRepository = annuncioRepository;
        this.utenteRepository = utenteRepository;
        this.immobileRepository = immobileRepository;
    }

    @Override
    public AnnuncioDTO creaAnnuncio(AnnuncioDTO annuncioDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utente = utenteRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        Immobile immobile = immobileRepository.findById(annuncioDTO.getIdImmobile())
                .orElseThrow(() -> new ImmobileNotFoundException("Immobile non trovato"));

        if (!immobile.getProprietario().getIdUtente().equals(utente.getIdUtente())) {
            throw new ImmobileOwnershipException("Non puoi creare un annuncio per un immobile che non possiedi");
        }
        
        if (immobile.getStato().equals(StatoImmobile.VENDUTO)) {
        	throw new ImmobileAlreadySoldException("Non puoi mettere un annuncio per un immobile venduto");
        }
        
        Annuncio annuncio = AnnuncioMapper.toEntity(annuncioDTO);
        annuncio.setImmobile(immobile);
        annuncio.setCreatore(utente);
        annuncio.setVenditore(immobile.getProprietario());
        Annuncio saved = annuncioRepository.save(annuncio);
        return AnnuncioMapper.toDto(saved);
    }

    @Override
    public AnnuncioDTO aggiornaAnnuncio(AnnuncioDTO annuncioDTO) {
        Annuncio annuncioEsistente = annuncioRepository.findById(annuncioDTO.getIdAnnuncio())
                .orElseThrow(() -> new AnnuncioNotFoundException("Annuncio con ID " + annuncioDTO.getIdAnnuncio() + " non trovato"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utente = utenteRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        Immobile immobile = immobileRepository.findById(annuncioDTO.getIdImmobile())
                .orElseThrow(() -> new ImmobileNotFoundException("Immobile non trovato"));

        if (!immobile.getProprietario().getIdUtente().equals(utente.getIdUtente())) {
            throw new ImmobileOwnershipException("Non puoi modificare un annuncio per un immobile che non possiedi");
        }

        if (!annuncioEsistente.getCreatore().getIdUtente().equals(utente.getIdUtente())) {
            throw new AnnuncioOwnershipException("Non puoi modificare un annuncio che non hai creato");
        }

        annuncioEsistente.setVisibile(annuncioDTO.isVisibile());
        annuncioEsistente.setVisualizzazioni(annuncioDTO.getVisualizzazioni());

        Annuncio updated = annuncioRepository.save(annuncioEsistente);
        return AnnuncioMapper.toDto(updated);
    }

    @Override
    public void eliminaAnnuncio(Long id) {
        if (!annuncioRepository.existsById(id)) {
            throw new AnnuncioNotFoundException("Annuncio con ID " + id + " non trovato");
        }
        annuncioRepository.deleteById(id);
    }

    @Override
    public Optional<AnnuncioDTO> trovaPerId(Long id) {
        return annuncioRepository.findById(id)
                .map(AnnuncioMapper::toDto);
    }

    @Override
    public List<AnnuncioDTO> trovaTutti() {
        return annuncioRepository.findAll()
                .stream()
                .map(AnnuncioMapper::toDto)
                .collect(Collectors.toList());
    }
    
//    @Override
//    public Page<AnnuncioDTO> trovaTuttiPaginati(Pageable pageable) {
//        return annuncioRepository.findAll(pageable)
//                .map(AnnuncioMapper::toDto);
//    }
    
    @Override
    public Page<AnnuncioHomeDTO> trovaTuttiPaginati(Pageable pageable) {
        return annuncioRepository.findAll(pageable)
            .map(annuncio -> {
                Immobile immobile = annuncio.getImmobile();

                return new AnnuncioHomeDTO(
                    annuncio.getIdAnnuncio(),
                    annuncio.getDataPubblicazione(),
                    annuncio.isVisibile(),
                    annuncio.getVisualizzazioni(),
                    immobile != null ? immobile.getIdImmobile() : null,
                    immobile != null ? immobile.getTitolo() : null,
                    immobile != null ? immobile.getIndirizzo() : null,
                    immobile != null ? immobile.getPrezzo() : null
                );
            });
    }
    
    @Override
    public Page<AnnuncioPersonaleDTO> trovaTuttiPersonaliPaginati(Pageable pageable) {
    	String emailUtenteLog = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utenteLog = utenteRepository.findByEmail(emailUtenteLog)
            .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));
        
        return annuncioRepository.findAllByCreatore_IdUtente(utenteLog.getIdUtente(), pageable)
        		.map(annuncio -> {
        			Immobile immobile = annuncio.getImmobile();
        			
        			return new AnnuncioPersonaleDTO(
        				annuncio.getIdAnnuncio(),
        				annuncio.getDataPubblicazione(),
        				annuncio.isVisibile(),
        				annuncio.getVisualizzazioni(),
        				immobile != null ? immobile.getIdImmobile() : null,
        	            immobile != null ? immobile.getTitolo() : null,
        	            immobile != null ? immobile.getIndirizzo() : null,
        	            immobile != null ? immobile.getPrezzo() : null,
        				annuncio.getVenditore().getIdUtente()
        		);
        	});
    }

}
