package org.battleplugins.api.registry;

/**
 * Represents a class that holds registrations
 * for various different values.
 */
public interface BaseRegistry {

    /**
     * If the registry is closed
     *
     * @return if the registry is closed
     */
    boolean isClosed();

    /**
     * Closes the registry
     */
    void close();
}
