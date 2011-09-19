package com.sopovs.moradan.uibinder.client.view;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public interface EditContactsView<T> {

	public interface Presenter<T> {
		void onSaveButtonClicked();

		void onCancelButtonClicked();
	}

	HasValue<String> getFirstName();

	HasValue<String> getLastName();

	HasValue<String> getEmailAddress();

	void setPresenter(Presenter<T> presenter);

	Widget asWidget();
}
