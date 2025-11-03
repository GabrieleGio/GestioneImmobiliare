package com.demo.immobiliare.controller;

import com.demo.immobiliare.dto.TrattativaDTO;
import com.demo.immobiliare.dto.TrattativaPersonaleDTO;
import com.demo.immobiliare.dto.TrattativaPropostaDTO;
import com.demo.immobiliare.dto.TrattativaResponseDTO;
import com.demo.immobiliare.service.ITrattativaService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trattative")
public class TrattativaController {

    private final ITrattativaService trattativaService;

    public TrattativaController(ITrattativaService trattativaService) {
        this.trattativaService = trattativaService;
    }

    @GetMapping
    public ResponseEntity<List<TrattativaDTO>> trovaTutti() {
        List<TrattativaDTO> trattative = trattativaService.trovaTutti();
        return ResponseEntity.ok(trattative);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrattativaDTO> trovaPerId(@PathVariable Long id) {
        Optional<TrattativaDTO> trattativaOpt = trattativaService.trovaPerId(id);
        return trattativaOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/personali")
    public ResponseEntity<Page<TrattativaPersonaleDTO>> getTrattativePersonaliPaginate(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idTrattativa") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) throws Exception {
    	Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TrattativaPersonaleDTO> risultato = trattativaService.trovaTuttiPersonaliPaginati(pageable);
        return ResponseEntity.ok(risultato);
    }
    
    @GetMapping("/annuncio/{idAnnuncio}")
    public ResponseEntity<Page<TrattativaDTO>> getTrattativePerAnnuncioPersonale(
            @PathVariable Long idAnnuncio, 
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idTrattativa") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) throws Exception {
    	
    	if (idAnnuncio == null) {
            return ResponseEntity.badRequest().body(null);
        }
        
        Sort sort = direction.equalsIgnoreCase("desc") 
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TrattativaDTO> risultato = trattativaService.trovaTuttiPerAnnuncioPersonale(idAnnuncio, pageable);
        return ResponseEntity.ok(risultato);
    }

    @PostMapping
    public ResponseEntity<?> creaTrattativa(@Valid @RequestBody TrattativaPropostaDTO propostaDTO) throws Exception {
        TrattativaDTO creato = trattativaService.creaTrattativa(propostaDTO);
        return new ResponseEntity<>(creato, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> aggiornaTrattativa(@PathVariable Long id, @Valid @RequestBody TrattativaDTO trattativaDTO) {
        try {
            trattativaDTO.setIdTrattativa(id);
            TrattativaDTO aggiornato = trattativaService.aggiornaTrattativa(trattativaDTO);
            return ResponseEntity.ok(aggiornato);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/vendi")
    public ResponseEntity<TrattativaResponseDTO> vendiTrattativa(@PathVariable Long id) {
        try {
            trattativaService.vendi(id);
            return ResponseEntity.ok(new TrattativaResponseDTO("Trattativa con ID " + id + " conclusa con successo"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TrattativaResponseDTO(e.getMessage()));
        }
    }
}
