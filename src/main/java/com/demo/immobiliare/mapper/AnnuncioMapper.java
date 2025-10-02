package com.demo.immobiliare.mapper;

import com.demo.immobiliare.dto.AnnuncioDTO;
import com.demo.immobiliare.model.Annuncio;

public class AnnuncioMapper {

    public static AnnuncioDTO toDto(Annuncio annuncio) {
        if (annuncio == null) {
            return null;
        }

        return new AnnuncioDTO(
            annuncio.getIdAnnuncio(),
            annuncio.getDataPubblicazione(),
            annuncio.isVisibile(),
            annuncio.getVisualizzazioni(),
            ImmobileMapper.toDto(annuncio.getImmobile()),
            UtenteMapper.toDto(annuncio.getVenditore())
        );
    }

    public static Annuncio toEntity(AnnuncioDTO dto) {
        if (dto == null) {
            return null;
        }

        Annuncio annuncio = new Annuncio();
        annuncio.setIdAnnuncio(dto.getIdAnnuncio());
        annuncio.setDataPubblicazione(dto.getDataPubblicazione());
        annuncio.setVisibile(dto.isVisibile());
        annuncio.setVisualizzazioni(dto.getVisualizzazioni());
        annuncio.setImmobile(ImmobileMapper.toEntity(dto.getImmobile()));
        annuncio.setVenditore(UtenteMapper.toEntity(dto.getVenditore()));
        return annuncio;
    }
}
