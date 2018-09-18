package fr.lps2ima.contact.dao;

import java.util.List;

import fr.lps2ima.contact.model.Contact;

public interface IContactDao {

	List<Contact> readAll();

	Contact findByName(String name);

	void add(Contact contact);

	void remove(String name);

}