package com.demo.immobiliare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "annuncio")
public class Annuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_annuncio", nullable = false)
    private Long idAnnuncio;

    @Column(name = "data_pubblicazione", nullable = false)
    @NotNull(message = "La data di pubblicazione è obbligatoria")
    private LocalDateTime dataPubblicazione = LocalDateTime.now();

    @Column(name = "visibile", nullable = false)
    private boolean visibile = true;

    @Column(name = "visualizzazioni", nullable = false)
    @Min(value = 0, message = "Le visualizzazioni non possono essere negative")
    private int visualizzazioni = 0;

    @OneToOne
    @JoinColumn(name = "id_immobile", nullable = false, unique = true)
    @NotNull(message = "L'immobile associato è obbligatorio")
    private Immobile immobile;
    
    @ManyToOne
    @JoinColumn(name = "id_venditore", nullable = false)
    @NotNull(message = "Il venditore è obbligatorio")
    private Utente venditore;
    
    @ManyToOne
    @JoinColumn(name = "id_creatore", nullable = false)
    @NotNull(message = "Il creatore è obbligatorio")
    private Utente creatore;
    
    public Annuncio() {
	}
    
    public Annuncio(Long idAnnuncio,
			@NotNull(message = "La data di pubblicazione è obbligatoria") LocalDateTime dataPubblicazione,
			boolean visibile,
			@Min(value = 0, message = "Le visualizzazioni non possono essere negative") int visualizzazioni,
			@NotNull(message = "L'immobile associato è obbligatorio") Immobile immobile,
			@NotNull(message = "Il venditore è obbligatorio") Utente venditore,
			@NotNull(message = "Il creatore è obbligatorio") Utente creatore) {
		super();
		this.idAnnuncio = idAnnuncio;
		this.dataPubblicazione = dataPubblicazione;
		this.visibile = visibile;
		this.visualizzazioni = visualizzazioni;
		this.immobile = immobile;
		this.venditore = venditore;
		this.creatore = creatore;
	}
    
	public Utente getCreatore() {
		return creatore;
	}

	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}

	public Long getIdAnnuncio() {
        return idAnnuncio;
    }

    public void setIdAnnuncio(Long idAnnuncio) {
        this.idAnnuncio = idAnnuncio;
    }

    public LocalDateTime getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(LocalDateTime dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    public int getVisualizzazioni() {
        return visualizzazioni;
    }

    public void setVisualizzazioni(int visualizzazioni) {
        this.visualizzazioni = visualizzazioni;
    }

    public Immobile getImmobile() {
        return immobile;
    }

    public void setImmobile(Immobile immobile) {
        this.immobile = immobile;
    }
    
    public Utente getVenditore() {
        return venditore;
    }

    public void setVenditore(Utente venditore) {
        this.venditore = venditore;
    }
}

