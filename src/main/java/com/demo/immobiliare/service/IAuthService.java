package com.demo.immobiliare.service;

import com.demo.immobiliare.dto.AuthResponseDTO;
import com.demo.immobiliare.dto.LoginDTO;

public interface IAuthService {

    AuthResponseDTO login(LoginDTO request);
    
}

