package org.battleplugins.bukkit.entity.living;

import org.battleplugins.bukkit.inventory.entity.BukkitPlayerInventory;
import org.bukkit.entity.HumanEntity;

public class BukkitHumanEntity<T extends HumanEntity> extends BukkitLivingEntity<T> implements org.battleplugins.entity.living.HumanEntity {

    public BukkitHumanEntity(T humanEntity) {
        super(humanEntity);
    }

    @Override
    public BukkitPlayerInventory getInventory() {
        return new BukkitPlayerInventory(this, handle.getInventory());
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
