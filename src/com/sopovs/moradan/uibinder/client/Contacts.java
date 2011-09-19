package com.sopovs.moradan.uibinder.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

public class Contacts implements EntryPoint {

	@Override
	public void onModuleLoad() {
		new AppController((ContactsServiceAsync) GWT.create(ContactsService.class),
				new SimpleEventBus()).go(RootPanel.get());
	}
}
