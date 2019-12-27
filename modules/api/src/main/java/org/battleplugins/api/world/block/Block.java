package org.battleplugins.api.world.block;

import org.battleplugins.api.world.Location;
import org.battleplugins.api.world.World;

/**
 * Represents a block in the world.
 */
public interface Block {

	/**
	 * The {@link World} this block is located in
	 *
	 * @return the world this block is located in
	 */
	default World getWorld() {
		return getLocation().getWorld();
	}

	/**
	 * The {@link Location} of the block
	 *
	 * @return the location of the block
	 */
	Location getLocation();

	/**
	 * The {@link BlockType} of this block
	 *
	 * @return the block type of this block
	 */
	BlockType getType();
}
