# Multithreading and Concurrency Assignments

## Assignment 1: Thread-Safe Rate Limiter (Easy)

### Problem Statement
Design and implement a thread-safe rate limiter that restricts the number of requests that can be processed within a given time window. The rate limiter should work correctly when accessed by multiple threads concurrently.

### Description
You need to build a `RateLimiter` class that allows a maximum of N requests per time window (in seconds). If the limit is exceeded, the request should be rejected. The rate limiter should handle concurrent access from multiple threads safely.

### Functional Requirements

1. **RateLimiter Class**
   - Constructor: `RateLimiter(int maxRequests, int timeWindowInSeconds)`
   - Method: `boolean allowRequest(String userId)` - Returns true if request is allowed, false otherwise
   - Method: `int getRequestCount(String userId)` - Returns current request count for a user
   - Method: `void reset()` - Resets all rate limiting data

2. **Thread Safety**
   - The rate limiter must be thread-safe
   - Multiple threads should be able to call `allowRequest()` simultaneously
   - No race conditions should occur

3. **Time Window Management**
   - Requests older than the time window should be automatically discarded
   - Use a sliding window approach (not fixed window)

4. **Demonstration**
   - Create a demo with at least 3 threads making concurrent requests
   - Show that the rate limiter correctly rejects requests exceeding the limit
   - Print logs showing which requests were allowed/rejected

### Constraints
- 1 <= maxRequests <= 1000
- 1 <= timeWindowInSeconds <= 3600
- userId is a non-null string
- Handle edge cases (null checks, negative values, etc.)

### Sample Input
```java
RateLimiter rateLimiter = new RateLimiter(5, 10); // 5 requests per 10 seconds

// Thread 1: User "user1" makes 3 requests immediately
// Thread 2: User "user1" makes 3 requests immediately
// Thread 3: User "user2" makes 2 requests immediately
// After 11 seconds: User "user1" makes 2 more requests
```

### Sample Output
```
[Thread-1] Request 1 for user1: ALLOWED (Count: 1/5)
[Thread-2] Request 1 for user1: ALLOWED (Count: 2/5)
[Thread-1] Request 2 for user1: ALLOWED (Count: 3/5)
[Thread-3] Request 1 for user2: ALLOWED (Count: 1/5)
[Thread-2] Request 2 for user1: ALLOWED (Count: 4/5)
[Thread-1] Request 3 for user1: ALLOWED (Count: 5/5)
[Thread-3] Request 2 for user2: ALLOWED (Count: 2/5)
[Thread-2] Request 3 for user1: REJECTED (Count: 5/5)
... (after 11 seconds)
[Thread-1] Request 4 for user1: ALLOWED (Count: 2/5)
[Thread-1] Request 5 for user1: ALLOWED (Count: 3/5)
```

### Expected Classes/Interfaces
```java
public class RateLimiter {
    // Your implementation
}

public class RateLimiterDemo {
    public static void main(String[] args) {
        // Demo with multiple threads
    }
}
```

### Evaluation Criteria
- Correct thread-safe implementation
- Proper use of synchronization mechanisms
- Accurate time window management
- Clean code with proper exception handling
- Comprehensive demo showing concurrent behavior

---

## Assignment 2: Parallel File Word Counter (Medium)

### Problem Statement
Build a multi-threaded application that processes multiple text files concurrently to count word frequencies across all files. The application should efficiently utilize multiple threads and aggregate results from all files.

### Description
Create a word frequency counter that reads multiple text files in parallel, counts the occurrence of each word, and produces a final aggregated report. The system should handle large files efficiently and demonstrate proper thread coordination.

### Functional Requirements

1. **FileWordCounter Class**
   - Method: `Map<String, Integer> processFiles(List<String> filePaths, int threadPoolSize)`
   - Processes all files using a thread pool
   - Returns aggregated word count across all files
   - Words are case-insensitive
   - Only count alphabetic words (ignore numbers, special characters)

2. **WordCountTask Class**
   - Implements `Callable<Map<String, Integer>>`
   - Processes a single file and returns word count map
   - Handles IOException appropriately

3. **Thread Pool Management**
   - Use ExecutorService with configurable thread pool size
   - Properly shutdown thread pool after processing
   - Handle timeouts (max 60 seconds for all files)

4. **Result Aggregation**
   - Merge results from all threads into a single map
   - Ensure thread-safe aggregation
   - Sort final results by frequency (descending)

5. **Statistics Display**
   - Total files processed
   - Total unique words
   - Top 10 most frequent words
   - Processing time for each file
   - Total processing time

6. **Error Handling**
   - Handle missing files gracefully
   - Handle file read errors
   - Continue processing other files if one fails
   - Report errors at the end

### Constraints
- File size can be up to 10 MB
- Number of files: 1 to 100
- Thread pool size: 1 to 20
- Handle UTF-8 encoding
- Memory efficient processing (don't load entire file at once for large files)

### Sample Input

**File 1 (file1.txt):**
```
Hello world! This is a test.
Hello Java programming.
```

**File 2 (file2.txt):**
```
Java is great for multithreading.
Hello world of concurrent programming.
```

**File 3 (file3.txt):**
```
Programming is fun and challenging.
Test your Java skills.
```

**Main Method:**
```java
List<String> files = Arrays.asList("file1.txt", "file2.txt", "file3.txt");
FileWordCounter counter = new FileWordCounter();
Map<String, Integer> result = counter.processFiles(files, 3);
```

### Sample Output
```
========================================
FILE PROCESSING STARTED
========================================
Thread Pool Size: 3
Total Files: 3

[Thread-pool-1] Processing file1.txt...
[Thread-pool-2] Processing file2.txt...
[Thread-pool-3] Processing file3.txt...

[Thread-pool-1] file1.txt completed in 45ms (8 words)
[Thread-pool-3] file3.txt completed in 52ms (9 words)
[Thread-pool-2] file2.txt completed in 58ms (9 words)

========================================
PROCESSING COMPLETE
========================================
Total Processing Time: 62ms
Total Files Processed: 3
Total Unique Words: 16

========================================
TOP 10 MOST FREQUENT WORDS
========================================
1. hello          : 3
2. java           : 3
3. programming    : 3
4. is             : 3
5. world          : 2
6. test           : 2
7. your           : 1
8. this           : 1
9. a              : 1
10. for           : 1

========================================
ALL WORD FREQUENCIES (Alphabetically)
========================================
a                 : 1
and               : 1
challenging       : 1
concurrent        : 1
for               : 1
fun               : 1
great             : 1
hello             : 3
is                : 3
java              : 3
multithreading    : 1
of                : 1
programming       : 3
skills            : 1
test              : 2
this              : 1
world             : 2
your              : 1
```

### Expected Classes/Interfaces
```java
public class FileWordCounter {
    public Map<String, Integer> processFiles(List<String> filePaths, int threadPoolSize);
}

public class WordCountTask implements Callable<Map<String, Integer>> {
    // Your implementation
}

public class WordCountResult {
    private Map<String, Integer> wordFrequency;
    private long processingTimeMs;
    private String fileName;
    // getters, setters
}

public class FileWordCounterDemo {
    public static void main(String[] args) {
        // Demo
    }
}
```

### Evaluation Criteria
- Correct concurrent processing using ExecutorService
- Thread-safe result aggregation
- Proper exception handling in multi-threaded environment
- Efficient file processing (streaming for large files)
- Clean separation of concerns
- Informative logging and statistics
- Proper resource cleanup

---

## Assignment 3: Advanced Task Scheduler with Priorities (Hard)

### Problem Statement
Design and implement a sophisticated multi-threaded task scheduler that can execute tasks with different priorities, handle task dependencies, support task cancellation, and provide real-time monitoring. The scheduler should efficiently manage a thread pool and ensure tasks are executed according to their priority and dependencies.

### Description
Build a production-grade task scheduler that manages concurrent task execution with the following features:
- Priority-based task scheduling
- Task dependency management (task B runs only after task A completes)
- Task cancellation and timeout support
- Real-time status monitoring
- Configurable thread pool with multiple strategies
- Graceful shutdown mechanism
- Comprehensive error handling and retry logic

### Functional Requirements

1. **Task Class**
   - Properties: taskId, name, priority (1-10, 10 being highest), estimatedDuration
   - Method: `execute()` - The actual work to be done
   - Support for dependencies on other tasks
   - Cancellable execution
   - Return status: SUCCESS, FAILED, CANCELLED, TIMEOUT

2. **TaskScheduler Class**
   - Constructor: `TaskScheduler(int coreThreads, int maxThreads, SchedulingStrategy strategy)`
   - Method: `String submitTask(Task task)` - Returns taskId
   - Method: `String submitTask(Task task, Set<String> dependsOn)` - Submit with dependencies
   - Method: `boolean cancelTask(String taskId)` - Cancel a pending/running task
   - Method: `TaskStatus getTaskStatus(String taskId)` - Get current status
   - Method: `List<TaskStatus> getAllTaskStatuses()` - Get all task statuses
   - Method: `void shutdown()` - Graceful shutdown
   - Method: `boolean awaitTermination(long timeout, TimeUnit unit)` - Wait for completion

3. **Scheduling Strategies**
   - PRIORITY_BASED: Higher priority tasks execute first
   - FIFO: First-in-first-out
   - SHORTEST_JOB_FIRST: Tasks with shortest estimated duration execute first

4. **Priority Queue Management**
   - Maintain a thread-safe priority queue for pending tasks
   - Automatically promote waiting tasks if dependencies are met
   - Handle dynamic priority adjustments

5. **Dependency Resolution**
   - Tasks wait until all dependent tasks complete successfully
   - If a dependent task fails, mark dependent tasks as FAILED
   - Detect circular dependencies and reject such tasks

6. **Monitoring and Statistics**
   - Real-time dashboard showing:
     - Number of pending, running, completed, failed, cancelled tasks
     - Average execution time
     - Thread pool utilization
     - Success rate
   - Event listeners for task state changes

7. **Error Handling and Retry**
   - Retry failed tasks (configurable retry count)
   - Exponential backoff between retries
   - Dead letter queue for permanently failed tasks
   - Timeout mechanism for long-running tasks

8. **Thread Pool Management**
   - Core threads: minimum threads always alive
   - Max threads: maximum threads that can be created
   - Thread reuse and lifecycle management
   - Proper cleanup on shutdown

### Constraints
- 1 <= coreThreads <= 50
- coreThreads <= maxThreads <= 100
- 1 <= priority <= 10
- Maximum 1000 tasks can be submitted
- Task execution time: 10ms to 30 seconds
- Dependency depth <= 5 levels
- Handle system resource constraints gracefully

### Sample Input
```java
TaskScheduler scheduler = new TaskScheduler(5, 10, SchedulingStrategy.PRIORITY_BASED);

// Task 1: Data Loading (High Priority)
Task loadData = new Task("load-data", "Load Database", 9, 2000) {
    @Override
    public TaskResult execute() {
        // Simulate data loading
        Thread.sleep(2000);
        return TaskResult.success("Data loaded: 1000 records");
    }
};

// Task 2: Data Processing (Depends on Task 1)
Task processData = new Task("process-data", "Process Records", 8, 3000) {
    @Override
    public TaskResult execute() {
        // Simulate processing
        Thread.sleep(3000);
        return TaskResult.success("Processed 1000 records");
    }
};

// Task 3: Generate Report (Depends on Task 2)
Task generateReport = new Task("generate-report", "Generate Report", 7, 1500) {
    @Override
    public TaskResult execute() {
        Thread.sleep(1500);
        return TaskResult.success("Report generated");
    }
};

// Task 4: Send Notifications (Low Priority, Independent)
Task sendNotifications = new Task("send-notif", "Send Notifications", 3, 1000) {
    @Override
    public TaskResult execute() {
        Thread.sleep(1000);
        return TaskResult.success("Notifications sent");
    }
};

// Task 5: Cleanup (Low Priority, Independent)
Task cleanup = new Task("cleanup", "Cleanup Temp Files", 2, 500) {
    @Override
    public TaskResult execute() {
        Thread.sleep(500);
        return TaskResult.success("Cleanup complete");
    }
};

// Submit tasks
String task1Id = scheduler.submitTask(loadData);
String task2Id = scheduler.submitTask(processData, Set.of(task1Id));
String task3Id = scheduler.submitTask(generateReport, Set.of(task2Id));
String task4Id = scheduler.submitTask(sendNotifications);
String task5Id = scheduler.submitTask(cleanup);

// Monitor and cancel
Thread.sleep(1000);
scheduler.cancelTask(task5Id);

// Wait for completion
scheduler.shutdown();
scheduler.awaitTermination(30, TimeUnit.SECONDS);
```

### Sample Output
```
========================================
TASK SCHEDULER INITIALIZED
========================================
Strategy: PRIORITY_BASED
Core Threads: 5
Max Threads: 10
========================================

[00:00.000] Task submitted: load-data (Priority: 9, Status: PENDING)
[00:00.005] Task submitted: process-data (Priority: 8, Status: WAITING_DEPENDENCY)
[00:00.008] Task submitted: generate-report (Priority: 7, Status: WAITING_DEPENDENCY)
[00:00.010] Task submitted: send-notif (Priority: 3, Status: PENDING)
[00:00.012] Task submitted: cleanup (Priority: 2, Status: PENDING)

========================================
EXECUTION STARTED
========================================

[00:00.015] [Worker-1] Started: load-data (Priority: 9)
[00:00.015] [Worker-2] Started: send-notif (Priority: 3)
[00:00.015] [Worker-3] Started: cleanup (Priority: 2)

[00:01.020] Task cleanup cancelled by user
[00:01.020] [Worker-3] Cancelled: cleanup

[00:01.015] [Worker-2] Completed: send-notif (Duration: 1000ms, Status: SUCCESS)

[00:02.015] [Worker-1] Completed: load-data (Duration: 2000ms, Status: SUCCESS)
[00:02.016] Dependency resolved for: process-data
[00:02.016] [Worker-1] Started: process-data (Priority: 8)

[00:05.016] [Worker-1] Completed: process-data (Duration: 3000ms, Status: SUCCESS)
[00:05.017] Dependency resolved for: generate-report
[00:05.017] [Worker-2] Started: generate-report (Priority: 7)

[00:06.517] [Worker-2] Completed: generate-report (Duration: 1500ms, Status: SUCCESS)

========================================
SHUTDOWN INITIATED
========================================
Waiting for active tasks to complete...
All tasks completed successfully.

========================================
EXECUTION STATISTICS
========================================
Total Tasks Submitted: 5
Completed Successfully: 3
Failed: 0
Cancelled: 1
Timeout: 0
Still Pending: 0

Success Rate: 75.00%
Average Execution Time: 2166ms
Total Execution Time: 6517ms

Thread Pool Statistics:
- Peak Active Threads: 3
- Total Tasks Executed: 4
- Average Thread Utilization: 60%

========================================
TASK DETAILS
========================================
1. load-data
   Status: SUCCESS
   Priority: 9
   Start Time: 00:00.015
   End Time: 00:02.015
   Duration: 2000ms
   Result: Data loaded: 1000 records

2. process-data
   Status: SUCCESS
   Priority: 8
   Start Time: 00:02.016
   End Time: 00:05.016
   Duration: 3000ms
   Dependencies: [load-data]
   Result: Processed 1000 records

3. generate-report
   Status: SUCCESS
   Priority: 7
   Start Time: 00:05.017
   End Time: 00:06.517
   Duration: 1500ms
   Dependencies: [process-data]
   Result: Report generated

4. send-notif
   Status: SUCCESS
   Priority: 3
   Start Time: 00:00.015
   End Time: 00:01.015
   Duration: 1000ms
   Result: Notifications sent

5. cleanup
   Status: CANCELLED
   Priority: 2
   Start Time: 00:00.015
   Cancelled At: 00:01.020

========================================
SCHEDULER SHUTDOWN COMPLETE
========================================
```

### Expected Classes/Interfaces
```java
public enum SchedulingStrategy {
    PRIORITY_BASED, FIFO, SHORTEST_JOB_FIRST
}

public enum TaskState {
    PENDING, WAITING_DEPENDENCY, RUNNING, SUCCESS, FAILED, CANCELLED, TIMEOUT
}

public abstract class Task {
    private String taskId;
    private String name;
    private int priority;
    private long estimatedDurationMs;
    
    public abstract TaskResult execute() throws Exception;
}

public class TaskResult {
    private TaskState state;
    private String message;
    private long executionTimeMs;
    // factory methods: success(), failure(), timeout()
}

public class TaskStatus {
    private String taskId;
    private String name;
    private TaskState state;
    private int priority;
    private Set<String> dependencies;
    private long submitTime;
    private long startTime;
    private long endTime;
    private TaskResult result;
    // getters
}

public class TaskScheduler {
    public TaskScheduler(int coreThreads, int maxThreads, SchedulingStrategy strategy);
    public String submitTask(Task task);
    public String submitTask(Task task, Set<String> dependsOn);
    public boolean cancelTask(String taskId);
    public TaskStatus getTaskStatus(String taskId);
    public List<TaskStatus> getAllTaskStatuses();
    public void shutdown();
    public boolean awaitTermination(long timeout, TimeUnit unit);
}

public class SchedulerStatistics {
    // Statistics tracking
}

public interface TaskEventListener {
    void onTaskStateChanged(String taskId, TaskState oldState, TaskState newState);
}

public class TaskSchedulerDemo {
    public static void main(String[] args) {
        // Comprehensive demo
    }
}
```

### Evaluation Criteria
- Sophisticated thread pool management
- Correct priority queue implementation with thread safety
- Accurate dependency resolution without deadlocks
- Proper task cancellation mechanism
- Comprehensive monitoring and statistics
- Graceful shutdown handling
- Exception handling across threads
- Clean architecture with proper separation of concerns
- Extensive error scenarios handled
- Well-documented code
- Performance optimization
- Production-ready code quality

### Bonus Features (Optional)
- Task scheduling at specific times (delayed execution)
- Recurring tasks support
- Task result caching
- Dynamic thread pool sizing based on load
- Multiple priority queues for different task types
- Task grouping and batch execution
- Metrics export (JSON/CSV format)
- Visualization of task execution timeline

---

## General Instructions for All Assignments

### Project Setup
1. Use Maven or Gradle as build tool
2. Use Java 8 or higher
3. Structure your project properly with packages

### Code Quality
- Follow Java naming conventions
- Write clean, readable code
- Add appropriate comments for complex logic
- Handle exceptions properly
- Use proper logging (System.out is acceptable for demo)

### Testing
- Create comprehensive demo classes
- Test edge cases
- Test with multiple threads
- Demonstrate error scenarios

### Documentation
- Include README.md with:
  - How to run the project
  - Design decisions
  - Class diagrams (optional but recommended)
  - Known limitations

### Submission
- Ensure code compiles without errors
- Include sample input files (for Assignment 2)
- Remove any hardcoded paths
- Provide clear instructions to run

---

## Learning Objectives

By completing these assignments, you will learn:

1. **Thread Safety**: Using synchronized blocks, locks, concurrent collections
2. **Thread Pools**: ExecutorService, ThreadPoolExecutor, custom thread pools
3. **Coordination**: CountDownLatch, CyclicBarrier, Semaphore, Phaser
4. **Concurrent Collections**: ConcurrentHashMap, BlockingQueue, PriorityQueue
5. **Atomic Operations**: AtomicInteger, AtomicReference
6. **Exception Handling**: Handling exceptions in multi-threaded environment
7. **Resource Management**: Proper cleanup, shutdown hooks
8. **Performance**: Thread pool sizing, optimization techniques
9. **Design Patterns**: Producer-Consumer, Future pattern, Observer pattern
10. **Java 8+ Features**: Lambda, Streams, Optional in concurrent context

Good luck with your assignments! 🚀
