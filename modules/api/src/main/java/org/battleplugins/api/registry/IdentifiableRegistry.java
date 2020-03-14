package org.battleplugins.api.registry;

import org.battleplugins.api.util.Identifiable;
import org.battleplugins.api.util.Identifier;

import java.util.Optional;

/**
 * Represents a registry that allows for
 * an identifiable type to be retrieved.
 */
public interface IdentifiableRegistry<T extends Identifiable> extends BaseRegistry {

    /**
     * Gets the identifiable object from the given
     * {@link Identifier}. Returns empty if the
     * object could not be found.
     *
     * @param identifier the given {@link Identifier}
     * @return the identifiable object
     */
    Optional<T> fromIdentifier(Identifier identifier);
}
