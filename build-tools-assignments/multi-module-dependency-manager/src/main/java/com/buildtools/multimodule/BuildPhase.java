package com.buildtools.multimodule;

import java.util.List;

/**
 * Represents a phase in the build sequence
 */
public class BuildPhase {
    private int phaseNumber;
    private List<String> modules; // Modules that can build in parallel

    // TODO: Add constructors, getters, and setters
}
