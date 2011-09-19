package com.sopovs.moradan.uibinder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditContactCancelledEvent extends GwtEvent<EditContactCancelledEventHandler> {
	public static Type<EditContactCancelledEventHandler> TYPE = new Type<EditContactCancelledEventHandler>();

	private final String contactId;

	public EditContactCancelledEvent(String contactId) {
		this.contactId = contactId;
	}

	@Override
	public Type<EditContactCancelledEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditContactCancelledEventHandler handler) {
		handler.onEditContactCancelled(this);
	}

	public String getContactId() {
		return contactId;
	}
}
