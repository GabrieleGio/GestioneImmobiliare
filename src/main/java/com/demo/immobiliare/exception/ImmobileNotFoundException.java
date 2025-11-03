package com.demo.immobiliare.exception;

public class ImmobileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImmobileNotFoundException(String message) {
		super(message);
	}
}
