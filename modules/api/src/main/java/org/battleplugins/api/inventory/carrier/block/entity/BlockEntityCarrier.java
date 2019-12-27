package org.battleplugins.api.inventory.carrier.block.entity;

import org.battleplugins.api.world.block.entity.BlockEntity;
import org.battleplugins.api.inventory.carrier.Carrier;

/**
 * Represents an inventory carrier that is a {@link BlockEntity}.
 * @param <T> the value
 */
public interface BlockEntityCarrier<T extends BlockEntity> extends Carrier {
}
