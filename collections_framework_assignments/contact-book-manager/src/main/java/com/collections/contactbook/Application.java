package com.collections.contactbook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.collections.contactbook.services.ContactService;
import java.util.Scanner;
import org.springframework.stereotype.Service;

/**
 * Contact Book Manager - Manages contacts using Collections
 *
 * TODO: Implement the required functionality for this assignment.
 * Refer to the assignment markdown file for detailed requirements.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    private ContactService _contactService;

    Application(ContactService contactService){
        this._contactService = contactService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started!");
        Scanner scanner = new Scanner(System.in);
        while(true){
            String command = scanner.next();
            if(command.equalsIgnoreCase("add")){
                String name, phoneNumber, email;
                name = scanner.next();
                phoneNumber = scanner.next();
                email = scanner.next();
                _contactService.addContact(name, phoneNumber, email);
            }else if(command.equalsIgnoreCase("view")){
                _contactService.viewAllContacts();
            }else if(command.equalsIgnoreCase("search")){
                String phone = scanner.next();
                _contactService.searchContacts(phone);
            }else if(command.equalsIgnoreCase("delete")){
                String id = scanner.next();
                _contactService.deleteContact(id);
            }else if(command.equalsIgnoreCase("exit")){
                System.out.println("Exiting...");
                break;
            }else{
                System.out.println("Wrong command try again!");
            }
        }
    }
}
