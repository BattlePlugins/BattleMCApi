package org.battleplugins.api.world;

import org.battleplugins.api.entity.Entity;
import org.battleplugins.api.world.block.entity.BlockEntity;

import java.util.List;

/**
 * Represents a section of the world.
 *
 * A chunk is a 16x16 section in the world on the X and Z axis.
 */
public interface Chunk {

    /**
     * The world of the chunk
     *
     * @return the world of the chunk
     */
    World getWorld();

    /**
     * The X coordinate of this chunk
     *
     * @return the X coordinate of this chunk
     */
    int getX();

    /**
     * The Z coordinate of this chunk
     *
     * @return the Z coordinate of this chunk
     */
    int getZ();

    /**
     * If the chunk is loaded
     *
     * @return if the chunk is loaded
     */
    boolean isLoaded();

    /**
     * All of the {@link Entity}'s in this chunk
     *
     * @return all of the entities in this chunk
     */
    List<? extends Entity> getEntities();

    /**
     * All of the {@link BlockEntity}'s in this chunk
     *
     * @return all of the block entities in this chunk
     */
    List<? extends BlockEntity> getBlockEntities();

    /**
     * Loads this chunk
     */
    default void loadChunk() {
        loadChunk(false);
    }

    /**
     * Loads this chunk
     *
     * @param generate if the chunk should be generated
     */
    void loadChunk(boolean generate);

    /**
     * Unloads this chunk
     */
    void unloadChunk();
}
