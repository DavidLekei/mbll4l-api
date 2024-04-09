package com.davidlekei.mbll4l;

public class Lawyer {

	public int id;
	public String lastName;
	public String firstName;
	public String firm;
	public Contact contact;
	public String status;
	public String history;

	public Lawyer(int id, String lastName, String firstName, String firm, Contact contact, String status, String history){
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.firm = firm;
		this.contact = contact;
		this.status = status;
		this.history = history;
	}

}
