package com.demo.immobiliare.service;

import com.demo.immobiliare.dto.TrattativaDTO;

import java.util.List;
import java.util.Optional;

public interface ITrattativaService {
    TrattativaDTO creaTrattativa(TrattativaDTO trattativaDTO);
    TrattativaDTO aggiornaTrattativa(TrattativaDTO trattativaDTO) throws Exception;
    void eliminaTrattativa(Long id) throws Exception;
    Optional<TrattativaDTO> trovaPerId(Long id);
    List<TrattativaDTO> trovaTutti();
}
