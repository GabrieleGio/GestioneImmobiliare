package com.demo.immobiliare.service.impl;

import com.demo.immobiliare.dto.ImmobileDTO;
import com.demo.immobiliare.mapper.ImmobileMapper;
import com.demo.immobiliare.model.Immobile;
import com.demo.immobiliare.repository.ImmobileRepository;
import com.demo.immobiliare.service.IImmobileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImmobileService implements IImmobileService {

    private final ImmobileRepository immobileRepository;

    @Autowired
    public ImmobileService(ImmobileRepository immobileRepository) {
        this.immobileRepository = immobileRepository;
    }

    @Override
    public ImmobileDTO creaImmobile(ImmobileDTO immobileDTO) {
        Immobile immobile = ImmobileMapper.toEntity(immobileDTO);
        Immobile saved = immobileRepository.save(immobile);
        return ImmobileMapper.toDto(saved);
    }

    @Override
    public ImmobileDTO aggiornaImmobile(ImmobileDTO immobileDTO) throws Exception {
        if (!immobileRepository.existsById(immobileDTO.getIdImmobile())) {
            throw new Exception("Immobile con ID " + immobileDTO.getIdImmobile() + " non trovato");
        }
        Immobile immobile = ImmobileMapper.toEntity(immobileDTO);
        Immobile updated = immobileRepository.save(immobile);
        return ImmobileMapper.toDto(updated);
    }

    @Override
    public void eliminaImmobile(Long id) throws Exception {
        if (!immobileRepository.existsById(id)) {
            throw new Exception("Immobile con ID " + id + " non trovato");
        }
        immobileRepository.deleteById(id);
    }

    @Override
    public Optional<ImmobileDTO> trovaPerId(Long id) {
        return immobileRepository.findById(id)
                .map(ImmobileMapper::toDto);
    }

    @Override
    public List<ImmobileDTO> trovaTutti() {
        return immobileRepository.findAll()
                .stream()
                .map(ImmobileMapper::toDto)
                .collect(Collectors.toList());
    }
}
