package com.buildtools.buildlifecycle;

import java.util.List;

/**
 * Represents the build configuration from JSON file
 */
public class BuildConfiguration {
    private String project;
    private List<Phase> phases;

    public BuildConfiguration() {}

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }
}
