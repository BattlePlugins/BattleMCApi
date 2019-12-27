package org.battleplugins.api.bukkit.entity.living;

import org.battleplugins.api.bukkit.inventory.entity.BukkitPlayerInventory;
import org.bukkit.entity.HumanEntity;

public class BukkitHumanEntity<T extends HumanEntity> extends BukkitLivingEntity<T> implements org.battleplugins.api.entity.living.HumanEntity {

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
