package com.collections.contactbook.services;

import com.collections.contactbook.models.Contact;
import org.springframework.stereotype.Service;
import com.collections.contactbook.database.Database;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private Database _db;
    public ContactService(){}

    public void addContact(String name, String phoneNumber, String email){
        boolean res = this._db.addContact(name, phoneNumber, email);
        if(res){
            Optional<Contact> c = this._db.getContactByPhone(phoneNumber);
            if(c.isEmpty()){
                System.out.println("Something went wrong");
                return;
            }
            Contact contact = c.get();
            System.out.println("New contact added!");
            contact.print();
        }
    }

    public void viewAllContacts(){
        List<Contact> contacts = _db.getAllContacts();
        _db.printContact(contacts);
    }

    public void searchContacts(String phoneNumber){
        Optional<Contact> c = _db.getContactByPhone(phoneNumber);
        if(c.isEmpty()){
            System.out.println("No contact exist with phone number "+phoneNumber);
            return;
        }
        System.out.println("Contact found!");
        c.get().print();
    }

    public void deleteContact(String id){
        boolean res = _db.deleteContact(id);
        if(res){
            System.out.println("Contact deleted successfully!");
        }else{
            System.out.println("Contact not found!");
        }
    }
}
