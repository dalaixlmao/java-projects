package com.collections.contactbook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// TODO: Import Scanner for user input
// TODO: Import HashMap for storing contacts
// TODO: Import other necessary classes

/**
 * Contact Book Manager - Manages contacts using Collections
 *
 * ASSIGNMENT REQUIREMENTS:
 * 1. Create a Contact class with: name, phoneNumber, email
 * 2. Use HashMap<String, Contact> to store contacts (key = phone number)
 * 3. Implement 5 operations: Add, View All, Search, Delete, Exit
 * 4. Validate inputs:
 *    - Phone: exactly 10 digits
 *    - Email: must contain '@'
 *    - Name: cannot be empty
 * 5. Maximum 100 contacts allowed
 * 6. Prevent duplicate phone numbers
 *
 * COACH EVALUATION: 5/100 - NOT STARTED
 * See EVALUATION.md for detailed feedback
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to Contact Book Manager!");

        // TODO STEP 1: Create ContactBookManager instance
        // ContactBookManager manager = new ContactBookManager();

        // TODO STEP 2: Create Scanner for user input
        // Scanner scanner = new Scanner(System.in);

        // TODO STEP 3: Create a while loop for menu
        // boolean running = true;
        // while (running) {

        // TODO STEP 4: Display menu
        // System.out.println("\n1. Add Contact");
        // System.out.println("2. View All Contacts");
        // System.out.println("3. Search Contact");
        // System.out.println("4. Delete Contact");
        // System.out.println("5. Exit");
        // System.out.print("Enter your choice: ");

        // TODO STEP 5: Read user choice
        // int choice = scanner.nextInt();
        // scanner.nextLine(); // Clear buffer

        // TODO STEP 6: Implement switch statement for menu options
        // switch (choice) {
        //     case 1: // Add Contact
        //         // Read name, phone, email
        //         // Validate inputs
        //         // Create Contact object
        //         // Call manager.addContact()
        //         break;
        //     case 2: // View All Contacts
        //         // Call manager.viewAllContacts()
        //         break;
        //     case 3: // Search Contact
        //         // Read phone number
        //         // Call manager.searchContact()
        //         break;
        //     case 4: // Delete Contact
        //         // Read phone number
        //         // Call manager.deleteContact()
        //         break;
        //     case 5: // Exit
        //         // running = false;
        //         break;
        //     default:
        //         // System.out.println("Invalid choice!");
        // }

        // TODO STEP 7: Close scanner
        // scanner.close();

        // TODO STEP 8: Create Contact.java class in this package
        /*
         * public class Contact {
         *     private String name;
         *     private String phoneNumber;
         *     private String email;
         *
         *     // Constructor with validation
         *     // Getters and setters
         *     // toString() method
         *     // equals() and hashCode() based on phoneNumber
         * }
         */

        // TODO STEP 9: Create ContactBookManager.java class
        /*
         * public class ContactBookManager {
         *     private HashMap<String, Contact> contacts;
         *     private static final int MAX_CONTACTS = 100;
         *
         *     public ContactBookManager() {
         *         contacts = new HashMap<>();
         *     }
         *
         *     public boolean addContact(Contact contact) {
         *         // Check if phone already exists
         *         // Check if max capacity reached
         *         // Add to HashMap
         *         // Return success/failure
         *     }
         *
         *     public void viewAllContacts() {
         *         // Check if empty
         *         // Loop through HashMap.values()
         *         // Display each contact
         *     }
         *
         *     public Contact searchContact(String phoneNumber) {
         *         // Use HashMap.get(phoneNumber)
         *         // Return contact or null
         *     }
         *
         *     public boolean deleteContact(String phoneNumber) {
         *         // Check if exists
         *         // Use HashMap.remove(phoneNumber)
         *         // Return success/failure
         *     }
         * }
         */

        // TODO STEP 10: Add input validation methods
        /*
         * private static boolean isValidPhone(String phone) {
         *     return phone != null && phone.matches("\\d{10}");
         * }
         *
         * private static boolean isValidEmail(String email) {
         *     return email != null && email.contains("@");
         * }
         *
         * private static boolean isValidName(String name) {
         *     return name != null && !name.trim().isEmpty();
         * }
         */

        System.out.println("\n⚠️  ASSIGNMENT NOT IMPLEMENTED!");
        System.out.println("📋 See EVALUATION.md for detailed requirements");
        System.out.println("📊 Current Grade: 5/100");
        System.out.println("🎯 Follow the TODO steps above to complete the assignment");
        System.out.println("\n💡 Start by creating Contact.java and ContactBookManager.java");
    }
}
