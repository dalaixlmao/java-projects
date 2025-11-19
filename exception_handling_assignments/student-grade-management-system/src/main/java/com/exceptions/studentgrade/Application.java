package com.exceptions.studentgrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

/**
 * Student Grade Management System - Exception Handling Assignment (Medium)
 *
 * Assignment Requirements:
 * 1. Create custom exceptions: DuplicateStudentException, StudentNotFoundException,
 *    InvalidGradeException, InvalidSubjectException
 * 2. GradeManager operations: addStudent, addGrade, getStudentAverage,
 *    getStudentGrades, removeStudent
 * 3. Validation: Student ID not empty, grade 0-100, subject not empty
 * 4. Proper exception throwing and catching
 *
 * All exceptions should have meaningful messages
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Student Grade Management System");
        
        // TODO: Create GradeManager instance
        // TODO: Process commands: ADD_STUDENT, ADD_GRADE, GET_AVERAGE, GET_GRADES, REMOVE_STUDENT
        // TODO: Implement custom exceptions with proper inheritance
        // TODO: Throw appropriate exceptions for validation failures
        // TODO: Catch and display user-friendly error messages
        
        System.out.println("\nImplement the TODO sections to complete the assignment.");
    }
}
