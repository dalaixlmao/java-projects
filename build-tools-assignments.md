# Build Tools (Maven/Gradle) - Practice Assignments

## Assignment 1: Dependency Analyzer (Easy)

### Problem Statement
Create a **Dependency Analyzer** that reads a simplified Maven POM file or Gradle build file and extracts all direct dependencies with their versions. The tool should be able to filter dependencies by scope (compile, test, runtime) and identify duplicate dependencies.

### Learning Objectives
- Understand dependency declaration in build tools
- Work with XML/text file parsing
- Practice Collections Framework and Java 8 Streams
- Handle file I/O operations

### Functional Requirements

1. **Parse Build File**
   - Read a simplified POM.xml or build.gradle file
   - Extract dependency information (groupId, artifactId, version, scope)
   - Handle missing or optional fields gracefully

2. **Dependency Analysis**
   - List all dependencies grouped by scope (compile, test, runtime, provided)
   - Identify duplicate dependencies (same groupId and artifactId but different versions)
   - Count total dependencies per scope

3. **Output Generation**
   - Generate a formatted report showing:
     - Total number of dependencies
     - Dependencies grouped by scope
     - List of duplicate dependencies (if any)
     - Dependencies sorted alphabetically within each scope

4. **Error Handling**
   - Handle file not found exceptions
   - Handle malformed XML/build file
   - Provide meaningful error messages

### Input Format

**Option 1: Simplified POM.xml**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.10</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.12.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.15</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
</project>
```

**Command Line Arguments:**
```
java DependencyAnalyzer --file pom.xml --format maven
```

### Expected Output

```
========================================
     DEPENDENCY ANALYSIS REPORT
========================================

Project: com.example:my-app:1.0.0

Total Dependencies: 4

Dependencies by Scope:
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

[COMPILE] - 3 dependencies
  • org.apache.commons:commons-lang3:3.12.0
  • org.springframework:spring-core:5.3.10
  • org.springframework:spring-core:5.3.15

[TEST] - 1 dependency
  • junit:junit:4.13.2

⚠️  DUPLICATE DEPENDENCIES DETECTED:
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

org.springframework:spring-core
  - Version 5.3.10 (compile)
  - Version 5.3.15 (compile)
  ⚠️  Recommendation: Use consistent version across all dependencies

========================================
Analysis completed successfully!
========================================
```

### Hints
- Use `java.nio.file.Files` for file reading
- Consider using a `Dependency` class to model each dependency
- Use `Map<String, List<Dependency>>` to group dependencies by scope
- Use Streams API for filtering and grouping operations
- For XML parsing, use simple string operations or `javax.xml.parsers.DocumentBuilder`

---

## Assignment 2: Build Lifecycle Simulator (Medium)

### Problem Statement
Create a **Build Lifecycle Simulator** that mimics how Maven/Gradle executes build phases/tasks. The system should read a build configuration file that defines custom phases/tasks with their dependencies, execute them in the correct order, and handle failures appropriately.

### Learning Objectives
- Understand build lifecycle and phase dependencies
- Implement topological sorting for dependency resolution
- Work with Generics and Custom Collections
- Practice Exception Handling and Optional
- Use Java 8+ functional programming features

### Functional Requirements

1. **Configuration Parser**
   - Read a JSON/text configuration file defining build phases
   - Each phase can have:
     - Name
     - Dependencies (other phases that must run first)
     - Duration (simulated execution time in milliseconds)
     - Success probability (to simulate failures)
     - Commands/actions to execute

2. **Dependency Resolution**
   - Build a dependency graph of phases
   - Detect circular dependencies and report errors
   - Calculate correct execution order using topological sort
   - Handle missing phase dependencies

3. **Execution Engine**
   - Execute phases in dependency order
   - Simulate execution with configurable delays
   - Track phase status (PENDING, RUNNING, SUCCESS, FAILED, SKIPPED)
   - Stop execution on failure (fail-fast mode) or continue (optional)
   - Support running specific phases (and their dependencies only)

4. **Reporting**
   - Generate execution summary with:
     - Total execution time
     - Phases executed (in order)
     - Success/Failure status for each phase
     - Execution time for each phase
     - Reasons for failures or skips

5. **Error Handling**
   - Handle circular dependency detection
   - Handle invalid configuration files
   - Handle phase execution failures
   - Provide detailed error messages with phase context

### Input Format

**build-config.json:**
```json
{
  "project": "my-awesome-project",
  "phases": [
    {
      "name": "clean",
      "dependencies": [],
      "duration": 100,
      "successProbability": 1.0,
      "description": "Clean previous build artifacts"
    },
    {
      "name": "validate",
      "dependencies": ["clean"],
      "duration": 150,
      "successProbability": 0.95,
      "description": "Validate project structure"
    },
    {
      "name": "compile",
      "dependencies": ["validate"],
      "duration": 2000,
      "successProbability": 0.9,
      "description": "Compile source code"
    },
    {
      "name": "test-compile",
      "dependencies": ["compile"],
      "duration": 1000,
      "successProbability": 1.0,
      "description": "Compile test code"
    },
    {
      "name": "test",
      "dependencies": ["test-compile"],
      "duration": 3000,
      "successProbability": 0.85,
      "description": "Run unit tests"
    },
    {
      "name": "package",
      "dependencies": ["test"],
      "duration": 500,
      "successProbability": 1.0,
      "description": "Package compiled code"
    },
    {
      "name": "verify",
      "dependencies": ["package"],
      "duration": 800,
      "successProbability": 0.95,
      "description": "Run integration tests"
    },
    {
      "name": "install",
      "dependencies": ["verify"],
      "duration": 300,
      "successProbability": 1.0,
      "description": "Install to local repository"
    }
  ]
}
```

**Command Line Arguments:**
```
java BuildLifecycleSimulator --config build-config.json --phase install --mode fail-fast
```

### Expected Output

**Scenario 1: Successful Build**
```
========================================
  BUILD LIFECYCLE SIMULATION
========================================

Project: my-awesome-project
Target Phase: install
Mode: fail-fast

Analyzing build graph...
✓ Dependency graph validated (no circular dependencies)
✓ Execution order determined: clean → validate → compile → test-compile → test → package → verify → install

Starting build execution...
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

[1/8] RUNNING clean (Clean previous build artifacts)
[1/8] ✓ clean completed successfully (102ms)

[2/8] RUNNING validate (Validate project structure)
[2/8] ✓ validate completed successfully (148ms)

[3/8] RUNNING compile (Compile source code)
[3/8] ✓ compile completed successfully (2001ms)

[4/8] RUNNING test-compile (Compile test code)
[4/8] ✓ test-compile completed successfully (999ms)

[5/8] RUNNING test (Run unit tests)
[5/8] ✓ test completed successfully (3005ms)

[6/8] RUNNING package (Package compiled code)
[6/8] ✓ package completed successfully (498ms)

[7/8] RUNNING verify (Run integration tests)
[7/8] ✓ verify completed successfully (802ms)

[8/8] RUNNING install (Install to local repository)
[8/8] ✓ install completed successfully (301ms)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
BUILD SUCCESS
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Total time: 7.856s
Phases executed: 8
Successful: 8
Failed: 0
Skipped: 0

========================================
```

**Scenario 2: Build Failure**
```
========================================
  BUILD LIFECYCLE SIMULATION
========================================

Project: my-awesome-project
Target Phase: install
Mode: fail-fast

Analyzing build graph...
✓ Dependency graph validated (no circular dependencies)
✓ Execution order determined: clean → validate → compile → test-compile → test → package → verify → install

Starting build execution...
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

[1/8] RUNNING clean (Clean previous build artifacts)
[1/8] ✓ clean completed successfully (101ms)

[2/8] RUNNING validate (Validate project structure)
[2/8] ✓ validate completed successfully (152ms)

[3/8] RUNNING compile (Compile source code)
[3/8] ✗ compile FAILED (1998ms)
      Error: Compilation failed - syntax error in Main.java:42

[4/8] ⊘ test-compile SKIPPED (dependency failed)
[5/8] ⊘ test SKIPPED (dependency failed)
[6/8] ⊘ package SKIPPED (dependency failed)
[7/8] ⊘ verify SKIPPED (dependency failed)
[8/8] ⊘ install SKIPPED (dependency failed)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
BUILD FAILED
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Total time: 2.251s
Phases executed: 3
Successful: 2
Failed: 1
Skipped: 5

Failed at: compile
Reason: Compilation failed - syntax error in Main.java:42

========================================
```

**Scenario 3: Circular Dependency Detection**
```
========================================
  BUILD LIFECYCLE SIMULATION
========================================

Project: broken-project
Target Phase: install

Analyzing build graph...
✗ ERROR: Circular dependency detected!

Dependency cycle found:
  compile → test → package → compile

Cannot execute build with circular dependencies.
Please fix the build configuration.

========================================
```

### Hints
- Create classes: `Phase`, `BuildGraph`, `ExecutionEngine`, `PhaseResult`
- Use `Map<String, Phase>` to store phases by name
- Implement topological sort using DFS or Kahn's algorithm
- Use `Optional<PhaseResult>` for phases that might not execute
- Use Streams for filtering and reporting
- Consider using `Enum` for phase status
- Use `Thread.sleep()` to simulate execution duration
- Use `Random` with success probability to simulate failures

---

## Assignment 3: Multi-Module Dependency Manager (Hard)

### Problem Statement
Create a **Multi-Module Dependency Manager** that handles complex multi-module projects (like Maven's reactor or Gradle's multi-project builds). The system should analyze inter-module dependencies, calculate optimal build order, handle version conflicts, perform transitive dependency resolution, and generate a dependency report with conflict resolution strategies.

### Learning Objectives
- Master complex dependency graph algorithms
- Implement advanced Collections and Generics usage
- Handle sophisticated exception scenarios
- Use Java 8+ features extensively (Streams, Optional, Lambda, Method References)
- Work with complex file I/O and parsing
- Implement graph traversal algorithms

### Functional Requirements

1. **Multi-Module Project Parser**
   - Parse a project structure file defining multiple modules
   - Each module has:
     - Module name and version
     - Direct dependencies (with version constraints)
     - Inter-module dependencies
     - Build order hints (optional)
   - Support for version ranges (e.g., "[1.0.0,2.0.0)", "(,1.0]", "1.5+")

2. **Dependency Graph Builder**
   - Build complete dependency graph including:
     - Direct dependencies
     - Inter-module dependencies
     - Transitive dependencies (dependencies of dependencies)
   - Support dependency scopes (compile, test, runtime, provided)
   - Handle optional dependencies

3. **Conflict Resolution**
   - Detect version conflicts in transitive dependencies
   - Implement resolution strategies:
     - Nearest wins (shortest path to dependency)
     - Highest version wins
     - Declared first wins
   - Handle scope conflicts (compile vs provided)
   - Detect incompatible version ranges

4. **Build Order Calculation**
   - Calculate correct module build order respecting inter-module dependencies
   - Detect circular dependencies between modules
   - Support parallel build groups (modules that can build simultaneously)
   - Handle diamond dependencies correctly

5. **Advanced Features**
   - Exclude specific transitive dependencies
   - Override dependency versions at root level
   - Detect unused dependencies
   - Identify security vulnerabilities (based on a vulnerability database file)
   - Generate dependency tree visualization

6. **Comprehensive Reporting**
   - Dependency tree for each module
   - Conflict resolution report
   - Build order with parallel opportunities
   - Vulnerability report
   - Suggestions for dependency optimization

### Input Format

**project-structure.json:**
```json
{
  "projectName": "enterprise-application",
  "modules": [
    {
      "name": "common-utils",
      "version": "1.0.0",
      "dependencies": [
        {
          "groupId": "org.apache.commons",
          "artifactId": "commons-lang3",
          "version": "3.12.0",
          "scope": "compile"
        },
        {
          "groupId": "com.google.guava",
          "artifactId": "guava",
          "version": "31.0-jre",
          "scope": "compile"
        }
      ]
    },
    {
      "name": "data-access",
      "version": "1.0.0",
      "dependencies": [
        {
          "module": "common-utils",
          "version": "1.0.0"
        },
        {
          "groupId": "org.apache.commons",
          "artifactId": "commons-lang3",
          "version": "3.11.0",
          "scope": "compile"
        },
        {
          "groupId": "commons-io",
          "artifactId": "commons-io",
          "version": "2.11.0",
          "scope": "compile"
        }
      ]
    },
    {
      "name": "business-logic",
      "version": "1.0.0",
      "dependencies": [
        {
          "module": "common-utils",
          "version": "1.0.0"
        },
        {
          "module": "data-access",
          "version": "1.0.0"
        },
        {
          "groupId": "com.google.guava",
          "artifactId": "guava",
          "version": "30.1-jre",
          "scope": "compile"
        }
      ]
    },
    {
      "name": "web-api",
      "version": "1.0.0",
      "dependencies": [
        {
          "module": "business-logic",
          "version": "1.0.0"
        },
        {
          "groupId": "commons-io",
          "artifactId": "commons-io",
          "version": "2.10.0",
          "scope": "compile"
        }
      ]
    }
  ],
  "dependencyManagement": {
    "org.apache.commons:commons-lang3": "3.12.0"
  }
}
```

**vulnerability-db.json:**
```json
{
  "vulnerabilities": [
    {
      "groupId": "com.google.guava",
      "artifactId": "guava",
      "affectedVersions": ["30.0-jre", "30.1-jre"],
      "severity": "HIGH",
      "cve": "CVE-2023-XXXX",
      "description": "Potential DoS vulnerability"
    }
  ]
}
```

**Command Line Arguments:**
```
java MultiModuleDependencyManager \
  --project project-structure.json \
  --vulnerabilities vulnerability-db.json \
  --strategy NEAREST_WINS \
  --report-format DETAILED
```

### Expected Output

```
╔══════════════════════════════════════════════════════════════╗
║       MULTI-MODULE DEPENDENCY ANALYSIS REPORT                ║
╚══════════════════════════════════════════════════════════════╝

Project: enterprise-application
Modules: 4
Conflict Resolution Strategy: NEAREST_WINS
Analysis Date: 2024-01-15 14:30:22

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
1. MODULE BUILD ORDER
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

✓ Dependency graph validated (no circular dependencies)

Build Sequence:

  Phase 1 (Parallel - 1 module):
    └─ common-utils:1.0.0

  Phase 2 (Parallel - 1 module):
    └─ data-access:1.0.0
    
  Phase 3 (Parallel - 1 module):
    └─ business-logic:1.0.0
    
  Phase 4 (Parallel - 1 module):
    └─ web-api:1.0.0

Estimated parallel build time reduction: 0% (sequential dependencies)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
2. DEPENDENCY TREE
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

[common-utils:1.0.0]
├─ org.apache.commons:commons-lang3:3.12.0 [compile]
└─ com.google.guava:guava:31.0-jre [compile]

[data-access:1.0.0]
├─ common-utils:1.0.0 [compile]
│  ├─ org.apache.commons:commons-lang3:3.12.0 [compile] (managed)
│  └─ com.google.guava:guava:31.0-jre [compile]
├─ org.apache.commons:commons-lang3:3.11.0 → 3.12.0 [compile] ⚠️  (conflict resolved)
└─ commons-io:commons-io:2.11.0 [compile]

[business-logic:1.0.0]
├─ common-utils:1.0.0 [compile]
│  ├─ org.apache.commons:commons-lang3:3.12.0 [compile]
│  └─ com.google.guava:guava:31.0-jre [compile]
├─ data-access:1.0.0 [compile]
│  ├─ org.apache.commons:commons-lang3:3.12.0 [compile]
│  ├─ com.google.guava:guava:31.0-jre [compile]
│  └─ commons-io:commons-io:2.11.0 [compile]
└─ com.google.guava:guava:30.1-jre → 31.0-jre [compile] ⚠️  (conflict resolved)

[web-api:1.0.0]
├─ business-logic:1.0.0 [compile]
│  ├─ common-utils:1.0.0 [compile]
│  │  ├─ org.apache.commons:commons-lang3:3.12.0 [compile]
│  │  └─ com.google.guava:guava:31.0-jre [compile]
│  ├─ data-access:1.0.0 [compile]
│  │  ├─ org.apache.commons:commons-lang3:3.12.0 [compile]
│  │  ├─ com.google.guava:guava:31.0-jre [compile]
│  │  └─ commons-io:commons-io:2.11.0 [compile]
│  └─ com.google.guava:guava:31.0-jre [compile]
└─ commons-io:commons-io:2.10.0 → 2.11.0 [compile] ⚠️  (conflict resolved)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
3. CONFLICT RESOLUTION REPORT
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Total Conflicts Detected: 3
Conflicts Resolved: 3

Details:

[1] org.apache.commons:commons-lang3
    Conflicting Versions:
      • 3.12.0 - via common-utils (depth: 1)
      • 3.11.0 - via data-access (depth: 1)
    
    Resolution: 3.12.0 (MANAGED in dependencyManagement)
    Strategy: Dependency Management Override
    
    Impact: 2 modules affected (data-access, business-logic)
    ✓ Safe - Minor version upgrade (3.11.0 → 3.12.0)

[2] com.google.guava:guava
    Conflicting Versions:
      • 31.0-jre - via common-utils (depth: 1)
      • 30.1-jre - via business-logic (depth: 1)
    
    Resolution: 31.0-jre
    Strategy: NEAREST_WINS (shorter path: common-utils)
    
    Impact: 1 module affected (business-logic)
    ⚠️  Attention Required - Major version difference
    🔒 Security: Resolves CVE-2023-XXXX (HIGH severity)

[3] commons-io:commons-io
    Conflicting Versions:
      • 2.11.0 - via data-access (depth: 2)
      • 2.10.0 - via web-api (depth: 1)
    
    Resolution: 2.11.0
    Strategy: HIGHEST_VERSION (prefer newer version)
    
    Impact: 1 module affected (web-api)
    ✓ Safe - Patch version upgrade (2.10.0 → 2.11.0)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
4. SECURITY VULNERABILITY REPORT
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

🔴 1 HIGH severity vulnerability detected

[HIGH] CVE-2023-XXXX
  Package: com.google.guava:guava
  Affected Versions: 30.0-jre, 30.1-jre
  Current Resolution: 31.0-jre ✓ (patched)
  
  Description: Potential DoS vulnerability
  
  Originally Requested By:
    • business-logic:1.0.0 (requested 30.1-jre)
  
  Status: ✓ RESOLVED by conflict resolution
  Action: No action required - already using safe version

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
5. DEPENDENCY STATISTICS
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Total Dependencies: 7 (3 unique external + 4 modules)
  • Direct: 9
  • Transitive: 5
  • Resolved: 7

By Scope:
  • compile: 7
  • test: 0
  • runtime: 0
  • provided: 0

Top External Dependencies:
  1. com.google.guava:guava:31.0-jre (used by 3 modules)
  2. org.apache.commons:commons-lang3:3.12.0 (used by 3 modules)
  3. commons-io:commons-io:2.11.0 (used by 2 modules)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
6. RECOMMENDATIONS
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

✓ Dependency management is working correctly
✓ No circular dependencies detected
✓ All security vulnerabilities resolved

Optimization Opportunities:

  1. Version Alignment
     Consider explicitly declaring guava version in business-logic
     to match common-utils (31.0-jre) for clarity.

  2. Dependency Management
     Add commons-io to dependencyManagement section to ensure
     consistent version across all modules.

  3. Build Performance
     Current structure requires sequential builds. No opportunities
     for parallel execution due to linear dependency chain.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Analysis completed in 1.23s
Report generated: /tmp/dependency-report-20240115-143022.txt

╔══════════════════════════════════════════════════════════════╗
║                    BUILD READY ✓                             ║
╚══════════════════════════════════════════════════════════════╝
```

### Advanced Scenarios to Handle

**Scenario: Circular Module Dependency**
```
ERROR: Circular dependency detected between modules!

Dependency Cycle:
  module-a:1.0.0 → module-b:1.0.0 → module-c:1.0.0 → module-a:1.0.0

Cannot determine build order. Please refactor module dependencies.

Suggestions:
  1. Extract common code into a separate base module
  2. Use dependency inversion principle
  3. Consider splitting module-b into smaller modules
```

**Scenario: Incompatible Version Ranges**
```
ERROR: Cannot resolve dependency version conflict!

Dependency: org.example:library
  • Module A requires: [2.0.0, 3.0.0) - versions 2.x only
  • Module B requires: [3.0.0, 4.0.0) - versions 3.x only

These version ranges do not overlap!

Resolution Options:
  1. Update Module A to support library 3.x
  2. Update Module B to support library 2.x
  3. Create a compatibility layer module
```

### Hints

- Design core classes:
  - `Module`, `Dependency`, `DependencyNode`, `DependencyGraph`
  - `ConflictResolver` (use Strategy pattern)
  - `VersionRange`, `Version` (implement Comparable)
  - `BuildOrderCalculator`, `VulnerabilityScanner`

- Use advanced Collections:
  - `Map<String, Module>` for module registry
  - `Map<String, Set<Version>>` for version conflicts
  - `DirectedGraph` implementation for dependency graph
  - `Queue<Module>` for topological sort

- Implement algorithms:
  - Topological sort (Kahn's or DFS)
  - Graph cycle detection (DFS with color marking)
  - Shortest path (BFS for nearest wins strategy)
  - Transitive closure for dependency resolution

- Use Streams extensively:
  ```java
  modules.stream()
      .flatMap(m -> m.getDependencies().stream())
      .collect(Collectors.groupingBy(
          Dependency::getCoordinate,
          Collectors.mapping(Dependency::getVersion, Collectors.toSet())
      ))
  ```

- Use Optional for safe navigation:
  ```java
  Optional<Version> resolvedVersion = conflictResolver
      .resolve(dependency)
      .filter(v -> isCompatible(v, requiredRange));
  ```

- Implement custom exceptions:
  - `CircularDependencyException`
  - `VersionConflictException`
  - `InvalidConfigurationException`

- Consider using recursion with memoization for transitive dependency resolution

---

## General Guidelines for All Assignments

### Testing Your Solution

1. Create multiple test cases with different scenarios
2. Test edge cases (empty files, malformed input, circular dependencies)
3. Verify output format matches expected output
4. Test error handling thoroughly

### Evaluation Criteria

- **Correctness**: Does it solve the problem correctly?
- **Code Quality**: Clean, readable, well-organized code
- **Error Handling**: Graceful handling of edge cases and errors
- **Use of Java Features**: Proper use of Collections, Streams, Optional, etc.
- **Documentation**: Clear comments and JavaDoc where appropriate
- **Performance**: Efficient algorithms and data structures

### Submission Format

```
src/
  main/
    java/
      com/buildtools/
        [Your implementation classes]
  test/
    java/
      com/buildtools/
        [Your test classes]
    resources/
      [Sample input files]
README.md (explaining how to run your solution)
```

---

## Additional Resources

- Maven Documentation: https://maven.apache.org/guides/
- Gradle Documentation: https://docs.gradle.org/
- Graph Algorithms: Topological Sort, Cycle Detection
- Java Collections Framework: https://docs.oracle.com/javase/tutorial/collections/
- Java Streams API: https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html

Happy Coding! 🚀
