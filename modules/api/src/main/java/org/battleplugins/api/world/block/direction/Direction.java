package org.battleplugins.api.world.block.direction;

import org.battleplugins.api.util.Identifiable;
import org.battleplugins.api.world.block.Block;

import java.util.Objects;

/**
 * Represents the direction of a {@link Block}.
 */
public interface Direction extends Identifiable {

    /**
     * The opposite direction
     *
     * @return the opposite direction
     */
    Direction getOpposite();

    /**
     * Gets if the opposite direction is
     * the same as the specified direction
     *
     * @param direction the specified direction
     * @return if the opposite direction is the same as specified
     */
    default boolean isOpposite(Direction direction) {
        return Objects.equals(getOpposite(), direction);
    }
}
