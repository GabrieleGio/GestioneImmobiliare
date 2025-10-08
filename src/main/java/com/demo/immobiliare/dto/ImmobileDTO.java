package com.demo.immobiliare.dto;

import com.demo.immobiliare.model.StatoImmobile;
import com.demo.immobiliare.model.Tipologia;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ImmobileDTO {

    private Long idImmobile;

    @Size(max = 255, message = "Il titolo non può superare i 255 caratteri")
    @NotBlank(message = "Il titolo è obbligatorio")
    private String titolo;

    @Size(max = 255, message = "La descrizione non può superare i 255 caratteri")
    @NotBlank(message = "La descrizione è obbligatoria")
    private String descrizione;

    @DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo deve essere maggiore di zero")
    @Digits(integer = 10, fraction = 2, message = "Prezzo non valido")
    @NotNull(message = "Il prezzo è obbligatorio")
    private BigDecimal prezzo;

    @NotNull(message = "La tipologia è obbligatoria")
    private Tipologia tipologia;

    @NotNull(message = "Lo stato è obbligatorio")
    private StatoImmobile stato;

    @Min(value = 1, message = "La superficie deve essere almeno 1 m²")
    @NotNull(message = "La superficie è obbligatoria")
    private Integer superficie;

    @Size(max = 255, message = "L'indirizzo non può superare i 255 caratteri")
    @NotBlank(message = "L'indirizzo è obbligatorio")
    @Pattern(
        regexp = "^[a-zA-Z0-9àèéìòùÀÈÉÌÒÙ\\s,.'-]+$",
        message = "L'indirizzo contiene caratteri non validi"
    )
    private String indirizzo;

    public ImmobileDTO() {
    }

    public ImmobileDTO(Long idImmobile, String titolo, String descrizione, BigDecimal prezzo,
                       Tipologia tipologia, StatoImmobile stato, Integer superficie, String indirizzo) {
        this.idImmobile = idImmobile;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.tipologia = tipologia;
        this.stato = stato;
        this.superficie = superficie;
        this.indirizzo = indirizzo;
    }

    public Long getIdImmobile() {
        return idImmobile;
    }

    public void setIdImmobile(Long idImmobile) {
        this.idImmobile = idImmobile;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    public StatoImmobile getStato() {
        return stato;
    }

    public void setStato(StatoImmobile stato) {
        this.stato = stato;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
