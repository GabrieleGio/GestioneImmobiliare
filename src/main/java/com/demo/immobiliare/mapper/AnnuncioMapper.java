package com.demo.immobiliare.mapper;

import com.demo.immobiliare.dto.AnnuncioDTO;
import com.demo.immobiliare.model.Annuncio;
import com.demo.immobiliare.model.Immobile;

public class AnnuncioMapper {

    public static AnnuncioDTO toDto(Annuncio annuncio) {
        if (annuncio == null) {
            return null;
        }

        Long idImmobile = annuncio.getImmobile() != null ? annuncio.getImmobile().getIdImmobile() : null;
        Long idVenditore = annuncio.getVenditore() != null ? annuncio.getVenditore().getIdUtente() : null;
        Long idCreatore = annuncio.getCreatore() != null ? annuncio.getCreatore().getIdUtente() : null;

        return new AnnuncioDTO(
            annuncio.getIdAnnuncio(),
            annuncio.getDataPubblicazione(),
            annuncio.isVisibile(),
            annuncio.getVisualizzazioni(),
            idImmobile,
            idVenditore,
            idCreatore
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

        if (dto.getIdImmobile() != null) {
            Immobile immobile = new Immobile();
            immobile.setIdImmobile(dto.getIdImmobile());
            annuncio.setImmobile(immobile);
        } else {
            annuncio.setImmobile(null);
        }

        annuncio.setVenditore(null);
        annuncio.setCreatore(null);

        return annuncio;
    }

}
