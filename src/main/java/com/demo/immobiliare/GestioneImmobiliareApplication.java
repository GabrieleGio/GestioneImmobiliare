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

}   //TODO fare una funzione rifiuta trattativa con controlli simili a quella per venderla
	// sistemare e implementare le service per aggiornare ed eliminare
	// mettere controlli ai dto !!!
	// mettere controlli mancanti di lunghezza massima e minima e vedere se sono corretti con DB
	// implementare lato front end funzioni per creare immobili e annunci con relativi validators
	// mettere controlli lato front end di quando un email o username è già in uso
	
	// TODO aggiungere controlli con errori custom nelle controller


