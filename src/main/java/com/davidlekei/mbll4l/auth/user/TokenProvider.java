package com.davidlekei.mbll4l.auth.user;

public interface TokenProvider {

	public String getNewToken(String username);
	public String verifyToken(String token);

}
