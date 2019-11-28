package org.battleplugins.entity.living;

import org.battleplugins.inventory.entity.PlayerInventory;

public interface HumanEntity extends LivingEntity {

    PlayerInventory getInventory();
}
