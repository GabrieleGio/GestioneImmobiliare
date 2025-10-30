package com.demo.immobiliare.service;

import com.demo.immobiliare.dto.ImmobileDTO;
import com.demo.immobiliare.dto.ImmobilePersonaleDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IImmobileService {
    ImmobileDTO creaImmobile(ImmobileDTO immobileDTO);
    ImmobileDTO aggiornaImmobile(ImmobileDTO immobileDTO);
    void eliminaImmobile(Long id);
    Optional<ImmobileDTO> trovaPerId(Long id);
    List<ImmobileDTO> trovaTutti();
    Page<ImmobilePersonaleDTO> trovaTuttiPersonaliPaginati(Pageable pageable);
    }
