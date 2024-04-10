package com.davidlekei.mbll4l.auth.user;

public enum UserStatus {

	INACTIVE(0),
	ACTIVE(1),
	DELETED(2);

	private final int value;

	UserStatus(final int newValue){
		value = newValue;
	}

	public int getValue(){return value;}

}
