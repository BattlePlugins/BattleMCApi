package org.battleplugins.api;

import org.battleplugins.api.registry.entity.EntityRegistry;
import org.battleplugins.api.registry.inventory.ItemRegistry;
import org.battleplugins.api.registry.world.BlockRegistry;
import org.battleplugins.api.registry.world.DirectionRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * A class containing the bulk of registrations for various
 * aspects of this API.
 */
public abstract class Registry {

    protected List<Class<?>> builders = new ArrayList<>();

    /**
     * Gets a builder from the
     * given builder class
     *
     * @param builderClass the builder class
     * @param <T> the value
     * @return an builder instance
     * @throws IllegalArgumentException if the class is not registered
     */
    public <T> T getBuilder(Class<T> builderClass) {
        for (Class<?> builderClazz : builders) {
            if (!builderClazz.isAssignableFrom(builderClass))
                continue;

            try {
                return builderClass.cast(builderClazz.newInstance());
            } catch (InstantiationException | IllegalAccessException ex) {
                throw new IllegalArgumentException("Builder class " + builderClass + " was unable to be instantiated!");
            }
        }

        throw new IllegalArgumentException("Builder class " + builderClass + " not registered!");
    }

    public abstract ItemRegistry getItemRegistry();

    public abstract BlockRegistry getBlockRegistry();

    public abstract EntityRegistry getEntityRegistry();

    public abstract DirectionRegistry getDirectionRegistry();
}
