package com.demo.immobiliare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.immobiliare.dto.RegisterDTO;
import com.demo.immobiliare.dto.UtenteDTO;
import com.demo.immobiliare.exception.EmailAlreadyInUseException;
import com.demo.immobiliare.exception.PasswordMismatchException;
import com.demo.immobiliare.exception.UserNotFoundException;
import com.demo.immobiliare.exception.UsernameAlreadyInUseException;
import com.demo.immobiliare.mapper.UtenteMapper;
import com.demo.immobiliare.model.Utente;
import com.demo.immobiliare.repository.UtenteRepository;
import com.demo.immobiliare.service.IUtenteService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UtenteService implements IUtenteService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UtenteDTO registraUtente(RegisterDTO registerDTO) {
        if (utenteRepository.existsByUsername(registerDTO.getUsername())) {
            throw new UsernameAlreadyInUseException("Username già in uso");
        }

        if (utenteRepository.existsByEmail(registerDTO.getEmail())) {
            throw new EmailAlreadyInUseException("Email già in uso");
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new PasswordMismatchException("Le password non coincidono");
        }
        
        String passwordCriptata = passwordEncoder.encode(registerDTO.getPassword());
        Utente utente = UtenteMapper.fromRegisterDtoToEntity(registerDTO, passwordCriptata);
        Utente saved = utenteRepository.save(utente);
        return UtenteMapper.toDto(saved);
    }

    @Override
    public UtenteDTO aggiornaUtente(UtenteDTO utenteDTO) {
        Optional<Utente> utenteEsistenteOpt = utenteRepository.findById(utenteDTO.getIdUtente());
        if (utenteEsistenteOpt.isEmpty()) {
            throw new UserNotFoundException("Utente non trovato");
        }

        Utente utenteEsistente = utenteEsistenteOpt.get();

        if (!utenteEsistente.getUsername().equals(utenteDTO.getUsername()) &&
            utenteRepository.existsByUsername(utenteDTO.getUsername())) {
            throw new UsernameAlreadyInUseException("Username già in uso");
        }
        
        if (!utenteEsistente.getEmail().equals(utenteDTO.getEmail()) &&
            utenteRepository.existsByEmail(utenteDTO.getEmail())) {
            throw new EmailAlreadyInUseException("Email già in uso");
        }

        utenteEsistente.setUsername(utenteDTO.getUsername());
        utenteEsistente.setEmail(utenteDTO.getEmail());

        utenteEsistente.setRuolo(utenteDTO.getRuolo());

        Utente saved = utenteRepository.save(utenteEsistente);
        return UtenteMapper.toDto(saved);
    }

    @Override
    public Optional<UtenteDTO> trovaPerId(Long idUtente) {
    	Utente utente = utenteRepository.findById(idUtente)
    	        .orElseThrow(() -> new UserNotFoundException("Utente con ID " + idUtente + " non trovato"));

    	return Optional.of(UtenteMapper.toDto(utente));
    }

    @Override
    public Optional<UtenteDTO> trovaPerUsername(String username) {
    	Utente utente = utenteRepository.findByUsername(username)
    	        .orElseThrow(() -> new UserNotFoundException("Utente con username " + username + " non trovato"));

    	return Optional.of(UtenteMapper.toDto(utente));
    }
    
    @Override
    public Optional<UtenteDTO> trovaPerEmail(String email){
    	Utente utente = utenteRepository.findByEmail(email)
    	        .orElseThrow(() -> new UserNotFoundException("Utente con email " + email + " non trovato"));

    	return Optional.of(UtenteMapper.toDto(utente));
    }

    @Override
    public List<UtenteDTO> trovaTutti() {
    	List<Utente> utenti = utenteRepository.findAll();
	    if (utenti.isEmpty()) {
	        throw new UserNotFoundException("Nessun utente trovato");
	    }
	    return utenti.stream()
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
    public boolean verificaPassword(String email, String rawPassword) {
        Utente utente = utenteRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        return passwordEncoder.matches(rawPassword, utente.getPassword());
    }

}
