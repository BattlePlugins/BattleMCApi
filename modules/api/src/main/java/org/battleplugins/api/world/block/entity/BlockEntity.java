package org.battleplugins.api.world.block.entity;

import org.battleplugins.api.world.block.Block;

/**
 * Represents a block entity.
 */
public interface BlockEntity {

    /**
     * The {@link Block} that corresponds to this block
     * entity
     *
     * @return the {@link Block}
     */
    Block getBlock();

    /**
     * Updates the block entity
     */
    void update();

    /**
     * Updates the block entity
     *
     * @param force if the update should be forced
     */
    void update(boolean force);
}
