package org.battleplugins.bukkit.inventory.item;

import org.battleplugins.bukkit.util.BukkitMaterialAdapter;
import org.battleplugins.inventory.item.ItemRegistry;
import org.battleplugins.inventory.item.ItemType;
import org.battleplugins.util.NamespacedKey;
import org.bukkit.Material;

import java.util.Optional;

public class BukkitItemRegistry implements ItemRegistry<Material> {

    @Override
    public ItemType fromPlatformItem(Material material) {
        return new BukkitItemType(material);
    }

    @Override
    public Optional<ItemType> fromKey(NamespacedKey namespacedKey) {
        return BukkitMaterialAdapter.getMaterial(namespacedKey.getKey()).map(this::fromPlatformItem);
    }
}
