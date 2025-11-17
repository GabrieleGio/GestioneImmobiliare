package com.demo.immobiliare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.immobiliare.dto.RegisterDTO;
import com.demo.immobiliare.dto.UtenteDTO;
import com.demo.immobiliare.service.IUtenteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/utenti")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
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
    
    @GetMapping("/paginati")
    public ResponseEntity<Page<UtenteDTO>> getImmobiliPaginati(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idUtente") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UtenteDTO> risultato = utenteService.trovaTuttiPaginati(pageable);
        return ResponseEntity.ok(risultato);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> trovaPerId(@PathVariable Long id) {
        Optional<UtenteDTO> utenteOpt = utenteService.trovaPerId(id);
        return utenteOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registraUtente(@Valid @RequestBody RegisterDTO registerDTO) {
        UtenteDTO utenteCreato = utenteService.registraUtente(registerDTO);
        return new ResponseEntity<>(utenteCreato, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> aggiornaUtente(@PathVariable Long id, @Valid @RequestBody UtenteDTO utenteDTO) {
        utenteDTO.setIdUtente(id);
        UtenteDTO utenteAggiornato = utenteService.aggiornaUtente(utenteDTO);
        return ResponseEntity.ok(utenteAggiornato);
    }
}
