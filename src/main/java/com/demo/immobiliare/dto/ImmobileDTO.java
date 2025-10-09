package com.demo.immobiliare.dto;

import com.demo.immobiliare.model.StatoImmobile;
import com.demo.immobiliare.model.Tipologia;

import java.math.BigDecimal;

public class ImmobileDTO {

    private Long idImmobile;
    private String titolo;
    private String descrizione;
    private BigDecimal prezzo;
    private Tipologia tipologia;
    private StatoImmobile stato;
    private Integer superficie;
    private String indirizzo;
    private Long idProprietario;

    public ImmobileDTO() {
    }

    public ImmobileDTO(Long idImmobile, String titolo, String descrizione, BigDecimal prezzo, Tipologia tipologia,
                       StatoImmobile stato, Integer superficie, String indirizzo, Long idProprietario) {
        this.idImmobile = idImmobile;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.tipologia = tipologia;
        this.stato = stato;
        this.superficie = superficie;
        this.indirizzo = indirizzo;
        this.idProprietario = idProprietario;
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

    public Long getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(Long idProprietario) {
        this.idProprietario = idProprietario;
    }
}
