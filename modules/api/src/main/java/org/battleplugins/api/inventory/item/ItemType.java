package org.battleplugins.api.inventory.item;

import org.battleplugins.api.util.Identifiable;

import java.util.Objects;

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

    /**
     * If this item type is equal to the
     * given type
     *
     * @param type the item type to check equality for
     * @return if this item type is equal to the given type
     */
    default boolean is(ItemType type) {
        return Objects.equals(this, type);
    }
}
