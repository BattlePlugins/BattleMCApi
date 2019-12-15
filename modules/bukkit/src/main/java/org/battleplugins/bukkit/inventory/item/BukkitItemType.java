package org.battleplugins.bukkit.inventory.item;

import org.battleplugins.bukkit.compat.BukkitCompatMaterial;
import org.battleplugins.inventory.item.ItemType;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.util.NamespacedKey;
import org.bukkit.Material;

public class BukkitItemType extends MCWrapper<Material> implements ItemType {

    protected BukkitItemType(Material handle) {
        super(handle);
    }

    @Override
    public NamespacedKey getKey() {
        BukkitCompatMaterial bukkitMaterial = BukkitCompatMaterial.fromMaterial(handle);
        return NamespacedKey.minecraft(bukkitMaterial.name().toLowerCase());
    }

    @Override
    public int getMaximumStackSize() {
        return handle.getMaxStackSize();
    }
}
