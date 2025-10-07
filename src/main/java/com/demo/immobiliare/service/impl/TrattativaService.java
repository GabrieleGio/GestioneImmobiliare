package com.demo.immobiliare.service.impl;

import com.demo.immobiliare.dto.TrattativaDTO;
import com.demo.immobiliare.mapper.TrattativaMapper;
import com.demo.immobiliare.model.Trattativa;
import com.demo.immobiliare.repository.TrattativaRepository;
import com.demo.immobiliare.service.ITrattativaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrattativaService implements ITrattativaService {

    private final TrattativaRepository trattativaRepository;

    @Autowired
    public TrattativaService(TrattativaRepository trattativaRepository) {
        this.trattativaRepository = trattativaRepository;
    }

    @Override
    public TrattativaDTO creaTrattativa(TrattativaDTO trattativaDTO) {
        Trattativa trattativa = TrattativaMapper.toEntity(trattativaDTO);
        Trattativa saved = trattativaRepository.save(trattativa);
        return TrattativaMapper.toDto(saved);
    }

    @Override
    public TrattativaDTO aggiornaTrattativa(TrattativaDTO trattativaDTO) throws Exception {
        if (!trattativaRepository.existsById(trattativaDTO.getIdTrattativa())) {
            throw new Exception("Trattativa con ID " + trattativaDTO.getIdTrattativa() + " non trovata");
        }
        Trattativa trattativa = TrattativaMapper.toEntity(trattativaDTO);
        Trattativa updated = trattativaRepository.save(trattativa);
        return TrattativaMapper.toDto(updated);
    }

    @Override
    public void eliminaTrattativa(Long id) throws Exception {
        if (!trattativaRepository.existsById(id)) {
            throw new Exception("Trattativa con ID " + id + " non trovata");
        }
        trattativaRepository.deleteById(id);
    }

    @Override
    public Optional<TrattativaDTO> trovaPerId(Long id) {
        return trattativaRepository.findById(id)
                .map(TrattativaMapper::toDto);
    }

    @Override
    public List<TrattativaDTO> trovaTutti() {
        return trattativaRepository.findAll()
                .stream()
                .map(TrattativaMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Page<TrattativaDTO> trovaTuttiPaginati(Pageable pageable) {
        return trattativaRepository.findAll(pageable)
                .map(TrattativaMapper::toDto);
    }
}
