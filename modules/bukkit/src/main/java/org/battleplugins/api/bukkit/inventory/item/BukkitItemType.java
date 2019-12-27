package org.battleplugins.api.bukkit.inventory.item;

import org.battleplugins.api.bukkit.compat.BukkitCompatMaterial;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.util.NamespacedKey;
import org.bukkit.Material;

public class BukkitItemType extends MCWrapper<Material> implements ItemType {

    BukkitItemType(Material handle) {
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
