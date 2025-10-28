package com.demo.immobiliare.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AnnuncioPersonaleDTO {
	
	private Long idAnnuncio;
    private LocalDateTime dataPubblicazione;
    private boolean visibile;
    private int visualizzazioni;
    private Long idImmobile;
    private String titoloImmobile;
    private String indirizzo;
    private BigDecimal prezzo;
    private Long idVenditore;
    
    public AnnuncioPersonaleDTO() {}

    public AnnuncioPersonaleDTO(Long idAnnuncio, LocalDateTime dataPubblicazione, boolean visibile, int visualizzazioni,
                       Long idImmobile, String titoloImmobile, String indirizzo, BigDecimal prezzo, Long idVenditore) {
        this.idAnnuncio = idAnnuncio;
        this.dataPubblicazione = dataPubblicazione;
        this.visibile = visibile;
        this.visualizzazioni = visualizzazioni;
        this.idImmobile = idImmobile;
        this.titoloImmobile = titoloImmobile;
        this.indirizzo = indirizzo;
        this.prezzo = prezzo;
        this.idVenditore = idVenditore;
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
    
    public String getTitoloImmobile() {
        return titoloImmobile;
    }

    public void setTitoloImmobile(String titoloImmobile) {
        this.titoloImmobile = titoloImmobile;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
}
