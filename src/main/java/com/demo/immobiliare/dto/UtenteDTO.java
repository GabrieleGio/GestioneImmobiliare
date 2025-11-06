package com.demo.immobiliare.dto;

import com.demo.immobiliare.model.Ruolo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UtenteDTO {

    private Long idUtente;
    
    @Size(min = 3, max = 25, message = "L'username deve essere compreso tra i 3 e i 25 caratteri")
	@NotBlank(message = "L'username è obbligatorio")
    private String username;
    
    @Email
	@NotNull(message = "L'email è obbligatoria")
	@Size(max = 255, message = "L'email non può superare i 255 caratteri")
    private String email;
    
    @NotNull(message = "Il ruolo è obbligatorio")
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
