package org.battleplugins.api.inventory.item;

import org.battleplugins.api.Platform;
import org.battleplugins.api.inventory.item.component.ItemComponent;
import org.battleplugins.api.util.NamespacedKey;

import java.util.*;

/**
 * A registry containing all the items based on various
 * implementations depending on the platform.
 *
 * Certain platforms base their implementations of items
 * on different values (e.g. Bukkit is Material/identifier
 * based whilst Nukkit is ID based). This class allows for
 * items to be mapped from their platform implementations as
 * well as from {@link NamespacedKey}'s.
 *
 * @param <T> the platform implementation
 */
public abstract class ItemRegistry<T> {

    private Map<Class<? extends ItemComponent>, Class<? extends ItemComponent>> itemComponents = new HashMap<>();

    /**
     * Gets the given item type from the platform
     * item
     *
     * @param item the platform item
     * @return the given item type
     */
    public abstract ItemType fromPlatformItem(T item);

    /**
     * Gets the item type from the given
     * {@link NamespacedKey}. Returns empty if the
     * item could not be found. The only reason this should
     * be used is if an item type needs to be obtained
     * from a string, an item is not in the {@link ItemTypes}
     * class, or if a modded item (Sponge) needs to be obtained.
     *
     * @param namespacedKey the given {@link NamespacedKey}
     * @return the item type from the given namespaced key
     */
    public abstract Optional<ItemType> fromKey(NamespacedKey namespacedKey);

    /**
     * Returns an item component instance from
     * the given component class
     *
     * @param componentClass the component class
     * @param <U> the value
     * @return an item component instance
     * @throws IllegalArgumentException if the class is not registered
     */
    <U> U getItemComponent(Class<U> componentClass) throws IllegalArgumentException {
        if (!itemComponents.containsKey(componentClass))
            throw new IllegalArgumentException("Component class " + componentClass + " not registered!");

        try {
            return componentClass.cast(itemComponents.get(componentClass).newInstance());
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalArgumentException("Component class " + componentClass + " was unable to be instantiated!");
        }
    }

    protected <U extends ItemComponent> void registerComponent(Class<U> component, Class<? extends U> componentImpl) {
        itemComponents.put(component, componentImpl);
    }
}
