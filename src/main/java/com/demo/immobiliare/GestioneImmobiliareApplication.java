package com.demo.immobiliare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.demo.immobiliare.config.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class GestioneImmobiliareApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestioneImmobiliareApplication.class, args);
	}

} //TODO fare pagina 'i tuoi annunci' dove hai la possibilità di vedere le trattative ricevute per un proprio annuncio
	// con la possibilità di accettarle oppure rifiutarle, fare pagina 'le tue trattative'
	// sistemare e implementare le service per aggiornare ed eliminare, fare controlli vari nelle service di creazione
