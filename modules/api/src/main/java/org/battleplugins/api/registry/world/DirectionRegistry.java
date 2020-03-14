package org.battleplugins.api.registry.world;

import org.battleplugins.api.Platform;
import org.battleplugins.api.registry.IdentifiableRegistry;
import org.battleplugins.api.world.block.direction.Direction;

/**
 * A registry containing all the registrations
 * for the {@link Direction}s.
 */
public abstract class DirectionRegistry implements IdentifiableRegistry<Direction> {

    private boolean closed;

    @Override
    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public void close() {
        this.closed = true;
    }

    /**
     * Gets the current direction registry
     *
     * @return the current entity registry
     */
    public static DirectionRegistry get() {
        return Platform.getRegistry().getDirectionRegistry();
    }
}
