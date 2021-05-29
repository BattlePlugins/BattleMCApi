package org.battleplugins.api.bukkit.inventory.item;

import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.util.Identifier;
import org.battleplugins.api.util.MCWrapper;
import org.bukkit.Material;

public class BukkitItemType extends MCWrapper<Material> implements ItemType {

    public BukkitItemType(Material handle) {
        super(handle);
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.of(handle.getKey().toString());
    }

    @Override
    public int getMaximumStackSize() {
        return handle.getMaxStackSize();
    }
}
