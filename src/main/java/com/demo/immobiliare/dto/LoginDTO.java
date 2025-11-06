package com.demo.immobiliare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginDTO {
	
	@Email
	@NotNull(message = "L'email è obbligatoria")
	@Size(max = 255, message = "L'email non può superare i 255 caratteri")
    private String email;
	
	@Size(min = 8, max = 255, message = "La password deve essere composta da minimo 5 caratteri e massimo 255")
	@NotBlank(message = "La password è obbligatoria")
    private String password;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
