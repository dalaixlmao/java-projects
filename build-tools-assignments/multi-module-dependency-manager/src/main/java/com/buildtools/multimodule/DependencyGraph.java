package com.buildtools.multimodule;

import java.util.*;

/**
 * Represents the complete dependency graph
 */
public class DependencyGraph {
    private Map<String, Set<Dependency>> graph;

    public DependencyGraph() {
        this.graph = new HashMap<>();
    }

    // TODO: Add methods to build and query the graph

    /**
     * TODO: Implement method to add a dependency
     */
    public void addDependency(String module, Dependency dependency) {
        // TODO: Add to graph
    }

    /**
     * TODO: Implement method to get all dependencies for a module
     */
    public Set<Dependency> getDependencies(String module) {
        return graph.getOrDefault(module, new HashSet<>());
    }
}
