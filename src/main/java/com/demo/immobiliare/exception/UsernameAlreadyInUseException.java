package com.demo.immobiliare.exception;

public class UsernameAlreadyInUseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UsernameAlreadyInUseException(String message) {
        super(message);
    }
}
