package org.battleplugins.world.block;

import org.battleplugins.inventory.item.ItemType;

/**
 * Represents a block type.
 */
public interface BlockType extends ItemType {

    /**
     * The hardness of the block
     *
     * @return the hardness of the block
     */
    float getHardness();
}
