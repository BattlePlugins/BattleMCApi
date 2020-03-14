package org.battleplugins.api.bukkit.inventory.item;

import org.battleplugins.api.bukkit.compat.BukkitCompatMaterial;
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
        BukkitCompatMaterial bukkitMaterial = BukkitCompatMaterial.fromMaterial(handle);
        return Identifier.minecraft(bukkitMaterial.name().toLowerCase());
    }

    @Override
    public int getMaximumStackSize() {
        return handle.getMaxStackSize();
    }
}
