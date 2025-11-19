# Java Generics Assignments

## Assignment 1: Generic Storage Container (Easy)

### Problem Statement
Design a generic storage container system that can store any type of items with a maximum capacity. The container should support basic operations like adding items, removing items, checking if it's full, and retrieving items by index.

### Functional Requirements

1. **Create a Generic Container Class**
   - The class should be parameterized with type `T`
   - Constructor should accept maximum capacity
   - Should maintain current count of items

2. **Implement the following methods:**
   - `boolean add(T item)` - Adds an item to the container. Returns true if successful, false if container is full
   - `T get(int index)` - Returns the item at the specified index
   - `T remove(int index)` - Removes and returns the item at the specified index
   - `boolean isEmpty()` - Returns true if container has no items
   - `boolean isFull()` - Returns true if container has reached maximum capacity
   - `int size()` - Returns current number of items in the container
   - `void clear()` - Removes all items from the container

3. **Exception Handling:**
   - Throw `IndexOutOfBoundsException` with appropriate message when accessing invalid index
   - Throw `IllegalArgumentException` if capacity is less than 1

4. **Create a Driver Class:**
   - Create containers for different types (String, Integer, Double)
   - Demonstrate all operations
   - Handle exceptions appropriately

### Sample Input
```java
// Create a container for Strings with capacity 3
Container<String> stringContainer = new Container<>(3);

// Add items
stringContainer.add("Apple");
stringContainer.add("Banana");
stringContainer.add("Cherry");

// Try to add when full
stringContainer.add("Date");

// Get item
String fruit = stringContainer.get(1);

// Remove item
String removed = stringContainer.remove(0);

// Check status
boolean isEmpty = stringContainer.isEmpty();
boolean isFull = stringContainer.isFull();
int size = stringContainer.size();
```

### Sample Output
```
Added: Apple
Added: Banana
Added: Cherry
Container is full! Cannot add: Date
Item at index 1: Banana
Removed item: Apple
Is Empty: false
Is Full: false
Current Size: 2

--- Integer Container Demo ---
Added: 10
Added: 20
Added: 30
Added: 40
Added: 50
Item at index 2: 30
Removed item from index 1: 20
Current Size: 4
Container cleared!
```

---

## Assignment 2: Generic Pair with Comparator (Medium)

### Problem Statement
Create a generic Pair class that can hold two values of potentially different types. Implement functionality to swap values, compare pairs, and work with collections of pairs. The system should support sorting pairs based on different criteria.

### Functional Requirements

1. **Create a Generic Pair Class**
   - Should be parameterized with two types `K` and `V` (for key and value)
   - Constructor should accept both values
   - Implement appropriate getter and setter methods

2. **Implement the following methods:**
   - `void swap()` - Swaps the first and second values (only if both types are the same)
   - `boolean contains(Object obj)` - Returns true if either first or second equals the object
   - `Pair<V, K> reverse()` - Returns a new pair with swapped types
   - Override `toString()` - Returns string representation like "(first, second)"
   - Override `equals(Object obj)` - Two pairs are equal if both elements are equal
   - Override `hashCode()` - Generate proper hash code

3. **Create a PairComparator Interface**
   - Generic interface with method `int compare(Pair<K,V> p1, Pair<K,V> p2)`

4. **Implement Comparators:**
   - `FirstElementComparator` - Compares pairs based on first element (assuming Comparable)
   - `SecondElementComparator` - Compares pairs based on second element (assuming Comparable)

5. **Create a PairProcessor Class with static methods:**
   - `<K,V> List<Pair<K,V>> filterByFirst(List<Pair<K,V>> pairs, K value)` - Returns pairs where first element equals value
   - `<K,V> List<Pair<K,V>> filterBySecond(List<Pair<K,V>> pairs, V value)` - Returns pairs where second element equals value
   - `<K extends Comparable<K>, V> void sortByFirst(List<Pair<K,V>> pairs)` - Sorts pairs by first element
   - `<K, V extends Comparable<V>> void sortBySecond(List<Pair<K,V>> pairs)` - Sorts pairs by second element

6. **Exception Handling:**
   - Throw `UnsupportedOperationException` when trying to swap pairs with different types
   - Throw `IllegalArgumentException` for null inputs where appropriate

7. **Create a Driver Class:**
   - Create pairs of different type combinations
   - Demonstrate all operations including filtering and sorting
   - Show exception handling

### Sample Input
```java
// Create pairs
Pair<String, Integer> p1 = new Pair<>("Alice", 25);
Pair<String, Integer> p2 = new Pair<>("Bob", 30);
Pair<String, Integer> p3 = new Pair<>("Charlie", 25);

// Create list of pairs
List<Pair<String, Integer>> pairs = new ArrayList<>();
pairs.add(p1);
pairs.add(p2);
pairs.add(p3);

// Filter by age 25
List<Pair<String, Integer>> filtered = PairProcessor.filterBySecond(pairs, 25);

// Sort by name
PairProcessor.sortByFirst(pairs);

// Reverse a pair
Pair<Integer, String> reversed = p1.reverse();

// Swap (for same type pair)
Pair<Integer, Integer> numPair = new Pair<>(10, 20);
numPair.swap();
```

### Sample Output
```
Created Pairs:
(Alice, 25)
(Bob, 30)
(Charlie, 25)

Filtered pairs with age 25:
(Alice, 25)
(Charlie, 25)

Sorted pairs by first element (name):
(Alice, 25)
(Bob, 30)
(Charlie, 25)

Sorted pairs by second element (age):
(Alice, 25)
(Charlie, 25)
(Bob, 30)

Reversed pair: (25, Alice)

Testing swap with same type:
Before swap: (10, 20)
After swap: (20, 10)

Testing swap with different types:
Error: Cannot swap pair with different types (String, Integer)

Equality test:
Pair (Alice, 25) equals (Alice, 25): true
Pair (Alice, 25) equals (Bob, 30): false

Contains test:
Pair (Alice, 25) contains 'Alice': true
Pair (Alice, 25) contains 30: false
```

---

## Assignment 3: Generic Type-Safe Repository System (Hard)

### Problem Statement
Design a generic repository system that can store and manage entities of different types with type-safe operations. The system should support CRUD operations, querying with predicates, and maintaining relationships between entities. Implement proper type bounds and wildcards to ensure type safety.

### Functional Requirements

1. **Create an Entity Interface**
   - Generic interface with type parameter for ID type
   - Methods: `getId()`, `setId(ID id)`

2. **Create a Generic Repository Interface**
   - Parameterized with entity type `T extends Entity<ID>` and ID type `ID`
   - Methods:
     - `void save(T entity)` - Saves or updates an entity
     - `T findById(ID id)` - Finds entity by ID
     - `List<T> findAll()` - Returns all entities
     - `boolean delete(ID id)` - Deletes entity by ID, returns true if successful
     - `boolean exists(ID id)` - Checks if entity exists
     - `int count()` - Returns total number of entities
     - `List<T> findByPredicate(Predicate<T> predicate)` - Finds entities matching predicate

3. **Create a Predicate Interface**
   - Generic functional interface with method `boolean test(T entity)`

4. **Implement InMemoryRepository Class**
   - Implements Repository interface
   - Uses appropriate collection to store entities
   - Thread-safe is not required

5. **Create Entity Classes:**
   - `User` class implementing `Entity<Long>`
     - Fields: id, name, email, age
   - `Product` class implementing `Entity<String>`
     - Fields: id (String), name, price, category

6. **Create a RepositoryManager Class:**
   - Should manage multiple repositories of different types
   - Methods:
     - `<T extends Entity<ID>, ID> void registerRepository(Class<T> entityClass, Repository<T, ID> repository)`
     - `<T extends Entity<ID>, ID> Repository<T, ID> getRepository(Class<T> entityClass)`

7. **Create Utility Methods (in a separate class):**
   - `<T extends Entity<ID>, ID> void copyEntities(Repository<? extends T, ID> source, Repository<? super T, ID> destination)` - Copies all entities from source to destination
   - `<T extends Entity<ID> & Comparable<T>, ID> T findMax(Repository<T, ID> repository)` - Finds maximum entity
   - `<T extends Entity<ID>, ID> Map<ID, T> toMap(Repository<T, ID> repository)` - Converts repository to map

8. **Exception Handling:**
   - Create custom exceptions:
     - `EntityNotFoundException` - When entity is not found
     - `DuplicateEntityException` - When trying to save entity with existing ID
   - Handle null inputs appropriately

9. **Create a Driver Class:**
   - Demonstrate repository operations with both User and Product
   - Show predicate usage for filtering
   - Demonstrate wildcard usage with copy method
   - Show type bounds with comparable entities
   - Handle all exceptions

### Sample Input
```java
// Create repositories
Repository<User, Long> userRepo = new InMemoryRepository<>();
Repository<Product, String> productRepo = new InMemoryRepository<>();

// Create and save users
User user1 = new User(1L, "John Doe", "john@example.com", 30);
User user2 = new User(2L, "Jane Smith", "jane@example.com", 25);
userRepo.save(user1);
userRepo.save(user2);

// Create and save products
Product p1 = new Product("P001", "Laptop", 999.99, "Electronics");
Product p2 = new Product("P002", "Mouse", 29.99, "Electronics");
productRepo.save(p1);
productRepo.save(p2);

// Query with predicates
List<User> youngUsers = userRepo.findByPredicate(user -> user.getAge() < 28);

// Filter products by category
List<Product> electronics = productRepo.findByPredicate(
    product -> product.getCategory().equals("Electronics")
);

// Copy entities
Repository<User, Long> backupRepo = new InMemoryRepository<>();
RepositoryUtil.copyEntities(userRepo, backupRepo);

// Convert to map
Map<Long, User> userMap = RepositoryUtil.toMap(userRepo);
```

### Sample Output
```
=== User Repository Operations ===

Saved user: User{id=1, name='John Doe', email='john@example.com', age=30}
Saved user: User{id=2, name='Jane Smith', email='jane@example.com', age=25}
Saved user: User{id=3, name='Bob Wilson', email='bob@example.com', age=35}

Total users: 3

Finding user by ID 2:
User{id=2, name='Jane Smith', email='jane@example.com', age=25}

All users:
1. User{id=1, name='John Doe', email='john@example.com', age=30}
2. User{id=2, name='Jane Smith', email='jane@example.com', age=25}
3. User{id=3, name='Bob Wilson', email='bob@example.com', age=35}

Finding users younger than 28:
User{id=2, name='Jane Smith', email='jane@example.com', age=25}

=== Product Repository Operations ===

Saved product: Product{id='P001', name='Laptop', price=999.99, category='Electronics'}
Saved product: Product{id='P002', name='Mouse', price=29.99, category='Electronics'}
Saved product: Product{id='P003', name='Desk', price=299.99, category='Furniture'}

Total products: 3

Finding products in Electronics category:
1. Product{id='P001', name='Laptop', price=999.99, category='Electronics'}
2. Product{id='P002', name='Mouse', price=29.99, category='Electronics'}

=== Repository Manager Operations ===

Registered repositories:
- User Repository
- Product Repository

Retrieved User Repository - Count: 3
Retrieved Product Repository - Count: 3

=== Utility Operations ===

Copying entities from userRepo to backupRepo:
Copied 3 entities successfully
Backup repository count: 3

Converting user repository to map:
Map size: 3
User with ID 1: John Doe
User with ID 2: Jane Smith
User with ID 3: Bob Wilson

=== Exception Handling Demonstrations ===

Attempting to find non-existent user (ID: 999):
Error: EntityNotFoundException - Entity with ID 999 not found

Attempting to save duplicate user (ID: 1):
Error: DuplicateEntityException - Entity with ID 1 already exists

Attempting to delete user (ID: 2):
User deleted successfully
Remaining users count: 2

Attempting to delete non-existent user (ID: 999):
Error: EntityNotFoundException - Cannot delete. Entity with ID 999 not found

=== Predicate Complex Queries ===

Users with age > 25 AND name starting with 'J':
User{id=1, name='John Doe', email='john@example.com', age=30}

Products with price > 100:
1. Product{id='P001', name='Laptop', price=999.99, category='Electronics'}
2. Product{id='P003', name='Desk', price=299.99, category='Furniture'}
```

---

## Evaluation Criteria

### Easy Assignment (Generic Storage Container)
- Correct implementation of generic class (20%)
- Proper exception handling (20%)
- Working implementations of all methods (40%)
- Code organization and documentation (20%)

### Medium Assignment (Generic Pair with Comparator)
- Generic pair class with proper type parameters (20%)
- Working swap and reverse methods (15%)
- Correct comparator implementations (15%)
- Generic filter and sort methods (25%)
- Exception handling (15%)
- Code quality and documentation (10%)

### Hard Assignment (Generic Repository System)
- Proper use of type bounds (`extends`, `super`) (20%)
- Correct wildcard usage (15%)
- Repository implementation with all CRUD operations (20%)
- Predicate-based querying (15%)
- Custom exception handling (10%)
- Generic utility methods (10%)
- Code organization and best practices (10%)

---

## Learning Outcomes

After completing these assignments, you should be able to:
- Create and use generic classes and interfaces
- Understand type parameters and type bounds
- Work with bounded type parameters (`extends`, `super`)
- Use wildcards effectively (`?`, `? extends T`, `? super T`)
- Implement generic methods
- Handle exceptions in generic code
- Work with generic collections
- Understand type erasure implications
- Write reusable, type-safe code

## Additional Notes

- Focus on making your code type-safe and avoiding raw types
- Use meaningful generic type parameter names (T for type, E for element, K for key, V for value, etc.)
- Document your generic constraints clearly
- Test with multiple types to ensure generic code works correctly
- Pay attention to compile-time vs runtime type information
