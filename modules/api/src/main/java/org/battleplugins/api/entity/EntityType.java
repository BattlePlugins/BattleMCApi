package org.battleplugins.api.entity;

import org.battleplugins.api.Platform;
import org.battleplugins.api.entity.component.EntityComponent;
import org.battleplugins.api.util.Identifier;

import java.util.Set;

public interface EntityType {

    /**
     * The full {@link Identifier} of this entity type
     *
     * @return the full identifier of this entity type
     */
    Identifier getIdentifier();

    /**
     * A set of the provided (allowed) {@link EntityComponent}s
     * that can be applied to this entity type
     *
     * @return a set of the allowed entity components
     */
    default Set<Class<? extends EntityComponent>> getProvidedComponents() {
        return Platform.getRegistry().getEntityRegistry().validComponentsFor(this);
    }
}
