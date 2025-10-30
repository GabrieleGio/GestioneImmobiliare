package com.demo.immobiliare.exception;

public class AnnuncioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AnnuncioNotFoundException(String message) {
        super(message);
    }
}
