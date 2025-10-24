package com.demo.immobiliare.mapper;

import java.time.LocalDateTime;

import com.demo.immobiliare.dto.TrattativaDTO;
import com.demo.immobiliare.dto.TrattativaPropostaDTO;
import com.demo.immobiliare.model.StatoTrattativa;
import com.demo.immobiliare.model.Trattativa;

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

        trattativa.setUtente(null);
        trattativa.setAnnuncio(null);

        trattativa.setPrezzoOfferto(dto.getPrezzoOfferto());
        trattativa.setDataProposta(dto.getDataProposta());
        trattativa.setStato(dto.getStato());
        trattativa.setMessaggio(dto.getMessaggio());

        return trattativa;
    }
    
    public static TrattativaDTO fromTrattativaPropostaDtoToTrattativa(TrattativaPropostaDTO propostaDto) {
        if (propostaDto == null) {
            return null;
        }

        TrattativaDTO dto = new TrattativaDTO();
        dto.setIdTrattativa(null); // null perchè auto increment
        dto.setIdUtente(null); // verrà impostato successivamente dal request header
        dto.setIdAnnuncio(propostaDto.getIdAnnuncio());
        dto.setPrezzoOfferto(propostaDto.getPrezzoOfferto());
        dto.setDataProposta(LocalDateTime.now());
        dto.setStato(StatoTrattativa.IN_ATTESA);
        dto.setMessaggio(propostaDto.getMessaggio());

        return dto;
    }

}
