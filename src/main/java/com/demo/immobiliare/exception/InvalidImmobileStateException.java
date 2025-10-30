package com.demo.immobiliare.exception;

public class InvalidImmobileStateException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidImmobileStateException(String message) {
		super(message);
	}
}
