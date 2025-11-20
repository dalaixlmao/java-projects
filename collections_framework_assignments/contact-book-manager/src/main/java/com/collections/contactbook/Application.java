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

        // TODO MISSING: Add menu display as per assignment requirement
        // Assignment expects a numbered menu like this:
        // private void displayMenu() {
        //     System.out.println("\n=== Contact Book Manager ===");
        //     System.out.println("1. Add Contact");
        //     System.out.println("2. View All Contacts");
        //     System.out.println("3. Search Contact");
        //     System.out.println("4. Delete Contact");
        //     System.out.println("5. Exit");
        //     System.out.print("Enter your choice: ");
        // }

        // TODO MISSING: Add try-catch for exception handling
        // Scanner operations can throw InputMismatchException
        Scanner scanner = new Scanner(System.in);
        while(true){
            // ❌ ISSUE: Assignment expects numeric input (1-5), not word commands
            // Current: user types "add", "view", etc.
            // Expected: user types 1, 2, 3, 4, or 5
            //
            // TODO FIX: Change to:
            // int choice = scanner.nextInt();
            // scanner.nextLine(); // clear buffer
            // switch(choice) {
            //     case 1: // Add
            //     case 2: // View
            //     case 3: // Search
            //     case 4: // Delete
            //     case 5: // Exit
            //     default: // Invalid
            // }
            String command = scanner.next();
            if(command.equalsIgnoreCase("add")){
                // TODO IMPROVEMENT: Add input validation before calling service
                // Check if name is not empty, phone is 10 digits, email contains @
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
        // TODO IMPROVEMENT: Add scanner.close() in finally block to prevent resource leak
    }
}
