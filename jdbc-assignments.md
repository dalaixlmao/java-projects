# JDBC and Database Connectivity - Practice Assignments

## Assignment 1: Library Book Management System (Easy)

### Problem Statement
Design and implement a Library Book Management System using JDBC that allows users to perform CRUD operations on books. The system should connect to a relational database, handle basic exceptions, and provide a command-line interface for interaction.

### Functional Requirements

#### Core Features
1. **Database Setup**
   - Create a `books` table with columns: `id` (auto-increment), `title`, `author`, `isbn`, `publication_year`, `available_copies`
   - Use proper data types and constraints

2. **CRUD Operations**
   - **Add Book**: Insert a new book into the database
   - **View All Books**: Display all books in a formatted manner
   - **Search Book**: Search by title, author, or ISBN
   - **Update Book**: Update book details by ID
   - **Delete Book**: Remove a book by ID

3. **Exception Handling**
   - Handle `SQLException` gracefully
   - Validate user inputs (non-empty fields, valid year, positive copies)
   - Handle duplicate ISBN entries

4. **Configuration Management**
   - Read database credentials from a properties file (`db.properties`)
   - Format: 
     ```
     db.url=jdbc:mysql://localhost:3306/library_db
     db.username=root
     db.password=password
     ```

5. **Resource Management**
   - Properly close all database connections, statements, and result sets
   - Use try-with-resources where applicable

#### Technical Requirements
- Use plain JDBC (no ORM frameworks)
- Implement at least one method using `PreparedStatement`
- Use Collections (List, Map) to temporarily store and display data
- Use Java 8 features for data filtering/sorting (Optional)

### Sample Input/Output

#### Sample Input 1: Add Book
```
Choose operation:
1. Add Book
2. View All Books
3. Search Book
4. Update Book
5. Delete Book
6. Exit

Enter choice: 1
Enter title: The Great Gatsby
Enter author: F. Scott Fitzgerald
Enter ISBN: 978-0-7432-7356-5
Enter publication year: 1925
Enter available copies: 5
```

#### Sample Output 1:
```
Book added successfully!
Book ID: 1
Title: The Great Gatsby
Author: F. Scott Fitzgerald
ISBN: 978-0-7432-7356-5
```

#### Sample Input 2: View All Books
```
Enter choice: 2
```

#### Sample Output 2:
```
=== Library Books ===
ID: 1 | Title: The Great Gatsby | Author: F. Scott Fitzgerald | ISBN: 978-0-7432-7356-5 | Year: 1925 | Copies: 5
ID: 2 | Title: 1984 | Author: George Orwell | ISBN: 978-0-452-28423-4 | Year: 1949 | Copies: 3
ID: 3 | Title: To Kill a Mockingbird | Author: Harper Lee | ISBN: 978-0-06-112008-4 | Year: 1960 | Copies: 4

Total books: 3
```

#### Sample Input 3: Search Book
```
Enter choice: 3
Search by (1-Title, 2-Author, 3-ISBN): 2
Enter author name: George Orwell
```

#### Sample Output 3:
```
=== Search Results ===
ID: 2 | Title: 1984 | Author: George Orwell | ISBN: 978-0-452-28423-4 | Year: 1949 | Copies: 3

Found 1 book(s)
```

#### Sample Error Handling:
```
Enter choice: 1
Enter title: 1984
Enter author: George Orwell
Enter ISBN: 978-0-452-28423-4
Enter publication year: 1949
Enter available copies: 3

Error: Book with ISBN 978-0-452-28423-4 already exists!
```

### Expected Deliverables
1. Complete Java source code with proper package structure
2. SQL script to create the database schema
3. Sample `db.properties` file
4. README with setup instructions

---

## Assignment 2: Employee Payroll System with CSV Import (Medium)

### Problem Statement
Build an Employee Payroll Management System that imports employee data from CSV files, processes payroll calculations, stores data in a database using JDBC transactions, and generates reports using Java 8 Streams. The system should handle concurrent data operations and provide comprehensive exception handling.

### Functional Requirements

#### Core Features

1. **Database Schema**
   - **employees** table: `id`, `employee_code`, `name`, `email`, `department`, `hire_date`, `salary`
   - **payroll_records** table: `id`, `employee_id`, `month`, `year`, `base_salary`, `bonus`, `deductions`, `net_salary`, `payment_date`
   - **departments** table: `id`, `department_name`, `budget`

2. **CSV Import Functionality**
   - Read employee data from CSV file
   - Validate data format and business rules
   - Batch insert employees using JDBC batch operations
   - Handle duplicate employee codes
   - Log import errors to a separate error file

3. **Payroll Processing**
   - Calculate monthly payroll for all employees
   - Formula: `net_salary = base_salary + bonus - deductions`
   - Use database transactions (commit all or rollback all)
   - Update department budget after payroll processing
   - Generate payroll reports

4. **Query Operations**
   - Find employees by department
   - Get payroll history for an employee
   - Calculate total payroll expense by department
   - Find top N earners
   - Get employees hired in a date range

5. **Reporting with Java 8 Streams**
   - Generate department-wise salary statistics (min, max, average)
   - Group employees by salary ranges
   - Filter and sort employee data
   - Export reports to CSV

6. **Exception Handling**
   - Custom exceptions: `InvalidEmployeeDataException`, `PayrollProcessingException`, `CSVImportException`
   - Handle SQL exceptions with transaction rollback
   - Validate email format, salary ranges, date formats
   - Log all exceptions with timestamp to `error.log` file

7. **Connection Pooling** (Basic implementation)
   - Implement a simple connection pool (min 5, max 10 connections)
   - Reuse connections instead of creating new ones
   - Handle connection timeout

#### Technical Requirements
- Use `PreparedStatement` for all queries
- Implement transaction management
- Use Java 8 Streams for data processing
- Use `LocalDate` for date handling
- Implement try-with-resources for all database operations
- Use Generics for reusable DAO methods
- Read database config from properties file

### Sample Input/Output

#### Sample CSV Input File: `employees.csv`
```csv
employee_code,name,email,department,hire_date,salary
EMP001,John Doe,john.doe@company.com,Engineering,2023-01-15,75000
EMP002,Jane Smith,jane.smith@company.com,Marketing,2023-02-20,65000
EMP003,Bob Johnson,bob.johnson@company.com,Engineering,2023-03-10,80000
EMP004,Alice Williams,alice.w@company.com,HR,2023-04-05,60000
EMP005,Charlie Brown,charlie.b@company.com,Finance,2023-05-12,70000
```

#### Sample Input 1: Import Employees
```
=== Employee Payroll System ===
1. Import Employees from CSV
2. Process Monthly Payroll
3. View Employee Details
4. Generate Department Report
5. Export Payroll Report
6. Exit

Enter choice: 1
Enter CSV file path: employees.csv
```

#### Sample Output 1:
```
=== Importing Employees ===
Processing file: employees.csv
✓ Imported: EMP001 - John Doe
✓ Imported: EMP002 - Jane Smith
✓ Imported: EMP003 - Bob Johnson
✓ Imported: EMP004 - Alice Williams
✓ Imported: EMP005 - Charlie Brown

Import Summary:
Total records: 5
Successfully imported: 5
Failed: 0
Time taken: 234ms
```

#### Sample Input 2: Process Payroll
```
Enter choice: 2
Enter month (1-12): 11
Enter year: 2024
Enter bonus percentage (0-100): 10
Enter deduction percentage (0-100): 5
```

#### Sample Output 2:
```
=== Processing Payroll for November 2024 ===

Processing employee: EMP001 - John Doe
  Base Salary: $75,000
  Bonus (10%): $7,500
  Deductions (5%): $3,750
  Net Salary: $78,750

Processing employee: EMP002 - Jane Smith
  Base Salary: $65,000
  Bonus (10%): $6,500
  Deductions (5%): $3,250
  Net Salary: $68,250

... (similar for all employees)

=== Payroll Summary ===
Total Employees Processed: 5
Total Payroll Amount: $364,000
Total Bonuses: $36,400
Total Deductions: $18,200
Net Payroll: $382,200

Transaction committed successfully!
```

#### Sample Input 3: Generate Department Report
```
Enter choice: 4
```

#### Sample Output 3:
```
=== Department-wise Salary Statistics ===

Department: Engineering
  Employees: 2
  Total Salary: $155,000
  Average Salary: $77,500
  Min Salary: $75,000
  Max Salary: $80,000
  Budget Utilization: 64.5%

Department: Marketing
  Employees: 1
  Total Salary: $65,000
  Average Salary: $65,000
  Min Salary: $65,000
  Max Salary: $65,000
  Budget Utilization: 54.2%

Department: HR
  Employees: 1
  Total Salary: $60,000
  Average Salary: $60,000
  Min Salary: $60,000
  Max Salary: $60,000
  Budget Utilization: 50.0%

Department: Finance
  Employees: 1
  Total Salary: $70,000
  Average Salary: $70,000
  Min Salary: $70,000
  Max Salary: $70,000
  Budget Utilization: 58.3%
```

#### Sample Error Handling:
```
Enter choice: 1
Enter CSV file path: invalid_employees.csv

=== Importing Employees ===
Processing file: invalid_employees.csv
✓ Imported: EMP001 - John Doe
✗ Error on line 3: Invalid email format for employee EMP002
✓ Imported: EMP003 - Bob Johnson
✗ Error on line 5: Salary must be positive for employee EMP004
✗ Error on line 6: Duplicate employee code: EMP001

Import Summary:
Total records: 6
Successfully imported: 2
Failed: 4
Errors logged to: import_errors_2024-11-19.log
```

### Expected Deliverables
1. Java source code with proper DAO pattern implementation
2. SQL scripts for schema creation
3. Sample CSV files (valid and invalid data)
4. Properties file for configuration
5. README with setup and usage instructions
6. Error log examples

---

## Assignment 3: Banking Transaction System with Concurrent Operations (Hard)

### Problem Statement
Develop a multi-threaded Banking Transaction System that handles concurrent account operations while maintaining ACID properties. The system should support multiple simultaneous transactions, implement custom connection pooling, maintain transaction audit logs, and provide real-time balance reconciliation. This assignment tests your understanding of JDBC transactions, concurrency control, exception handling, and Java 8+ features.

### Functional Requirements

#### Core Features

1. **Database Schema**
   - **accounts** table: `account_id`, `account_number`, `account_holder_name`, `account_type` (SAVINGS/CURRENT), `balance`, `status` (ACTIVE/BLOCKED/CLOSED), `created_date`, `last_modified`, `version` (for optimistic locking)
   - **transactions** table: `transaction_id`, `transaction_ref`, `from_account_id`, `to_account_id`, `transaction_type` (DEPOSIT/WITHDRAWAL/TRANSFER), `amount`, `timestamp`, `status` (SUCCESS/FAILED/PENDING), `description`
   - **audit_logs** table: `log_id`, `account_id`, `action`, `old_balance`, `new_balance`, `user`, `timestamp`, `ip_address`
   - **transaction_limits** table: `account_type`, `daily_limit`, `per_transaction_limit`, `minimum_balance`

2. **Account Operations**
   - Create new account with initial deposit
   - Close account (only if balance is zero)
   - Block/Unblock account
   - View account details and transaction history
   - Implement optimistic locking using version field

3. **Transaction Operations** (Must be thread-safe)
   - **Deposit**: Add money to an account
   - **Withdrawal**: Deduct money from an account (check minimum balance)
   - **Transfer**: Move money between two accounts (atomic operation)
   - **Batch Transfer**: Process multiple transfers from CSV file
   - Each transaction should:
     - Validate account status and limits
     - Use database transactions (ISOLATION_SERIALIZABLE)
     - Update audit logs
     - Handle concurrent modifications
     - Generate unique transaction reference

4. **Concurrent Transaction Processing**
   - Support 10+ simultaneous transactions
   - Use thread pool (ExecutorService) with configurable size
   - Implement retry mechanism for failed transactions (max 3 attempts)
   - Handle deadlock scenarios gracefully
   - Use synchronization to prevent race conditions

5. **Connection Pool Implementation**
   - Custom connection pool with:
     - Configurable min/max connections (min: 10, max: 50)
     - Connection validation before use
     - Connection timeout (30 seconds)
     - Automatic connection recycling
     - Pool statistics (active, idle, waiting connections)
   - Thread-safe implementation using `BlockingQueue`

6. **Reporting with Java 8+ Features**
   - Transaction summary by date range (using Streams)
   - Daily balance snapshot
   - Identify suspicious transactions (large amounts, frequent transfers)
   - Generate account statements with filtering options
   - Export reports to CSV with parallel processing

7. **Audit and Compliance**
   - Log every balance change to audit_logs table
   - Store transaction history for 7 years
   - Track failed transaction attempts
   - Generate compliance reports
   - Write audit logs to file system (async I/O)

8. **Exception Handling**
   - Custom exceptions:
     - `InsufficientFundsException`
     - `AccountBlockedException`
     - `TransactionLimitExceededException`
     - `ConcurrentModificationException`
     - `ConnectionPoolExhaustedException`
   - Implement retry logic for transient failures
   - Rollback transactions on any failure
   - Dead letter queue for failed transactions

9. **Configuration Management**
   - Database credentials
   - Connection pool settings
   - Transaction limits
   - Thread pool configuration
   - Use `Optional` for nullable configuration values

#### Technical Requirements
- Use PreparedStatement with batch updates
- Implement proper transaction isolation levels
- Use Java 8 `CompletableFuture` for async operations (optional)
- Implement custom connection pooling (no third-party libraries)
- Use `java.util.concurrent` utilities
- File-based audit logs with rotation
- Implement builder pattern for complex objects
- Use Generics for reusable components
- Maven/Gradle for dependency management

### Sample Input/Output

#### Sample Input 1: Create Accounts
```
=== Banking Transaction System ===
1. Create Account
2. Deposit Money
3. Withdraw Money
4. Transfer Money
5. Batch Transfer from CSV
6. View Account Details
7. Transaction History
8. Generate Reports
9. System Statistics
10. Exit

Enter choice: 1
Enter account holder name: John Doe
Select account type (1-SAVINGS, 2-CURRENT): 1
Enter initial deposit: 10000
```

#### Sample Output 1:
```
=== Account Created Successfully ===
Account Number: ACC1730234567890
Account Holder: John Doe
Account Type: SAVINGS
Initial Balance: $10,000.00
Status: ACTIVE
Created On: 2024-11-19 14:30:45

Transaction Reference: TXN-DEP-1730234567890
```

#### Sample Input 2: Concurrent Transfers
```
Enter choice: 5
Enter CSV file path: batch_transfers.csv
```

#### Sample CSV File: `batch_transfers.csv`
```csv
from_account,to_account,amount,description
ACC1730234567890,ACC1730234567891,500,Payment for services
ACC1730234567890,ACC1730234567892,1000,Rent payment
ACC1730234567891,ACC1730234567890,200,Refund
ACC1730234567892,ACC1730234567891,750,Loan repayment
ACC1730234567890,ACC1730234567893,300,Utility bill
```

#### Sample Output 2:
```
=== Processing Batch Transfers ===
Started at: 2024-11-19 14:35:00
Thread Pool Size: 10
Total Transactions: 5

[Thread-1] Processing: ACC1730234567890 -> ACC1730234567891 ($500)
[Thread-2] Processing: ACC1730234567890 -> ACC1730234567892 ($1000)
[Thread-3] Processing: ACC1730234567891 -> ACC1730234567890 ($200)
[Thread-1] ✓ Success: TXN-TRF-1730234600001 | Time: 45ms
[Thread-4] Processing: ACC1730234567892 -> ACC1730234567891 ($750)
[Thread-2] ✓ Success: TXN-TRF-1730234600002 | Time: 52ms
[Thread-3] ✓ Success: TXN-TRF-1730234600003 | Time: 48ms
[Thread-5] Processing: ACC1730234567890 -> ACC1730234567893 ($300)
[Thread-4] ✓ Success: TXN-TRF-1730234600004 | Time: 50ms
[Thread-5] ✓ Success: TXN-TRF-1730234600005 | Time: 47ms

=== Batch Transfer Summary ===
Completed at: 2024-11-19 14:35:01
Total Duration: 1.2 seconds
Successful: 5
Failed: 0
Total Amount Transferred: $2,750.00

Final Balances:
ACC1730234567890: $8,200.00 (was $10,000.00)
ACC1730234567891: $1,450.00 (was $1,000.00)
ACC1730234567892: $2,250.00 (was $3,000.00)
ACC1730234567893: $3,300.00 (was $3,000.00)

Audit logs written to: audit_2024-11-19.log
```

#### Sample Input 3: Generate Report
```
Enter choice: 8
Select report type:
1. Transaction Summary
2. Account Statement
3. Suspicious Transactions
4. Daily Balance Snapshot

Enter choice: 1
Enter start date (yyyy-MM-dd): 2024-11-01
Enter end date (yyyy-MM-dd): 2024-11-19
Filter by account (leave empty for all): ACC1730234567890
```

#### Sample Output 3:
```
=== Transaction Summary Report ===
Period: 2024-11-01 to 2024-11-19
Account: ACC1730234567890 (John Doe - SAVINGS)

Transaction Breakdown:
  Total Transactions: 45
  Deposits: 12 | Total: $15,500.00
  Withdrawals: 18 | Total: $8,750.00
  Transfers Out: 10 | Total: $5,200.00
  Transfers In: 5 | Total: $2,100.00

Balance Summary:
  Opening Balance (Nov 1): $5,000.00
  Closing Balance (Nov 19): $8,650.00
  Net Change: +$3,650.00
  Average Daily Balance: $7,200.00

Largest Transactions:
  1. 2024-11-05 | Deposit | $2,500.00 | Salary credit
  2. 2024-11-10 | Transfer Out | $2,000.00 | Investment
  3. 2024-11-15 | Withdrawal | $1,500.00 | ATM withdrawal

Monthly Statistics (using Java 8 Streams):
  Average Transaction Amount: $295.50
  Median Transaction Amount: $200.00
  Transaction Frequency: 2.5 per day

Report generated in: 156ms
Export to CSV? (y/n): y
Report exported to: statement_ACC1730234567890_2024-11-19.csv
```

#### Sample Input 4: System Statistics
```
Enter choice: 9
```

#### Sample Output 4:
```
=== System Statistics ===

Connection Pool Status:
  Total Connections: 50
  Active Connections: 12
  Idle Connections: 38
  Waiting Requests: 0
  Connection Wait Time (avg): 15ms
  Pool Hit Rate: 98.5%

Thread Pool Status:
  Core Threads: 10
  Active Threads: 3
  Queue Size: 0
  Completed Tasks: 1,247
  Average Task Time: 42ms

Transaction Statistics (Last 24 hours):
  Total Transactions: 856
  Successful: 847 (98.9%)
  Failed: 9 (1.1%)
  Average Response Time: 38ms
  Throughput: 35.6 TPS

Database Statistics:
  Total Accounts: 324
  Active Accounts: 312
  Total Balance: $4,523,450.00
  Transactions (Today): 156

Failed Transactions (Last 1 hour):
  1. TXN-TRF-1730234700123 | Insufficient Funds | ACC1730234567894
  2. TXN-WTH-1730234700456 | Account Blocked | ACC1730234567895

Memory Usage:
  Heap: 456MB / 1024MB (44.5%)
  Non-Heap: 89MB / 256MB (34.7%)
  GC Count: 12
  GC Time: 234ms
```

#### Sample Error Handling - Concurrent Modification:
```
[Thread-3] Processing transfer: ACC1730234567890 -> ACC1730234567891 ($500)
[Thread-5] Processing transfer: ACC1730234567890 -> ACC1730234567892 ($1000)

[Thread-3] Error: Account balance modified by another transaction (Optimistic Lock Failure)
[Thread-3] Retry attempt 1/3...
[Thread-3] ✓ Success on retry: TXN-TRF-1730234600006 | Time: 95ms

[Thread-5] ✓ Success: TXN-TRF-1730234600007 | Time: 53ms
```

#### Sample Error Handling - Insufficient Funds:
```
[Thread-2] Processing withdrawal: ACC1730234567890 ($15,000)
[Thread-2] ✗ Failed: InsufficientFundsException
  Account Balance: $10,000.00
  Requested Amount: $15,000.00
  Available Balance: $10,000.00 (Min Balance: $500.00)
  
Transaction rolled back.
Failed transaction logged to: failed_transactions.log
```

#### Sample Audit Log Output: `audit_2024-11-19.log`
```
[2024-11-19 14:35:01.234] [INFO] Account: ACC1730234567890 | Action: TRANSFER_OUT | Old Balance: 10000.00 | New Balance: 9500.00 | Amount: 500.00 | User: SYSTEM | IP: 192.168.1.100 | TXN: TXN-TRF-1730234600001
[2024-11-19 14:35:01.287] [INFO] Account: ACC1730234567891 | Action: TRANSFER_IN | Old Balance: 1000.00 | New Balance: 1500.00 | Amount: 500.00 | User: SYSTEM | IP: 192.168.1.100 | TXN: TXN-TRF-1730234600001
[2024-11-19 14:35:01.340] [INFO] Account: ACC1730234567890 | Action: TRANSFER_OUT | Old Balance: 9500.00 | New Balance: 8500.00 | Amount: 1000.00 | User: SYSTEM | IP: 192.168.1.100 | TXN: TXN-TRF-1730234600002
```

### Expected Deliverables
1. Complete Java application with proper architecture:
   - DAO layer (AccountDAO, TransactionDAO, AuditDAO)
   - Service layer (AccountService, TransactionService)
   - Connection pool implementation
   - Thread pool manager
   - Custom exception hierarchy
2. SQL scripts for schema with indexes and constraints
3. Maven/Gradle build configuration
4. Sample CSV files for batch processing
5. Configuration files (db.properties, app.properties)
6. Comprehensive README with:
   - Architecture diagram
   - Setup instructions
   - Concurrency handling explanation
   - Performance considerations
7. Sample audit logs and reports

### Bonus Challenges
1. Implement a distributed transaction coordinator for multi-database transactions
2. Add support for scheduled batch jobs using `ScheduledExecutorService`
3. Implement database sharding for accounts table
4. Add monitoring dashboard using file-based metrics
5. Implement transaction replay mechanism from audit logs
6. Add support for transaction reversal
7. Implement rate limiting for API-like operations

---

## General Notes

### Setup Instructions for All Assignments
1. Install MySQL/PostgreSQL database
2. Create database: `CREATE DATABASE assignment_db;`
3. Run the provided SQL scripts
4. Configure database credentials in properties files
5. Compile and run the Java application

### Evaluation Criteria
- Code organization and structure
- Proper use of JDBC APIs
- Exception handling coverage
- Resource management (connection, statement, result set closing)
- Transaction management
- Use of Java 8+ features
- Thread safety (for assignment 3)
- Code documentation
- Performance considerations

### Learning Outcomes
After completing these assignments, you will understand:
- JDBC API and database connectivity
- PreparedStatement vs Statement
- Transaction management and ACID properties
- Connection pooling concepts
- Exception handling strategies
- Java Collections and Streams in real applications
- File I/O for configuration and logging
- Concurrent programming with databases
- Optimistic vs Pessimistic locking
- DAO design pattern

### Additional Resources
- JDBC API Documentation: https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html
- MySQL JDBC Driver: https://dev.mysql.com/downloads/connector/j/
- PostgreSQL JDBC Driver: https://jdbc.postgresql.org/

### Submission Guidelines
1. Create a GitHub repository for each assignment
2. Include all source code with proper package structure
3. Provide SQL scripts in a `/sql` directory
4. Add a comprehensive README.md
5. Include sample input/output files
6. Document any assumptions made
7. Add comments explaining complex logic

---

**Good luck with your assignments! Focus on clean code, proper exception handling, and resource management.**
