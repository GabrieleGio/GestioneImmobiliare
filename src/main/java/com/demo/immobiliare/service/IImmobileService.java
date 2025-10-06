package com.demo.immobiliare.service;

import com.demo.immobiliare.dto.ImmobileDTO;

import java.util.List;
import java.util.Optional;

public interface IImmobileService {
    ImmobileDTO creaImmobile(ImmobileDTO immobileDTO);
    ImmobileDTO aggiornaImmobile(ImmobileDTO immobileDTO) throws Exception;
    void eliminaImmobile(Long id) throws Exception;
    Optional<ImmobileDTO> trovaPerId(Long id);
    List<ImmobileDTO> trovaTutti();
}
