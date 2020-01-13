package org.battleplugins.api.entity;

import org.battleplugins.api.Platform;
import org.battleplugins.api.entity.component.EntityComponent;
import org.battleplugins.api.util.NamespacedKey;

import java.util.Set;

public interface EntityType {

    /**
     * The identifier of this entity type
     *
     * @return the identifier of this entity type
     */
    default String getIdentifier() {
        return getKey().toString();
    }

    /**
     * The full {@link NamespacedKey} of this entity type
     *
     * @return the full namespaced key of this entity type
     */
    NamespacedKey getKey();

    /**
     * A set of the provided (allowed) {@link EntityComponent}s
     * that can be applied to this entity type
     *
     * @return a set of the allowed entity components
     */
    default Set<Class<? extends EntityComponent>> getProvidedComponents() {
        return Platform.getPlatform().getRegistry().getEntityRegistry().validComponentsFor(this);
    }
}
