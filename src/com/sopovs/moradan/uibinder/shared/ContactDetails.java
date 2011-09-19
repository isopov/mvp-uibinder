package com.sopovs.moradan.uibinder.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ContactDetails implements Serializable {
	private String id;
	private String displayName;

	/**
	 * Mandatory for RPC serialization.
	 */
	public ContactDetails() {
	}

	public ContactDetails(String id, String displayName) {
		this.id = id;
		this.displayName = displayName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
