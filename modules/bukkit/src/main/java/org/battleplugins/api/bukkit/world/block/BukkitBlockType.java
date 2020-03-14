package org.battleplugins.api.bukkit.world.block;

import org.battleplugins.api.Platform;
import org.battleplugins.api.bukkit.registry.inventory.BukkitItemRegistry;
import org.battleplugins.api.inventory.item.ItemType;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.block.BlockType;
import org.bukkit.Material;

public class BukkitBlockType extends MCWrapper<Material> implements BlockType {

    public BukkitBlockType(Material handle) {
        super(handle);
    }

    @Override
    public ItemType toItemType() {
        return ((BukkitItemRegistry) Platform.getRegistry().getItemRegistry()).fromPlatformItem(handle);
    }

    @Override
    public float getHardness() {
        return handle.getHardness();
    }
}
