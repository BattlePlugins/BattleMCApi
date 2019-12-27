package org.battleplugins;

import org.battleplugins.inventory.item.ItemRegistry;
import org.battleplugins.inventory.item.component.ItemComponent;
import org.battleplugins.world.block.BlockRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * A class containing the bulk of registrations for various
 * aspects of this API.
 */
public abstract class Registry {

    protected List<Class<? extends ItemComponent>> itemComponents = new ArrayList<>();
    protected List<Class<?>> builders = new ArrayList<>();

    /**
     * Returns an item component instance from
     * the given component class
     *
     * @param componentClass the component class
     * @param <T> the value
     * @return an item component instance
     * @throws IllegalArgumentException if the class is not registered
     */
    public <T> T getItemComponent(Class<T> componentClass) throws IllegalArgumentException {
        for (Class<? extends ItemComponent> componentClazz : itemComponents) {
            if (!componentClass.isAssignableFrom(componentClazz))
                continue;

            try {
                return componentClass.cast(componentClazz.newInstance());
            } catch (InstantiationException | IllegalAccessException ex) {
                throw new IllegalArgumentException("Component class " + componentClass + " was unable to be instantiated!");
            }
        }

        throw new IllegalArgumentException("Component class " + componentClass + " not registered!");
    }

    /**
     * Returns a builder from the
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

    public abstract ItemRegistry<?> getItemRegistry();

    public abstract BlockRegistry<?> getBlockRegistry();
}
