package org.battleplugins.api.bukkit.entity.living;

import org.battleplugins.api.bukkit.inventory.entity.BukkitPlayerInventory;
import org.battleplugins.api.entity.living.Human;
import org.bukkit.entity.HumanEntity;

public class BukkitHuman<T extends HumanEntity> extends BukkitLiving<T> implements Human {

    public BukkitHuman(T humanEntity) {
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
