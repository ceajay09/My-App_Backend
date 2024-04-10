package com.example.myproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class loginCredentials {
	@JsonProperty("email")
	protected String email;

	@JsonProperty("password")
	protected String password;
	
	
	public String toString() {
		return this.email+" "+this.password;
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
}
