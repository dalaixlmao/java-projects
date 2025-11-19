package com.collections.contactbook.models;

/*
 * - Contact
 *  - id
 *  - name
 *  - phoneNumber
 *  - email
* */

public class Contact {
    private String _id;
    private String _name;
    private String _phoneNumber;
    private String _email;

    public Contact(String id, String name, String phoneNumber, String email){
        this._id = id;
        this._name = name;
        this._phoneNumber = phoneNumber;
        this._email = email;
    }

    public String getId() { return this._id; }
    public String getName() { return this._name; }
    public String getPhoneNumber() { return this._phoneNumber; }
    public String getEmail() { return this._email; }

    public void setId(String id) { this._id = id; }
    public void setName(String name) { this._name = name; }
    public void setPhoneNumber(String phoneNumber) { this._phoneNumber = phoneNumber; }
    public void setEmail(String email) { this._email = email; }

    public void print(){
        System.out.println("ID="+this._id+",Name="+this._name+",Email="+this._email+"PhoneNumber="+this._phoneNumber);
    }
}
