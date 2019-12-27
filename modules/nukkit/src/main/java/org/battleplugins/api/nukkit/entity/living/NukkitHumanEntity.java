package org.battleplugins.api.nukkit.entity.living;

import cn.nukkit.entity.EntityHuman;

import org.battleplugins.api.entity.living.HumanEntity;
import org.battleplugins.api.nukkit.inventory.entity.NukkitPlayerInventory;

import java.util.UUID;

public class NukkitHumanEntity<T extends EntityHuman> extends NukkitLivingEntity<T> implements HumanEntity {

    public NukkitHumanEntity(T humanEntity) {
        super(humanEntity);
    }

    @Override
    public NukkitPlayerInventory getInventory() {
        return new NukkitPlayerInventory(this, handle.getInventory());
    }

    @Override
    public UUID getUniqueId() {
        return handle.getUniqueId();
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
