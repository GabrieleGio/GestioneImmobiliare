package com.demo.immobiliare.dto;

import com.demo.immobiliare.model.Ruolo;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UtenteDTO {

    private Long idUtente;

    @Size(min = 3, max = 25, message = "L'username deve essere compreso tra i 3 e i 25 caratteri")
    private String username;

    @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "Email non valida")
    private String email;

    @Size(min = 8, message = "La password deve essere composta da minimo 5 caratteri")
    private String password;

    private Ruolo ruolo;

    public UtenteDTO() {
    }

    public UtenteDTO(Long idUtente,
                     String username,
                     String email,
                     String password,
                     Ruolo ruolo) {
        this.idUtente = idUtente;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }
}
