package com.sopovs.moradan.uibinder.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sopovs.moradan.uibinder.shared.Contact;

public class EditContactViewImpl extends Composite implements EditContactsView<Contact> {
	private Presenter<Contact> presenter;

	@UiTemplate("EditContactView.ui.xml")
	interface EditContactViewUiBinder extends UiBinder<Widget, EditContactViewImpl> {
	}

	private static EditContactViewUiBinder uiBinder = GWT.create(EditContactViewUiBinder.class);

	@UiField
	TextBox firstName;
	@UiField
	TextBox lastName;
	@UiField
	TextBox email;

	@UiField
	Button saveButton;
	@UiField
	Button cancelButton;

	public EditContactViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("saveButton")
	void onSaveButtonClicked(@SuppressWarnings("unused") ClickEvent event) {
		if (presenter != null) {
			presenter.onSaveButtonClicked();
		}
	}

	@UiHandler("cancelButton")
	void onCancelButtonClicked(@SuppressWarnings("unused") ClickEvent event) {
		if (presenter != null) {
			presenter.onCancelButtonClicked();
		}
	}

	@Override
	public HasValue<String> getFirstName() {
		return firstName;
	}

	@Override
	public HasValue<String> getLastName() {
		return lastName;
	}

	@Override
	public HasValue<String> getEmailAddress() {
		return email;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(Presenter<Contact> presenter) {
		this.presenter = presenter;
	}
}
