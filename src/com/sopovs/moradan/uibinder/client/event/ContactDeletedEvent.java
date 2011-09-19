package com.sopovs.moradan.uibinder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ContactDeletedEvent extends GwtEvent<ContactDeletedEventHandler>{
  public static Type<ContactDeletedEventHandler> TYPE = new Type<ContactDeletedEventHandler>();
  
  @Override
  public Type<ContactDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ContactDeletedEventHandler handler) {
    handler.onContactDeleted(this);
  }
}
