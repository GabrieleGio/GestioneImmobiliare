package com.demo.immobiliare.service;

import com.demo.immobiliare.dto.TrattativaDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITrattativaService {
    TrattativaDTO creaTrattativa(TrattativaDTO trattativaDTO) throws Exception;
    TrattativaDTO aggiornaTrattativa(TrattativaDTO trattativaDTO) throws Exception;
    void eliminaTrattativa(Long id) throws Exception;
    Optional<TrattativaDTO> trovaPerId(Long id);
    List<TrattativaDTO> trovaTutti();
    Page<TrattativaDTO> trovaTuttiPaginati(Pageable pageable);
    void vendi(Long id) throws Exception;;
}
