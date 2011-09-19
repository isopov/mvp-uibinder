package com.sopovs.moradan.uibinder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.sopovs.moradan.uibinder.client.event.AddContactEvent;
import com.sopovs.moradan.uibinder.client.event.AddContactEventHandler;
import com.sopovs.moradan.uibinder.client.event.ContactUpdatedEvent;
import com.sopovs.moradan.uibinder.client.event.ContactUpdatedEventHandler;
import com.sopovs.moradan.uibinder.client.event.EditContactCancelledEvent;
import com.sopovs.moradan.uibinder.client.event.EditContactCancelledEventHandler;
import com.sopovs.moradan.uibinder.client.event.EditContactEvent;
import com.sopovs.moradan.uibinder.client.event.EditContactEventHandler;
import com.sopovs.moradan.uibinder.client.presenter.ContactsPresenter;
import com.sopovs.moradan.uibinder.client.presenter.EditContactPresenter;
import com.sopovs.moradan.uibinder.client.presenter.Presenter;
import com.sopovs.moradan.uibinder.client.view.ContactsViewImpl;
import com.sopovs.moradan.uibinder.client.view.EditContactViewImpl;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final EventBus eventBus;
	private final ContactsServiceAsync rpcService;
	private HasWidgets container;
	private ContactsViewImpl contactsView = null;
	private EditContactViewImpl editContactView = null;

	public AppController(ContactsServiceAsync rpcService,
			EventBus eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(AddContactEvent.TYPE,
				new AddContactEventHandler() {
					@Override
					public void onAddContact(AddContactEvent event) {
						doAddNewContact();
					}
				});

		eventBus.addHandler(EditContactEvent.TYPE,
				new EditContactEventHandler() {
					@Override
					public void onEditContact(EditContactEvent event) {
						doEditContact(event.getId());
					}
				});

		eventBus.addHandler(EditContactCancelledEvent.TYPE,
				new EditContactCancelledEventHandler() {
					@Override
					public void onEditContactCancelled(EditContactCancelledEvent event) {
						doEditContactCancelled();
					}
				});

		eventBus.addHandler(ContactUpdatedEvent.TYPE,
				new ContactUpdatedEventHandler() {
					@Override
					public void onContactUpdated(ContactUpdatedEvent event) {
						doContactUpdated();
					}
				});
	}

	private void doAddNewContact() {
		History.newItem("add");
	}

	private void doEditContact(String id) {
		History.newItem("edit" + id);
	}

	private void doEditContactCancelled() {
		History.newItem("list");
	}

	private void doContactUpdated() {
		History.newItem("list");
	}

	@Override
	public void go(final HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("list");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		final String token = event.getValue();

		if (token != null) {
			if (token.equals("list")) {
				GWT.runAsync(new RunAsyncCallback() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess() {
						new ContactsPresenter(rpcService, eventBus, getContactsView()).go(container);
					}
				});
			} else if (token.equals("add") || token.equals("edit")) {
				GWT.runAsync(new RunAsyncCallback() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess() {
						new EditContactPresenter(rpcService, eventBus, getEditContactView()).
								go(container);
					}
				});
			} else if (token.startsWith("edit")) {
				GWT.runAsync(new RunAsyncCallback() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess() {
						new EditContactPresenter(rpcService, eventBus, getEditContactView(), token.substring("edit"
								.length())).
								go(container);
					}
				});
			}
		}
	}

	private EditContactViewImpl getEditContactView() {
		if (editContactView == null) {
			editContactView = new EditContactViewImpl();
		}
		return editContactView;
	}

	private ContactsViewImpl getContactsView() {
		if (contactsView == null) {
			contactsView = new ContactsViewImpl();
		}
		return contactsView;
	}
}
