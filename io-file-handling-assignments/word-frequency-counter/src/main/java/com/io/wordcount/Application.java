package com.io.wordcount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

/**
 * Word Frequency Counter - IO File Handling Assignment (Easy)
 *
 * Assignment Requirements:
 * 1. Read text file and count word frequencies (case-insensitive)
 * 2. Remove punctuation from words
 * 3. Sort by frequency (descending), then alphabetically
 * 4. Write results to word_frequency.txt
 * 5. Handle FileNotFoundException and IOException
 *
 * Use BufferedReader and BufferedWriter
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Word Frequency Counter");
        
        // TODO: Accept input file path as argument
        // TODO: Read and parse file
        // TODO: Count word frequencies using HashMap
        // TODO: Sort results
        // TODO: Write to output file
        
        System.out.println("\nImplement the TODO sections to complete the assignment.");
    }
}
