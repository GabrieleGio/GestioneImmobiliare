package com.demo.immobiliare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// UTENTE

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<String> handleEmailAlreadyInUse(EmailAlreadyInUseException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameAlreadyInUseException.class)
    public ResponseEntity<String> handleUsernameAlreadyInUse(UsernameAlreadyInUseException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    // IMMOBILE
    
    @ExceptionHandler(ImmobileNotFoundException.class)
    public ResponseEntity<String> handleImmobileNotFound(ImmobileNotFoundException ex) {
    	return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ImmobileOwnershipException.class)
    public ResponseEntity<String> handleImmobileOwnership(ImmobileOwnershipException ex) {
    	return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ImmobileAlreadySoldException.class)
    public ResponseEntity<String> handleImmobileAlreadySold(ImmobileAlreadySoldException ex) {
    	return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidImmobileStateException.class)
    public ResponseEntity<String> handleInvalidImmobileState(InvalidImmobileStateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    // ANNUNCIO
    
    @ExceptionHandler(AnnuncioNotFoundException.class)
    public ResponseEntity<String> handleAnnuncioNotFound(AnnuncioNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(AnnuncioOwnershipException.class)
    public ResponseEntity<String> handleAnnuncioOwnership(AnnuncioOwnershipException ex) {
    	return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(AnnuncioNotVisibleException.class)
    public ResponseEntity<String> handleAnnuncioNotVisible(AnnuncioNotVisibleException ex) {
    	return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
    
    // TRATTATIVA
    
    @ExceptionHandler(TrattativaNotFoundException.class)
    public ResponseEntity<String> handleTrattativaNotFound(TrattativaNotFoundException ex) {
    	return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(TrattativaAlreadyClosedException.class)
    public ResponseEntity<String> handleTrattativaAlreadyClosed(TrattativaAlreadyClosedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidTrattativaStateException.class)
    public ResponseEntity<String> handleInvalidTrattativaState(InvalidTrattativaStateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(SameUserTrattativaException.class)
    public ResponseEntity<String> handleSameUserTrattativa(SameUserTrattativaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    // AUTH
    
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException ex) {
    	return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    // GENERALE

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Errore interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
