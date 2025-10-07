package com.demo.immobiliare.service.impl;

import com.demo.immobiliare.dto.AnnuncioDTO;
import com.demo.immobiliare.mapper.AnnuncioMapper;
import com.demo.immobiliare.model.Annuncio;
import com.demo.immobiliare.repository.AnnuncioRepository;
import com.demo.immobiliare.service.IAnnuncioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnuncioService implements IAnnuncioService {

    private final AnnuncioRepository annuncioRepository;

    @Autowired
    public AnnuncioService(AnnuncioRepository annuncioRepository) {
        this.annuncioRepository = annuncioRepository;
    }

    @Override
    public AnnuncioDTO creaAnnuncio(AnnuncioDTO annuncioDTO) {
        Annuncio annuncio = AnnuncioMapper.toEntity(annuncioDTO);
        Annuncio saved = annuncioRepository.save(annuncio);
        return AnnuncioMapper.toDto(saved);
    }

    @Override
    public AnnuncioDTO aggiornaAnnuncio(AnnuncioDTO annuncioDTO) throws Exception {
        if (!annuncioRepository.existsById(annuncioDTO.getIdAnnuncio())) {
            throw new Exception("Annuncio con ID " + annuncioDTO.getIdAnnuncio() + " non trovato");
        }
        Annuncio annuncio = AnnuncioMapper.toEntity(annuncioDTO);
        Annuncio updated = annuncioRepository.save(annuncio);
        return AnnuncioMapper.toDto(updated);
    }

    @Override
    public void eliminaAnnuncio(Long id) throws Exception {
        if (!annuncioRepository.existsById(id)) {
            throw new Exception("Annuncio con ID " + id + " non trovato");
        }
        annuncioRepository.deleteById(id);
    }

    @Override
    public Optional<AnnuncioDTO> trovaPerId(Long id) {
        return annuncioRepository.findById(id)
                .map(AnnuncioMapper::toDto);
    }

    @Override
    public List<AnnuncioDTO> trovaTutti() {
        return annuncioRepository.findAll()
                .stream()
                .map(AnnuncioMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Page<AnnuncioDTO> trovaTuttiPaginati(Pageable pageable) {
        return annuncioRepository.findAll(pageable)
                .map(AnnuncioMapper::toDto);
    }
}
