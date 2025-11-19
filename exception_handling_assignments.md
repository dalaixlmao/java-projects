# Exception Handling - Practice Assignments

## Assignment 1: Safe Calculator (Easy)

### Problem Statement
Design a simple calculator application that performs basic arithmetic operations (addition, subtraction, multiplication, division, and modulo). The calculator should handle various exceptions that may occur during operations and provide meaningful error messages to users.

### Functional Requirements

1. **Calculator Class**
   - Should have methods: `add()`, `subtract()`, `multiply()`, `divide()`, `modulo()`
   - Each method should take two parameters and return the result
   - Must handle arithmetic exceptions appropriately

2. **Exception Handling**
   - Handle `ArithmeticException` for division by zero and modulo by zero
   - Handle `NumberFormatException` when parsing invalid number strings
   - Use try-catch blocks appropriately
   - Display user-friendly error messages

3. **Operation Processing**
   - Accept operations in the format: `number1 operator number2`
   - Supported operators: `+`, `-`, `*`, `/`, `%`
   - Process multiple operations until user enters "exit"

4. **Error Messages**
   - For division/modulo by zero: "ERROR: Cannot divide by zero"
   - For invalid numbers: "ERROR: Invalid number format"
   - For invalid operators: "ERROR: Invalid operator"

### Sample Input
```
10 + 5
20 / 4
15 / 0
10 * abc
25 $ 5
100 % 3
exit
```

### Sample Output
```
Result: 15.0
Result: 5.0
ERROR: Cannot divide by zero
ERROR: Invalid number format
ERROR: Invalid operator
Result: 1.0
Calculator closed.
```

### Evaluation Criteria
- Proper use of try-catch blocks
- Appropriate exception handling for different error scenarios
- Clean and readable code
- User-friendly error messages

---

## Assignment 2: Student Grade Management System (Medium)

### Problem Statement
Build a Student Grade Management System that maintains student records and their grades across multiple subjects. The system should implement custom exceptions to handle various error scenarios like duplicate student IDs, invalid grades, student not found, etc.

### Functional Requirements

1. **Student Class**
   - Properties: studentId (String), name (String), grades (HashMap storing subject-grade pairs)
   - Constructor should validate student ID is not empty

2. **Custom Exceptions (Create these classes)**
   - `DuplicateStudentException`: When trying to add a student with existing ID
   - `StudentNotFoundException`: When trying to access/update non-existent student
   - `InvalidGradeException`: When grade is not between 0-100
   - `InvalidSubjectException`: When subject name is empty or null

3. **GradeManager Class**
   - `addStudent(String id, String name)`: Add a new student
   - `addGrade(String studentId, String subject, int grade)`: Add/update grade for a subject
   - `getStudentAverage(String studentId)`: Calculate and return average grade
   - `getStudentGrades(String studentId)`: Return all grades for a student
   - `removeStudent(String studentId)`: Remove a student from system
   - Store students in a HashMap with studentId as key

4. **Exception Handling Rules**
   - Throw `DuplicateStudentException` if student ID already exists
   - Throw `StudentNotFoundException` if student ID doesn't exist
   - Throw `InvalidGradeException` if grade < 0 or grade > 100
   - Throw `InvalidSubjectException` if subject is null or empty
   - All custom exceptions should have meaningful messages

5. **Main Class Operations**
   - ADD_STUDENT: id, name
   - ADD_GRADE: studentId, subject, grade
   - GET_AVERAGE: studentId
   - GET_GRADES: studentId
   - REMOVE_STUDENT: studentId
   - Process commands until "EXIT"

### Sample Input
```
ADD_STUDENT S001 John
ADD_GRADE S001 Math 85
ADD_GRADE S001 Physics 90
ADD_GRADE S001 Chemistry 78
GET_AVERAGE S001
ADD_STUDENT S001 Jane
ADD_GRADE S002 Math 95
ADD_GRADE S001 Math 105
ADD_GRADE S001 "" 88
GET_GRADES S001
REMOVE_STUDENT S002
EXIT
```

### Sample Output
```
Student added successfully: S001 - John
Grade added: S001 - Math: 85
Grade added: S001 - Physics: 90
Grade added: S001 - Chemistry: 78
Average grade for S001: 84.33
ERROR: DuplicateStudentException - Student with ID S001 already exists
ERROR: StudentNotFoundException - Student with ID S002 not found
ERROR: InvalidGradeException - Grade must be between 0 and 100: 105
ERROR: InvalidSubjectException - Subject name cannot be empty
Grades for S001:
  Math: 85
  Physics: 90
  Chemistry: 78
ERROR: StudentNotFoundException - Student with ID S002 not found
System closed.
```

### Evaluation Criteria
- Proper implementation of custom exceptions with inheritance from Exception class
- Exception chaining where appropriate
- Correct use of throw and throws keywords
- Proper exception propagation
- Clean separation of concerns
- Appropriate use of Collections Framework (HashMap)

---

## Assignment 3: Bank Transaction Processing System (Hard)

### Problem Statement
Design a comprehensive Bank Transaction Processing System that handles multiple account operations with robust exception handling, transaction validation, and rollback mechanisms. The system should maintain account balances, process various types of transactions, and handle complex error scenarios including cascading failures.

### Functional Requirements

1. **Account Class**
   - Properties: accountNumber (String), accountHolderName (String), balance (double), transactionHistory (ArrayList)
   - Minimum balance requirement: 1000.0
   - Maximum transaction limit per operation: 50000.0

2. **Transaction Class**
   - Properties: transactionId (String), type (String), amount (double), timestamp (String), status (String)
   - Types: DEPOSIT, WITHDRAWAL, TRANSFER
   - Status: SUCCESS, FAILED, ROLLED_BACK

3. **Custom Exceptions (Create comprehensive exception hierarchy)**
   - `BankException` (Base exception class)
     - `AccountNotFoundException` extends BankException
     - `InsufficientFundsException` extends BankException
     - `InvalidAmountException` extends BankException
     - `TransactionLimitExceededException` extends BankException
     - `MinimumBalanceException` extends BankException
     - `InvalidAccountException` extends BankException
   - Each exception should store additional context (account number, attempted amount, etc.)
   - Implement proper exception chaining with cause

4. **BankingSystem Class - Core Operations**
   - `createAccount(String accNum, String name, double initialDeposit)`: Create new account
   - `deposit(String accNum, double amount)`: Deposit money
   - `withdraw(String accNum, double amount)`: Withdraw money
   - `transfer(String fromAcc, String toAcc, double amount)`: Transfer between accounts
   - `getBalance(String accNum)`: Get current balance
   - `getTransactionHistory(String accNum)`: Get all transactions
   - Store accounts in HashMap with account number as key

5. **Validation Rules**
   - Initial deposit must be >= 1000.0
   - Withdrawal/Transfer amount must be > 0
   - Cannot withdraw if balance - amount < minimum balance (1000.0)
   - Single transaction cannot exceed 50000.0
   - Account number must be exactly 10 digits
   - Account holder name cannot be empty

6. **Transfer Operation Specifics**
   - Must be atomic: either both debit and credit succeed, or both fail
   - If transfer fails midway, rollback the debit operation
   - Add transactions to both accounts' history
   - Handle partial failure scenarios with proper rollback

7. **Exception Handling Strategy**
   - Use try-catch-finally blocks for resource cleanup
   - Implement transaction rollback in catch blocks for transfers
   - Log all failed transactions with complete error details
   - Maintain system integrity even during cascading failures
   - Provide detailed error messages with account context

8. **Command Format**
   - CREATE_ACCOUNT: accountNumber, holderName, initialAmount
   - DEPOSIT: accountNumber, amount
   - WITHDRAW: accountNumber, amount
   - TRANSFER: fromAccount, toAccount, amount
   - GET_BALANCE: accountNumber
   - GET_HISTORY: accountNumber
   - EXIT

### Sample Input
```
CREATE_ACCOUNT 1234567890 Alice 5000
CREATE_ACCOUNT 9876543210 Bob 3000
DEPOSIT 1234567890 2000
WITHDRAW 1234567890 500
GET_BALANCE 1234567890
TRANSFER 1234567890 9876543210 3000
GET_BALANCE 1234567890
GET_BALANCE 9876543210
WITHDRAW 9876543210 6000
CREATE_ACCOUNT 1234567890 Charlie 2000
WITHDRAW 1234567890 5000
TRANSFER 9876543210 1234567890 55000
CREATE_ACCOUNT 12345 David 1500
DEPOSIT 9999999999 1000
TRANSFER 1234567890 5555555555 2000
GET_HISTORY 1234567890
EXIT
```

### Sample Output
```
Account created successfully: 1234567890 - Alice (Balance: 5000.0)
Account created successfully: 9876543210 - Bob (Balance: 3000.0)
Transaction successful: Deposited 2000.0 to account 1234567890
Transaction successful: Withdrawn 500.0 from account 1234567890
Current balance for 1234567890: 6500.0
Transaction successful: Transferred 3000.0 from 1234567890 to 9876543210
Current balance for 1234567890: 3500.0
Current balance for 9876543210: 6000.0
ERROR: InsufficientFundsException - Cannot withdraw 6000.0 from account 9876543210. Available balance after maintaining minimum: 5000.0
ERROR: AccountAlreadyExistsException - Account 1234567890 already exists
ERROR: MinimumBalanceException - Cannot withdraw 5000.0 from account 1234567890. Transaction would breach minimum balance requirement of 1000.0
ERROR: TransactionLimitExceededException - Transaction amount 55000.0 exceeds maximum limit of 50000.0 for account 9876543210
ERROR: InvalidAccountException - Account number must be exactly 10 digits: 12345
ERROR: AccountNotFoundException - Account 9999999999 not found
ERROR: Transfer failed - AccountNotFoundException: Account 5555555555 not found. Transaction rolled back for account 1234567890
Transaction history for 1234567890:
  [TX001] DEPOSIT: 2000.0 - SUCCESS - 2024-01-15 10:30:00
  [TX002] WITHDRAWAL: 500.0 - SUCCESS - 2024-01-15 10:31:00
  [TX003] TRANSFER_OUT: 3000.0 - SUCCESS - 2024-01-15 10:32:00
  [TX004] TRANSFER_OUT: 2000.0 - ROLLED_BACK - 2024-01-15 10:35:00
System shutdown complete.
```

### Evaluation Criteria
- **Exception Hierarchy** (25%): Well-designed custom exception hierarchy with proper inheritance
- **Exception Handling** (25%): Comprehensive try-catch-finally usage, proper exception chaining, and meaningful error messages
- **Transaction Integrity** (20%): Proper implementation of rollback mechanism for failed transfers
- **Validation Logic** (15%): Complete validation of all inputs with appropriate exceptions
- **Code Organization** (10%): Clean code structure with proper separation of concerns
- **Edge Case Handling** (5%): Handling of corner cases and cascading failures

### Additional Challenges (Optional)
1. Implement a transaction log file that records all operations (requires exception handling for file operations)
2. Add support for multiple currency types with conversion rate validation
3. Implement daily transaction limits per account with appropriate exceptions
4. Add account freezing capability with FrozenAccountException

---

## General Guidelines for All Assignments

### Code Quality Expectations
- Use meaningful variable and method names
- Add appropriate comments for complex logic
- Follow Java naming conventions
- Proper indentation and code formatting

### Exception Handling Best Practices to Demonstrate
- Don't catch exceptions you can't handle
- Use specific exceptions rather than generic Exception class
- Include context information in exception messages
- Clean up resources in finally blocks
- Don't swallow exceptions silently
- Use exception chaining to preserve stack traces

### Testing Considerations
- Test all happy path scenarios
- Test all exception scenarios
- Test boundary conditions
- Test invalid inputs
- Test cascading failures (for Assignment 3)

### Submission Requirements
- Well-commented source code
- README with setup and running instructions
- Test cases covering all scenarios
- Brief explanation of your exception handling strategy

---

## Learning Outcomes

After completing these assignments, you should be able to:
1. Understand and implement try-catch-finally blocks effectively
2. Create custom exception classes with proper inheritance
3. Use throw and throws keywords appropriately
4. Implement exception chaining and preserve stack traces
5. Handle multiple exception types in a single catch block
6. Design exception hierarchies for domain-specific errors
7. Implement rollback mechanisms for transaction failures
8. Write robust code that maintains system integrity during failures
9. Use Collections Framework (ArrayList, HashMap) in conjunction with exception handling
10. Understand the importance of proper error messaging and logging
