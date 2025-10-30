package com.demo.immobiliare.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TrattativaPropostaDTO {

    private Long idAnnuncio;
    
    @NotNull(message = "Prezzo offerto non valido")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di zero")
    private BigDecimal prezzoOfferto;
    
    @Size(max = 500, message = "Il messaggio non può superare i 500 caratteri")
    private String messaggio;

    public TrattativaPropostaDTO() {}

    public TrattativaPropostaDTO(Long idAnnuncio, BigDecimal prezzoOfferto, String messaggio) {
        this.idAnnuncio = idAnnuncio;
        this.prezzoOfferto = prezzoOfferto;
        this.messaggio = messaggio;
    }

    public Long getIdAnnuncio() {
        return idAnnuncio;
    }

    public void setIdAnnuncio(Long idAnnuncio) {
        this.idAnnuncio = idAnnuncio;
    }

    public BigDecimal getPrezzoOfferto() {
        return prezzoOfferto;
    }

    public void setPrezzoOfferto(BigDecimal prezzoOfferto) {
        this.prezzoOfferto = prezzoOfferto;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
}
