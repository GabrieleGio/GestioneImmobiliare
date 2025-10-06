package com.demo.immobiliare.service;

import java.util.List;
import java.util.Optional;

import com.demo.immobiliare.dto.UtenteDTO;

public interface IUtenteService {
    UtenteDTO registraUtente(UtenteDTO utente) throws Exception;
    UtenteDTO aggiornaUtente(UtenteDTO utente) throws Exception;
    Optional<UtenteDTO> trovaPerId(Long idUtente);
    Optional<UtenteDTO> trovaPerUsername(String username);
    List<UtenteDTO> trovaTutti();
    boolean esisteUsername(String username);
    boolean esisteEmail(String email);
    boolean verificaPassword(String username, String rawPassword) throws Exception;
}