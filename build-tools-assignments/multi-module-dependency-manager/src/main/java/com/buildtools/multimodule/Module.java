package com.buildtools.multimodule;

import java.util.List;

/**
 * Represents a module in the multi-module project
 */
public class Module {
    private String name;
    private String version;
    private List<Dependency> dependencies;

    // TODO: Add constructors, getters, and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }
}
