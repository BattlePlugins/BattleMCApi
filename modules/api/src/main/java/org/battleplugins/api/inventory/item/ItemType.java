package org.battleplugins.api.inventory.item;

import org.battleplugins.api.util.Identifier;

/**
 * Represents an item type.
 */
public interface ItemType {

    /**
     * The item identifier of the item
     *
     * @return the item identifier of the item
     */
    Identifier getIdentifier();

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
