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

import com.demo.immobiliare.dto.LoginRequestDTO;
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

    @PostMapping
    public ResponseEntity<?> registraUtente(@RequestBody UtenteDTO utenteDTO) {
        try {
            UtenteDTO utenteCreato = utenteService.registraUtente(utenteDTO);
            return new ResponseEntity<>(utenteCreato, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {

        String email = request.getEmail();
        String password = request.getPassword();

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Email e password sono obbligatori");
        }

        try {
            boolean autenticato = utenteService.verificaPassword(email, password);

            if (autenticato) {
                Optional<UtenteDTO> utente = utenteService.trovaPerUsername(email);
                return ResponseEntity.ok(utente);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password errata");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
