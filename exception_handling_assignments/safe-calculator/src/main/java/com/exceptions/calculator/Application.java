package com.exceptions.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

/**
 * Safe Calculator - Exception Handling Assignment (Easy)
 *
 * Assignment Requirements:
 * 1. Implement Calculator with methods: add, subtract, multiply, divide, modulo
 * 2. Handle ArithmeticException for division/modulo by zero
 * 3. Handle NumberFormatException for invalid number strings
 * 4. Process operations in format: "number1 operator number2"
 * 5. Display user-friendly error messages
 *
 * Operators: +, -, *, /, %
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Safe Calculator");
        
        // TODO: Create Calculator instance
        // TODO: Read operations from user (format: "10 + 5")
        // TODO: Parse input and extract numbers and operator
        // TODO: Handle ArithmeticException (division by zero)
        // TODO: Handle NumberFormatException (invalid numbers)
        // TODO: Handle invalid operators
        // TODO: Continue until user enters "exit"
        
        System.out.println("\nImplement the TODO sections to complete the assignment.");
    }
}
