package org.battleplugins.api.sponge.entity.living;

import org.battleplugins.api.entity.living.Human;
import org.battleplugins.api.sponge.inventory.entity.SpongePlayerInventory;
import org.spongepowered.api.entity.living.Humanoid;

public class SpongeHuman<T extends Humanoid> extends SpongeLiving<T> implements Human {

    public SpongeHuman(T humanEntity) {
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
