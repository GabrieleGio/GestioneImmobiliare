package com.demo.immobiliare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


@Entity
@Table(name = "immobile")
public class Immobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_immobile", nullable = false)
    private Long idImmobile;

    @Column(name = "titolo", nullable = false, length = 255)
    @Size(max = 255, message = "Il titolo non può superare i 255 caratteri")
    @NotBlank(message = "Il titolo è obbligatorio")
    private String titolo;

    @Column(name = "descrizione", nullable = false, length = 255)
    @Size(max = 255, message = "La descrizione non può superare i 255 caratteri")
    @NotBlank(message = "La descrizione è obbligatoria")
    private String descrizione;

    @Column(name = "prezzo", nullable = false, precision = 12, scale = 2)
    @DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo deve essere maggiore di zero")
    @DecimalMax(value = "999999999.99", inclusive = true, message = "Il prezzo non può superare 999 milioni")
    @Digits(integer = 10, fraction = 2, message = "Prezzo non valido")
    @NotNull(message = "Il prezzo è obbligatorio")
    private BigDecimal prezzo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipologia", nullable = false, length = 50)
    @NotNull(message = "La tipologia è obbligatoria")
    private Tipologia tipologia;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", nullable = false, length = 50)
    @NotNull(message = "Lo stato è obbligatorio")
    private StatoImmobile stato;

    @Column(name = "superficie", nullable = false)
    @Min(value = 1, message = "La superficie deve essere almeno 1 m²")
    @Max(value = 999999, message = "La superficie non può superare i 999999 m²")
    @NotNull(message = "La superficie è obbligatoria")
    private Integer superficie;

    @Column(name = "indirizzo", nullable = false, length = 255)
    @Size(max = 255, message = "L'indirizzo non può superare i 255 caratteri")
    @NotBlank(message = "L'indirizzo è obbligatorio")
    @Pattern(
        regexp = "^[a-zA-Z0-9àèéìòùÀÈÉÌÒÙ\\s,.'-]+$",
        message = "L'indirizzo contiene caratteri non validi"
    )
    private String indirizzo;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_utente", nullable = true)
    private Utente proprietario;

    public Immobile() {
	}
    
    public Immobile(Long idImmobile,
			@Size(max = 255, message = "Il titolo non può superare i 255 caratteri") @NotBlank(message = "Il titolo è obbligatorio") String titolo,
			@Size(max = 255, message = "La descrizione non può superare i 255 caratteri") @NotBlank(message = "La descrizione è obbligatoria") String descrizione,
			@DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo deve essere maggiore di zero") @Digits(integer = 10, fraction = 2, message = "Prezzo non valido") @NotNull(message = "Il prezzo è obbligatorio") BigDecimal prezzo,
			@NotNull(message = "La tipologia è obbligatoria") Tipologia tipologia,
			@Min(value = 1, message = "La superficie deve essere almeno 1 m²") @NotNull(message = "La superficie è obbligatoria") Integer superficie,
			@Size(max = 255, message = "L'indirizzo non può superare i 255 caratteri") @NotBlank(message = "L'indirizzo è obbligatorio") @Pattern(regexp = "^[a-zA-Z0-9àèéìòùÀÈÉÌÒÙ\\s,.'-]+$", message = "L'indirizzo contiene caratteri non validi") String indirizzo,
			Utente proprietario) {
		super();
		this.idImmobile = idImmobile;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.tipologia = tipologia;
		this.stato = StatoImmobile.DISPONIBILE;
		this.superficie = superficie;
		this.indirizzo = indirizzo;
		this.proprietario = proprietario;
	}

	public Long getIdImmobile() {
        return idImmobile;
    }

    public void setIdImmobile(Long idImmobile) {
        this.idImmobile = idImmobile;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    public StatoImmobile getStato() {
        return stato;
    }

    public void setStato(StatoImmobile stato) {
        this.stato = stato;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

	public Utente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Utente proprietario) {
		this.proprietario = proprietario;
	}
}


