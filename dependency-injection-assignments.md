# Dependency Injection and IoC - Machine Coding Assignments

## Assignment 1: E-Commerce Order Processing System (Easy)

### Problem Statement
Design and implement a simple e-commerce order processing system that demonstrates basic Dependency Injection principles using constructor injection. The system should process orders through various stages (validation, payment, inventory, notification) where each service depends on other services.

### Learning Objectives
- Understand constructor-based dependency injection
- Learn loose coupling between components
- Implement dependency inversion principle
- Practice interface-based programming

### Functional Requirements

1. **Core Interfaces**
   - `PaymentService`: Process payments for orders
   - `InventoryService`: Check and update inventory
   - `NotificationService`: Send notifications (email, SMS)
   - `OrderValidator`: Validate order details

2. **Order Processing**
   - Create an `OrderProcessor` that depends on all the above services
   - Process order through: validation → payment → inventory → notification
   - Handle different payment methods (Credit Card, PayPal, UPI)
   - Support different notification channels (Email, SMS)

3. **Dependency Injection**
   - All dependencies must be injected through constructors
   - No direct instantiation of dependencies using `new` keyword inside classes
   - Use interfaces for all service dependencies

4. **Error Handling**
   - Throw custom exceptions for validation failures
   - Handle payment failures gracefully
   - Handle insufficient inventory scenarios

5. **Main Application**
   - Create a `Main` class that manually wires all dependencies
   - Demonstrate processing at least 3 different orders
   - Show how easy it is to swap implementations (e.g., Email → SMS notification)

### Expected Classes/Interfaces

```java
// Service Interfaces
interface PaymentService { boolean processPayment(Order order); }
interface InventoryService { boolean checkAndUpdateInventory(Order order); }
interface NotificationService { void sendNotification(Order order, String message); }
interface OrderValidator { void validate(Order order); }

// Implementations
class CreditCardPaymentService implements PaymentService { ... }
class PayPalPaymentService implements PaymentService { ... }
class EmailNotificationService implements NotificationService { ... }
class SMSNotificationService implements NotificationService { ... }
class DefaultInventoryService implements InventoryService { ... }
class BasicOrderValidator implements OrderValidator { ... }

// Core
class OrderProcessor {
    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    private final NotificationService notificationService;
    private final OrderValidator validator;
    
    // Constructor injection
    public OrderProcessor(PaymentService ps, InventoryService is, 
                         NotificationService ns, OrderValidator ov) {
        this.paymentService = ps;
        this.inventoryService = is;
        this.notificationService = ns;
        this.validator = ov;
    }
    
    public void processOrder(Order order) { ... }
}

class Order { ... }
class Main { ... }
```

### Sample Input/Output

**Sample Input:**
```java
// Main.java
public static void main(String[] args) {
    // Manual dependency injection
    PaymentService paymentService = new CreditCardPaymentService();
    InventoryService inventoryService = new DefaultInventoryService();
    NotificationService notificationService = new EmailNotificationService();
    OrderValidator validator = new BasicOrderValidator();
    
    OrderProcessor processor = new OrderProcessor(
        paymentService, 
        inventoryService, 
        notificationService, 
        validator
    );
    
    Order order1 = new Order("ORD001", "user@example.com", 
                             Arrays.asList("Laptop", "Mouse"), 1500.00);
    processor.processOrder(order1);
    
    // Easy to swap implementations
    NotificationService smsService = new SMSNotificationService();
    OrderProcessor smsProcessor = new OrderProcessor(
        paymentService, 
        inventoryService, 
        smsService, 
        validator
    );
    
    Order order2 = new Order("ORD002", "+1234567890", 
                             Arrays.asList("Phone"), 800.00);
    smsProcessor.processOrder(order2);
}
```

**Sample Output:**
```
[OrderProcessor] Processing order: ORD001
[BasicOrderValidator] Validating order ORD001... Valid!
[CreditCardPaymentService] Processing payment of $1500.00... Success!
[DefaultInventoryService] Checking inventory for 2 items... Available!
[DefaultInventoryService] Updating inventory... Updated!
[EmailNotificationService] Sending email to user@example.com: Your order ORD001 has been confirmed!
[OrderProcessor] Order ORD001 processed successfully!

[OrderProcessor] Processing order: ORD002
[BasicOrderValidator] Validating order ORD002... Valid!
[CreditCardPaymentService] Processing payment of $800.00... Success!
[DefaultInventoryService] Checking inventory for 1 items... Available!
[DefaultInventoryService] Updating inventory... Updated!
[SMSNotificationService] Sending SMS to +1234567890: Your order ORD002 has been confirmed!
[OrderProcessor] Order ORD002 processed successfully!
```

### Evaluation Criteria
- Proper use of constructor injection (no `new` in business logic classes)
- All dependencies are interfaces
- Clean separation of concerns
- Proper exception handling
- Code is easily testable
- Demonstration of swapping implementations

---

## Assignment 2: Custom Dependency Injection Container (Medium)

### Problem Statement
Build a custom lightweight Dependency Injection (IoC) container from scratch that can register, resolve, and manage dependencies. The container should support constructor injection, singleton and prototype scopes, and handle circular dependency detection.

### Learning Objectives
- Understand how DI containers work internally
- Use Java Reflection API
- Implement Generics for type-safe container
- Handle complex dependency graphs
- Implement lifecycle management

### Functional Requirements

1. **Container Core Features**
   - Register classes with the container (both interfaces and concrete classes)
   - Resolve dependencies automatically using reflection
   - Support constructor injection (choose constructor with most parameters)
   - Maintain a registry of all registered types

2. **Scopes**
   - **Singleton**: Single instance per container (default)
   - **Prototype**: New instance on every resolution
   - Allow scope specification during registration

3. **Dependency Resolution**
   - Automatically resolve constructor dependencies recursively
   - Detect and prevent circular dependencies
   - Throw meaningful exceptions for missing dependencies

4. **Advanced Features**
   - Support registering instances directly
   - Support factory methods for complex object creation
   - Allow named registrations for multiple implementations of same interface
   - Provide container hierarchy (parent-child containers)

5. **Collections Integration**
   - Use `Map` for storing registrations
   - Use `Set` for tracking resolution chain (circular dependency detection)
   - Use `Optional` for nullable returns

6. **Testing**
   - Write JUnit tests for all core functionality
   - Test circular dependency detection
   - Test singleton vs prototype behavior

### Expected Classes/Interfaces

```java
class DependencyContainer {
    private final Map<String, Registration<?>> registrations;
    private final DependencyContainer parent;
    
    public <T> void registerSingleton(Class<T> interfaceType, Class<? extends T> implType) { ... }
    public <T> void registerPrototype(Class<T> interfaceType, Class<? extends T> implType) { ... }
    public <T> void registerInstance(Class<T> type, T instance) { ... }
    public <T> void register(Class<T> interfaceType, Class<? extends T> implType, Scope scope) { ... }
    public <T> void register(String name, Class<T> interfaceType, Class<? extends T> implType, Scope scope) { ... }
    
    public <T> T resolve(Class<T> type) throws DependencyResolutionException { ... }
    public <T> T resolve(String name, Class<T> type) throws DependencyResolutionException { ... }
    
    public boolean isRegistered(Class<?> type) { ... }
    public void clear() { ... }
}

enum Scope { SINGLETON, PROTOTYPE }

class Registration<T> {
    private final Class<T> interfaceType;
    private final Class<? extends T> implementationType;
    private final Scope scope;
    private T singletonInstance;
    private Supplier<T> factory;
    // ... constructors and methods
}

class DependencyResolutionException extends RuntimeException { ... }
class CircularDependencyException extends DependencyResolutionException { ... }
class DependencyNotFoundException extends DependencyResolutionException { ... }
```

### Sample Input/Output

**Sample Input:**
```java
// Define some services
interface ILogger { void log(String message); }
class ConsoleLogger implements ILogger {
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

interface IDatabase { void connect(); }
class MySQLDatabase implements IDatabase {
    private final ILogger logger;
    
    public MySQLDatabase(ILogger logger) {
        this.logger = logger;
    }
    
    public void connect() {
        logger.log("Connected to MySQL database");
    }
}

interface IUserService { void createUser(String name); }
class UserService implements IUserService {
    private final IDatabase database;
    private final ILogger logger;
    
    public UserService(IDatabase database, ILogger logger) {
        this.database = database;
        this.logger = logger;
    }
    
    public void createUser(String name) {
        database.connect();
        logger.log("Creating user: " + name);
    }
}

// Main usage
public static void main(String[] args) {
    DependencyContainer container = new DependencyContainer();
    
    // Register dependencies
    container.registerSingleton(ILogger.class, ConsoleLogger.class);
    container.registerSingleton(IDatabase.class, MySQLDatabase.class);
    container.registerPrototype(IUserService.class, UserService.class);
    
    // Resolve - container automatically injects dependencies
    IUserService userService1 = container.resolve(IUserService.class);
    userService1.createUser("Alice");
    
    IUserService userService2 = container.resolve(IUserService.class);
    userService2.createUser("Bob");
    
    System.out.println("userService1 == userService2: " + (userService1 == userService2));
    
    // Test circular dependency detection
    container.clear();
    interface IA { }
    interface IB { }
    class A implements IA { public A(IB b) {} }
    class B implements IB { public B(IA a) {} }
    
    container.registerSingleton(IA.class, A.class);
    container.registerSingleton(IB.class, B.class);
    
    try {
        container.resolve(IA.class);
    } catch (CircularDependencyException e) {
        System.out.println("Caught circular dependency: " + e.getMessage());
    }
}
```

**Sample Output:**
```
[LOG] Connected to MySQL database
[LOG] Creating user: Alice
[LOG] Connected to MySQL database
[LOG] Creating user: Bob
userService1 == userService2: false
Caught circular dependency: Circular dependency detected: IA -> IB -> IA
```

**Sample JUnit Test:**
```java
@Test
public void testSingletonScope() {
    DependencyContainer container = new DependencyContainer();
    container.registerSingleton(ILogger.class, ConsoleLogger.class);
    
    ILogger logger1 = container.resolve(ILogger.class);
    ILogger logger2 = container.resolve(ILogger.class);
    
    assertSame(logger1, logger2);
}

@Test
public void testPrototypeScope() {
    DependencyContainer container = new DependencyContainer();
    container.registerPrototype(ILogger.class, ConsoleLogger.class);
    
    ILogger logger1 = container.resolve(ILogger.class);
    ILogger logger2 = container.resolve(ILogger.class);
    
    assertNotSame(logger1, logger2);
}

@Test(expected = CircularDependencyException.class)
public void testCircularDependencyDetection() {
    DependencyContainer container = new DependencyContainer();
    container.registerSingleton(IA.class, A.class);
    container.registerSingleton(IB.class, B.class);
    
    container.resolve(IA.class); // Should throw CircularDependencyException
}

@Test(expected = DependencyNotFoundException.class)
public void testUnregisteredDependency() {
    DependencyContainer container = new DependencyContainer();
    container.resolve(ILogger.class); // Not registered
}
```

### Evaluation Criteria
- Correct implementation of reflection-based dependency resolution
- Proper singleton management (thread-safe)
- Accurate circular dependency detection
- Clean use of generics
- Comprehensive exception handling
- All unit tests passing
- Code is well-documented
- Efficient use of Collections Framework

### Bonus Points
- Implement thread-safe singleton resolution using synchronization or concurrent collections
- Support property/setter injection in addition to constructor injection
- Implement a fluent API for registration
- Add container lifecycle callbacks (onCreate, onDestroy)

---

## Assignment 3: Annotation-Based IoC Container with Auto-Wiring (Hard)

### Problem Statement
Build a production-grade Inversion of Control (IoC) container that supports custom annotations for automatic dependency injection, component scanning, lifecycle management, and configuration. The container should rival basic features of Spring's IoC without using any external frameworks.

### Learning Objectives
- Deep understanding of Java Reflection and Annotations
- Implement classpath scanning and component discovery
- Build complex dependency graphs with qualifiers
- Implement AOP-lite (proxies for lazy initialization)
- Thread-safe singleton management with double-checked locking
- Advanced exception handling and error reporting

### Functional Requirements

1. **Custom Annotations**
   ```java
   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.TYPE)
   @interface Component {
       String value() default "";
       Scope scope() default Scope.SINGLETON;
   }
   
   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.TYPE)
   @interface Service { String value() default ""; }
   
   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.TYPE)
   @interface Repository { String value() default ""; }
   
   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
   @interface Inject { }
   
   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.FIELD, ElementType.PARAMETER})
   @interface Qualifier { String value(); }
   
   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.METHOD)
   @interface PostConstruct { }
   
   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.METHOD)
   @interface PreDestroy { }
   
   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.TYPE)
   @interface Configuration { }
   
   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.METHOD)
   @interface Bean {
       String name() default "";
       Scope scope() default Scope.SINGLETON;
   }
   
   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.FIELD)
   @interface Value { String value(); }
   ```

2. **Component Scanning**
   - Scan specified packages for classes annotated with `@Component`, `@Service`, `@Repository`
   - Automatically register discovered components
   - Support exclusion filters
   - Handle nested packages

3. **Auto-Wiring**
   - Support constructor injection (preferred)
   - Support field injection using `@Inject`
   - Support `@Qualifier` for disambiguating multiple implementations
   - Automatic dependency resolution based on type
   - Handle generic types correctly

4. **Configuration Classes**
   - Support `@Configuration` classes
   - Process `@Bean` methods to create and register beans
   - Support bean dependencies in configuration methods
   - Handle method ordering based on dependencies

5. **Lifecycle Management**
   - Call `@PostConstruct` methods after dependency injection
   - Call `@PreDestroy` methods on container shutdown
   - Proper initialization order based on dependency graph
   - Thread-safe lazy initialization for singletons

6. **Property Injection**
   - Support `@Value` annotation for primitive types and Strings
   - Load properties from `.properties` file
   - Support default values in `@Value` annotation
   - Type conversion (String to int, double, boolean, etc.)

7. **Advanced Features**
   - Support for profiles (dev, prod, test)
   - Conditional registration based on presence of classes
   - Factory bean support
   - Lazy initialization with proxy creation
   - Bean post-processors for customization

8. **Error Handling & Validation**
   - Detect and report circular dependencies with full chain
   - Validate bean definitions at startup
   - Meaningful error messages with suggestions
   - Handle missing dependencies gracefully

9. **Concurrency**
   - Thread-safe bean creation and storage
   - Use `ConcurrentHashMap` for registries
   - Implement double-checked locking for lazy singletons
   - Handle race conditions in bean initialization

10. **Testing**
    - Comprehensive JUnit tests for all features
    - Test concurrent bean resolution
    - Test edge cases (circular deps, missing deps, qualifiers)
    - Integration tests with real-world scenarios

### Expected Architecture

```java
class ApplicationContext {
    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitions;
    private final ConcurrentHashMap<String, Object> singletonBeans;
    private final ComponentScanner scanner;
    private final BeanFactory beanFactory;
    private final PropertyResolver propertyResolver;
    
    public ApplicationContext(String... basePackages) { ... }
    public ApplicationContext(Class<?>... configClasses) { ... }
    
    public <T> T getBean(Class<T> type) { ... }
    public <T> T getBean(String name, Class<T> type) { ... }
    public Object getBean(String name) { ... }
    
    public void refresh() { ... } // Initialize all singletons
    public void close() { ... } // Call @PreDestroy and cleanup
}

class ComponentScanner {
    public Set<Class<?>> scan(String... basePackages) { ... }
    public Set<Class<?>> findAnnotatedClasses(String packageName, Class<? extends Annotation> annotation) { ... }
}

class BeanDefinition {
    private final String beanName;
    private final Class<?> beanClass;
    private final Scope scope;
    private final Constructor<?> constructor;
    private final List<Field> injectFields;
    private final Method postConstructMethod;
    private final Method preDestroyMethod;
    private final List<DependencyDescriptor> dependencies;
}

class DependencyDescriptor {
    private final Class<?> dependencyType;
    private final String qualifier;
    private final boolean required;
}

class BeanFactory {
    public Object createBean(BeanDefinition definition, ApplicationContext context) { ... }
    public void injectDependencies(Object bean, BeanDefinition definition, ApplicationContext context) { ... }
    public void invokePostConstruct(Object bean, BeanDefinition definition) { ... }
    public void invokePreDestroy(Object bean, BeanDefinition definition) { ... }
}

class PropertyResolver {
    private final Properties properties;
    public PropertyResolver(String propertiesFile) { ... }
    public <T> T resolve(String key, Class<T> type, T defaultValue) { ... }
}

enum Scope { SINGLETON, PROTOTYPE }
```

### Sample Input/Output

**Sample Input:**

```java
// application.properties
app.name=MyApplication
app.version=1.0.0
database.url=jdbc:mysql://localhost:3306/mydb
database.pool.size=10
feature.cache.enabled=true

// Component classes
@Component
class Cache {
    @Value("${feature.cache.enabled:false}")
    private boolean enabled;
    
    private Map<String, Object> store = new ConcurrentHashMap<>();
    
    @PostConstruct
    public void init() {
        System.out.println("Cache initialized. Enabled: " + enabled);
    }
    
    public void put(String key, Object value) {
        if (enabled) store.put(key, value);
    }
}

@Repository
class UserRepository {
    @Value("${database.url}")
    private String databaseUrl;
    
    @Inject
    private Cache cache;
    
    @PostConstruct
    public void connect() {
        System.out.println("Connected to database: " + databaseUrl);
    }
    
    public String findUser(String id) {
        return (String) cache.get(id);
    }
}

@Service
class UserService {
    private final UserRepository repository;
    private final NotificationService notificationService;
    
    @Inject
    public UserService(UserRepository repository, 
                      @Qualifier("email") NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }
    
    public void registerUser(String id, String name) {
        System.out.println("Registering user: " + name);
        notificationService.send("Welcome " + name);
    }
}

interface NotificationService {
    void send(String message);
}

@Service("email")
class EmailNotificationService implements NotificationService {
    public void send(String message) {
        System.out.println("[EMAIL] " + message);
    }
}

@Service("sms")
class SMSNotificationService implements NotificationService {
    public void send(String message) {
        System.out.println("[SMS] " + message);
    }
}

@Configuration
class AppConfig {
    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(5);
    }
    
    @Bean
    public MetricsCollector metricsCollector(Cache cache) {
        return new MetricsCollector(cache);
    }
}

// Main application
public class Application {
    public static void main(String[] args) {
        // Create context with package scanning
        ApplicationContext context = new ApplicationContext("com.example");
        
        // Load properties
        context.loadProperties("application.properties");
        
        // Refresh to initialize all singletons
        context.refresh();
        
        // Get beans and use them
        UserService userService = context.getBean(UserService.class);
        userService.registerUser("U001", "Alice");
        
        // Get specific qualified bean
        NotificationService smsService = context.getBean("sms", NotificationService.class);
        smsService.send("Test SMS");
        
        // Test singleton behavior
        UserService service1 = context.getBean(UserService.class);
        UserService service2 = context.getBean(UserService.class);
        System.out.println("Same instance: " + (service1 == service2));
        
        // Concurrent access test
        ExecutorService executor = context.getBean(ExecutorService.class);
        for (int i = 0; i < 10; i++) {
            final int userId = i;
            executor.submit(() -> {
                UserService service = context.getBean(UserService.class);
                service.registerUser("U" + userId, "User" + userId);
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        
        // Cleanup
        context.close();
    }
}
```

**Sample Output:**
```
[ApplicationContext] Scanning packages: [com.example]
[ComponentScanner] Found 6 components
[ApplicationContext] Registering bean: cache (class Cache)
[ApplicationContext] Registering bean: userRepository (class UserRepository)
[ApplicationContext] Registering bean: userService (class UserService)
[ApplicationContext] Registering bean: email (class EmailNotificationService)
[ApplicationContext] Registering bean: sms (class SMSNotificationService)
[ApplicationContext] Processing configuration: AppConfig
[ApplicationContext] Registering bean: executorService (factory method)
[ApplicationContext] Registering bean: metricsCollector (factory method)
[ApplicationContext] Starting refresh...
[BeanFactory] Creating singleton: cache
Cache initialized. Enabled: true
[BeanFactory] Creating singleton: userRepository
Connected to database: jdbc:mysql://localhost:3306/mydb
[BeanFactory] Creating singleton: email
[BeanFactory] Creating singleton: userService
[BeanFactory] Injecting dependencies into userService
[ApplicationContext] All singletons initialized
Registering user: Alice
[EMAIL] Welcome Alice
[SMS] Test SMS
Same instance: true
Registering user: User0
[EMAIL] Welcome User0
Registering user: User1
[EMAIL] Welcome User1
...
[ApplicationContext] Closing context...
[BeanFactory] Calling @PreDestroy on cache
[BeanFactory] Calling @PreDestroy on userRepository
Cache destroyed
Database connection closed
[ApplicationContext] Context closed
```

**Sample JUnit Tests:**

```java
@Test
public void testComponentScanning() {
    ApplicationContext context = new ApplicationContext("com.example.services");
    UserService service = context.getBean(UserService.class);
    assertNotNull(service);
}

@Test
public void testQualifierInjection() {
    ApplicationContext context = new ApplicationContext("com.example");
    NotificationService email = context.getBean("email", NotificationService.class);
    NotificationService sms = context.getBean("sms", NotificationService.class);
    
    assertNotSame(email, sms);
    assertTrue(email instanceof EmailNotificationService);
    assertTrue(sms instanceof SMSNotificationService);
}

@Test
public void testPropertyInjection() {
    ApplicationContext context = new ApplicationContext("com.example");
    context.loadProperties("test.properties");
    context.refresh();
    
    Cache cache = context.getBean(Cache.class);
    // Use reflection to check private field
    Field field = Cache.class.getDeclaredField("enabled");
    field.setAccessible(true);
    assertTrue((Boolean) field.get(cache));
}

@Test(expected = CircularDependencyException.class)
public void testCircularDependencyDetection() {
    // Setup classes with circular dependency
    @Service class A { @Inject B b; }
    @Service class B { @Inject A a; }
    
    ApplicationContext context = new ApplicationContext("com.example.circular");
    context.refresh(); // Should throw
}

@Test
public void testLifecycleMethods() {
    ApplicationContext context = new ApplicationContext("com.example");
    context.refresh();
    
    // @PostConstruct should have been called
    // Verify through log or bean state
    
    context.close();
    
    // @PreDestroy should have been called
    // Verify cleanup occurred
}

@Test
public void testConcurrentBeanCreation() throws Exception {
    ApplicationContext context = new ApplicationContext("com.example");
    
    ExecutorService executor = Executors.newFixedThreadPool(10);
    Set<UserService> instances = ConcurrentHashMap.newKeySet();
    
    for (int i = 0; i < 100; i++) {
        executor.submit(() -> {
            UserService service = context.getBean(UserService.class);
            instances.add(service);
        });
    }
    
    executor.shutdown();
    executor.awaitTermination(10, TimeUnit.SECONDS);
    
    // All should be same instance (singleton)
    assertEquals(1, instances.size());
}

@Test
public void testPrototypeScope() {
    @Component(scope = Scope.PROTOTYPE)
    class PrototypeBean { }
    
    ApplicationContext context = new ApplicationContext("com.example");
    context.refresh();
    
    PrototypeBean bean1 = context.getBean(PrototypeBean.class);
    PrototypeBean bean2 = context.getBean(PrototypeBean.class);
    
    assertNotSame(bean1, bean2);
}
```

### Evaluation Criteria
- Correct implementation of all annotations
- Accurate component scanning using reflection
- Proper auto-wiring with qualifier support
- Thread-safe singleton management
- Correct lifecycle method invocation
- Property injection with type conversion
- Comprehensive circular dependency detection
- Clean architecture with separation of concerns
- Extensive unit and integration tests (>80% coverage)
- Performance optimization for bean creation
- Clear error messages and exception handling
- Well-documented code with Javadoc

### Bonus Points
- Implement AOP-lite with proxy-based lazy initialization
- Support for conditional beans based on properties
- Bean validation using custom annotations
- Performance monitoring (bean creation time tracking)
- Support for bean profiles (dev/prod/test)
- Implement a fluent builder API for manual bean registration
- Create a graphical dependency visualizer
- Support for importing external configuration
- Implement bean aliases

### Performance Requirements
- Bean creation should handle 10,000 beans efficiently
- Concurrent bean resolution should be thread-safe
- Singleton creation should use double-checked locking
- Memory efficient storage of bean definitions

### Deliverables
1. Complete source code with package structure
2. JUnit test suite with >80% coverage
3. README.md with:
   - Architecture overview
   - Design decisions and trade-offs
   - Usage examples
   - Performance benchmarks
4. Build configuration (Maven/Gradle)
5. Sample application demonstrating all features

---

## General Guidelines for All Assignments

### Code Quality Standards
- Follow Java naming conventions
- Use meaningful variable and method names
- Keep methods small and focused (Single Responsibility)
- Write self-documenting code with comments where necessary
- Use appropriate design patterns
- Handle all exceptions properly

### Testing Requirements
- Write unit tests for all public methods
- Test edge cases and error conditions
- Achieve good code coverage (aim for >70%)
- Use meaningful test names
- Follow AAA pattern (Arrange, Act, Assert)

### Documentation Requirements
- Include Javadoc for public classes and methods
- Add README with setup instructions
- Include architecture diagrams if applicable
- Document design decisions and trade-offs

### Build Tool Integration
- Use Maven or Gradle
- Include all dependencies
- Configure proper project structure
- Add plugins for testing and coverage reports

### Submission Format
```
project-root/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/example/
│   │           ├── Main.java
│   │           ├── container/
│   │           ├── services/
│   │           └── annotations/
│   └── test/
│       └── java/
│           └── com/example/
│               └── container/
├── pom.xml / build.gradle
└── README.md
```

### Time Estimates
- **Easy Assignment**: 3-4 hours
- **Medium Assignment**: 8-12 hours
- **Hard Assignment**: 20-30 hours

### Learning Resources
- Java Reflection API documentation
- Effective Java by Joshua Bloch (especially chapters on generics and concurrency)
- Design Patterns: Elements of Reusable Object-Oriented Software
- Spring Framework source code (for inspiration, not copying!)

---

## Tips for Success

1. **Start Simple**: Begin with basic functionality, then add features incrementally
2. **Test Early**: Write tests as you develop, not after
3. **Read Spring Source**: Study how Spring implements these features (but don't copy!)
4. **Use Debugger**: Step through your reflection code to understand what's happening
5. **Handle Errors**: Think about what can go wrong and handle it gracefully
6. **Refactor**: Don't be afraid to refactor as you learn better approaches
7. **Document**: Write down your design decisions and why you made them

Good luck! These assignments will give you a deep understanding of how dependency injection containers work under the hood! 🚀
