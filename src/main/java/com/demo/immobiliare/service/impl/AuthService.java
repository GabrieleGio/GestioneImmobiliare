package com.demo.immobiliare.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.immobiliare.dto.AuthResponseDTO;
import com.demo.immobiliare.dto.LoginDTO;
import com.demo.immobiliare.exception.InvalidCredentialsException;
import com.demo.immobiliare.exception.UserNotFoundException;
import com.demo.immobiliare.model.Utente;
import com.demo.immobiliare.repository.UtenteRepository;
import com.demo.immobiliare.security.JwtUtil;
import com.demo.immobiliare.service.IAuthService;

@Service
public class AuthService implements IAuthService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponseDTO login(LoginDTO request) {
        Utente utente = utenteRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Credenziali non valide"));

        if (!passwordEncoder.matches(request.getPassword(), utente.getPassword())) {
            throw new InvalidCredentialsException("Credenziali non valide");
        }

        String token = jwtUtil.generateToken(utente.getEmail(), utente.getIdUtente());
        long expiresIn = jwtUtil.getExpirationTime();

        return new AuthResponseDTO(token, "Bearer", expiresIn);
    }
}
