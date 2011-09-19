package com.sopovs.moradan.uibinder.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Contact implements Serializable {

	private String id;
	private String firstName;
	private String emailAddress;
	private String lastName;

	/**
	 * Mandatory for RPC serialization.
	 */
	public Contact() {
	}

	public Contact(String id, String firstName, String lastName, String emailAddress) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	public ContactDetails getLightWeightContact() {
		return new ContactDetails(id, getFullName());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}
}
