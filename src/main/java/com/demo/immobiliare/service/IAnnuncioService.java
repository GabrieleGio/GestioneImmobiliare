package com.demo.immobiliare.service;

import com.demo.immobiliare.dto.AnnuncioDTO;
import com.demo.immobiliare.dto.AnnuncioHomeDTO;
import com.demo.immobiliare.dto.AnnuncioPersonaleDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAnnuncioService {
    AnnuncioDTO creaAnnuncio(AnnuncioDTO annuncioDTO);
    AnnuncioDTO pubblicaAnnuncio(Long idAnnuncio);
    AnnuncioDTO aggiornaAnnuncio(AnnuncioDTO annuncioDTO);
    void eliminaAnnuncio(Long id);
    Optional<AnnuncioDTO> trovaPerId(Long id);
    List<AnnuncioDTO> trovaTutti();
    Page<AnnuncioHomeDTO> trovaTuttiPaginati(Pageable pageable);
    Page<AnnuncioPersonaleDTO> trovaTuttiPersonaliPaginati(Pageable pageable);
}
