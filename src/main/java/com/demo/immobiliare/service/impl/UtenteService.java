package com.demo.immobiliare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.immobiliare.dto.UtenteDTO;
import com.demo.immobiliare.mapper.UtenteMapper;
import com.demo.immobiliare.model.Utente;
import com.demo.immobiliare.repository.UtenteRepository;
import com.demo.immobiliare.service.IUtenteService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UtenteService implements IUtenteService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public UtenteDTO registraUtente(UtenteDTO utenteDTO) throws Exception {
        if (utenteRepository.existsByUsername(utenteDTO.getUsername())) {
            throw new Exception("Username già in uso");
        }
        if (utenteRepository.existsByEmail(utenteDTO.getEmail())) {
            throw new Exception("Email già in uso");
        }

        Utente utente = UtenteMapper.toEntity(utenteDTO);
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));

        Utente saved = utenteRepository.save(utente);
        return UtenteMapper.toDto(saved);
    }

    @Override
    public UtenteDTO aggiornaUtente(UtenteDTO utenteDTO) throws Exception {
        Optional<Utente> utenteEsistenteOpt = utenteRepository.findById(utenteDTO.getIdUtente());
        if (utenteEsistenteOpt.isEmpty()) {
            throw new Exception("Utente non trovato");
        }

        Utente utenteEsistente = utenteEsistenteOpt.get();

        if (!utenteEsistente.getUsername().equals(utenteDTO.getUsername()) &&
            utenteRepository.existsByUsername(utenteDTO.getUsername())) {
            throw new Exception("Username già in uso");
        }

        if (!utenteEsistente.getEmail().equals(utenteDTO.getEmail()) &&
            utenteRepository.existsByEmail(utenteDTO.getEmail())) {
            throw new Exception("Email già in uso");
        }

        utenteEsistente.setUsername(utenteDTO.getUsername());
        utenteEsistente.setEmail(utenteDTO.getEmail());

        if (!passwordEncoder.matches(utenteDTO.getPassword(), utenteEsistente.getPassword())) {
            utenteEsistente.setPassword(passwordEncoder.encode(utenteDTO.getPassword()));
        }

        utenteEsistente.setRuolo(utenteDTO.getRuolo());

        Utente saved = utenteRepository.save(utenteEsistente);
        return UtenteMapper.toDto(saved);
    }

    @Override
    public Optional<UtenteDTO> trovaPerId(Long idUtente) {
        return utenteRepository.findById(idUtente)
                .map(UtenteMapper::toDto);
    }

    @Override
    public Optional<UtenteDTO> trovaPerUsername(String username) {
        return utenteRepository.findByUsername(username)
                .map(UtenteMapper::toDto);
    }

    @Override
    public List<UtenteDTO> trovaTutti() {
        return utenteRepository.findAll()
                .stream()
                .map(UtenteMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Page<UtenteDTO> trovaTuttiPaginati(Pageable pageable) {
        return utenteRepository.findAll(pageable)
                .map(UtenteMapper::toDto);
    }

    @Override
    public boolean esisteUsername(String username) {
        return utenteRepository.existsByUsername(username);
    }

    @Override
    public boolean esisteEmail(String email) {
        return utenteRepository.existsByEmail(email);
    }

    @Override
    public boolean verificaPassword(String username, String rawPassword) throws Exception {
        Utente utente = utenteRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("Utente non trovato"));

        return passwordEncoder.matches(rawPassword, utente.getPassword());
    }
}
