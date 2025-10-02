package com.demo.immobiliare.mapper;

import com.demo.immobiliare.dto.TrattativaDTO;
import com.demo.immobiliare.model.Trattativa;

public class TrattativaMapper {

    public static TrattativaDTO toDto(Trattativa trattativa) {
        if (trattativa == null) {
            return null;
        }

        return new TrattativaDTO(
            trattativa.getIdTrattativa(),
            UtenteMapper.toDto(trattativa.getUtente()),
            AnnuncioMapper.toDto(trattativa.getAnnuncio()),
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
        trattativa.setUtente(UtenteMapper.toEntity(dto.getUtente()));
        trattativa.setAnnuncio(AnnuncioMapper.toEntity(dto.getAnnuncio()));
        trattativa.setPrezzoOfferto(dto.getPrezzoOfferto());
        trattativa.setDataProposta(dto.getDataProposta());
        trattativa.setStato(dto.getStato());
        trattativa.setMessaggio(dto.getMessaggio());
        return trattativa;
    }
}
