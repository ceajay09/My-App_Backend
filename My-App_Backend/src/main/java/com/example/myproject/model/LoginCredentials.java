package com.example.myproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Encapsulates login credentials, including email and password, for authentication purposes.
 */
public class LoginCredentials {
	//TODO: Annotations (@Email, @NotNull, @Data, @NoArgsConstructor, @AllArgsConstructor, @NotBlank)

	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;

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

	@Override
	public String toString() {
		return "LoginCredentials{email='" + email + "'}";
	}
}
