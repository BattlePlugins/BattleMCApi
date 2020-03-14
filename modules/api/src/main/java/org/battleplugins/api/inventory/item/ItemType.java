package org.battleplugins.api.inventory.item;

import org.battleplugins.api.util.Identifiable;

/**
 * Represents an item type.
 */
public interface ItemType extends Identifiable {

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
