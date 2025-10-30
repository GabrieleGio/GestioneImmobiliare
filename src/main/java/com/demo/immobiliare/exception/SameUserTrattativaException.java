package com.demo.immobiliare.exception;

public class SameUserTrattativaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SameUserTrattativaException(String message) {
        super(message);
    }
}
