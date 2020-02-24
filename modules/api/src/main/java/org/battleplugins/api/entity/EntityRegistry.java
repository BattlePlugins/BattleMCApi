package org.battleplugins.api.entity;

import org.battleplugins.api.Platform;
import org.battleplugins.api.entity.component.EntityComponent;
import org.battleplugins.api.util.Identifier;

import java.util.*;

/**
 * A registry containing all the registered entities.
 *
 * @param <T> the platform implementation
 */
public abstract class EntityRegistry<T> {

    private Map<EntityType, Set<Class<? extends EntityComponent>>> validComponents = new HashMap<>();
    private Map<Class<? extends EntityComponent<?>>, EntityComponent<?>> entityComponents = new HashMap<>();

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

    Set<Class<? extends EntityComponent>> validComponentsFor(EntityType type) {
        return validComponents.computeIfAbsent(type, set -> new HashSet<>());
    }

    /**
     * Returns an entity component instance from
     * the given component class
     *
     * @param componentClass the component class
     * @param <U> the value
     * @return an entity component instance
     * @throws IllegalArgumentException if the class is not registered
     */
    <U> U getEntityComponent(Class<U> componentClass) throws IllegalArgumentException {
        if (!entityComponents.containsKey(componentClass))
            throw new IllegalArgumentException("Component class " + componentClass + " not registered!");


        return componentClass.cast(entityComponents.get(componentClass));
    }

    public <U extends EntityComponent<?>> void registerComponent(Class<U> component, U componentImpl) {
        for (EntityType type : componentImpl.getValidEntityTypes()) {
            Set<Class<? extends EntityComponent>> components = validComponents.getOrDefault(type, new HashSet<>());
            components.add(component);
            validComponents.put(type, components);
        }

        entityComponents.put(component, componentImpl);
    }

    /**
     * Gets the current entity registry
     *
     * @return the current entity registry
     */
    public static EntityRegistry<?> get() {
        return Platform.getRegistry().getEntityRegistry();
    }
}
