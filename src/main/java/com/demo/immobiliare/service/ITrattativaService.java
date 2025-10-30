package com.demo.immobiliare.service;

import com.demo.immobiliare.dto.TrattativaDTO;
import com.demo.immobiliare.dto.TrattativaPersonaleDTO;
import com.demo.immobiliare.dto.TrattativaPropostaDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITrattativaService {
    TrattativaDTO creaTrattativa(TrattativaPropostaDTO trattativaPropostaDTO);
    TrattativaDTO aggiornaTrattativa(TrattativaDTO trattativaDTO);
    void eliminaTrattativa(Long id);
    Optional<TrattativaDTO> trovaPerId(Long id);
    List<TrattativaDTO> trovaTutti();
    Page<TrattativaDTO> trovaTuttiPaginati(Pageable pageable);
    Page<TrattativaPersonaleDTO> trovaTuttiPersonaliPaginati(Pageable pageable);
    Page<TrattativaDTO> trovaTuttiPerAnnuncioPersonale(Long idAnnuncio, Pageable pageable);
    void vendi(Long id);
}
