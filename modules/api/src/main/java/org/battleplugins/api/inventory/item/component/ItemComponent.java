package org.battleplugins.api.inventory.item.component;

import org.battleplugins.api.inventory.item.ItemStack;

import java.util.Optional;

/**
 * Represents a component that can be added to an item.
 *
 * @param <T> the component able to be modified
 */
public interface ItemComponent<T> {

    void applyComponent(ItemStack itemStack, T value);

    Optional<T> getValue(ItemStack itemStack);
}
