package com.demo.immobiliare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.immobiliare.dto.UtenteDTO;
import com.demo.immobiliare.service.IUtenteService;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    private final IUtenteService utenteService;

    public UtenteController(IUtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping
    public ResponseEntity<List<UtenteDTO>> trovaTutti() {
        List<UtenteDTO> utenti = utenteService.trovaTutti();
        return ResponseEntity.ok(utenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> trovaPerId(@PathVariable Long id) {
        Optional<UtenteDTO> utenteOpt = utenteService.trovaPerId(id);
        return utenteOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> registraUtente(@RequestBody UtenteDTO utenteDTO) {
        try {
            UtenteDTO utenteCreato = utenteService.registraUtente(utenteDTO);
            return new ResponseEntity<>(utenteCreato, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> aggiornaUtente(@PathVariable Long id, @RequestBody UtenteDTO utenteDTO) {
        try {
            utenteDTO.setIdUtente(id);
            UtenteDTO utenteAggiornato = utenteService.aggiornaUtente(utenteDTO);
            return ResponseEntity.ok(utenteAggiornato);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
