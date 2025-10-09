package com.demo.immobiliare.mapper;

import com.demo.immobiliare.dto.TrattativaDTO;
import com.demo.immobiliare.model.Trattativa;
import com.demo.immobiliare.model.Utente;
import com.demo.immobiliare.model.Annuncio;

public class TrattativaMapper {

    public static TrattativaDTO toDto(Trattativa trattativa) {
        if (trattativa == null) {
            return null;
        }

        Long idUtente = trattativa.getUtente() != null ? trattativa.getUtente().getIdUtente() : null;
        Long idAnnuncio = trattativa.getAnnuncio() != null ? trattativa.getAnnuncio().getIdAnnuncio() : null;

        return new TrattativaDTO(
            trattativa.getIdTrattativa(),
            idUtente,
            idAnnuncio,
            trattativa.getPrezzoOfferto(),
            trattativa.getDataProposta(),
            trattativa.getStato(),
            trattativa.getMessaggio()
        );
    }

    public static Trattativa toEntity(TrattativaDTO dto) {
        if (dto == null) {
            return null;
        }

        Trattativa trattativa = new Trattativa();
        trattativa.setIdTrattativa(dto.getIdTrattativa());

        Utente utente = new Utente();
        utente.setIdUtente(dto.getIdUtente());
        trattativa.setUtente(utente);

        Annuncio annuncio = new Annuncio();
        annuncio.setIdAnnuncio(dto.getIdAnnuncio());
        trattativa.setAnnuncio(annuncio);

        trattativa.setPrezzoOfferto(dto.getPrezzoOfferto());
        trattativa.setDataProposta(dto.getDataProposta());
        trattativa.setStato(dto.getStato());
        trattativa.setMessaggio(dto.getMessaggio());

        return trattativa;
    }
}
