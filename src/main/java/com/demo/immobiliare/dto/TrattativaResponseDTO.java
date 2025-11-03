package com.demo.immobiliare.dto;


public class TrattativaResponseDTO {
    
    private String message;

    public TrattativaResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

