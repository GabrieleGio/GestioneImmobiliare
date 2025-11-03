package com.demo.immobiliare.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class AnnuncioDTO {

    private Long idAnnuncio;
    
    @NotNull(message = "La data di pubblicazione è obbligatoria")
    @PastOrPresent(message = "La data di pubblicazione non può essere nel futuro")
    private LocalDateTime dataPubblicazione;
    
    @NotNull(message = "La visibilità deve essere impostata")
    private boolean visibile;
    
    @Min(value = 0, message = "Le visualizzazioni non possono essere negative")
    @NotNull(message = "Le visualizzazioni sono obbligatorie")
    private int visualizzazioni;
    
    @NotNull(message = "L'immobile associato è obbligatorio")
    private Long idImmobile;
    
    @NotNull(message = "Il venditore è obbligatorio")
    private Long idVenditore;
    
    @NotNull(message = "Il creatore è obbligatorio")
    private Long idCreatore;

    public AnnuncioDTO() {}

    public AnnuncioDTO(Long idAnnuncio, LocalDateTime dataPubblicazione, boolean visibile, int visualizzazioni,
                       Long idImmobile, Long idVenditore, Long idCreatore) {
        this.idAnnuncio = idAnnuncio;
        this.dataPubblicazione = dataPubblicazione;
        this.visibile = visibile;
        this.visualizzazioni = visualizzazioni;
        this.idImmobile = idImmobile;
        this.idVenditore = idVenditore;
        this.idCreatore = idCreatore;
    }

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

    public Long getIdImmobile() {
        return idImmobile;
    }

    public void setIdImmobile(Long idImmobile) {
        this.idImmobile = idImmobile;
    }

    public Long getIdVenditore() {
        return idVenditore;
    }

    public void setIdVenditore(Long idVenditore) {
        this.idVenditore = idVenditore;
    }

    public Long getIdCreatore() {
        return idCreatore;
    }

    public void setIdCreatore(Long idCreatore) {
        this.idCreatore = idCreatore;
    }
}
