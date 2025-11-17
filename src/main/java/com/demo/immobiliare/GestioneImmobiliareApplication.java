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
	
	// TODO gestire gli errori lato front end (evitare i return of null)
	

