package com.sopovs.moradan.uibinder.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;
import com.sopovs.moradan.uibinder.shared.ContactDetails;

public class ContactsViewImpl extends Composite implements ContactsView<ContactDetails> {

	@UiTemplate("ContactsView.ui.xml")
	interface ContactsViewUiBinder extends UiBinder<Widget, ContactsViewImpl> {
	}

	private static ContactsViewUiBinder uiBinder =
			GWT.create(ContactsViewUiBinder.class);

	@UiField
	FlexTable contactsTable;
	@UiField
	Button addButton;
	@UiField
	Button deleteButton;

	private Presenter<ContactDetails> presenter;
	private List<ContactDetails> rowData;

	public ContactsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("addButton")
	void onAddButtonClicked(@SuppressWarnings("unused") ClickEvent event) {
		if (presenter != null) {
			presenter.onAddButtonClicked();
		}
	}

	@UiHandler("deleteButton")
	void onDeleteButtonClicked(@SuppressWarnings("unused") ClickEvent event) {
		if (presenter != null) {
			presenter.onDeleteButtonClicked();
		}
	}

	@UiHandler("contactsTable")
	void onTableClicked(ClickEvent event) {
		if (presenter != null) {
			HTMLTable.Cell cell = contactsTable.getCellForEvent(event);

			if (cell != null) {
				// Suppress clicks if the user is actually selecting the 
				//  check box
				//
				if (cell.getCellIndex() > 0) {
					presenter.onItemClicked(rowData.get(cell.getRowIndex()));
				} else {
					presenter.onItemSelected(rowData.get(cell.getRowIndex()));
				}
			}
		}
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(Presenter<ContactDetails> presenter) {
		this.presenter = presenter;

	}

	@Override
	public void setRowData(List<ContactDetails> rowData) {
		contactsTable.removeAllRows();
		for (int i = 0; i < rowData.size(); i++) {
			contactsTable.setWidget(i, 0, new CheckBox());
			contactsTable.setText(i, 1, rowData.get(i).getDisplayName());
		}
		this.rowData = rowData;
	}
}
