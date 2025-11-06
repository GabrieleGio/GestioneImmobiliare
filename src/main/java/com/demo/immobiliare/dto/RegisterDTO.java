package com.demo.immobiliare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterDTO {

	@Size(min = 3, max = 25, message = "L'username deve essere compreso tra i 3 e i 25 caratteri")
	@NotBlank(message = "L'username è obbligatorio")
    private String username;

	@Email
	@NotNull(message = "L'email è obbligatoria")
	@Size(max = 255, message = "L'email non può superare i 255 caratteri")
    private String email;

	@Size(min = 8, max = 255, message = "La password deve essere composta da minimo 5 caratteri e massimo 255")
	@NotBlank(message = "La password è obbligatoria")
    private String password;

	@Size(min = 8, max = 255, message = "La conferma della password deve essere composta da minimo 5 caratteri e massimo 255")
	@NotBlank(message = "La conferma della password è obbligatoria")
    private String confirmPassword;
	
	public RegisterDTO() {}

	public RegisterDTO(String username, String email, String password, String confirmPassword) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confermaPassword) {
		this.confirmPassword = confermaPassword;
	}
}
