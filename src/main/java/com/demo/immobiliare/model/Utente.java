package com.demo.immobiliare.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "utente")
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUtente;
	
	@Column(name = "username", nullable = false, unique = true)
	@Size(min = 3, max = 25, message = "L'username deve essere compreso tra i 3 e i 25 caratteri")
	@NotBlank(message = "L'username è obbligatorio")
	private String username;
	
	@Column(name = "email", nullable = false, unique = true)
	@Email
	@NotNull(message = "L'email è obbligatoria")
	@Size(max = 255, message = "L'email non può superare i 255 caratteri")
	private String email;
	
	@Column(name = "password", nullable = false)
	@Size(min = 8, max = 255, message = "La password deve essere composta da minimo 5 caratteri e massimo 255")
	@NotBlank(message = "La password è obbligatoria")
	private String password;
	
	@Column(name = "ruolo", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Il ruolo è obbligatorio")
	private Ruolo ruolo = Ruolo.CLIENTE;
	
	@OneToMany(mappedBy = "proprietario")
    @JsonIgnore
    private List<Immobile> immobiliPosseduti;

    @OneToMany(mappedBy = "creatore")
    @JsonIgnore
    private List<Annuncio> annunciCreati;

    @OneToMany(mappedBy = "utente")
    @JsonIgnore
    private List<Trattativa> trattativeCreate;
	
	public Utente() {
	}
	public Utente(Long idUtente,
			@Size(min = 3, max = 25, message = "L'username deve essere compreso tra i 3 e i 25 caratteri") String username,
			@Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "Email non valida") String email,
			@Size(min = 8, message = "La password deve essere composta da minimo 5 caratteri") String password,
			Ruolo ruolo) {
		super();
		this.idUtente = idUtente;
		this.username = username;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
	}
	
	public void aggiungiImmobile(Immobile immobile) {
	    this.immobiliPosseduti.add(immobile);
	    immobile.setProprietario(this);
	}
	
	public void rimuoviImmobile(Immobile immobile) {
		this.immobiliPosseduti.remove(immobile);
		immobile.setProprietario(null);
	}

	public Long getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public List<Immobile> getImmobiliPosseduti() {
		return immobiliPosseduti;
	}
	public void setImmobiliPosseduti(List<Immobile> immobiliPosseduti) {
		this.immobiliPosseduti = immobiliPosseduti;
	}
	public List<Annuncio> getAnnunciCreati() {
		return annunciCreati;
	}
	public void setAnnunciCreati(List<Annuncio> annunciCreati) {
		this.annunciCreati = annunciCreati;
	}
	public List<Trattativa> getTrattativeCreate() {
		return trattativeCreate;
	}
	public void setTrattativeCreate(List<Trattativa> trattativeCreate) {
		this.trattativeCreate = trattativeCreate;
	}
}
