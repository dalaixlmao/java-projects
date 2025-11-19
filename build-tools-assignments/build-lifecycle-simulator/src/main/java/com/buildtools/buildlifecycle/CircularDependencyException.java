package com.buildtools.buildlifecycle;

/**
 * Exception thrown when circular dependency is detected in build graph
 */
public class CircularDependencyException extends Exception {

    public CircularDependencyException(String message) {
        super(message);
    }

    public CircularDependencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
