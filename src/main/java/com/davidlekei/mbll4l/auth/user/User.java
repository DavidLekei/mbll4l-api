package com.davidlekei.mbll4l.auth.user;

public class User {

	private Token token;
	private String email;

	public User(Token token, String email){
		this.token = token;
		this.email = email;
	}

	public Token getToken(){
		return token;
	}

	public String getEmail(){
		return email;
	}

}
