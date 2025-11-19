package com.collections.contactbook.database;

import com.collections.contactbook.models.*;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.*;

@Component
public class Database {
    private static final Map<String, Contact> _contacts = new HashMap<>();
    private static final Map<String, Contact> _phoneNumbers = new HashMap<>();
    public Database(){}

    public boolean containsPhoneNumber(String phoneNumber){
        return Database._phoneNumbers.containsKey(phoneNumber);
    }

    public boolean addContact(String name, String phoneNumber, String email){
        if(containsPhoneNumber(phoneNumber))
            return false;
        String newId = UUID.randomUUID().toString();
        while(Database._contacts.containsKey(newId)) newId = UUID.randomUUID().toString();
        Contact newContact = new Contact(newId, name, phoneNumber, email);
        Database._contacts.put(newId, newContact);
        Database._phoneNumbers.put(phoneNumber, newContact);
        return true;
    }

    public List<Contact> getAllContacts(){
        return Database._contacts.values().stream().toList();
    }

    public Optional<Contact> getContactById(String id){
        if(!Database._contacts.containsKey(id)){
            return Optional.empty();
        }
        return Optional.of(Database._contacts.get(id));
    }

    public void printContact(List<Contact> contacts){
        contacts.forEach(Contact::print);
    }

    public Optional<Contact> getContactByPhone(String phone) {
        if(!Database._phoneNumbers.containsKey(phone)){
            return Optional.empty();
        }
        return Optional.of(Database._phoneNumbers.get(phone));
    }

    public boolean deleteContact(String id){
        Optional<Contact> c = getContactById(id);
        if(c.isEmpty())
            return false;
        Contact contact = c.get();
        Database._phoneNumbers.remove(contact.getPhoneNumber());
        Database._contacts.remove(id);
        return true;
    }
}
