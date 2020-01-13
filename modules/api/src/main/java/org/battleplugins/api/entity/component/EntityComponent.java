package org.battleplugins.api.entity.component;

import org.battleplugins.api.component.Component;
import org.battleplugins.api.entity.Entity;
import org.battleplugins.api.entity.EntityType;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a component that can be added to an entity.
 *
 * @param <V> the component able to be modified
 */
public interface EntityComponent<V> extends Component<Entity, V> {

    /**
     * A set of the valid {@link EntityType}s this component
     * can be applied to
     *
     * @return a set of valid entity types this component can be applied to
     */
    default Set<EntityType> getValidEntityTypes() {
        return new HashSet<>();
    }
}
