package com.junit.concurrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

/**
 * Concurrent Order Processing System - JUnit Assignment (Hard)
 *
 * Assignment Requirements:
 * 1. Implement ConcurrentOrderProcessor with thread-safe operations
 * 2. Use H2 in-memory database for testing
 * 3. Write 25+ tests covering concurrency scenarios
 * 4. Test race conditions, deadlocks, inventory consistency
 * 5. Use CountDownLatch, CyclicBarrier in tests
 * 6. Test with generics for different product types
 *
 * Use @RepeatedTest for stress testing, achieve >80% coverage
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Concurrent Order Processing System");
        
        // TODO: Implement Order, Product, InventoryManager classes
        // TODO: Implement thread-safe ConcurrentOrderProcessor
        // TODO: Implement OrderRepository with H2
        // TODO: Write concurrency tests
        // TODO: Test with 100+ concurrent threads
        
        System.out.println("\nImplement the TODO sections to complete the assignment.");
        System.out.println("Main focus: Thread safety testing with H2 database");
    }
}
