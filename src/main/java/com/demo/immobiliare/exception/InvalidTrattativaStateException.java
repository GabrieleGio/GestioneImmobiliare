package com.demo.immobiliare.exception;

public class InvalidTrattativaStateException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidTrattativaStateException(String message) {
		super(message);
	}
}
