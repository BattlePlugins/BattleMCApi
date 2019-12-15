package org.battleplugins.nukkit.world.block;

import cn.nukkit.block.Block;

import org.battleplugins.nukkit.inventory.item.NukkitItemType;
import org.battleplugins.util.NamespacedKey;
import org.battleplugins.world.block.BlockType;

public class NukkitBlockType extends NukkitItemType implements BlockType {

    private Block block;

    protected NukkitBlockType(NamespacedKey key, Block block) {
        super(key, block.toItem());

        this.block = block;
    }

    @Override
    public float getHardness() {
        return (float) block.getHardness();
    }
}
