#!/bin/bash

# Function to create pom.xml
create_pom() {
    local project_dir=$1
    local group_id=$2
    local artifact_id=$3
    local description=$4

    cat > "$project_dir/pom.xml" <<'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>

    <groupId>GROUP_ID</groupId>
    <artifactId>ARTIFACT_ID</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>ARTIFACT_ID</name>
    <description>DESCRIPTION</description>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
EOF

    sed -i "s/GROUP_ID/$group_id/g" "$project_dir/pom.xml"
    sed -i "s/ARTIFACT_ID/$artifact_id/g" "$project_dir/pom.xml"
    sed -i "s/DESCRIPTION/$description/g" "$project_dir/pom.xml"
}

# Function to create Application.java
create_application() {
    local java_file=$1
    local package_name=$2
    local class_description=$3

    mkdir -p "$(dirname "$java_file")"

    cat > "$java_file" <<EOF
package $package_name;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * $class_description
 *
 * TODO: Implement the required functionality for this assignment.
 * Refer to the assignment markdown file for detailed requirements.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started!");

        // TODO: Implement your assignment logic here
        // TODO: Add necessary classes and methods
        // TODO: Handle exceptions appropriately
        // TODO: Add validation where required

        System.out.println("Please implement the assignment requirements.");
    }
}
EOF
}

# Create all projects
echo "Creating 30 Spring Boot projects..."

# Build Tools Assignments
create_pom "build-tools-assignments/dependency-analyzer" "com.buildtools" "dependency-analyzer" "Dependency Analyzer Assignment"
create_application "build-tools-assignments/dependency-analyzer/src/main/java/com/buildtools/dependencyanalyzer/Application.java" "com.buildtools.dependencyanalyzer" "Dependency Analyzer - Analyzes Maven/Gradle dependencies"

create_pom "build-tools-assignments/build-lifecycle-simulator" "com.buildtools" "build-lifecycle-simulator" "Build Lifecycle Simulator Assignment"
create_application "build-tools-assignments/build-lifecycle-simulator/src/main/java/com/buildtools/buildlifecycle/Application.java" "com.buildtools.buildlifecycle" "Build Lifecycle Simulator - Simulates build phases"

create_pom "build-tools-assignments/multi-module-dependency-manager" "com.buildtools" "multi-module-dependency-manager" "Multi-Module Dependency Manager Assignment"
create_application "build-tools-assignments/multi-module-dependency-manager/src/main/java/com/buildtools/multimodule/Application.java" "com.buildtools.multimodule" "Multi-Module Dependency Manager - Manages multi-module dependencies"

# Collections Framework Assignments
create_pom "collections_framework_assignments/contact-book-manager" "com.collections" "contact-book-manager" "Contact Book Manager Assignment"
create_application "collections_framework_assignments/contact-book-manager/src/main/java/com/collections/contactbook/Application.java" "com.collections.contactbook" "Contact Book Manager - Manages contacts using Collections"

create_pom "collections_framework_assignments/library-management-system" "com.collections" "library-management-system" "Library Management System Assignment"
create_application "collections_framework_assignments/library-management-system/src/main/java/com/collections/library/Application.java" "com.collections.library" "Library Management System - Manages books and members"

create_pom "collections_framework_assignments/student-grade-management-system" "com.collections" "student-grade-management-system" "Student Grade Management System Assignment"
create_application "collections_framework_assignments/student-grade-management-system/src/main/java/com/collections/student/Application.java" "com.collections.student" "Student Grade Management System - Manages student grades"

# Exception Handling Assignments
create_pom "exception_handling_assignments/safe-calculator" "com.exception" "safe-calculator" "Safe Calculator Assignment"
create_application "exception_handling_assignments/safe-calculator/src/main/java/com/exception/calculator/Application.java" "com.exception.calculator" "Safe Calculator - Calculator with exception handling"

create_pom "exception_handling_assignments/student-grade-management-system" "com.exception" "student-grade-management-system" "Student Grade Management System Assignment"
create_application "exception_handling_assignments/student-grade-management-system/src/main/java/com/exception/student/Application.java" "com.exception.student" "Student Grade Management System - With custom exceptions"

create_pom "exception_handling_assignments/bank-transaction-processing-system" "com.exception" "bank-transaction-processing-system" "Bank Transaction Processing System Assignment"
create_application "exception_handling_assignments/bank-transaction-processing-system/src/main/java/com/exception/bank/Application.java" "com.exception.bank" "Bank Transaction Processing System - With comprehensive exception handling"

# Dependency Injection Assignments
create_pom "dependency-injection-assignments/e-commerce-order-processing-system" "com.di" "e-commerce-order-processing-system" "E-Commerce Order Processing System Assignment"
create_application "dependency-injection-assignments/e-commerce-order-processing-system/src/main/java/com/di/ecommerce/Application.java" "com.di.ecommerce" "E-Commerce Order Processing System - Using dependency injection"

create_pom "dependency-injection-assignments/custom-dependency-injection-container" "com.di" "custom-dependency-injection-container" "Custom Dependency Injection Container Assignment"
create_application "dependency-injection-assignments/custom-dependency-injection-container/src/main/java/com/di/container/Application.java" "com.di.container" "Custom Dependency Injection Container - Build your own DI container"

create_pom "dependency-injection-assignments/annotation-based-ioc-container-with-auto-wiring" "com.di" "annotation-based-ioc-container" "Annotation-Based IoC Container Assignment"
create_application "dependency-injection-assignments/annotation-based-ioc-container-with-auto-wiring/src/main/java/com/di/ioc/Application.java" "com.di.ioc" "Annotation-Based IoC Container - With auto-wiring support"

# Generics Assignments
create_pom "generics-assignments/generic-storage-container" "com.generics" "generic-storage-container" "Generic Storage Container Assignment"
create_application "generics-assignments/generic-storage-container/src/main/java/com/generics/storage/Application.java" "com.generics.storage" "Generic Storage Container - Type-safe storage using generics"

create_pom "generics-assignments/generic-pair-with-comparator" "com.generics" "generic-pair-with-comparator" "Generic Pair with Comparator Assignment"
create_application "generics-assignments/generic-pair-with-comparator/src/main/java/com/generics/pair/Application.java" "com.generics.pair" "Generic Pair with Comparator - Generic pair implementation"

create_pom "generics-assignments/generic-type-safe-repository-system" "com.generics" "generic-type-safe-repository-system" "Generic Type-Safe Repository System Assignment"
create_application "generics-assignments/generic-type-safe-repository-system/src/main/java/com/generics/repository/Application.java" "com.generics.repository" "Generic Type-Safe Repository System - Generic repository pattern"

# IO File Handling Assignments
create_pom "io-file-handling-assignments/word-frequency-counter" "com.io" "word-frequency-counter" "Word Frequency Counter Assignment"
create_application "io-file-handling-assignments/word-frequency-counter/src/main/java/com/io/wordfreq/Application.java" "com.io.wordfreq" "Word Frequency Counter - Counts word frequencies in files"

create_pom "io-file-handling-assignments/log-file-analyzer" "com.io" "log-file-analyzer" "Log File Analyzer Assignment"
create_application "io-file-handling-assignments/log-file-analyzer/src/main/java/com/io/loganalyzer/Application.java" "com.io.loganalyzer" "Log File Analyzer - Analyzes application log files"

create_pom "io-file-handling-assignments/csv-based-employee-management-system" "com.io" "csv-employee-management-system" "CSV-Based Employee Management System Assignment"
create_application "io-file-handling-assignments/csv-based-employee-management-system/src/main/java/com/io/employee/Application.java" "com.io.employee" "CSV-Based Employee Management System - Manages employees with CSV"

# Java8 Assignments
create_pom "java8_assignments/student-performance-analyzer" "com.java8" "student-performance-analyzer" "Student Performance Analyzer Assignment"
create_application "java8_assignments/student-performance-analyzer/src/main/java/com/java8/student/Application.java" "com.java8.student" "Student Performance Analyzer - Using Java 8 streams and lambdas"

create_pom "java8_assignments/e-commerce-order-processing-system" "com.java8" "e-commerce-order-processing-system" "E-Commerce Order Processing System Assignment"
create_application "java8_assignments/e-commerce-order-processing-system/src/main/java/com/java8/ecommerce/Application.java" "com.java8.ecommerce" "E-Commerce Order Processing System - Using Java 8 features"

create_pom "java8_assignments/movie-recommendation-engine" "com.java8" "movie-recommendation-engine" "Movie Recommendation Engine Assignment"
create_application "java8_assignments/movie-recommendation-engine/src/main/java/com/java8/movie/Application.java" "com.java8.movie" "Movie Recommendation Engine - Advanced Java 8 features"

# JDBC Assignments
create_pom "jdbc-assignments/library-book-management-system" "com.jdbc" "library-book-management-system" "Library Book Management System Assignment"
create_application "jdbc-assignments/library-book-management-system/src/main/java/com/jdbc/library/Application.java" "com.jdbc.library" "Library Book Management System - Using JDBC"

create_pom "jdbc-assignments/employee-payroll-system-with-csv-import" "com.jdbc" "employee-payroll-system" "Employee Payroll System Assignment"
create_application "jdbc-assignments/employee-payroll-system-with-csv-import/src/main/java/com/jdbc/payroll/Application.java" "com.jdbc.payroll" "Employee Payroll System - JDBC with CSV import"

create_pom "jdbc-assignments/banking-transaction-system-with-concurrent-operations" "com.jdbc" "banking-transaction-system" "Banking Transaction System Assignment"
create_application "jdbc-assignments/banking-transaction-system-with-concurrent-operations/src/main/java/com/jdbc/banking/Application.java" "com.jdbc.banking" "Banking Transaction System - JDBC with concurrency"

# JUnit Testing Assignments
create_pom "junit-testing-assignments/banking-calculator-test-suite" "com.junit" "banking-calculator-test-suite" "Banking Calculator Test Suite Assignment"
create_application "junit-testing-assignments/banking-calculator-test-suite/src/main/java/com/junit/calculator/Application.java" "com.junit.calculator" "Banking Calculator Test Suite - JUnit testing"

create_pom "junit-testing-assignments/log-file-analyzer-with-testing" "com.junit" "log-file-analyzer-with-testing" "Log File Analyzer with Testing Assignment"
create_application "junit-testing-assignments/log-file-analyzer-with-testing/src/main/java/com/junit/loganalyzer/Application.java" "com.junit.loganalyzer" "Log File Analyzer with Testing - Advanced JUnit"

create_pom "junit-testing-assignments/concurrent-order-processing-system-with-database" "com.junit" "concurrent-order-processing-system" "Concurrent Order Processing System Assignment"
create_application "junit-testing-assignments/concurrent-order-processing-system-with-database/src/main/java/com/junit/orderprocessing/Application.java" "com.junit.orderprocessing" "Concurrent Order Processing System - JUnit with database"

# Multithreading Concurrency Assignments
create_pom "multithreading_concurrency_assignments/thread-safe-rate-limiter" "com.concurrency" "thread-safe-rate-limiter" "Thread-Safe Rate Limiter Assignment"
create_application "multithreading_concurrency_assignments/thread-safe-rate-limiter/src/main/java/com/concurrency/ratelimiter/Application.java" "com.concurrency.ratelimiter" "Thread-Safe Rate Limiter - Multithreading basics"

create_pom "multithreading_concurrency_assignments/parallel-file-word-counter" "com.concurrency" "parallel-file-word-counter" "Parallel File Word Counter Assignment"
create_application "multithreading_concurrency_assignments/parallel-file-word-counter/src/main/java/com/concurrency/wordcount/Application.java" "com.concurrency.wordcount" "Parallel File Word Counter - Concurrent file processing"

create_pom "multithreading_concurrency_assignments/advanced-task-scheduler-with-priorities" "com.concurrency" "advanced-task-scheduler" "Advanced Task Scheduler Assignment"
create_application "multithreading_concurrency_assignments/advanced-task-scheduler-with-priorities/src/main/java/com/concurrency/scheduler/Application.java" "com.concurrency.scheduler" "Advanced Task Scheduler - Priority-based scheduling"

echo "All 30 projects created successfully!"
