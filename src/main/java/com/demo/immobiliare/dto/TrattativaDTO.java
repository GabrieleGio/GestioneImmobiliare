package com.demo.immobiliare.dto;

import com.demo.immobiliare.model.StatoTrattativa;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TrattativaDTO {

    private Long idTrattativa;
    private Long idUtente;
    private Long idAnnuncio;
    private BigDecimal prezzoOfferto;
    private LocalDateTime dataProposta;
    private StatoTrattativa stato;
    private String messaggio;

    public TrattativaDTO() {}

    public TrattativaDTO(Long idTrattativa, Long idUtente, Long idAnnuncio, BigDecimal prezzoOfferto,
                         LocalDateTime dataProposta, StatoTrattativa stato, String messaggio) {
        this.idTrattativa = idTrattativa;
        this.idUtente = idUtente;
        this.idAnnuncio = idAnnuncio;
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

    public Long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
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
