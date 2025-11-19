package com.buildtools.multimodule;

/**
 * Represents a dependency (external or inter-module)
 */
public class Dependency {
    private String groupId;
    private String artifactId;
    private String version;
    private String scope;
    private boolean isModule; // true if inter-module dependency

    // TODO: Add constructors, getters, setters

    /**
     * TODO: Implement coordinate method
     * @return "groupId:artifactId" or module name for inter-module dependencies
     */
    public String getCoordinate() {
        return "";
    }
}
