package org.battleplugins.api.world.block.entity;

import org.battleplugins.api.world.Location;
import org.battleplugins.api.world.World;
import org.battleplugins.api.world.block.Block;

/**
 * Represents a block entity.
 */
public interface BlockEntity {

    /**
     * The {@link World} this block entity is located in
     *
     * @return the world this block entity is located in
     */
    default World getWorld() {
        return getLocation().getWorld();
    }

    /**
     * The {@link Location} of the block entity
     *
     * @return the location of the block entity
     */
    default Location getLocation() {
        return getBlock().getLocation();
    }

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
