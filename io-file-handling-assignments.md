# I/O and File Handling Assignments

## Assignment 1: Word Frequency Counter (Easy)

### Problem Statement
Create a program that reads a text file, counts the frequency of each word (case-insensitive), and writes the results to an output file sorted by frequency in descending order.

### Functional Requirements

1. **Read Input File**
   - Accept the input file path as a command-line argument or user input
   - Handle file not found exceptions gracefully

2. **Process Text**
   - Convert all text to lowercase for case-insensitive comparison
   - Remove punctuation marks (.,!?;:) from words
   - Ignore empty lines and whitespace
   - Split text into individual words

3. **Count Frequencies**
   - Store word-frequency pairs in an appropriate collection
   - Count each unique word's occurrences

4. **Generate Output**
   - Sort words by frequency (highest to lowest)
   - If frequencies are equal, sort alphabetically
   - Write results to "word_frequency.txt" in the format: `word: frequency`

5. **Error Handling**
   - Handle FileNotFoundException
   - Handle IOException
   - Display meaningful error messages

### Sample Input File (input.txt)
```
Hello World! Hello Java.
Java is great. Java is powerful.
Hello programming world.
```

### Sample Output File (word_frequency.txt)
```
java: 3
hello: 3
world: 2
is: 2
great: 1
powerful: 1
programming: 1
```

### Expected Classes/Interfaces
- `WordFrequencyCounter` (main class)
- Use `HashMap<String, Integer>` or `Map<String, Long>`
- Use `BufferedReader` for reading
- Use `BufferedWriter` for writing

### Bonus Points
- Use Java 8 Streams for processing
- Handle multiple file formats (specify encoding)
- Add option to exclude common stop words (the, is, a, an, etc.)

---

## Assignment 2: Log File Analyzer (Medium)

### Problem Statement
Build a log file analyzer that reads application log files, filters entries based on log level and time range, generates statistics, and exports filtered logs with a summary report.

### Functional Requirements

1. **Read Log File**
   - Accept log file path and filter criteria as input
   - Parse log entries in the format: `[TIMESTAMP] [LEVEL] [MODULE] Message`
   - Example: `[2024-11-19 10:30:45] [ERROR] [PaymentService] Payment processing failed`

2. **Filter Logs**
   - Filter by log level: DEBUG, INFO, WARN, ERROR, FATAL
   - Filter by date range (start date to end date)
   - Filter by module name (optional)
   - Support multiple filter combinations

3. **Generate Statistics**
   - Count total logs per level
   - Count logs per module
   - Find most frequent error messages
   - Calculate time span of logs
   - Identify peak error hours

4. **Export Results**
   - Write filtered logs to "filtered_logs.txt"
   - Write statistics to "log_analysis_report.txt"
   - Format report in a readable structure

5. **Error Handling**
   - Handle invalid log format entries (skip and log count of skipped entries)
   - Handle invalid date formats
   - Handle file I/O exceptions

### Sample Input File (application.log)
```
[2024-11-19 09:15:30] [INFO] [UserService] User login successful
[2024-11-19 09:16:45] [ERROR] [PaymentService] Payment gateway timeout
[2024-11-19 09:17:20] [DEBUG] [DatabaseService] Query executed in 45ms
[2024-11-19 09:18:10] [ERROR] [PaymentService] Payment processing failed
[2024-11-19 09:19:05] [WARN] [CacheService] Cache miss for key: user_123
[2024-11-19 10:20:30] [ERROR] [PaymentService] Payment gateway timeout
[2024-11-19 10:21:15] [INFO] [UserService] User logout successful
[2024-11-19 10:22:40] [FATAL] [DatabaseService] Database connection lost
```

### Sample Filter Criteria
```
Log Level: ERROR, FATAL
Date Range: 2024-11-19 09:00:00 to 2024-11-19 10:30:00
Module: (All)
```

### Sample Output File 1 (filtered_logs.txt)
```
[2024-11-19 09:16:45] [ERROR] [PaymentService] Payment gateway timeout
[2024-11-19 09:18:10] [ERROR] [PaymentService] Payment processing failed
[2024-11-19 10:20:30] [ERROR] [PaymentService] Payment gateway timeout
[2024-11-19 10:22:40] [FATAL] [DatabaseService] Database connection lost
```

### Sample Output File 2 (log_analysis_report.txt)
```
=== Log Analysis Report ===
Generated on: 2024-11-19 11:00:00

Filter Criteria:
- Log Levels: ERROR, FATAL
- Date Range: 2024-11-19 09:00:00 to 2024-11-19 10:30:00
- Module: All

Statistics:
- Total Logs Analyzed: 8
- Total Logs Matched: 4
- Skipped/Invalid Entries: 0

Logs by Level:
- ERROR: 3
- FATAL: 1

Logs by Module:
- PaymentService: 3
- DatabaseService: 1

Top Error Messages:
1. "Payment gateway timeout" - 2 occurrences
2. "Payment processing failed" - 1 occurrence
3. "Database connection lost" - 1 occurrence

Time Analysis:
- First Log: 2024-11-19 09:16:45
- Last Log: 2024-11-19 10:22:40
- Time Span: 1 hour 5 minutes 55 seconds
```

### Expected Classes/Interfaces
- `LogEntry` (class to represent a log entry)
- `LogAnalyzer` (main analyzer class)
- `LogFilter` (class for filter criteria)
- `LogStatistics` (class for statistics generation)
- Use `Optional<T>` for nullable fields
- Use Streams API for filtering and aggregation
- Use `LocalDateTime` for date/time handling

### Bonus Points
- Support regex-based message filtering
- Export to JSON or CSV format
- Support multiple log file formats
- Create visualizable statistics (exportable format)

---

## Assignment 3: CSV-Based Employee Management System (Hard)

### Problem Statement
Design and implement a complete employee management system that performs CRUD operations on employee data stored in CSV files, supports complex queries, data validation, and generates various reports with data export capabilities.

### Functional Requirements

1. **Data Model**
   - Employee: id, firstName, lastName, email, department, designation, salary, dateOfJoining
   - Department: id, name, manager, location
   - Store employees in "employees.csv" and departments in "departments.csv"

2. **CRUD Operations**
   - **Create**: Add new employee with validation
   - **Read**: Fetch employee by ID or list all employees
   - **Update**: Update employee details
   - **Delete**: Remove employee (soft delete by adding "isActive" flag)
   - Maintain data integrity across operations

3. **Data Validation**
   - Email format validation using regex
   - Salary must be positive
   - Date of joining must be valid and not in future
   - Department must exist in departments.csv
   - No duplicate employee IDs

4. **Search and Filter Operations**
   - Search employees by name (partial match, case-insensitive)
   - Filter by department
   - Filter by salary range
   - Filter by date of joining range
   - Support multiple filter combinations

5. **Aggregation and Reports**
   - Generate department-wise employee count
   - Calculate average salary by department
   - Find highest paid employees (top N)
   - Generate salary distribution report (ranges: <30K, 30K-60K, 60K-100K, >100K)
   - List employees by tenure (sorted by joining date)
   - Export filtered results to CSV

6. **Backup and Recovery**
   - Create backup before any modification
   - Store backups with timestamp in "backups/" folder
   - Implement rollback functionality to restore from backup

7. **Error Handling and Logging**
   - Handle CSV parsing errors
   - Handle data validation errors
   - Log all operations to "operations.log" with timestamp
   - Handle concurrent file access scenarios
   - Display user-friendly error messages

8. **Command-Line Interface**
   - Interactive menu-based system
   - Support for command-line arguments for batch operations

### Sample Input File 1 (employees.csv)
```csv
id,firstName,lastName,email,department,designation,salary,dateOfJoining,isActive
1,John,Doe,john.doe@company.com,IT,Software Engineer,75000,2022-03-15,true
2,Jane,Smith,jane.smith@company.com,HR,HR Manager,85000,2021-07-20,true
3,Bob,Johnson,bob.johnson@company.com,IT,Senior Developer,95000,2020-05-10,true
4,Alice,Williams,alice.w@company.com,Finance,Accountant,60000,2023-01-08,true
5,Charlie,Brown,charlie.b@company.com,IT,Tech Lead,110000,2019-11-25,true
```

### Sample Input File 2 (departments.csv)
```csv
id,name,manager,location
1,IT,Charlie Brown,Building A
2,HR,Jane Smith,Building B
3,Finance,David Miller,Building C
```

### Sample Operations and Outputs

#### Operation 1: Add New Employee
**Input:**
```
firstName: Sarah
lastName: Connor
email: sarah.connor@company.com
department: IT
designation: DevOps Engineer
salary: 80000
dateOfJoining: 2024-11-01
```

**Output:**
```
Employee added successfully!
Employee ID: 6
Backup created: backups/employees_backup_20241119_103045.csv
```

#### Operation 2: Search Employees
**Input:**
```
Search by: department
Department: IT
```

**Output (Console):**
```
=== Search Results ===
Found 4 employees in IT department:

ID: 1 | Name: John Doe | Designation: Software Engineer | Salary: $75,000
ID: 3 | Name: Bob Johnson | Designation: Senior Developer | Salary: $95,000
ID: 5 | Name: Charlie Brown | Designation: Tech Lead | Salary: $110,000
ID: 6 | Name: Sarah Connor | Designation: DevOps Engineer | Salary: $80,000
```

#### Operation 3: Generate Department Report
**Input:**
```
Report Type: Department Analysis
```

**Output File (department_report.csv):**
```csv
department,employeeCount,averageSalary,highestSalary,lowestSalary
IT,4,90000.00,110000.00,75000.00
HR,1,85000.00,85000.00,85000.00
Finance,1,60000.00,60000.00,60000.00
```

#### Operation 4: Generate Salary Distribution Report
**Output File (salary_distribution.txt):**
```
=== Salary Distribution Report ===
Generated on: 2024-11-19 10:45:30

Total Employees: 6

Salary Ranges:
- Below $30,000: 0 employees (0.00%)
- $30,000 - $60,000: 1 employee (16.67%)
  * Alice Williams - Finance - $60,000
- $60,000 - $100,000: 3 employees (50.00%)
  * John Doe - IT - $75,000
  * Sarah Connor - IT - $80,000
  * Jane Smith - HR - $85,000
- Above $100,000: 2 employees (33.33%)
  * Bob Johnson - IT - $95,000
  * Charlie Brown - IT - $110,000

Average Salary: $84,166.67
Median Salary: $82,500.00
```

#### Operation 5: Update Employee
**Input:**
```
Employee ID: 1
Field to update: salary
New value: 82000
```

**Output:**
```
Employee updated successfully!
Old Value: 75000
New Value: 82000
Backup created: backups/employees_backup_20241119_104530.csv
```

### Expected Classes/Interfaces

1. **Core Classes**
   - `Employee` (data model with validation)
   - `Department` (data model)
   - `EmployeeRepository` (handles CSV operations)
   - `DepartmentRepository` (handles CSV operations)

2. **Service Classes**
   - `EmployeeService` (business logic)
   - `ValidationService` (data validation)
   - `ReportService` (report generation)
   - `BackupService` (backup and recovery)

3. **Utility Classes**
   - `CSVReader<T>` (generic CSV reader)
   - `CSVWriter<T>` (generic CSV writer)
   - `Logger` (operation logging)

4. **Exception Classes**
   - `EmployeeNotFoundException`
   - `DuplicateEmployeeException`
   - `InvalidDataException`
   - `CSVProcessingException`

### Technical Requirements
- Use Generics for CSV reading/writing utilities
- Use Java 8 Streams for filtering and aggregation
- Use Optional<Employee> for nullable returns
- Use Lambda expressions for sorting and filtering
- Use try-with-resources for file operations
- Use Collections (List, Map, Set) appropriately
- Implement proper exception handling hierarchy

### Menu Structure
```
=== Employee Management System ===
1. Add Employee
2. View Employee
3. Update Employee
4. Delete Employee
5. Search Employees
6. Generate Reports
7. Backup Data
8. Restore from Backup
9. View Logs
0. Exit

Select an option:
```

### Bonus Points
- Implement undo/redo functionality for last N operations
- Add data export to JSON format
- Implement concurrent file access with file locking
- Add bulk import from CSV with validation report
- Create unit tests for core functionality
- Add support for employee photos (store file paths)
- Implement email notification system (write to email_queue.txt)

### Evaluation Criteria
- Code organization and modularity (20%)
- Proper use of OOP principles (20%)
- Exception handling and validation (15%)
- File I/O operations correctness (15%)
- Use of Java 8+ features (15%)
- Report generation accuracy (10%)
- Code documentation (5%)

---

## General Guidelines for All Assignments

1. **Code Quality**
   - Follow Java naming conventions
   - Write clean, readable code
   - Add appropriate comments
   - Use meaningful variable names

2. **File Handling**
   - Always use try-with-resources for auto-closing
   - Handle file not found scenarios
   - Handle I/O exceptions properly
   - Use appropriate buffer sizes

3. **Testing**
   - Test with various input files
   - Test edge cases (empty files, large files)
   - Test error scenarios
   - Verify output file contents

4. **Documentation**
   - Include README.md with setup instructions
   - Document public methods
   - Provide sample input files
   - Include execution commands

5. **Submission**
   - Source code files (.java)
   - Sample input files
   - README.md
   - Any generated output files (as samples)

---

## Resources and Tips

### File I/O Classes to Use
- `BufferedReader` and `BufferedWriter` for text files
- `FileReader` and `FileWriter` (with buffering)
- `Files` class (java.nio.file) for modern file operations
- `Path` and `Paths` for file path handling

### Collections to Consider
- `HashMap` / `LinkedHashMap` for key-value pairs
- `ArrayList` for dynamic arrays
- `TreeMap` for sorted key-value pairs
- `HashSet` for unique elements

### Java 8+ Features to Leverage
- Streams API: `filter()`, `map()`, `collect()`, `sorted()`
- Lambda expressions for comparators
- Optional for null safety
- Method references where applicable

### Common Pitfalls to Avoid
- Not closing file resources (use try-with-resources)
- Not handling character encoding (specify UTF-8)
- Not validating input data
- Not handling empty or corrupted files
- Ignoring exceptions

---

**Happy Coding! 🚀**
