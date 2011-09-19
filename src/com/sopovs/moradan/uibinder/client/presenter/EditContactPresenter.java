package com.sopovs.moradan.uibinder.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.sopovs.moradan.uibinder.client.ContactsServiceAsync;
import com.sopovs.moradan.uibinder.client.event.ContactUpdatedEvent;
import com.sopovs.moradan.uibinder.client.event.EditContactCancelledEvent;
import com.sopovs.moradan.uibinder.client.view.EditContactsView;
import com.sopovs.moradan.uibinder.shared.Contact;

public class EditContactPresenter implements Presenter, EditContactsView.Presenter<Contact> {

	private Contact contact;
	private final ContactsServiceAsync rpcService;
	private final EventBus eventBus;
	private final EditContactsView<Contact> display;

	public EditContactPresenter(ContactsServiceAsync rpcService, EventBus eventBus, EditContactsView<Contact> display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.contact = new Contact();
		this.display = display;
		display.setPresenter(this);
	}

	public EditContactPresenter(ContactsServiceAsync rpcService, EventBus eventBus, EditContactsView<Contact> display,
			String id) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		display.setPresenter(this);

		rpcService.getContact(id, new AsyncCallback<Contact>() {
			@Override
			public void onSuccess(Contact result) {
				if (result == null) {
					contact = new Contact();
				} else {
					contact = result;
					EditContactPresenter.this.display.getFirstName().setValue(contact.getFirstName());
					EditContactPresenter.this.display.getLastName().setValue(contact.getLastName());
					EditContactPresenter.this.display.getEmailAddress().setValue(contact.getEmailAddress());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error retrieving contact");
			}
		});

	}

	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void onSaveButtonClicked() {
		contact.setFirstName(display.getFirstName().getValue());
		contact.setLastName(display.getLastName().getValue());
		contact.setEmailAddress(display.getEmailAddress().getValue());

		rpcService.updateContact(contact, new AsyncCallback<Contact>() {
			@Override
			public void onSuccess(Contact result) {
				eventBus.fireEvent(new ContactUpdatedEvent(result));
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error updating contact");
			}
		});
	}

	@Override
	public void onCancelButtonClicked() {
		eventBus.fireEvent(new EditContactCancelledEvent(contact.getId()));
	}

}
