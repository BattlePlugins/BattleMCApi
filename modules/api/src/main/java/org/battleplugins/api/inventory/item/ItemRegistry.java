package org.battleplugins.api.inventory.item;

import org.battleplugins.api.Platform;
import org.battleplugins.api.util.NamespacedKey;

import java.util.Optional;

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
public interface ItemRegistry<T> {

    ItemRegistry<?> REGISTRY = Platform.getPlatform().getRegistry().getItemRegistry();

    /**
     * Gets the given item type from the platform
     * item
     *
     * @param item the platform item
     * @return the given item type
     */
    ItemType fromPlatformItem(T item);

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
    Optional<ItemType> fromKey(NamespacedKey namespacedKey);
}
