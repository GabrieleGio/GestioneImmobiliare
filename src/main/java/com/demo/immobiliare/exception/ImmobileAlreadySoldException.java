package com.demo.immobiliare.exception;

public class ImmobileAlreadySoldException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImmobileAlreadySoldException(String message) {
		super(message);
	}
}
