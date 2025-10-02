package com.demo.immobiliare.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AnnuncioDTO {

    private Long idAnnuncio;

    @NotNull(message = "La data di pubblicazione è obbligatoria")
    private LocalDateTime dataPubblicazione;

    private boolean visibile;

    @Min(value = 0, message = "Le visualizzazioni non possono essere negative")
    private int visualizzazioni;

    @NotNull(message = "L'immobile associato è obbligatorio")
    private ImmobileDTO immobile;

    @NotNull(message = "Il venditore è obbligatorio")
    private UtenteDTO venditore;

    public AnnuncioDTO() {
    }

    public AnnuncioDTO(Long idAnnuncio, LocalDateTime dataPubblicazione, boolean visibile,
                       int visualizzazioni, ImmobileDTO immobile, UtenteDTO venditore) {
        this.idAnnuncio = idAnnuncio;
        this.dataPubblicazione = dataPubblicazione;
        this.visibile = visibile;
        this.visualizzazioni = visualizzazioni;
        this.immobile = immobile;
        this.venditore = venditore;
    }

    // Getter e Setter

    public Long getIdAnnuncio() {
        return idAnnuncio;
    }

    public void setIdAnnuncio(Long idAnnuncio) {
        this.idAnnuncio = idAnnuncio;
    }

    public LocalDateTime getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(LocalDateTime dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    public int getVisualizzazioni() {
        return visualizzazioni;
    }

    public void setVisualizzazioni(int visualizzazioni) {
        this.visualizzazioni = visualizzazioni;
    }

    public ImmobileDTO getImmobile() {
        return immobile;
    }

    public void setImmobile(ImmobileDTO immobile) {
        this.immobile = immobile;
    }

    public UtenteDTO getVenditore() {
        return venditore;
    }

    public void setVenditore(UtenteDTO venditore) {
        this.venditore = venditore;
    }
}
