# Collections Framework - Programming Assignments

## Assignment 1 (Easy): Contact Book Manager

### Problem Statement
Build a simple contact book application that allows users to store and manage contact information. The application should support adding, viewing, searching, and deleting contacts.

### Functional Requirements

1. **Add Contact**
   - Add a new contact with name, phone number, and email
   - Prevent duplicate phone numbers
   - Display appropriate message on successful addition

2. **View All Contacts**
   - Display all contacts in the order they were added
   - Show name, phone number, and email for each contact
   - If no contacts exist, display "No contacts available"

3. **Search Contact**
   - Search for a contact by phone number
   - Display contact details if found
   - Display "Contact not found" if phone number doesn't exist

4. **Delete Contact**
   - Delete a contact by phone number
   - Display success message if contact is deleted
   - Display "Contact not found" if phone number doesn't exist

5. **Exit**
   - Exit the application

### Constraints
- Phone number must be exactly 10 digits
- Name cannot be empty
- Email must contain '@' symbol
- Maximum 100 contacts can be stored

### Sample Input/Output

```
Welcome to Contact Book Manager!
1. Add Contact
2. View All Contacts
3. Search Contact
4. Delete Contact
5. Exit
Enter your choice: 1

Enter name: John Doe
Enter phone number: 9876543210
Enter email: john@example.com
Contact added successfully!

Enter your choice: 1

Enter name: Jane Smith
Enter phone number: 9876543210
Enter email: jane@example.com
Error: Phone number already exists!

Enter your choice: 2

All Contacts:
1. Name: John Doe, Phone: 9876543210, Email: john@example.com

Enter your choice: 3

Enter phone number to search: 9876543210
Contact Found:
Name: John Doe, Phone: 9876543210, Email: john@example.com

Enter your choice: 4

Enter phone number to delete: 9876543210
Contact deleted successfully!

Enter your choice: 2

No contacts available

Enter your choice: 5

Thank you for using Contact Book Manager!
```

### Implementation Guidelines
- Use `HashMap<String, Contact>` where key is phone number and value is Contact object
- Create a Contact class with fields: name, phoneNumber, email
- Use Scanner for input
- Implement proper validation for all inputs

---

## Assignment 2 (Medium): Library Management System

### Problem Statement
Design a library management system that manages books and members. The system should handle book inventory, member registration, book borrowing, and book returns with proper tracking.

### Functional Requirements

1. **Add Book**
   - Add a new book with book ID, title, author, and total copies
   - Book ID must be unique
   - Display success message

2. **Add Member**
   - Add a new member with member ID and name
   - Member ID must be unique
   - Display success message

3. **Borrow Book**
   - Allow a member to borrow a book using member ID and book ID
   - Check if book is available (available copies > 0)
   - Check if member exists
   - Track which member has borrowed which books
   - A member can borrow maximum 3 books at a time
   - Display success or error message

4. **Return Book**
   - Allow a member to return a book using member ID and book ID
   - Verify that the member actually borrowed this book
   - Increment available copies
   - Display success or error message

5. **View Available Books**
   - Display all books that have at least one copy available
   - Show book ID, title, author, and available copies

6. **View Member Books**
   - Display all books currently borrowed by a specific member
   - Show book details for each borrowed book

7. **Exit**

### Constraints
- Book ID and Member ID must be positive integers
- Total copies of a book cannot exceed 10
- Maximum 50 books and 50 members can be registered
- Member names and book titles cannot be empty

### Sample Input/Output

```
Library Management System
1. Add Book
2. Add Member
3. Borrow Book
4. Return Book
5. View Available Books
6. View Member Books
7. Exit
Enter your choice: 1

Enter Book ID: 101
Enter Title: The Great Gatsby
Enter Author: F. Scott Fitzgerald
Enter Total Copies: 3
Book added successfully!

Enter your choice: 2

Enter Member ID: 1001
Enter Member Name: Alice Johnson
Member added successfully!

Enter your choice: 3

Enter Member ID: 1001
Enter Book ID: 101
Book borrowed successfully!

Enter your choice: 5

Available Books:
Book ID: 101, Title: The Great Gatsby, Author: F. Scott Fitzgerald, Available: 2

Enter your choice: 3

Enter Member ID: 1001
Enter Book ID: 101
Error: Book already borrowed by this member!

Enter your choice: 6

Enter Member ID: 1001
Books borrowed by Alice Johnson:
1. Book ID: 101, Title: The Great Gatsby, Author: F. Scott Fitzgerald

Enter your choice: 4

Enter Member ID: 1001
Enter Book ID: 101
Book returned successfully!

Enter your choice: 5

Available Books:
Book ID: 101, Title: The Great Gatsby, Author: F. Scott Fitzgerald, Available: 3

Enter your choice: 7

Thank you for using Library Management System!
```

### Implementation Guidelines
- Use `HashMap<Integer, Book>` for books (key: bookId)
- Use `HashMap<Integer, Member>` for members (key: memberId)
- Use `HashMap<Integer, ArrayList<Integer>>` to track borrowed books (key: memberId, value: list of bookIds)
- Create Book class with fields: bookId, title, author, totalCopies, availableCopies
- Create Member class with fields: memberId, name
- Implement proper validation and error messages

---

## Assignment 3 (Hard): Student Grade Management System

### Problem Statement
Build a comprehensive student grade management system that handles student enrollment, course registration, grade assignment, and generates various reports including GPA calculation, honor roll, and course-wise statistics.

### Functional Requirements

1. **Add Student**
   - Add a student with student ID, name, and department
   - Student ID must be unique
   - Display success message

2. **Add Course**
   - Add a course with course code, course name, and credits
   - Course code must be unique
   - Credits must be between 1 and 4
   - Display success message

3. **Enroll Student in Course**
   - Enroll a student in a course using student ID and course code
   - Verify both student and course exist
   - A student can enroll in maximum 6 courses
   - A student cannot enroll in the same course twice
   - Display success or error message

4. **Assign Grade**
   - Assign a letter grade (A, B, C, D, F) to a student for a specific course
   - Verify student is enrolled in the course
   - Update the grade if already exists
   - Display success or error message

5. **View Student Report**
   - Display all courses a student is enrolled in with grades
   - Calculate and display SGPA (Semester Grade Point Average)
   - Grade points: A=4.0, B=3.0, C=2.0, D=1.0, F=0.0
   - SGPA = (Sum of (Grade Points × Credits)) / (Total Credits)
   - Format SGPA to 2 decimal places

6. **View Course Statistics**
   - For a given course, display:
     - Total enrolled students
     - Number of students with each grade (A, B, C, D, F)
     - Average grade point for the course

7. **View Top Performers**
   - Display top N students based on SGPA
   - Show student ID, name, department, and SGPA
   - Students must have grades in at least 3 courses to qualify
   - Sort in descending order of SGPA

8. **View Students by Department**
   - Display all students in a specific department
   - Show student ID, name, and SGPA (if applicable)
   - Sort by student name alphabetically

9. **Exit**

### Constraints
- Student ID and Course Code are strings (alphanumeric)
- Maximum 100 students and 30 courses
- Credits must be between 1 and 4
- Valid grades: A, B, C, D, F only
- Names and departments cannot be empty
- Top N cannot exceed total eligible students

### Sample Input/Output

```
Student Grade Management System
1. Add Student
2. Add Course
3. Enroll Student in Course
4. Assign Grade
5. View Student Report
6. View Course Statistics
7. View Top Performers
8. View Students by Department
9. Exit
Enter your choice: 1

Enter Student ID: S101
Enter Name: John Smith
Enter Department: Computer Science
Student added successfully!

Enter your choice: 1

Enter Student ID: S102
Enter Name: Emma Wilson
Enter Department: Computer Science
Student added successfully!

Enter your choice: 2

Enter Course Code: CS101
Enter Course Name: Data Structures
Enter Credits: 4
Course added successfully!

Enter your choice: 2

Enter Course Code: CS102
Enter Course Name: Algorithms
Enter Credits: 3
Course added successfully!

Enter your choice: 3

Enter Student ID: S101
Enter Course Code: CS101
Student enrolled successfully!

Enter your choice: 3

Enter Student ID: S101
Enter Course Code: CS102
Student enrolled successfully!

Enter your choice: 3

Enter Student ID: S102
Enter Course Code: CS101
Student enrolled successfully!

Enter your choice: 4

Enter Student ID: S101
Enter Course Code: CS101
Enter Grade (A/B/C/D/F): A
Grade assigned successfully!

Enter your choice: 4

Enter Student ID: S101
Enter Course Code: CS102
Enter Grade (A/B/C/D/F): B
Grade assigned successfully!

Enter your choice: 4

Enter Student ID: S102
Enter Course Code: CS101
Enter Grade (A/B/C/D/F): A
Grade assigned successfully!

Enter your choice: 5

Enter Student ID: S101

Student Report for John Smith (S101)
Department: Computer Science

Courses Enrolled:
1. Course: CS101 - Data Structures (4 credits) - Grade: A (4.0)
2. Course: CS102 - Algorithms (3 credits) - Grade: B (3.0)

SGPA: 3.57

Enter your choice: 6

Enter Course Code: CS101

Course Statistics for CS101 - Data Structures:
Total Enrolled Students: 2
Grade Distribution:
A: 2 students
B: 0 students
C: 0 students
D: 0 students
F: 0 students
Average Grade Point: 4.00

Enter your choice: 7

Enter number of top performers: 2

Top Performers:
1. S101 - John Smith (Computer Science) - SGPA: 3.57
2. S102 - Emma Wilson (Computer Science) - SGPA: 4.00

(Note: S102 needs at least 3 courses with grades to qualify, so only S101 would appear if constraint is enforced)

Enter your choice: 8

Enter Department Name: Computer Science

Students in Computer Science:
1. S101 - Emma Wilson - SGPA: 4.00
2. S102 - John Smith - SGPA: 3.57

Enter your choice: 9

Thank you for using Student Grade Management System!
```

### Implementation Guidelines
- Use `HashMap<String, Student>` for students (key: studentId)
- Use `HashMap<String, Course>` for courses (key: courseCode)
- Use `HashMap<String, ArrayList<String>>` for student enrollments (key: studentId, value: list of courseCodes)
- Use nested HashMap or custom structure for grades: `HashMap<String, HashMap<String, String>>` (studentId -> courseCode -> grade)
- Create Student class with fields: studentId, name, department
- Create Course class with fields: courseCode, courseName, credits
- Implement a method to calculate SGPA for a student
- Implement a method to convert letter grade to grade point
- For sorting students, create a custom comparison logic
- Implement proper validation for all inputs and operations

### Additional Implementation Notes
- When displaying students by department, sort alphabetically by name
- When displaying top performers, sort by SGPA in descending order
- Handle edge cases like calculating SGPA when no grades are assigned
- Provide clear error messages for all validation failures
