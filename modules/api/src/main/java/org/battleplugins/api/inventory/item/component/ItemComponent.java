package org.battleplugins.api.inventory.item.component;

import org.battleplugins.api.component.Component;
import org.battleplugins.api.inventory.item.ItemStack;

import java.util.Optional;

/**
 * Represents a component that can be added to an item.
 *
 * @param <V> the component able to be modified
 */
public interface ItemComponent<V> extends Component<ItemStack, V> {
}
