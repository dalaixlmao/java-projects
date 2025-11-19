# JUnit Testing Assignments

## Assignment 1 (Easy): Banking Calculator Test Suite

### Problem Statement
Create a comprehensive test suite for a `BankingCalculator` class that performs various financial calculations. You need to implement the calculator class and write extensive JUnit tests to ensure its correctness, covering normal cases, edge cases, and exception scenarios.

### Functional Requirements

1. **BankingCalculator Class** should implement the following methods:
   - `calculateSimpleInterest(double principal, double rate, int years)` - Returns simple interest
   - `calculateCompoundInterest(double principal, double rate, int years, int compoundingFrequency)` - Returns compound interest
   - `calculateEMI(double principal, double annualRate, int months)` - Returns monthly EMI
   - `convertCurrency(double amount, String fromCurrency, String toCurrency, Map<String, Double> exchangeRates)` - Converts currency using provided exchange rates
   - `calculateTax(double income, List<TaxSlab> taxSlabs)` - Calculates tax based on tax slabs

2. **TaxSlab Class** should have:
   - `double lowerLimit`
   - `double upperLimit`
   - `double taxRate`

3. **Test Requirements**:
   - Write at least 15 test cases covering:
     - Normal calculations
     - Edge cases (zero values, very large numbers)
     - Exception cases (negative values, null inputs, invalid parameters)
     - Boundary conditions
   - Use JUnit 5 annotations: `@Test`, `@BeforeEach`, `@AfterEach`, `@DisplayName`, `@Disabled`
   - Use assertions: `assertEquals`, `assertThrows`, `assertTrue`, `assertNotNull`
   - Organize tests using nested test classes with `@Nested`

### Sample Input/Output

#### Test Case 1: Simple Interest
```java
Input: 
principal = 10000.0
rate = 5.0
years = 2

Output:
simpleInterest = 1000.0
```

#### Test Case 2: Compound Interest
```java
Input:
principal = 10000.0
rate = 5.0
years = 2
compoundingFrequency = 4 (quarterly)

Output:
compoundInterest = 1047.05 (approximately)
```

#### Test Case 3: EMI Calculation
```java
Input:
principal = 500000.0
annualRate = 8.5
months = 60

Output:
emi = 10298.35 (approximately)
```

#### Test Case 4: Currency Conversion
```java
Input:
amount = 100.0
fromCurrency = "USD"
toCurrency = "INR"
exchangeRates = {"USD": 1.0, "INR": 83.0, "EUR": 0.92}

Output:
convertedAmount = 8300.0
```

#### Test Case 5: Tax Calculation
```java
Input:
income = 1000000.0
taxSlabs = [
    {0, 250000, 0.0},
    {250000, 500000, 0.05},
    {500000, 1000000, 0.20},
    {1000000, Double.MAX_VALUE, 0.30}
]

Output:
tax = 112500.0
```

### Exception Test Cases
```java
// Should throw IllegalArgumentException
calculateSimpleInterest(-1000, 5, 2)
calculateEMI(100000, -5, 12)
convertCurrency(100, null, "INR", exchangeRates)
```

### Deliverables
1. `BankingCalculator.java` - Implementation of the calculator
2. `TaxSlab.java` - TaxSlab model class
3. `BankingCalculatorTest.java` - Comprehensive test suite
4. `pom.xml` or `build.gradle` - Build configuration with JUnit 5 dependency

### Evaluation Criteria
- Code coverage > 90%
- All edge cases handled
- Proper use of JUnit annotations
- Clear and descriptive test names
- Proper exception testing

---

## Assignment 2 (Medium): Log File Analyzer with Testing

### Problem Statement
Build a `LogFileAnalyzer` system that processes server log files and extracts meaningful statistics. Implement comprehensive unit tests that verify file parsing, data aggregation, exception handling, and stream operations using JUnit 5 features including parameterized tests.

### Functional Requirements

1. **LogEntry Class**:
   - `LocalDateTime timestamp`
   - `String level` (INFO, WARN, ERROR, DEBUG)
   - `String message`
   - `String source`
   - `Optional<String> exceptionType`

2. **LogFileAnalyzer Class** should implement:
   - `List<LogEntry> parseLogFile(String filePath)` - Parses log file and returns entries
   - `Map<String, Long> getLogCountByLevel(List<LogEntry> logs)` - Groups logs by level
   - `List<LogEntry> filterLogsByDateRange(List<LogEntry> logs, LocalDateTime start, LocalDateTime end)` - Filters using streams
   - `Optional<LogEntry> findFirstError(List<LogEntry> logs)` - Returns first error log
   - `Map<String, List<LogEntry>> groupBySource(List<LogEntry> logs)` - Groups by source using Collectors
   - `double calculateErrorRate(List<LogEntry> logs)` - Returns percentage of error logs
   - `List<String> getTopNFrequentMessages(List<LogEntry> logs, int n)` - Returns most frequent messages
   - `void exportFilteredLogs(List<LogEntry> logs, String outputPath, Predicate<LogEntry> filter)` - Exports filtered logs

3. **LogParseException** (Custom Exception):
   - Should be thrown for malformed log lines
   - Should contain line number and reason

4. **Test Requirements**:
   - Write at least 20 test cases covering:
     - File parsing with valid/invalid formats
     - Empty files, missing files, permission issues
     - Stream operations and filtering
     - Optional handling
     - Custom exception scenarios
     - Parameterized tests for multiple inputs
   - Use `@ParameterizedTest` with `@CsvSource`, `@ValueSource`, `@MethodSource`
   - Use `@TempDir` for temporary file testing
   - Test with Java 8+ features (streams, lambdas, Optional)
   - Use `assertAll()` for grouped assertions

### Sample Input/Output

#### Sample Log File (server.log)
```
2024-01-15 10:23:45 INFO [UserService] User login successful - userId: 1001
2024-01-15 10:24:12 WARN [DatabaseService] Connection pool running low - available: 2
2024-01-15 10:24:50 ERROR [PaymentService] Payment processing failed - orderId: 5678 | Exception: PaymentGatewayException
2024-01-15 10:25:30 INFO [UserService] User logout - userId: 1001
2024-01-15 10:26:15 ERROR [OrderService] Order creation failed - userId: 2002 | Exception: ValidationException
2024-01-15 10:27:00 DEBUG [CacheService] Cache hit ratio: 0.95
2024-01-15 10:28:22 INFO [NotificationService] Email sent successfully - userId: 1001
```

#### Test Case 1: Parse Valid Log File
```java
Input: 
filePath = "server.log" (content above)

Output:
List<LogEntry> with 7 entries
First entry: 
  - timestamp = 2024-01-15T10:23:45
  - level = "INFO"
  - source = "UserService"
  - message = "User login successful - userId: 1001"
  - exceptionType = Optional.empty()
```

#### Test Case 2: Log Count By Level
```java
Input:
logs = parsed entries from above

Output:
{
  "INFO": 3,
  "WARN": 1,
  "ERROR": 2,
  "DEBUG": 1
}
```

#### Test Case 3: Filter By Date Range
```java
Input:
logs = parsed entries
start = 2024-01-15T10:24:00
end = 2024-01-15T10:26:00

Output:
List with 3 entries (WARN, ERROR from PaymentService, INFO logout)
```

#### Test Case 4: Calculate Error Rate
```java
Input:
logs = parsed entries (7 total, 2 errors)

Output:
errorRate = 28.57 (approximately)
```

#### Test Case 5: Top N Frequent Messages
```java
Input:
logs = multiple entries with repeated messages
n = 3

Output:
["User login successful", "Payment processing failed", "Cache hit ratio"]
```

### Parameterized Test Examples
```java
@ParameterizedTest
@CsvSource({
    "INFO, 3",
    "ERROR, 2",
    "WARN, 1",
    "DEBUG, 1"
})
void testLogCountForEachLevel(String level, long expectedCount)

@ParameterizedTest
@ValueSource(strings = {"", "invalid-file.log", "/root/restricted.log"})
void testInvalidFilePaths(String filePath)

@ParameterizedTest
@MethodSource("provideLogEntries")
void testFilteringVariousConditions(List<LogEntry> logs, Predicate<LogEntry> filter, int expectedCount)
```

### Exception Scenarios
```java
// Should throw LogParseException
parseLogFile("malformed.log") // with invalid date format
parseLogFile("corrupt.log")   // with missing required fields

// Should throw FileNotFoundException
parseLogFile("nonexistent.log")

// Should throw IllegalArgumentException
getTopNFrequentMessages(logs, -1)
filterLogsByDateRange(logs, null, LocalDateTime.now())
```

### Deliverables
1. `LogEntry.java` - Log entry model
2. `LogFileAnalyzer.java` - Main analyzer class
3. `LogParseException.java` - Custom exception
4. `LogFileAnalyzerTest.java` - Comprehensive test suite with parameterized tests
5. Sample log files for testing
6. `pom.xml` or `build.gradle` with JUnit 5 and AssertJ (optional)

### Evaluation Criteria
- Proper use of Java 8+ features (streams, lambdas, Optional)
- Comprehensive parameterized testing
- Exception handling and testing
- File I/O operations tested with `@TempDir`
- Code coverage > 85%
- Clean separation of concerns

---

## Assignment 3 (Hard): Concurrent Order Processing System with Database

### Problem Statement
Design and implement a `ConcurrentOrderProcessor` that handles multiple orders simultaneously, persists them to a database, and manages inventory in a thread-safe manner. Create a comprehensive test suite that validates thread safety, database operations, transactional integrity, and concurrent behavior using JUnit 5 advanced features.

### Functional Requirements

1. **Order Class** (Generic):
   ```java
   class Order<T extends Product> {
       String orderId;
       String customerId;
       List<T> products;
       OrderStatus status;
       LocalDateTime createdAt;
       double totalAmount;
   }
   ```

2. **Product Interface and Implementations**:
   - `Product` interface with `String getId()`, `String getName()`, `double getPrice()`, `int getQuantity()`
   - `PhysicalProduct` and `DigitalProduct` implementations

3. **OrderStatus Enum**: 
   - `PENDING`, `PROCESSING`, `COMPLETED`, `FAILED`, `CANCELLED`

4. **InventoryManager Class** (Thread-Safe):
   - `ConcurrentHashMap<String, Integer> inventory`
   - `boolean reserveStock(String productId, int quantity)` - Atomic operation
   - `void releaseStock(String productId, int quantity)` - Atomic operation
   - `int getAvailableStock(String productId)` - Thread-safe read
   - `void updateStock(String productId, int quantity)` - Thread-safe update
   - `Map<String, Integer> getInventorySnapshot()` - Returns immutable snapshot

5. **OrderRepository Interface**:
   ```java
   interface OrderRepository {
       void save(Order<?> order) throws SQLException;
       Optional<Order<?>> findById(String orderId) throws SQLException;
       List<Order<?>> findByCustomerId(String customerId) throws SQLException;
       void updateStatus(String orderId, OrderStatus status) throws SQLException;
       void delete(String orderId) throws SQLException;
   }
   ```

6. **ConcurrentOrderProcessor Class**:
   - `ExecutorService executorService` (configurable thread pool)
   - `InventoryManager inventoryManager`
   - `OrderRepository orderRepository`
   - `Future<Order<?>> submitOrder(Order<?> order)` - Processes order asynchronously
   - `List<Future<Order<?>>> submitBatchOrders(List<Order<?>> orders)` - Bulk processing
   - `void processOrder(Order<?> order)` - Business logic (reserve stock, save to DB, update status)
   - `CompletableFuture<OrderSummary> getOrderSummary(String customerId)` - Aggregates order data
   - `void shutdown()` - Graceful shutdown
   - `boolean awaitTermination(long timeout, TimeUnit unit)` - Wait for completion

7. **OrderSummary Class**:
   - `int totalOrders`
   - `Map<OrderStatus, Long> ordersByStatus`
   - `double totalRevenue`

8. **Test Requirements**:
   - Write at least 25 test cases covering:
     - Single-threaded order processing
     - Concurrent order processing (10+ threads)
     - Race conditions and thread safety
     - Database operations with in-memory H2 database
     - Transaction rollback scenarios
     - Deadlock prevention
     - Inventory consistency under load
     - Exception handling in multithreaded context
     - Timeout scenarios
     - Graceful shutdown
   - Use `@RepeatedTest` for stress testing
   - Use `CountDownLatch`, `CyclicBarrier` for coordinating threads in tests
   - Use in-memory H2 database for testing (JDBC)
   - Mock database failures
   - Test with generics for different product types
   - Use `assertTimeout()` and `assertTimeoutPreemptively()`

### Sample Input/Output

#### Test Case 1: Single Order Processing
```java
Input:
Order<PhysicalProduct> order = new Order<>(
    orderId: "ORD-001",
    customerId: "CUST-100",
    products: [
        PhysicalProduct("PROD-1", "Laptop", 50000, 1),
        PhysicalProduct("PROD-2", "Mouse", 500, 2)
    ]
)
Initial Inventory: {"PROD-1": 10, "PROD-2": 50}

Output:
Order processed successfully
order.status = COMPLETED
order.totalAmount = 51000.0
Updated Inventory: {"PROD-1": 9, "PROD-2": 48}
Database: Order saved with status COMPLETED
```

#### Test Case 2: Concurrent Order Processing (10 threads)
```java
Input:
10 orders submitted simultaneously, each ordering:
- PROD-1 (quantity: 2)
Initial Inventory: {"PROD-1": 15}

Output:
7 orders: status = COMPLETED (used 14 units)
3 orders: status = FAILED (insufficient stock)
Final Inventory: {"PROD-1": 1}
All database records consistent
No race conditions
```

#### Test Case 3: Race Condition Test
```java
Input:
100 threads simultaneously trying to order the same product
Product: PROD-1 (available: 10)
Each order requests: 1 unit

Output:
Exactly 10 orders succeed (status = COMPLETED)
Exactly 90 orders fail (status = FAILED)
Final Inventory: {"PROD-1": 0}
No overselling
No lost updates
```

#### Test Case 4: Database Transaction Rollback
```java
Input:
Order with 3 products
Product 1 & 2: Available in stock
Product 3: Out of stock
Simulate database connection failure after reserving stock for products 1 & 2

Output:
Order status: FAILED
All stock reservations rolled back
Inventory restored to original state
Database: No partial order saved
Exception: SQLException caught and handled
```

#### Test Case 5: Batch Order Summary
```java
Input:
Customer "CUST-100" has placed 15 orders
Status distribution: 10 COMPLETED, 3 FAILED, 2 PENDING
Total revenue from completed: 150000.0

Output:
OrderSummary:
  totalOrders = 15
  ordersByStatus = {COMPLETED: 10, FAILED: 3, PENDING: 2}
  totalRevenue = 150000.0
```

### Concurrency Test Scenarios

#### Test 1: Inventory Consistency Under High Load
```java
@RepeatedTest(10)
void testInventoryConsistencyUnderLoad() {
    // 100 threads, each processing 10 orders
    // Verify final inventory = initial - (all successful orders)
}
```

#### Test 2: No Deadlock Scenario
```java
@Test
void testNoDeadlockWithMultipleResources() {
    // Order 1: Needs PROD-A then PROD-B
    // Order 2: Needs PROD-B then PROD-A
    // Should not deadlock
}
```

#### Test 3: Graceful Shutdown
```java
@Test
void testGracefulShutdown() {
    // Submit 50 orders
    // Shutdown immediately
    // All in-flight orders should complete
    // New orders should be rejected
}
```

### Database Test Setup
```java
@BeforeEach
void setupDatabase() {
    // Initialize H2 in-memory database
    // Create tables: ORDERS, PRODUCTS, INVENTORY
    // Insert test data
}

@AfterEach
void cleanupDatabase() {
    // Drop tables
    // Close connections
}
```

### Exception Scenarios
```java
// Should handle gracefully
- SQLException during order save
- InterruptedException during processing
- RejectedExecutionException when executor is shutdown
- TimeoutException for slow operations
- ConcurrentModificationException (should not occur with proper synchronization)
- IllegalStateException for invalid state transitions
```

### Deliverables
1. `Order.java` - Generic order class
2. `Product.java`, `PhysicalProduct.java`, `DigitalProduct.java` - Product models
3. `OrderStatus.java` - Enum
4. `InventoryManager.java` - Thread-safe inventory management
5. `OrderRepository.java` - Repository interface
6. `InMemoryOrderRepository.java` - H2 database implementation
7. `ConcurrentOrderProcessor.java` - Main processor
8. `OrderSummary.java` - Summary model
9. `ConcurrentOrderProcessorTest.java` - Comprehensive test suite
10. `schema.sql` - Database schema
11. `pom.xml` or `build.gradle` - Dependencies (JUnit 5, H2 Database, HikariCP for connection pooling)

### Evaluation Criteria
- Thread safety verified through concurrent tests
- No race conditions or deadlocks
- Proper use of concurrent collections and atomic operations
- Database operations tested with in-memory H2
- Proper transaction management
- Comprehensive exception handling
- Code coverage > 80%
- Use of generics demonstrated
- Proper resource cleanup (database connections, thread pools)
- Performance: Should handle 1000 concurrent orders within 5 seconds

### Advanced Testing Techniques to Demonstrate
1. **Concurrency Testing**:
   - Use `CountDownLatch` to start all threads simultaneously
   - Use `CyclicBarrier` for coordinated testing
   - Verify atomicity with `AtomicInteger` counters

2. **Database Testing**:
   - Test with H2 in-memory database
   - Verify JDBC operations
   - Test transaction rollback
   - Test connection pool exhaustion

3. **Stress Testing**:
   - Use `@RepeatedTest` for flaky test detection
   - Test with varying thread pool sizes
   - Test with high order volumes

4. **Timeout Testing**:
   - Use `assertTimeout()` for performance validation
   - Test slow database operations
   - Test executor shutdown timing

### Bonus Challenges
1. Implement and test priority-based order processing
2. Add support for order cancellation with compensation
3. Implement and test distributed locking mechanism
4. Add metrics collection and test reporting (order/second, success rate)
5. Implement retry logic with exponential backoff and test it

---

## General Guidelines for All Assignments

### Project Setup
All assignments should include proper Maven/Gradle configuration:

**Maven (pom.xml)**:
```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.1</version>
        <scope>test</scope>
    </dependency>
    <!-- For Assignment 3 -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>2.2.224</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Testing Best Practices
1. **Test Naming**: Use descriptive names - `testMethodName_Scenario_ExpectedBehavior()`
2. **AAA Pattern**: Arrange, Act, Assert
3. **One Assertion Per Test**: When possible, test one thing
4. **Test Independence**: Tests should not depend on each other
5. **Clean Code**: Tests should be as clean as production code

### Code Coverage Requirements
- Assignment 1: > 90%
- Assignment 2: > 85%
- Assignment 3: > 80%

### Submission Checklist
- [ ] All source files included
- [ ] All test files included
- [ ] Build file (pom.xml/build.gradle) included
- [ ] README.md with instructions
- [ ] All tests pass
- [ ] Code coverage report generated
- [ ] No compilation warnings
- [ ] Proper package structure

---

**Good luck with your assignments! Remember: Good tests are the foundation of maintainable code.**
