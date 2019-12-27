package org.battleplugins.api.sponge.entity.living;

import org.battleplugins.api.entity.living.HumanEntity;
import org.battleplugins.api.sponge.inventory.entity.SpongePlayerInventory;
import org.spongepowered.api.entity.living.Humanoid;

public class SpongeHumanEntity<T extends Humanoid> extends SpongeLivingEntity<T> implements HumanEntity {

    public SpongeHumanEntity(T humanEntity) {
        super(humanEntity);
    }

    @Override
    public SpongePlayerInventory getInventory() {
        return new SpongePlayerInventory(handle.getInventory());
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
