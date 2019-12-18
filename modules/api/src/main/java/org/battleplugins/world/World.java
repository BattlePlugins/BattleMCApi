package org.battleplugins.world;

import org.battleplugins.entity.living.player.Player;
import org.battleplugins.world.block.Block;
import org.battleplugins.world.block.entity.BlockEntity;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Represents a world, which may contain entities, chunks
 * and blocks.
 */
public interface World {

    /**
     * The name of the world
     *
     * @return the name of the world
     */
    String getName();

    /**
     * Gets the {@link BlockEntity} at the given location. Returns
     * empty if one is not present there
     *
     * @param loc the location to get the block entity at
     * @return the block entity at the given location
     */
    Optional<? extends BlockEntity> getBlockEntityAt(Location loc);

    /**
     * Gets the {@link Block} at the given location
     *
     * @param loc the location to get the block at
     * @return the block at the given location
     */
    Block getBlockAt(Location loc);

    /**
     * Checks if the given {@link BlockEntity} is an instance of
     * the given class
     *
     * @param blockEntity the block entity to check
     * @param clazz the class to check if the block entity is an instance of
     * @return if the block entity is an instance of the class
     */
    boolean isType(BlockEntity blockEntity, Class<? extends BlockEntity> clazz);

    /**
     * Gets the given {@link BlockEntity} as an instance of the
     * given class
     *
     * @param blockEntity the block entity
     * @param clazz the class
     * @param <T> the value
     * @return the given block entity as an instance of the given class
     * @throws ClassCastException if the block entity is not an instance of the given class
     */
    <T extends BlockEntity> T toType(BlockEntity blockEntity, Class<T> clazz) throws ClassCastException;

    /**
     * Sends a {@link Block} update to the given location
     *
     * @param player the player to send the block update to
     * @param location the location to send the block update to
     * @param block the 'updated' block
     */
    void sendBlockUpdate(Player player, Location location, Block block);

    /**
     * Sends a {@link Block} update to the given location
     *
     * @param player the player to send the block entity update to
     * @param location the location to send the block entity update to
     * @param blockEntity the 'updated' block entity
     */
    void sendBlockEntityUpdate(Player player, Location location, BlockEntity blockEntity);

    /**
     * Gets the {@link Chunk} at the given location instantly
     *
     * @param location the location of the chunk
     * @return the chunk at the given location instantly
     * @throws ExecutionException if this future completed exceptionally
     * @throws InterruptedException if the current thread was interrupted
     */
    default Chunk getChunkAtInstantly(Location location) throws ExecutionException, InterruptedException {
        return getChunkAt(location).get();
    }

    /**
     * Gets the {@link Chunk} at the given X and Z coordinates
     * instantly
     *
     * @param x the X coordinate
     * @param z the X coordinate
     * @return the chunk at the given X and Z coordinates instantly
     * @throws ExecutionException if this future completed exceptionally
     * @throws InterruptedException if the current thread was interrupted
     */
    default Chunk getChunkAtInstantly(int x, int z) throws ExecutionException, InterruptedException {
        return getChunkAt(x, z).get();
    }

    /**
     * Gets the {@link Chunk} at the given location
     *
     * @param location the location of the chunk
     * @return the chunk at the given location
     */
    default CompletableFuture<? extends Chunk> getChunkAt(Location location) {
        return getChunkAt(location.getBlockX(), location.getBlockZ());
    }

    /**
     * Gets the {@link Chunk} at the given X and Z coordinates
     *
     * @param x the X coordinate
     * @param z the X coordinate
     * @return the chunk at the given X and Z coordinates
     */
    default CompletableFuture<? extends Chunk> getChunkAt(int x, int z) {
        return getChunkAt(x, z, true);
    }

    /**
     * Gets the {@link Chunk} at the given location
     *
     * @param location the location of the chunk
     * @param generate if the chunk should generate
     * @return the chunk at the given location
     */
    default CompletableFuture<? extends Chunk> getChunkAt(Location location, boolean generate) {
        return getChunkAt(location.getBlockX(), location.getBlockZ(), generate);
    }

    /**
     * Gets the {@link Chunk} at the given X and Z coordinates
     *
     * @param x the X coordinate
     * @param z the X coordinate
     * @param generate if the chunk should generate
     * @return the chunk at the given X and Z coordinates
     */
    CompletableFuture<? extends Chunk> getChunkAt(int x, int z, boolean generate);

    /**
     * Gets the {@link Chunk} at the given location.
     * Returns empty if the chunk is not loaded
     *
     * @param location the location of the chunk
     * @return the chunk at the given location if loaded
     */
    default Optional<? extends Chunk> getChunkIfLoaded(Location location) {
        return getChunkIfLoaded(location.getBlockX(), location.getBlockZ());
    }

    /**
     * Gets the {@link Chunk} at the given X and Z coordinates.
     * Returns empty if the chunk is not loaded
     *
     * @param x the X coordinate
     * @param z the X coordinate
     * @return the chunk at the given X and Z coordinates if loaded
     */
    Optional<? extends Chunk> getChunkIfLoaded(int x, int z);
}
