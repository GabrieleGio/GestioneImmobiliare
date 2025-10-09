package com.demo.immobiliare.dto;

import com.demo.immobiliare.model.Ruolo;

public class UtenteDTO {

    private Long idUtente;
    private String username;
    private String email;
    private Ruolo ruolo;

    public UtenteDTO() {
    }

    public UtenteDTO(Long idUtente, String username, String email, Ruolo ruolo) {
        this.idUtente = idUtente;
        this.username = username;
        this.email = email;
        this.ruolo = ruolo;
    }

    public Long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }
}
