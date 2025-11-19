package com.collections.contactbook.database;

import com.collections.contactbook.models.*;
import com.collections.contactbook.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Database {
    // TODO IMPROVEMENT: Avoid static fields in Spring Components - defeats bean lifecycle
    // Should be: private final Map<String, Contact> contacts = new HashMap<>();
    private static final Map<String, Contact> _contacts = new HashMap<>();
    private static final Map<String, Contact> _phoneNumbers = new HashMap<>();

    // TODO IMPROVEMENT: Remove underscore prefix from field names
    private Utils _utils;
    public Database(Utils utils){this._utils = utils;}

    public boolean containsPhoneNumber(String phoneNumber){
        return Database._phoneNumbers.containsKey(phoneNumber);
    }

    public boolean validateEmail(String email){
        return _utils.validateEmail(email);
    }

    public boolean validatePhone(String phone){
        return _utils.validatePhone(phone);
    }

    public boolean addContact(String name, String phoneNumber, String email){
        // 🚨 CRITICAL BUG: Validation logic is INVERTED! This rejects VALID contacts!
        // Current logic: "if phone exists AND email valid AND phone valid, then reject"
        // This is backwards - you're rejecting when validation PASSES!
        //
        // TODO FIX: Change to proper validation logic:
        // if(containsPhoneNumber(phoneNumber)) {
        //     System.out.println("Error: Phone number already exists!");
        //     return false;
        // }
        // if(!validateEmail(email)) {  // Note the ! (NOT)
        //     System.out.println("Error: Invalid email format!");
        //     return false;
        // }
        // if(!validatePhone(phoneNumber)) {  // Note the ! (NOT)
        //     System.out.println("Error: Phone must be exactly 10 digits!");
        //     return false;
        // }
        if(containsPhoneNumber(phoneNumber) && validateEmail(email) && validatePhone(phoneNumber))
            return false;

        // TODO MISSING: Add MAX_CONTACTS check as per assignment requirement
        // private static final int MAX_CONTACTS = 100;
        // if(_contacts.size() >= MAX_CONTACTS) {
        //     System.out.println("Error: Contact book full (max 100 contacts)");
        //     return false;
        // }

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
