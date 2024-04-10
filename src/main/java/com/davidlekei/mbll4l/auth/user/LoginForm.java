package com.davidlekei.mbll4l.auth.user;

public class LoginForm {

	private String email;
	private String password;

	public LoginForm(String email, String password){
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
