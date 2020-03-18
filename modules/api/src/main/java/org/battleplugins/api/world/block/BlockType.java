package org.battleplugins.api.world.block;

import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.util.Identifiable;
import org.battleplugins.api.util.Identifier;

import java.util.Objects;

/**
 * Represents a block type.
 */
public interface BlockType extends Identifiable {

    /**
     * The full identifier of the block
     *
     * @return the full identifier of the block
     */
    @Override
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

    /**
     * If this block type is equal to the
     * given type
     *
     * @param type the block type to check equality for
     * @return if this block type is equal to the given type
     */
    default boolean is(BlockType type) {
        return Objects.equals(this, type);
    }
}
