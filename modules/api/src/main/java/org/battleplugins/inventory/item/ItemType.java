package org.battleplugins.inventory.item;

import org.battleplugins.util.NamespacedKey;

/**
 * Represents an item type.
 */
public interface ItemType {

    /**
     * The item identifier of the item
     *
     * @return the item identifier of the item
     */
    default String getIdentifier() {
        return getKey().toString();
    }

    /**
     * The full namespaced key of the item
     *
     * @return the full namespaced key of the item
     */
    NamespacedKey getKey();

    /**
     * The maximum size this item can stack to
     *
     * @return the maximum size this item can stack to
     */
    int getMaximumStackSize();

    /**
     * If the item can be stacked
     *
     * @return if the item can be stacked
     */
    default boolean isStackable() {
        return getMaximumStackSize() > 1;
    }
}
