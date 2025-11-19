package com.buildtools.dependencyanalyzer;

/**
 * Model class representing project information from build file
 */
public class ProjectInfo {
    private String groupId;
    private String artifactId;
    private String version;

    // TODO: Add constructors, getters, and setters

    public ProjectInfo() {}

    public ProjectInfo(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

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

    @Override
    public String toString() {
        return String.format("%s:%s:%s", groupId, artifactId, version);
    }
}
