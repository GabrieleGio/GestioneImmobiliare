package com.demo.immobiliare.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AnnuncioPersonaleDTO {
	
	private Long idAnnuncio;
	
	@NotNull(message = "La data di pubblicazione è obbligatoria")
    @PastOrPresent(message = "La data di pubblicazione non può essere nel futuro")
    private LocalDateTime dataPubblicazione;
	
	@NotNull(message = "La visibilità deve essere impostata")
    private boolean visibile;
	
	@Min(value = 0, message = "Le visualizzazioni non possono essere negative")
    @NotNull(message = "Le visualizzazioni sono obbligatorie")
    private int visualizzazioni;
	
	@NotNull(message = "L'immobile associato è obbligatorio")
    private Long idImmobile;
	
	@Size(max = 255, message = "Il titolo non può superare i 255 caratteri")
    @NotBlank(message = "Il titolo è obbligatorio")
    private String titoloImmobile;
	
	@Size(max = 255, message = "L'indirizzo non può superare i 255 caratteri")
    @NotBlank(message = "L'indirizzo è obbligatorio")
    @Pattern(
        regexp = "^[a-zA-Z0-9àèéìòùÀÈÉÌÒÙ\\s,.'-]+$",
        message = "L'indirizzo contiene caratteri non validi"
    )
    private String indirizzo;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo deve essere maggiore di zero")
    @DecimalMax(value = "999999999.99", inclusive = true, message = "Il prezzo non può superare 999 milioni")
    @Digits(integer = 10, fraction = 2, message = "Prezzo non valido")
    @NotNull(message = "Il prezzo è obbligatorio")
    private BigDecimal prezzo;
    
    @NotNull(message = "Il venditore è obbligatorio")
    private Long idVenditore;
    
    public AnnuncioPersonaleDTO() {}

    public AnnuncioPersonaleDTO(Long idAnnuncio, LocalDateTime dataPubblicazione, boolean visibile, int visualizzazioni,
                       Long idImmobile, String titoloImmobile, String indirizzo, BigDecimal prezzo, Long idVenditore) {
        this.idAnnuncio = idAnnuncio;
        this.dataPubblicazione = dataPubblicazione;
        this.visibile = visibile;
        this.visualizzazioni = visualizzazioni;
        this.idImmobile = idImmobile;
        this.titoloImmobile = titoloImmobile;
        this.indirizzo = indirizzo;
        this.prezzo = prezzo;
        this.idVenditore = idVenditore;
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

    public Long getIdImmobile() {
        return idImmobile;
    }

    public void setIdImmobile(Long idImmobile) {
        this.idImmobile = idImmobile;
    }

    public Long getIdVenditore() {
        return idVenditore;
    }

    public void setIdVenditore(Long idVenditore) {
        this.idVenditore = idVenditore;
    }
    
    public String getTitoloImmobile() {
        return titoloImmobile;
    }

    public void setTitoloImmobile(String titoloImmobile) {
        this.titoloImmobile = titoloImmobile;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
}
