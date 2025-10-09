package com.demo.immobiliare.mapper;

import com.demo.immobiliare.dto.ImmobileDTO;
import com.demo.immobiliare.model.Immobile;
import com.demo.immobiliare.model.Utente;

public class ImmobileMapper {

    public static ImmobileDTO toDto(Immobile immobile) {
        if (immobile == null) {
            return null;
        }

        Long idProprietario = immobile.getProprietario() != null ? immobile.getProprietario().getIdUtente() : null;

        return new ImmobileDTO(
                immobile.getIdImmobile(),
                immobile.getTitolo(),
                immobile.getDescrizione(),
                immobile.getPrezzo(),
                immobile.getTipologia(),
                immobile.getStato(),
                immobile.getSuperficie(),
                immobile.getIndirizzo(),
                idProprietario
        );
    }

    public static Immobile toEntity(ImmobileDTO dto) {
        if (dto == null) {
            return null;
        }

        Immobile immobile = new Immobile();
        immobile.setIdImmobile(dto.getIdImmobile());
        immobile.setTitolo(dto.getTitolo());
        immobile.setDescrizione(dto.getDescrizione());
        immobile.setPrezzo(dto.getPrezzo());
        immobile.setTipologia(dto.getTipologia());
        immobile.setStato(dto.getStato());
        immobile.setSuperficie(dto.getSuperficie());
        immobile.setIndirizzo(dto.getIndirizzo());

        if (dto.getIdProprietario() != null) {
            Utente proprietario = new Utente();
            proprietario.setIdUtente(dto.getIdProprietario());
            immobile.setProprietario(proprietario);
        } else {
            immobile.setProprietario(null);
        }

        return immobile;
    }
}
