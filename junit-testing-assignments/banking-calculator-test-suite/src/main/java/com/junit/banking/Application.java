package com.junit.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

/**
 * Banking Calculator Test Suite - JUnit Assignment (Easy)
 *
 * Assignment Requirements:
 * 1. Implement BankingCalculator with methods:
 *    - calculateSimpleInterest
 *    - calculateCompoundInterest
 *    - calculateEMI
 *    - convertCurrency
 *    - calculateTax
 * 2. Write 15+ test cases covering normal, edge, and exception scenarios
 * 3. Use JUnit 5 annotations
 * 4. Achieve >90% code coverage
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Banking Calculator - for testing with JUnit");
        
        // TODO: Implement BankingCalculator class
        // TODO: Create TaxSlab class
        // TODO: Write comprehensive tests in test directory
        // TODO: Test all calculation methods
        // TODO: Test exception scenarios
        
        System.out.println("\nImplement the TODO sections to complete the assignment.");
        System.out.println("Main focus: Create comprehensive test suite in src/test/java");
    }
}
