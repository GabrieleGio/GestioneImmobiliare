package com.demo.immobiliare.service.impl;

import com.demo.immobiliare.dto.TrattativaDTO;
import com.demo.immobiliare.dto.TrattativaPersonaleDTO;
import com.demo.immobiliare.dto.TrattativaPropostaDTO;
import com.demo.immobiliare.mapper.TrattativaMapper;
import com.demo.immobiliare.model.Annuncio;
import com.demo.immobiliare.model.Immobile;
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

    public TrattativaDTO creaTrattativa(TrattativaPropostaDTO propostaDTO) throws Exception {
    	
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utente = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new Exception("Utente non trovato"));

        Annuncio annuncio = annuncioRepository.findById(propostaDTO.getIdAnnuncio())
            .orElseThrow(() -> new Exception("Annuncio non trovato"));

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
    public TrattativaDTO aggiornaTrattativa(TrattativaDTO dto) throws Exception {
        
    	Trattativa esistente = trattativaRepository.findById(dto.getIdTrattativa())
            .orElseThrow(() -> new Exception("Trattativa con ID " + dto.getIdTrattativa() + " non trovata"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utente = utenteRepository.findByEmail(email)
            .orElseThrow(() -> new Exception("Utente non trovato"));

        Annuncio annuncio = annuncioRepository.findById(dto.getIdAnnuncio())
            .orElseThrow(() -> new Exception("Annuncio non trovato"));

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
    public void eliminaTrattativa(Long id) throws Exception {
        if (!trattativaRepository.existsById(id)) {
            throw new Exception("Trattativa con ID " + id + " non trovata");
        }
        trattativaRepository.deleteById(id);
    }

    @Override
    public Optional<TrattativaDTO> trovaPerId(Long id) {
        return trattativaRepository.findById(id)
                .map(TrattativaMapper::toDto);
    }

    @Override
    public List<TrattativaDTO> trovaTutti() {
        return trattativaRepository.findAll()
                .stream()
                .map(TrattativaMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Page<TrattativaDTO> trovaTuttiPaginati(Pageable pageable) {
        return trattativaRepository.findAll(pageable)
                .map(TrattativaMapper::toDto);
    }
    
    @Override
    public Page<TrattativaPersonaleDTO> trovaTuttiPersonaliPaginati(Pageable pageable) throws Exception {
    	String emailUtenteLog = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utenteLog = utenteRepository.findByEmail(emailUtenteLog)
            .orElseThrow(() -> new Exception("Utente non trovato"));
        
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
	@Transactional
	public void vendi(Long idTrattativa) throws Exception {
		
		Trattativa trattativa = trattativaRepository.findById(idTrattativa)
			    .orElseThrow(() -> new RuntimeException("Trattativa non trovata"));
		
		if (!trattativa.getStato().equals(StatoTrattativa.ACCETTATA)) {
			throw new Exception("Errore, la trattativa non è stata ancora accettata");
		}
		
		Annuncio annuncio = annuncioRepository.findById(trattativa.getAnnuncio().getIdAnnuncio())
				.orElseThrow(() -> new RuntimeException("Annuncio corrispondente non trovato"));
		
		Utente venditore = annuncio.getVenditore();
		
		Utente acquirente = trattativa.getUtente();
		
		Immobile immobileVenduto = annuncio.getImmobile();
		
		venditore.rimuoviImmobile(immobileVenduto);
		
		acquirente.aggiungiImmobile(immobileVenduto);
		
		trattativa.setStato(StatoTrattativa.CONCLUSA);
		
		annuncio.setVisibile(false);
		
		immobileRepository.save(immobileVenduto);
		
		utenteRepository.save(venditore);
		
		utenteRepository.save(acquirente);
		
		annuncioRepository.save(annuncio);
		
		trattativaRepository.save(trattativa);
		
	}
}
