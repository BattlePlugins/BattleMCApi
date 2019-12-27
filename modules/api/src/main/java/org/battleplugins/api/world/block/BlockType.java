package org.battleplugins.api.world.block;

import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.util.NamespacedKey;

/**
 * Represents a block type.
 */
public interface BlockType {

    /**
     * The item identifier of the item
     *
     * @return the item identifier of the item
     */
    default String getIdentifier() {
        return getKey().toString();
    }

    /**
     * The full namespaced key of the block
     *
     * @return the full namespaced key of the block
     */
    default NamespacedKey getKey() {
        return toItemType().getKey();
    }

    /**
     * The block type as an {@link ItemType}
     *
     * @return the block type as an item type
     */
    ItemType toItemType();

    /**
     * The hardness of the block
     *
     * @return the hardness of the block
     */
    float getHardness();
}
