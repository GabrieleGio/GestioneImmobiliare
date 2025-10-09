package com.demo.immobiliare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trattativa")
public class Trattativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trattativa", nullable = false)
    private Long idTrattativa;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    @NotNull(message = "L'utente è obbligatorio")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_annuncio", nullable = false)
    @NotNull(message = "L'annuncio è obbligatorio")
    private Annuncio annuncio;

    @Column(name = "prezzo_offerto", nullable = false, precision = 12, scale = 2)
    @DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo offerto deve essere maggiore di zero")
    @Digits(integer = 10, fraction = 2, message = "Prezzo offerto non valido")
    private BigDecimal prezzoOfferto;

    @Column(name = "data_proposta", nullable = false)
    private LocalDateTime dataProposta;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", nullable = false, length = 50)
    private StatoTrattativa stato;

    @Column(name = "messaggio", length = 500)
    @Size(max = 500, message = "Il messaggio non può superare i 500 caratteri")
    private String messaggio;
    
    public Trattativa() {}
  
    public Trattativa(Long idTrattativa, @NotNull(message = "L'utente è obbligatorio") Utente utente,
			@NotNull(message = "L'annuncio è obbligatorio") Annuncio annuncio,
			
			@DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo offerto deve essere maggiore di zero") 
            @Digits(integer = 10, fraction = 2, message = "Prezzo offerto non valido") BigDecimal prezzoOfferto,
            
			@Size(max = 500, message = "Il messaggio non può superare i 500 caratteri") String messaggio) {
		super();
		this.idTrattativa = idTrattativa;
		this.utente = utente;
		this.annuncio = annuncio;
		this.prezzoOfferto = prezzoOfferto;
		this.dataProposta = LocalDateTime.now();
		this.stato = StatoTrattativa.IN_ATTESA;
		this.messaggio = messaggio;
	}

	public Long getIdTrattativa() {
        return idTrattativa;
    }

    public void setIdTrattativa(Long idTrattativa) {
        this.idTrattativa = idTrattativa;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Annuncio getAnnuncio() {
        return annuncio;
    }

    public void setAnnuncio(Annuncio annuncio) {
        this.annuncio = annuncio;
    }

    public BigDecimal getPrezzoOfferto() {
        return prezzoOfferto;
    }

    public void setPrezzoOfferto(BigDecimal prezzoOfferto) {
        this.prezzoOfferto = prezzoOfferto;
    }

    public LocalDateTime getDataProposta() {
        return dataProposta;
    }

    public void setDataProposta(LocalDateTime dataProposta) {
        this.dataProposta = dataProposta;
    }

    public StatoTrattativa getStato() {
        return stato;
    }

    public void setStato(StatoTrattativa stato) {
        this.stato = stato;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
}

