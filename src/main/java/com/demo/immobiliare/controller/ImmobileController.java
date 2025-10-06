package com.demo.immobiliare.controller;

import com.demo.immobiliare.dto.ImmobileDTO;
import com.demo.immobiliare.service.IImmobileService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/immobili")
public class ImmobileController {

    private final IImmobileService immobileService;

    public ImmobileController(IImmobileService immobileService) {
        this.immobileService = immobileService;
    }

    @GetMapping
    public ResponseEntity<List<ImmobileDTO>> trovaTutti() {
        List<ImmobileDTO> immobili = immobileService.trovaTutti();
        return ResponseEntity.ok(immobili);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImmobileDTO> trovaPerId(@PathVariable Long id) {
        Optional<ImmobileDTO> immobileOpt = immobileService.trovaPerId(id);
        return immobileOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> creaImmobile(@RequestBody ImmobileDTO immobileDTO) {
        ImmobileDTO creato = immobileService.creaImmobile(immobileDTO);
        return new ResponseEntity<>(creato, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> aggiornaImmobile(@PathVariable Long id, @RequestBody ImmobileDTO immobileDTO) {
        try {
            immobileDTO.setIdImmobile(id);
            ImmobileDTO aggiornato = immobileService.aggiornaImmobile(immobileDTO);
            return ResponseEntity.ok(aggiornato);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
