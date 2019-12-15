package org.battleplugins.sponge.inventory.item;

import org.battleplugins.inventory.item.ItemType;
import org.battleplugins.sponge.compat.SpongeCompatItemType;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.util.NamespacedKey;

public class SpongeItemType extends MCWrapper<org.spongepowered.api.item.ItemType> implements ItemType {

    protected SpongeItemType(org.spongepowered.api.item.ItemType handle) {
        super(handle);
    }

    @Override
    public NamespacedKey getKey() {
        SpongeCompatItemType spongeItemType = SpongeCompatItemType.fromMaterial(handle);
        return NamespacedKey.minecraft(spongeItemType.name().toLowerCase());
    }

    @Override
    public int getMaximumStackSize() {
        return handle.getMaxStackQuantity();
    }
}
