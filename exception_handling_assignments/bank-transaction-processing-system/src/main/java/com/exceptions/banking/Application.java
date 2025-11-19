package com.exceptions.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

/**
 * Bank Transaction Processing System - Exception Handling Assignment (Hard)
 *
 * Assignment Requirements:
 * 1. Custom exception hierarchy with BankException as base
 * 2. Account operations: createAccount, deposit, withdraw, transfer, getBalance
 * 3. Validation: Minimum balance 1000, max transaction 50000, account number 10 digits
 * 4. Transfer rollback on failure (atomic operation)
 * 5. Transaction history tracking with status
 *
 * Exceptions: InsufficientFundsException, AccountBlockedException,
 * TransactionLimitExceededException, MinimumBalanceException, InvalidAccountException
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Banking Transaction System");
        
        // TODO: Create BankingSystem instance
        // TODO: Implement exception hierarchy
        // TODO: Implement atomic transfer with rollback
        // TODO: Track transaction history with status
        // TODO: Handle all validation rules
        // TODO: Process commands and handle exceptions
        
        System.out.println("\nImplement the TODO sections to complete the assignment.");
    }
}
