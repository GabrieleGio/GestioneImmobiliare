package com.demo.immobiliare.dto;

import com.demo.immobiliare.model.StatoTrattativa;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TrattativaDTO {

    private Long idTrattativa;

    @NotNull(message = "L'utente è obbligatorio")
    private UtenteDTO utente;

    @NotNull(message = "L'annuncio è obbligatorio")
    private AnnuncioDTO annuncio;

    @DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo offerto deve essere maggiore di zero")
    @Digits(integer = 10, fraction = 2, message = "Prezzo offerto non valido")
    @NotNull(message = "Il prezzo offerto è obbligatorio")
    private BigDecimal prezzoOfferto;

    @NotNull(message = "La data della proposta è obbligatoria")
    private LocalDateTime dataProposta;

    @NotNull(message = "Lo stato della trattativa è obbligatorio")
    private StatoTrattativa stato;

    @Size(max = 500, message = "Il messaggio non può superare i 500 caratteri")
    private String messaggio;

    public TrattativaDTO() {
    }

    public TrattativaDTO(Long idTrattativa, UtenteDTO utente, AnnuncioDTO annuncio, BigDecimal prezzoOfferto,
                         LocalDateTime dataProposta, StatoTrattativa stato, String messaggio) {
        this.idTrattativa = idTrattativa;
        this.utente = utente;
        this.annuncio = annuncio;
        this.prezzoOfferto = prezzoOfferto;
        this.dataProposta = dataProposta;
        this.stato = stato;
        this.messaggio = messaggio;
    }

    public Long getIdTrattativa() {
        return idTrattativa;
    }

    public void setIdTrattativa(Long idTrattativa) {
        this.idTrattativa = idTrattativa;
    }

    public UtenteDTO getUtente() {
        return utente;
    }

    public void setUtente(UtenteDTO utente) {
        this.utente = utente;
    }

    public AnnuncioDTO getAnnuncio() {
        return annuncio;
    }

    public void setAnnuncio(AnnuncioDTO annuncio) {
        this.annuncio = annuncio;
    }

    public BigDecimal getPrezzoOfferto() {
        return prezzoOfferto;
    }

    public void setPrezzoOfferto(BigDecimal prezzoOfferto) {
        this.prezzoOfferto = prezzoOfferto;
    }

    public LocalDateTime getDataProposta() {
        return dataProposta;
    }

    public void setDataProposta(LocalDateTime dataProposta) {
        this.dataProposta = dataProposta;
    }

    public StatoTrattativa getStato() {
        return stato;
    }

    public void setStato(StatoTrattativa stato) {
        this.stato = stato;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
}
