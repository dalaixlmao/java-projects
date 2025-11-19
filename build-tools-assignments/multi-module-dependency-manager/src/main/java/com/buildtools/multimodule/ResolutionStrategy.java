package com.buildtools.multimodule;

/**
 * Enum for conflict resolution strategies
 */
public enum ResolutionStrategy {
    NEAREST_WINS,      // Shortest path wins
    HIGHEST_VERSION,   // Highest version wins
    DECLARED_FIRST     // First declared wins
}
