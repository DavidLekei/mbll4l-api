package com.davidlekei.mbll4l.auth.user;

public class JWT implements Token{

	private String value;
	private String expiration;

	public JWT(String value, String expiration){
		this.value = value;
		this.expiration = expiration;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getExpiration() {
		return expiration;
	}
}
