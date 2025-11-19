package com.collections.studentgrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

/**
 * Student Grade Management System - Collections Framework Assignment (Hard)
 *
 * Assignment Requirements:
 * 1. Add Student - Register student with ID, name, department
 * 2. Add Course - Create course with code, name, credits (1-4)
 * 3. Enroll Student - Enroll in course (max 6 courses, no duplicates)
 * 4. Assign Grade - Assign letter grade (A/B/C/D/F)
 * 5. View Student Report - Show courses, grades, calculate SGPA
 * 6. View Course Statistics - Count by grade, average grade point
 * 7. View Top Performers - Top N students by SGPA (min 3 courses)
 * 8. View Students by Department - Sorted by name
 *
 * Data Structures:
 * - HashMap<String, Student>, HashMap<String, Course>
 * - HashMap for enrollments and grades
 * SGPA = Sum(Grade Points × Credits) / Total Credits
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Student Grade Management System");
        
        // TODO: Create StudentGradeManager instance
        // TODO: Implement 9-option menu system
        // TODO: Implement SGPA calculation logic
        // TODO: Implement grade statistics and reporting
        // TODO: Use Streams for filtering and sorting
        
        System.out.println("\nImplement the TODO sections to complete the assignment.");
    }
}
