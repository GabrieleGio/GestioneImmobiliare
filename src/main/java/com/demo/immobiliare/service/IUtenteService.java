package com.demo.immobiliare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.immobiliare.dto.RegisterDTO;
import com.demo.immobiliare.dto.UtenteDTO;

public interface IUtenteService {
    UtenteDTO registraUtente(RegisterDTO utente) throws Exception;
    UtenteDTO aggiornaUtente(UtenteDTO utente) throws Exception;
    Optional<UtenteDTO> trovaPerId(Long idUtente);
    Optional<UtenteDTO> trovaPerUsername(String username);
    Optional<UtenteDTO> trovaPerEmail(String email);
    List<UtenteDTO> trovaTutti();
    Page<UtenteDTO> trovaTuttiPaginati(Pageable pageable);
    boolean esisteUsername(String username);
    boolean esisteEmail(String email);
    boolean verificaPassword(String username, String rawPassword) throws Exception;
}