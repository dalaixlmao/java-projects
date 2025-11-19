package com.buildtools.dependencyanalyzer;

import java.util.List;

/**
 * Model class representing a group of duplicate dependencies
 */
public class DuplicateGroup {
    private String coordinate; // groupId:artifactId
    private List<Dependency> dependencies;

    public DuplicateGroup() {}

    public DuplicateGroup(String coordinate, List<Dependency> dependencies) {
        this.coordinate = coordinate;
        this.dependencies = dependencies;
    }

    // TODO: Add getters and setters

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }
}
