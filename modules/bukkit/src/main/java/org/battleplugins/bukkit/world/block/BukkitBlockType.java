package org.battleplugins.bukkit.world.block;

import org.battleplugins.bukkit.inventory.item.BukkitItemRegistry;
import org.battleplugins.inventory.item.ItemRegistry;
import org.battleplugins.inventory.item.ItemType;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.world.block.BlockType;
import org.bukkit.Material;

public class BukkitBlockType extends MCWrapper<Material> implements BlockType {

    BukkitBlockType(Material handle) {
        super(handle);
    }

    @Override
    public ItemType toItemType() {
        return ((BukkitItemRegistry) ItemRegistry.REGISTRY).fromPlatformItem(handle);
    }

    @Override
    public float getHardness() {
        return handle.getHardness();
    }
}
