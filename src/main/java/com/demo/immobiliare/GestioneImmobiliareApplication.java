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
	// mettere controlli lato front end di quando un email o username è già in uso
	
	// TODO aggiungere controlli con errori custom nelle controller e finire di mettere validators nei DTO

	//TODO funzioni per creare immobili tramite tasto aggiungi immobile, funzione per mettere l'annuncio di un immobile
	// tramite un tasto posizionato sulla card dell'immobile in questione (ogni card avra un tasto "Pubblica" o simile)
	// ricordare i validators


