package org.battleplugins.api.world.block;

import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.util.Identifier;

/**
 * Represents a block type.
 */
public interface BlockType {

    /**
     * The full identifier of the block
     *
     * @return the full identifier of the block
     */
    default Identifier getIdentifier() {
        return toItemType().getIdentifier();
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
