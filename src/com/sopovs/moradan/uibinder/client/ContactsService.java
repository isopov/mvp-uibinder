package com.sopovs.moradan.uibinder.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sopovs.moradan.uibinder.shared.Contact;
import com.sopovs.moradan.uibinder.shared.ContactDetails;

@RemoteServiceRelativePath("contactsService")
public interface ContactsService extends RemoteService {

	Contact addContact(Contact contact);

	Boolean deleteContact(String id);

	ArrayList<ContactDetails> deleteContacts(ArrayList<String> ids);

	ArrayList<ContactDetails> getContactDetails();

	Contact getContact(String id);

	Contact updateContact(Contact contact);
}
