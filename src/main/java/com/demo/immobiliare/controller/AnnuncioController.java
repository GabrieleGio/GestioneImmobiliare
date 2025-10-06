package com.demo.immobiliare.controller;

import com.demo.immobiliare.dto.AnnuncioDTO;
import com.demo.immobiliare.service.IAnnuncioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/annunci")
public class AnnuncioController {

    private final IAnnuncioService annuncioService;

    public AnnuncioController(IAnnuncioService annuncioService) {
        this.annuncioService = annuncioService;
    }

    @GetMapping
    public ResponseEntity<List<AnnuncioDTO>> trovaTutti() {
        List<AnnuncioDTO> annunci = annuncioService.trovaTutti();
        return ResponseEntity.ok(annunci);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnuncioDTO> trovaPerId(@PathVariable Long id) {
        Optional<AnnuncioDTO> annuncioOpt = annuncioService.trovaPerId(id);
        return annuncioOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> creaAnnuncio(@RequestBody AnnuncioDTO annuncioDTO) {
        AnnuncioDTO creato = annuncioService.creaAnnuncio(annuncioDTO);
        return new ResponseEntity<>(creato, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> aggiornaAnnuncio(@PathVariable Long id, @RequestBody AnnuncioDTO annuncioDTO) {
        try {
            annuncioDTO.setIdAnnuncio(id);
            AnnuncioDTO aggiornato = annuncioService.aggiornaAnnuncio(annuncioDTO);
            return ResponseEntity.ok(aggiornato);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
