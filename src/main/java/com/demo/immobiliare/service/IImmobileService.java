package com.demo.immobiliare.service;

import com.demo.immobiliare.dto.ImmobileDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IImmobileService {
    ImmobileDTO creaImmobile(ImmobileDTO immobileDTO);
    ImmobileDTO aggiornaImmobile(ImmobileDTO immobileDTO) throws Exception;
    void eliminaImmobile(Long id) throws Exception;
    Optional<ImmobileDTO> trovaPerId(Long id);
    List<ImmobileDTO> trovaTutti();
    Page<ImmobileDTO> trovaTuttiPaginati(Pageable pageable);
}
