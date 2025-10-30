package com.demo.immobiliare.exception;

public class TrattativaAlreadyClosedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TrattativaAlreadyClosedException(String message) {
		super(message);
	}
}
