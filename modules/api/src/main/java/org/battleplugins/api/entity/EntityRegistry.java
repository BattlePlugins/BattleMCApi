package org.battleplugins.api.entity;

import org.battleplugins.api.Platform;
import org.battleplugins.api.entity.component.EntityComponent;
import org.battleplugins.api.entity.component.ageable.BabyComponent;
import org.battleplugins.api.util.NamespacedKey;

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
public abstract class EntityRegistry<T> {

    public static final EntityRegistry<?> REGISTRY = Platform.getPlatform().getRegistry().getEntityRegistry();

    private static Map<EntityType, Set<Class<? extends EntityComponent>>> validComponents = new HashMap<>();

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
     * {@link NamespacedKey}. Returns empty if the
     * entity could not be found. The only reason this should
     * be used is if an entity type needs to be obtained
     * from a string, an item is not in the {@link EntityTypes}
     * class, or if a modded entity (Sponge) needs to be obtained.
     *
     * @param namespacedKey the given namespaced key
     * @return the entity type from the given namespaced key
     */
    public abstract Optional<EntityType> fromKey(NamespacedKey namespacedKey);

    Set<Class<? extends EntityComponent>> validComponentsFor(EntityType type) {
        return validComponents.computeIfAbsent(type, set -> new HashSet<>());
    }

    static {
        registerComponent(BabyComponent.class);
    }

    private static void registerComponent(Class<? extends EntityComponent> componentClass) {
        EntityComponent<?> component = Platform.getPlatform().getRegistry().getEntityComponent(componentClass);
        for (EntityType type : component.getValidEntityTypes()) {
            Set<Class<? extends EntityComponent>> components = validComponents.getOrDefault(type, new HashSet<>());
            components.add(componentClass);
            validComponents.put(type, components);
        }
    }
}
