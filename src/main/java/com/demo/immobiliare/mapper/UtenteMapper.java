package com.demo.immobiliare.mapper;

import com.demo.immobiliare.dto.RegisterDTO;
import com.demo.immobiliare.dto.UtenteDTO;
import com.demo.immobiliare.model.Ruolo;
import com.demo.immobiliare.model.Utente;

public class UtenteMapper {

    public static UtenteDTO toDto(Utente utente) {
        if (utente == null) {
            return null;
        }
        return new UtenteDTO(
            utente.getIdUtente(),
            utente.getUsername(),
            utente.getEmail(),
            utente.getRuolo()
        );
    }

    public static Utente toEntity(UtenteDTO dto) {
        if (dto == null) {
            return null;
        }
        Utente utente = new Utente();
        utente.setIdUtente(dto.getIdUtente());
        utente.setUsername(dto.getUsername());
        utente.setEmail(dto.getEmail());
        utente.setRuolo(dto.getRuolo());
        return utente;
    }
    
    public static Utente fromRegisterDtoToEntity(RegisterDTO dto, String passwordCriptata) {
        if (dto == null) {
            return null;
        }
        Utente utente = new Utente();
        utente.setUsername(dto.getUsername());
        utente.setEmail(dto.getEmail());
        utente.setPassword(passwordCriptata);
        utente.setRuolo(Ruolo.CLIENTE);
        return utente;
    }
}
