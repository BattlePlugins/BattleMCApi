package org.battleplugins.api.inventory.item.component;

import org.battleplugins.api.inventory.item.component.flag.ItemFlag;

import java.util.Set;

/**
 * Represents the component responsible for applying item flags
 * to itemstacks.
 */
public interface ItemFlagComponent extends ItemComponent<Set<ItemFlag>> {
}
