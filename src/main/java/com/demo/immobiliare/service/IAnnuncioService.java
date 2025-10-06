package com.demo.immobiliare.service;

import com.demo.immobiliare.dto.AnnuncioDTO;

import java.util.List;
import java.util.Optional;

public interface IAnnuncioService {
    AnnuncioDTO creaAnnuncio(AnnuncioDTO annuncioDTO);
    AnnuncioDTO aggiornaAnnuncio(AnnuncioDTO annuncioDTO) throws Exception;
    void eliminaAnnuncio(Long id) throws Exception;
    Optional<AnnuncioDTO> trovaPerId(Long id);
    List<AnnuncioDTO> trovaTutti();
}
