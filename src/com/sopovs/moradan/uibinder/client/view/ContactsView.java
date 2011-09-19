package com.sopovs.moradan.uibinder.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public interface ContactsView<T> {

	public interface Presenter<T> {
		void onAddButtonClicked();

		void onDeleteButtonClicked();

		void onItemClicked(T clickedItem);

		void onItemSelected(T selectedItem);
	}

	void setPresenter(Presenter<T> presenter);

	void setRowData(List<T> rowData);

	Widget asWidget();
}
