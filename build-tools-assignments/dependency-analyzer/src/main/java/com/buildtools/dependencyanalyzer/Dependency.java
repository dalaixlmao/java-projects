package com.buildtools.dependencyanalyzer;

/**
 * Model class representing a Maven/Gradle dependency
 */
public class Dependency {
    private String groupId;
    private String artifactId;
    private String version;
    private String scope;

    // TODO: Add constructors
    public Dependency() {}

    public Dependency(String groupId, String artifactId, String version, String scope) {
        // TODO: Initialize fields
    }

    // TODO: Add getters and setters
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        // TODO: Default to "compile" if scope is null or empty
        this.scope = scope;
    }

    /**
     * TODO: Implement coordinate method
     * @return String in format "groupId:artifactId"
     */
    public String getCoordinate() {
        // TODO: Return groupId:artifactId
        return "";
    }

    @Override
    public String toString() {
        // TODO: Implement toString for formatted output
        return String.format("%s:%s:%s", groupId, artifactId, version);
    }
}
