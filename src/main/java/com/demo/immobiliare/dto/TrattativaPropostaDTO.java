package com.demo.immobiliare.dto;

import java.math.BigDecimal;

public class TrattativaPropostaDTO {

    private Long idAnnuncio;
    private BigDecimal prezzoOfferto;
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
