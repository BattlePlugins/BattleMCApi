package org.battleplugins.api.registry.world;

import org.battleplugins.api.Platform;
import org.battleplugins.api.registry.IdentifiableRegistry;
import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.world.block.BlockType;
import org.battleplugins.api.world.block.BlockTypes;

import java.util.Optional;

/**
 * A registry containing all the blocks based on various
 * implementations depending on the platform.
 *
 * Certain platforms base their implementations of blocks
 * on different values (e.g. Bukkit is Material/identifier
 * based whilst Nukkit is ID based). This class allows for
 * blocks to be mapped from their platform implementations as
 * well as from {@link Identifier}'s.
 *
 * @param <T> the platform implementation
 */
public abstract class BlockRegistry<T> implements IdentifiableRegistry<BlockType> {

    private boolean closed;

    /**
     * Gets the given block type from the platform
     * block
     *
     * @param block the platform block
     * @return the given block type
     */
    public abstract BlockType fromPlatformBlock(T block);

    /**
     * Gets the block type from the given
     * {@link Identifier}. Returns empty if the
     * block could not be found. The only reason this should
     * be used is if an block type needs to be obtained
     * from a string, an block is not in the {@link BlockTypes}
     * class, or if a modded block (Sponge) needs to be obtained.
     *
     * @param identifier the given {@link Identifier}
     * @return the block type from the given identifier
     */
    public abstract Optional<BlockType> fromIdentifier(Identifier identifier);

    @Override
    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public void close() {
        this.closed = true;
    }

    /**
     * Gets the current block registry
     *
     * @return the current block registry
     */
    public static BlockRegistry get() {
        return Platform.getRegistry().getBlockRegistry();
    }
}
