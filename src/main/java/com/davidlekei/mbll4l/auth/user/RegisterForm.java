package com.davidlekei.mbll4l.auth.user;

public class RegisterForm {

	private String email;
	private String password;

	public RegisterForm(String email, String password){
		this.email = email;
		this.password = password;
	}

	public String getEmail(){
		return this.email;
	}

	public String getPassword(){
		return this.password;
	}

}
