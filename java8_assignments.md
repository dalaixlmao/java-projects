# Java 8+ Features Assignments

## Assignment 1: Student Performance Analyzer (Easy)

### Problem Statement
Build a **Student Performance Analyzer** that processes a list of students and their exam scores using Java 8 Streams and Lambda expressions. The system should filter, transform, and aggregate student data based on various criteria.

### Functional Requirements

1. **Student Model**
   - Create a `Student` class with fields: `id`, `name`, `age`, `department`, and `marks` (List of integers)
   - Implement appropriate constructors, getters, and toString method

2. **Core Operations** (use Streams API)
   - Calculate average marks for each student
   - Find top N students based on average marks
   - Filter students by department
   - Find students who passed (average >= 40) and failed
   - Get distinct departments
   - Find the student with highest average marks using Optional

3. **Statistics**
   - Calculate overall class average
   - Count students per department
   - Find minimum and maximum average marks

4. **Input Processing**
   - Accept a list of Student objects
   - All operations should use Lambda expressions and Streams
   - Handle edge cases using Optional (e.g., empty lists, no students in department)

### Constraints
- Do not use traditional loops (for, while)
- Use method references where applicable
- Handle null safety using Optional
- Department names are case-insensitive

### Sample Input

```java
List<Student> students = Arrays.asList(
    new Student(1, "Alice", 20, "CSE", Arrays.asList(85, 90, 78, 92)),
    new Student(2, "Bob", 21, "ECE", Arrays.asList(72, 68, 75, 70)),
    new Student(3, "Charlie", 19, "CSE", Arrays.asList(95, 88, 91, 89)),
    new Student(4, "Diana", 20, "MECH", Arrays.asList(45, 50, 48, 52)),
    new Student(5, "Eve", 22, "ECE", Arrays.asList(65, 70, 68, 72)),
    new Student(6, "Frank", 21, "CSE", Arrays.asList(30, 35, 32, 38)),
    new Student(7, "Grace", 20, "MECH", Arrays.asList(88, 85, 90, 87))
);
```

### Sample Output

```
=== Student Performance Analysis ===

Top 3 Students:
1. Charlie (CSE) - Average: 90.75
2. Grace (MECH) - Average: 87.50
3. Alice (CSE) - Average: 86.25

Students by Department:
CSE: [Alice (86.25), Charlie (90.75), Frank (33.75)]
ECE: [Bob (71.25), Eve (68.75)]
MECH: [Diana (48.75), Grace (87.50)]

Pass/Fail Summary:
Passed (6): Alice, Bob, Charlie, Diana, Eve, Grace
Failed (1): Frank

Department Statistics:
CSE: 3 students, Avg: 70.25
ECE: 2 students, Avg: 70.00
MECH: 2 students, Avg: 68.12

Overall Statistics:
Class Average: 69.64
Highest Average: Charlie - 90.75
Lowest Average: Frank - 33.75
Distinct Departments: 3
```

---

## Assignment 2: E-Commerce Order Processing System (Medium)

### Problem Statement
Develop an **Order Processing System** for an e-commerce platform that analyzes orders, products, and customer data using advanced Java 8 features including Streams, Optional, Lambda expressions, and custom Collectors.

### Functional Requirements

1. **Data Models**
   - `Product`: `productId`, `name`, `category`, `price`
   - `OrderItem`: `product`, `quantity`
   - `Order`: `orderId`, `customerId`, `customerName`, `orderItems` (List), `orderDate`, `status` (PENDING/COMPLETED/CANCELLED)
   - `Customer`: `customerId`, `name`, `email`, `tier` (REGULAR/PREMIUM/VIP)

2. **Core Operations**
   - Calculate total order value for each order
   - Find top N products by revenue (price × quantity sold)
   - Group orders by customer and calculate total spending per customer
   - Find most popular category (by number of items sold)
   - Filter orders above a certain amount
   - Get customer details using Optional (handle missing customers gracefully)

3. **Advanced Analytics**
   - Calculate discount eligibility: VIP gets 15%, PREMIUM gets 10%, REGULAR gets 0%
   - Find customers who spent more than average
   - Get product recommendations based on category frequency
   - Calculate month-wise revenue (group by month from orderDate)
   - Find orders with out-of-stock items (quantity > availableStock)

4. **Optional Chaining**
   - Safely retrieve nested Optional values
   - Find the most expensive product in a specific category (return Optional)
   - Get customer email for an order (handle missing customer)

5. **Custom Operations**
   - Create a custom Collector to group orders by price range (0-1000, 1001-5000, 5000+)
   - Implement flatMap to get all products from all orders
   - Use reduce to calculate grand total revenue

### Constraints
- Use Stream API for all data processing
- Leverage Optional for null safety
- Use method references where applicable
- No traditional loops
- Use LocalDate for dates
- Handle edge cases (empty orders, missing products, etc.)

### Sample Input

```java
List<Product> products = Arrays.asList(
    new Product(1, "Laptop", "Electronics", 50000),
    new Product(2, "Mouse", "Electronics", 500),
    new Product(3, "Keyboard", "Electronics", 1500),
    new Product(4, "Desk", "Furniture", 8000),
    new Product(5, "Chair", "Furniture", 5000),
    new Product(6, "Book", "Books", 300),
    new Product(7, "Pen", "Stationery", 20)
);

List<Customer> customers = Arrays.asList(
    new Customer(101, "John Doe", "john@email.com", CustomerTier.VIP),
    new Customer(102, "Jane Smith", "jane@email.com", CustomerTier.PREMIUM),
    new Customer(103, "Bob Wilson", "bob@email.com", CustomerTier.REGULAR)
);

List<Order> orders = Arrays.asList(
    new Order(1, 101, "John Doe", Arrays.asList(
        new OrderItem(products.get(0), 1),
        new OrderItem(products.get(1), 2)
    ), LocalDate.of(2024, 1, 15), OrderStatus.COMPLETED),
    
    new Order(2, 102, "Jane Smith", Arrays.asList(
        new OrderItem(products.get(3), 2),
        new OrderItem(products.get(4), 2)
    ), LocalDate.of(2024, 1, 20), OrderStatus.COMPLETED),
    
    new Order(3, 103, "Bob Wilson", Arrays.asList(
        new OrderItem(products.get(5), 5),
        new OrderItem(products.get(6), 10)
    ), LocalDate.of(2024, 2, 10), OrderStatus.COMPLETED),
    
    new Order(4, 101, "John Doe", Arrays.asList(
        new OrderItem(products.get(2), 1)
    ), LocalDate.of(2024, 2, 25), OrderStatus.PENDING)
);
```

### Sample Output

```
=== Order Processing Analysis ===

Order Summary:
Order #1: ₹51,000 (Customer: John Doe, Status: COMPLETED)
  - Laptop x1 @ ₹50,000
  - Mouse x2 @ ₹500
Order #2: ₹26,000 (Customer: Jane Smith, Status: COMPLETED)
  - Desk x2 @ ₹8,000
  - Chair x2 @ ₹5,000
Order #3: ₹1,700 (Customer: Bob Wilson, Status: COMPLETED)
  - Book x5 @ ₹300
  - Pen x10 @ ₹20
Order #4: ₹1,500 (Customer: John Doe, Status: PENDING)
  - Keyboard x1 @ ₹1,500

Top 3 Products by Revenue:
1. Laptop - ₹50,000 (1 units sold)
2. Desk - ₹16,000 (2 units sold)
3. Chair - ₹10,000 (2 units sold)

Customer Spending Analysis:
John Doe (VIP): ₹52,500 (2 orders)
  - Discount Applied: 15%
  - Final Amount: ₹44,625
Jane Smith (PREMIUM): ₹26,000 (1 order)
  - Discount Applied: 10%
  - Final Amount: ₹23,400
Bob Wilson (REGULAR): ₹1,700 (1 order)
  - Discount Applied: 0%
  - Final Amount: ₹1,700

Category Analysis:
Most Popular Category: Electronics (4 items sold)
Revenue by Category:
  - Electronics: ₹52,000
  - Furniture: ₹26,000
  - Books: ₹1,500
  - Stationery: ₹200

Orders by Price Range:
₹0 - ₹1,000: 0 orders
₹1,001 - ₹5,000: 2 orders
₹5,001+: 2 orders

Monthly Revenue:
January 2024: ₹77,000
February 2024: ₹3,200

Customers Above Average Spending:
John Doe: ₹52,500 (Average: ₹26,733)
Jane Smith: ₹26,000 (Average: ₹26,733)
```

---

## Assignment 3: Movie Recommendation Engine (Hard)

### Problem Statement
Build a sophisticated **Movie Recommendation Engine** that processes movie data, user ratings, and viewing history to generate personalized recommendations using advanced Java 8+ features including complex Stream operations, Optional chaining, custom Collectors, and functional composition.

### Functional Requirements

1. **Data Models**
   - `Movie`: `movieId`, `title`, `genres` (List\<String\>), `releaseYear`, `director`, `cast` (List\<String\>), `duration` (minutes), `language`
   - `Rating`: `userId`, `movieId`, `rating` (1-5), `timestamp`
   - `User`: `userId`, `name`, `age`, `preferredGenres` (List\<String\>), `watchHistory` (List\<Integer\> - movieIds)
   - `Review`: `reviewId`, `userId`, `movieId`, `comment`, `helpful` (count)

2. **Core Recommendation Logic**
   - **Content-Based Filtering**: Recommend movies based on user's preferred genres and watch history
   - **Collaborative Filtering**: Find similar users (based on rating patterns) and recommend their highly-rated movies
   - **Hybrid Approach**: Combine both approaches with weighted scoring

3. **Advanced Stream Operations**
   - Calculate average rating for each movie using groupingBy and averagingDouble
   - Find top-rated movies per genre (complex grouping)
   - Identify trending movies (high ratings in recent timestamps)
   - Find movie clusters (movies with similar cast/director/genre combinations)
   - Calculate user-to-user similarity scores using rating correlation

4. **Optional Handling**
   - Safely retrieve movie details, handling missing movies
   - Chain Optional operations to find "best match" from multiple criteria
   - Use Optional with flatMap for nested object navigation
   - Provide fallback recommendations when primary algorithms fail

5. **Complex Aggregations**
   - Custom Collector: Create a recommendation score collector that weighs multiple factors
   - PartitioningBy: Separate watched vs. unwatched movies
   - Multi-level grouping: Group by genre → year → rating range
   - Reduce operations: Calculate composite scores from multiple metrics

6. **Functional Composition**
   - Create reusable Predicate chains for movie filtering
   - Compose Function transformations for data processing
   - Use BiFunction for combining different recommendation strategies
   - Implement custom Comparators using comparing() and thenComparing()

7. **Performance Considerations**
   - Use parallel streams where appropriate
   - Implement lazy evaluation strategies
   - Optimize with limit() and short-circuit operations

### Constraints
- All data processing must use Stream API
- No traditional loops (for/while)
- Extensive use of Optional for null safety
- Use method references wherever possible
- Handle edge cases: new users, movies with no ratings, etc.
- Generic types must be properly used
- Custom exceptions for business logic errors

### Sample Input

```java
List<Movie> movies = Arrays.asList(
    new Movie(1, "Inception", Arrays.asList("Sci-Fi", "Thriller"), 2010, 
              "Christopher Nolan", Arrays.asList("Leonardo DiCaprio", "Tom Hardy"), 148, "English"),
    new Movie(2, "The Dark Knight", Arrays.asList("Action", "Crime", "Drama"), 2008,
              "Christopher Nolan", Arrays.asList("Christian Bale", "Heath Ledger"), 152, "English"),
    new Movie(3, "Interstellar", Arrays.asList("Sci-Fi", "Drama"), 2014,
              "Christopher Nolan", Arrays.asList("Matthew McConaughey", "Anne Hathaway"), 169, "English"),
    new Movie(4, "The Shawshank Redemption", Arrays.asList("Drama"), 1994,
              "Frank Darabont", Arrays.asList("Tim Robbins", "Morgan Freeman"), 142, "English"),
    new Movie(5, "Pulp Fiction", Arrays.asList("Crime", "Drama"), 1994,
              "Quentin Tarantino", Arrays.asList("John Travolta", "Samuel L. Jackson"), 154, "English"),
    new Movie(6, "The Matrix", Arrays.asList("Sci-Fi", "Action"), 1999,
              "Wachowski Brothers", Arrays.asList("Keanu Reeves", "Laurence Fishburne"), 136, "English"),
    new Movie(7, "Forrest Gump", Arrays.asList("Drama", "Romance"), 1994,
              "Robert Zemeckis", Arrays.asList("Tom Hanks", "Robin Wright"), 142, "English"),
    new Movie(8, "The Godfather", Arrays.asList("Crime", "Drama"), 1972,
              "Francis Ford Coppola", Arrays.asList("Marlon Brando", "Al Pacino"), 175, "English"),
    new Movie(9, "Parasite", Arrays.asList("Thriller", "Drama"), 2019,
              "Bong Joon-ho", Arrays.asList("Song Kang-ho", "Lee Sun-kyun"), 132, "Korean"),
    new Movie(10, "Spirited Away", Arrays.asList("Animation", "Fantasy"), 2001,
              "Hayao Miyazaki", Arrays.asList("Rumi Hiiragi", "Miyu Irino"), 125, "Japanese")
);

List<Rating> ratings = Arrays.asList(
    new Rating(1, 1, 5.0, System.currentTimeMillis() - 86400000),
    new Rating(1, 2, 4.5, System.currentTimeMillis() - 172800000),
    new Rating(1, 3, 5.0, System.currentTimeMillis() - 259200000),
    new Rating(2, 1, 4.0, System.currentTimeMillis() - 86400000),
    new Rating(2, 4, 5.0, System.currentTimeMillis() - 172800000),
    new Rating(2, 5, 4.5, System.currentTimeMillis() - 259200000),
    new Rating(3, 2, 5.0, System.currentTimeMillis() - 86400000),
    new Rating(3, 3, 4.0, System.currentTimeMillis() - 172800000),
    new Rating(3, 6, 4.5, System.currentTimeMillis() - 259200000),
    new Rating(4, 4, 5.0, System.currentTimeMillis() - 86400000),
    new Rating(4, 7, 4.5, System.currentTimeMillis() - 172800000),
    new Rating(4, 8, 5.0, System.currentTimeMillis() - 259200000),
    new Rating(5, 9, 5.0, System.currentTimeMillis() - 86400000),
    new Rating(5, 10, 4.5, System.currentTimeMillis() - 172800000)
);

List<User> users = Arrays.asList(
    new User(1, "Alice", 28, Arrays.asList("Sci-Fi", "Thriller"), Arrays.asList(1, 2, 3)),
    new User(2, "Bob", 35, Arrays.asList("Drama", "Crime"), Arrays.asList(4, 5)),
    new User(3, "Charlie", 24, Arrays.asList("Action", "Sci-Fi"), Arrays.asList(2, 3, 6)),
    new User(4, "Diana", 42, Arrays.asList("Drama", "Romance"), Arrays.asList(4, 7, 8)),
    new User(5, "Eve", 31, Arrays.asList("Thriller", "Animation"), Arrays.asList(9, 10))
);

List<Review> reviews = Arrays.asList(
    new Review(1, 1, 1, "Mind-bending masterpiece!", 45),
    new Review(2, 2, 4, "Powerful and moving story", 38),
    new Review(3, 3, 6, "Revolutionary sci-fi", 52),
    new Review(4, 1, 2, "Best superhero movie ever", 67),
    new Review(5, 4, 8, "Timeless classic", 89)
);
```

### Sample Output

```
=== Movie Recommendation Engine ===

Recommendations for User: Alice (Age: 28)
Preferred Genres: [Sci-Fi, Thriller]
Watch History: Inception, The Dark Knight, Interstellar

--- Content-Based Recommendations ---
1. The Matrix (Score: 92.5)
   Genres: [Sci-Fi, Action]
   Match Reason: 2 matching genres with preferences
   Avg Rating: 4.5 ★
   Similar to watched: Inception (same genre)

2. The Godfather (Score: 78.3)
   Genres: [Crime, Drama]
   Match Reason: Popular among similar users
   Avg Rating: 5.0 ★
   Similar to watched: The Dark Knight (director style match)

3. Parasite (Score: 75.8)
   Genres: [Thriller, Drama]
   Match Reason: 1 matching genre with preferences
   Avg Rating: 5.0 ★
   Trending: High recent ratings

--- Collaborative Recommendations ---
Similar Users Found: Charlie (Similarity Score: 0.87)
Charlie also highly rated:
1. The Matrix (Rating: 4.5 ★)
   - Not yet watched by Alice
   - 78% of similar users loved this

--- Hybrid Recommendations (Top 5) ---
1. The Matrix - Final Score: 89.4
   Content Score: 92.5 | Collaborative Score: 86.3
   
2. Parasite - Final Score: 82.1
   Content Score: 75.8 | Collaborative Score: 88.4
   
3. The Godfather - Final Score: 78.3
   Content Score: 78.3 | Collaborative Score: N/A

=== Genre-wise Top Rated Movies ===
Sci-Fi:
  1. Inception (5.0 ★)
  2. Interstellar (4.67 ★)
  3. The Matrix (4.5 ★)

Drama:
  1. The Shawshank Redemption (5.0 ★)
  2. The Godfather (5.0 ★)
  3. The Dark Knight (4.75 ★)

Thriller:
  1. Parasite (5.0 ★)
  2. Inception (5.0 ★)

=== Advanced Analytics ===

Movie Clusters (By Director):
Christopher Nolan's Movies:
  - Inception, The Dark Knight, Interstellar
  - Average Rating: 4.78 ★
  - Total Views: 8
  - Recommendation: If you liked one, try others!

Trending Movies (Last 7 Days):
1. Parasite (Avg: 5.0 ★, 1 recent ratings)
2. Inception (Avg: 4.5 ★, 2 recent ratings)
3. The Matrix (Avg: 4.5 ★, 1 recent rating)

User Similarity Matrix:
Alice ↔ Charlie: 87% similar (both love Sci-Fi)
Alice ↔ Bob: 62% similar (some genre overlap)
Bob ↔ Diana: 78% similar (both love Drama)

Multi-level Grouping - Movies by Genre → Year → Rating:
Sci-Fi → 2010-2020 → High (4.5+): [Inception, Interstellar]
Drama → 1990-2000 → High (4.5+): [The Shawshank Redemption, Forrest Gump]

=== Personalization Insights ===

For Alice:
- Genre Affinity: Sci-Fi (100%), Thriller (67%), Action (33%)
- Preferred Era: 2008-2014
- Favorite Directors: Christopher Nolan (watched 3 movies)
- Average Rating Given: 4.83 ★
- Watch Pattern: Prefers longer movies (avg 156 mins)

Recommendations Strategy Used:
✓ Content-Based Filtering (60% weight)
✓ Collaborative Filtering (30% weight)
✓ Trending Factor (10% weight)

Edge Cases Handled:
✓ New user detection
✓ Movies with insufficient ratings (fallback to global avg)
✓ Empty genre preferences (used watch history)
✓ Language preference applied (English priority)

=== Optional Chain Examples ===

Safely retrieving best match for Alice:
1. Checking preferred genres... ✓ Found: Sci-Fi
2. Finding highest-rated in Sci-Fi... ✓ Found: Inception
3. Checking if already watched... ✓ Yes, finding next...
4. Next best unwatched... ✓ Found: The Matrix

Movie Details for ID 999 (Missing Movie):
Result: Optional.empty → Fallback applied
Recommendation: Showing popular movies instead
```

### Expected Implementation Details

Your solution should include:

1. **Custom Collectors**
```java
// Example: Recommendation score collector
Collector<Movie, ?, Double> recommendationScoreCollector()
```

2. **Optional Chaining**
```java
// Example: Safe navigation
Optional<Movie> findBestMatch(User user) {
    return getPreferredGenre(user)
        .flatMap(this::findHighestRatedInGenre)
        .filter(movie -> !user.hasWatched(movie))
        .or(() -> getFallbackRecommendation());
}
```

3. **Complex Stream Operations**
```java
// Multi-level grouping
Map<String, Map<Integer, List<Movie>>> genreYearRatingMap = movies.stream()
    .collect(groupingBy(
        Movie::getGenre,
        groupingBy(
            m -> m.getReleaseYear() / 10 * 10,
            filtering(m -> getAvgRating(m) > 4.0, toList())
        )
    ));
```

4. **Functional Composition**
```java
// Reusable predicates
Predicate<Movie> isSciFi = m -> m.getGenres().contains("Sci-Fi");
Predicate<Movie> recentMovie = m -> m.getReleaseYear() > 2010;
Predicate<Movie> highRated = m -> getAvgRating(m) >= 4.5;

List<Movie> topSciFiMovies = movies.stream()
    .filter(isSciFi.and(recentMovie).and(highRated))
    .collect(toList());
```

5. **Generic Methods**
```java
// Generic similarity calculator
<T> double calculateSimilarity(T item1, T item2, 
    Function<T, List<String>> attributeExtractor)
```

---

## Evaluation Criteria

### Easy Assignment (30 points)
- Basic Stream operations: 10 points
- Lambda expressions: 5 points
- Optional usage: 5 points
- Code readability: 5 points
- Edge case handling: 5 points

### Medium Assignment (35 points)
- Advanced Stream operations: 10 points
- Custom Collectors: 8 points
- Optional chaining: 7 points
- Functional programming concepts: 5 points
- Code organization: 5 points

### Hard Assignment (35 points)
- Complex Stream operations: 10 points
- Functional composition: 8 points
- Generic programming: 7 points
- Performance optimization: 5 points
- Overall design: 5 points

## Submission Guidelines

1. Create separate packages for each assignment
2. Include comprehensive JavaDoc comments
3. Write unit tests (you can use simple assertions without JUnit)
4. Handle all edge cases gracefully
5. Follow Java naming conventions
6. Use appropriate access modifiers
7. Demonstrate knowledge of Collections Framework
8. Proper exception handling where needed

## Learning Outcomes

After completing these assignments, you will be proficient in:
- Lambda expressions and method references
- Stream API (intermediate and terminal operations)
- Optional class for null safety
- Functional interfaces (Predicate, Function, Consumer, Supplier)
- Custom Collectors
- Generic programming
- Functional composition and method chaining
- Exception handling in functional style
- Collections Framework with Java 8 enhancements

---

**Note**: These assignments progressively increase in complexity. Start with the easy assignment to build foundation, move to medium for practical applications, and tackle hard assignment for advanced mastery of Java 8+ features.
