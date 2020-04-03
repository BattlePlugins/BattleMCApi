package org.battleplugins.api.registry.entity;

import org.battleplugins.api.Platform;
import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.entity.EntityTypes;
import org.battleplugins.api.entity.component.EntityComponent;
import org.battleplugins.api.registry.ComponentRegistry;
import org.battleplugins.api.registry.IdentifiableRegistry;
import org.battleplugins.api.util.Identifier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * A registry containing all the registered entities.
 *
 * @param <T> the platform implementation
 */
public abstract class EntityRegistry<T> extends ComponentRegistry<EntityComponent<?>> implements IdentifiableRegistry<EntityType> {

    private Map<EntityType, Set<Class<? extends EntityComponent<?>>>> validComponents = new HashMap<>();

    /**
     * Gets the given {@link EntityType} from the platform
     * entity
     *
     * @param type the platform entity type
     * @return the given entity type
     */
    public abstract EntityType fromPlatformType(T type);

    /**
     * Gets the {@link EntityType} from the given
     * {@link Identifier}. Returns empty if the
     * entity could not be found. The only reason this should
     * be used is if an entity type needs to be obtained
     * from a string, an item is not in the {@link EntityTypes}
     * class, or if a modded entity (Sponge) needs to be obtained.
     *
     * @param identifier the identifier
     * @return the entity type from the given identifier
     */
    public abstract Optional<EntityType> fromIdentifier(Identifier identifier);

    public Set<Class<? extends EntityComponent<?>>> validComponentsFor(EntityType type) {
        return validComponents.computeIfAbsent(type, set -> new HashSet<>());
    }

    @Override
    public <V extends U, U extends EntityComponent<?>> void registerComponent(Class<U> componentClass, V component) {
        super.registerComponent(componentClass, component);

        for (EntityType type : component.getValidEntityTypes()) {
            Set<Class<? extends EntityComponent<?>>> components = validComponents.getOrDefault(type, new HashSet<>());
            components.add(componentClass);
            validComponents.put(type, components);
        }
    }

    /**
     * Gets the current entity registry
     *
     * @return the current entity registry
     */
    public static EntityRegistry get() {
        return Platform.getRegistry().getEntityRegistry();
    }
}
