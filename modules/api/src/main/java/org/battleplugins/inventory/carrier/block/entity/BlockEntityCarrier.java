package org.battleplugins.inventory.carrier.block.entity;

import org.battleplugins.inventory.carrier.Carrier;
import org.battleplugins.world.block.entity.BlockEntity;

/**
 * Represents an inventory carrier that is a {@link BlockEntity}.
 * @param <T> the value
 */
public interface BlockEntityCarrier<T extends BlockEntity> extends Carrier {
}
