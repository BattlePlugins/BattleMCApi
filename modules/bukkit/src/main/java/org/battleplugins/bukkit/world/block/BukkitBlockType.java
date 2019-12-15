package org.battleplugins.bukkit.world.block;

import org.battleplugins.bukkit.inventory.item.BukkitItemType;
import org.battleplugins.world.block.BlockType;
import org.bukkit.Material;

public class BukkitBlockType extends BukkitItemType implements BlockType {

    protected BukkitBlockType(Material handle) {
        super(handle);
    }

    @Override
    public float getHardness() {
        return handle.getHardness();
    }
}
