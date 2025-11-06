package com.demo.immobiliare.service.impl;

import com.demo.immobiliare.dto.TrattativaDTO;
import com.demo.immobiliare.dto.TrattativaPersonaleDTO;
import com.demo.immobiliare.dto.TrattativaPropostaDTO;
import com.demo.immobiliare.exception.AnnuncioNotFoundException;
import com.demo.immobiliare.exception.AnnuncioNotVisibleException;
import com.demo.immobiliare.exception.AnnuncioOwnershipException;
import com.demo.immobiliare.exception.ImmobileOwnershipException;
import com.demo.immobiliare.exception.InvalidImmobileStateException;
import com.demo.immobiliare.exception.InvalidTrattativaStateException;
import com.demo.immobiliare.exception.SameUserTrattativaException;
import com.demo.immobiliare.exception.TrattativaNotFoundException;
import com.demo.immobiliare.exception.UserNotFoundException;
import com.demo.immobiliare.mapper.TrattativaMapper;
import com.demo.immobiliare.model.Annuncio;
import com.demo.immobiliare.model.Immobile;
import com.demo.immobiliare.model.StatoImmobile;
import com.demo.immobiliare.model.StatoTrattativa;
import com.demo.immobiliare.model.Trattativa;
import com.demo.immobiliare.model.Utente;
import com.demo.immobiliare.repository.AnnuncioRepository;
import com.demo.immobiliare.repository.ImmobileRepository;
import com.demo.immobiliare.repository.TrattativaRepository;
import com.demo.immobiliare.repository.UtenteRepository;
import com.demo.immobiliare.service.ITrattativaService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrattativaService implements ITrattativaService {

    private final TrattativaRepository trattativaRepository;
    private final AnnuncioRepository annuncioRepository;
    private final UtenteRepository utenteRepository;
    private final ImmobileRepository immobileRepository;

    @Autowired
    public TrattativaService(TrattativaRepository trattativaRepository, AnnuncioRepository annuncioRepository,
    		ImmobileRepository immobileRepository, UtenteRepository utenteRepository) {
        this.trattativaRepository = trattativaRepository;
        this.annuncioRepository = annuncioRepository;
        this.utenteRepository = utenteRepository;
        this.immobileRepository = immobileRepository;
    }

    public TrattativaDTO creaTrattativa(TrattativaPropostaDTO propostaDTO) {
    	
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utente = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        Annuncio annuncio = annuncioRepository.findById(propostaDTO.getIdAnnuncio())
            .orElseThrow(() -> new AnnuncioNotFoundException("Annuncio non trovato"));

        Trattativa trattativa = new Trattativa();
        trattativa.setUtente(utente);
        trattativa.setAnnuncio(annuncio);
        trattativa.setPrezzoOfferto(propostaDTO.getPrezzoOfferto());
        trattativa.setMessaggio(propostaDTO.getMessaggio());
        trattativa.setDataProposta(LocalDateTime.now());
        trattativa.setStato(StatoTrattativa.IN_ATTESA);

        Trattativa salvata = trattativaRepository.save(trattativa);

        return TrattativaMapper.toDto(salvata);
    }



    @Override
    public TrattativaDTO aggiornaTrattativa(TrattativaDTO dto) {
        
    	Trattativa esistente = trattativaRepository.findById(dto.getIdTrattativa())
            .orElseThrow(() -> new TrattativaNotFoundException("Trattativa con ID " + dto.getIdTrattativa() + " non trovata"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utente = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        Annuncio annuncio = annuncioRepository.findById(dto.getIdAnnuncio())
            .orElseThrow(() -> new AnnuncioNotFoundException("Annuncio non trovato"));

        Trattativa aggiornata = TrattativaMapper.toEntity(dto);

        aggiornata.setIdTrattativa(esistente.getIdTrattativa());
        aggiornata.setUtente(utente);
        aggiornata.setAnnuncio(annuncio);

        aggiornata.setDataProposta(esistente.getDataProposta());

        aggiornata.setStato(dto.getStato() != null ? dto.getStato() : esistente.getStato());

        Trattativa salvata = trattativaRepository.save(aggiornata);

        return TrattativaMapper.toDto(salvata);
    }


    @Override
    public void eliminaTrattativa(Long id) {
        if (!trattativaRepository.existsById(id)) {
            throw new TrattativaNotFoundException("Trattativa con ID " + id + " non trovata");
        }
        trattativaRepository.deleteById(id);
    }

    @Override
    public Optional<TrattativaDTO> trovaPerId(Long id) {
    	Trattativa trattativa = trattativaRepository.findById(id)
    	        .orElseThrow(() -> new TrattativaNotFoundException("Trattativa con ID " + id + " non trovata"));

    	return Optional.of(TrattativaMapper.toDto(trattativa));
    }

    @Override
    public List<TrattativaDTO> trovaTutti() {
    	List<Trattativa> trattative = trattativaRepository.findAll();
	    if (trattative.isEmpty()) {
	        throw new TrattativaNotFoundException("Nessuna trattativa trovata");
	    }
	    return trattative.stream()
	                   .map(TrattativaMapper::toDto)
	                   .collect(Collectors.toList());
    }
    
    @Override
    public Page<TrattativaDTO> trovaTuttiPaginati(Pageable pageable) {
        return trattativaRepository.findAll(pageable)
                .map(TrattativaMapper::toDto);
    }
    
    @Override
    public Page<TrattativaPersonaleDTO> trovaTuttiPersonaliPaginati(Pageable pageable) {
    	String emailUtenteLog = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utenteLog = utenteRepository.findByEmail(emailUtenteLog)
            .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));
        
        return trattativaRepository.findAllByUtente_IdUtente(utenteLog.getIdUtente(), pageable)
        		.map(trattativa -> new TrattativaPersonaleDTO(
        				trattativa.getIdTrattativa(),
        				trattativa.getAnnuncio().getIdAnnuncio(),
        				trattativa.getPrezzoOfferto(),
        				trattativa.getDataProposta(),
        				trattativa.getStato(),
        				trattativa.getMessaggio()
        		));
    }
    
    @Override
    public Page<TrattativaDTO> trovaTuttiPerAnnuncioPersonale(Long idAnnuncio, Pageable pageable) {
    	String emailUtenteLog = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utenteLog = utenteRepository.findByEmail(emailUtenteLog)
            .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));
        
        Annuncio annuncio = annuncioRepository.findById(idAnnuncio)
        		.orElseThrow(() -> new AnnuncioNotFoundException("Annuncio non trovato"));
        
        if (!annuncio.getCreatore().equals(utenteLog)) {
        	throw new AnnuncioOwnershipException("Non puoi visualizzare trattative di annunci che non sono tuoi");
        }
        
        if (!annuncio.isVisibile()) {
            throw new AnnuncioNotVisibleException("Le trattative per questo annuncio non sono più visibili.");
        }
        
        return trattativaRepository.findAllByAnnuncio_IdAnnuncio(idAnnuncio, pageable)
        		.map(trattativa -> new TrattativaDTO(
        				trattativa.getIdTrattativa(),
        				trattativa.getUtente().getIdUtente(),
        				trattativa.getAnnuncio().getIdAnnuncio(),
        				trattativa.getPrezzoOfferto(),
        				trattativa.getDataProposta(),
        				trattativa.getStato(),
        				trattativa.getMessaggio()
        				
        				));
    }

	@Override
	@Transactional
	public void vendi(Long idTrattativa) {
		
		Trattativa trattativa = trattativaRepository.findById(idTrattativa)
			    .orElseThrow(() -> new TrattativaNotFoundException("Trattativa non trovata"));
		
		Annuncio annuncio = annuncioRepository.findById(trattativa.getAnnuncio().getIdAnnuncio())
				.orElseThrow(() -> new AnnuncioNotFoundException("Annuncio corrispondente non trovato"));
		
		if (trattativa.getStato().equals(StatoTrattativa.CONCLUSA)) {
			throw new InvalidTrattativaStateException("Non puoi accettare una trattativa già conclusa");
		}
		
		if (!trattativa.getStato().equals(StatoTrattativa.IN_ATTESA)) {
			throw new InvalidTrattativaStateException("La trattativa non è in stato di attesa e non può essere accettata");
		}
		
		String emailUtenteLog = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utenteLog = utenteRepository.findByEmail(emailUtenteLog)
            .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));
		
		Utente venditore = annuncio.getVenditore();
		
		Utente acquirente = trattativa.getUtente();
		
		if (venditore.equals(acquirente)) {
		    throw new SameUserTrattativaException("Il venditore e l'acquirente non possono essere la stessa persona");
		}
		
		Immobile immobileVenduto = annuncio.getImmobile();
		
		if (!immobileVenduto.getStato().equals(StatoImmobile.DISPONIBILE)) {
			throw new InvalidImmobileStateException("L'immobile non è più disponibile");
		}
		
		if (!annuncio.getCreatore().equals(utenteLog)) {
        	throw new AnnuncioOwnershipException("Non puoi gestire trattative di annunci che non sono tuoi");
        }
		
		if (!immobileVenduto.getProprietario().equals(venditore)) {
			throw new ImmobileOwnershipException("L'immobile non è posseduto dal venditore");
		}
		
		venditore.rimuoviImmobile(immobileVenduto);
		
		acquirente.aggiungiImmobile(immobileVenduto);
		
		trattativa.setStato(StatoTrattativa.CONCLUSA);
		
		immobileVenduto.setStato(StatoImmobile.VENDUTO);
		
		// CANCELLAZIONE ANNUNCIO E TRATTATIVE COLLEGATE A ESSO A VENDITA CONCLUSA
		//FIXME per ora non posso salvare nè storico degli annunci e nè storico delle trattative
		annuncio.setVisibile(false);
		List<Trattativa> trattativeCollegate = trattativaRepository.findAllByAnnuncio_IdAnnuncio(annuncio.getIdAnnuncio());
		trattativaRepository.deleteAll(trattativeCollegate);
		annuncioRepository.delete(annuncio);
		
		immobileRepository.save(immobileVenduto);
		
		utenteRepository.save(venditore);
		
		utenteRepository.save(acquirente);
				
	}
}
